/* Released under the GPL2. See license.txt for details. */
package br.com.centralit.nagios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Class JavNag is the main class for obtaining and processing Nagios statusses. It can retrieve the status from a Nagios status-file or from a (TCP-)socket.
 *
 * @author Folkert van Heusden
 * @version %I%, %G%
 * @since 0.1
 */
public class JavNag {

    List<Host> hosts = new ArrayList<>();

    int socketTimeout = -1;

    String userAgent = "JavNag";

    public void setUserAgent(final String agent) {
        userAgent = agent;
    }

    public void setSocketTimeout(final int st) {
        System.out.println("Setting socket timeout to: " + st);
        socketTimeout = st;
    }

    public Socket connectToSocket(final String host, final int port) throws Exception {
        final Socket socket = new Socket();

        if (socketTimeout != -1) {
            socket.connect(new InetSocketAddress(host, port), socketTimeout);
            socket.setSoTimeout(socketTimeout);
        } else {
            socket.connect(new InetSocketAddress(host, port));
        }

        return socket;
    }

    public String getField(final String host, final String field) {
        for (final Host h : hosts) {
            if (h.getHostName().equals(host)) {
                return h.getParameter(field);
            }
        }

        return null;
    }

    public String getField(final String host, final String service, final String field) {
        for (final Host h : hosts) {
            if (h.getHostName().equals(host)) {
                for (final Service s : h.getServices()) {
                    if (s.getServiceName().equals(service)) {
                        return s.getParameter(field);
                    }
                }
                break;
            }
        }

        return null;
    }

    /**
     * Cria um novo Host ou localiza um já existente retornando sua instância.
     *
     * @param nagiosSource
     *            - Nome do Nagios
     * @param hostName
     *            - Nome do host
     * @return - Host criado ou já existente.
     */
    private Host addAndOrFindHost(final String nagiosSource, final String hostName) {

        for (final Host currentHost : hosts) {
            if (currentHost.getHostName().equals(hostName)) {
                return currentHost;
            }
        }

        final Host newHost = new Host(nagiosSource, hostName);

        hosts.add(newHost);

        return newHost;
    }

    /**
     * Adiciona um novo ParameterEntry (Parâmetro/Valor) ao Host.
     *
     * @param host
     *            - Host que será adicionado o Parâmetro/Valor.
     * @param serviceParameterName
     *            - Nome do Parâmetro.
     * @param serviceParameterValue
     *            - Valor do Parâmetro.
     */
    private void addHostParameterEntry(final Host host, final String serviceParameterName, final String serviceParameterValue) {
        host.addParameter(serviceParameterName, serviceParameterValue);
    }

    /**
     * Adiciona um novo ParameterEntry (Parâmetro/Valor) ao Serviço.
     *
     * @param service
     *            - Serviço
     * @param serviceParameterName
     *            - Nome do parâmetro.
     * @param serviceParameterValue
     *            - Valor do parâmetro.
     */
    private void addServiceEntry(final Service service, final String serviceParameterName, final String serviceParameterValue) {
        service.addParameter(new ParameterEntry(serviceParameterName, serviceParameterValue));
    }

    private void addFromNagios1(final String nagiosSource, final List<String> fileDump) throws Exception {
        for (final String currentLine : fileDump) {
            if (currentLine.length() == 0) {
                continue;
            }
            if (currentLine.substring(0, 1).equals("#")) {
                continue;
            }

            final String[] elements = currentLine.split(";");
            if (elements.length < 2) {
                continue;
            }
            final int space = elements[0].indexOf(" ");
            if (space == -1) {
                throw new Exception("Invalid line: first field should contain space (" + currentLine + ")");
            }
            final String type = elements[0].substring(space + 1);
            final String hostName = elements[1];

            if (type.equals("HOST")) {
                if (elements.length != 21) {
                    throw new Exception("Expecting 21 for a HOST-line, got " + elements.length + ": " + currentLine);
                }

                final Host host = this.addAndOrFindHost(nagiosSource, hostName);
                int current_state = 255;
                if (elements[2].equals("UP") || elements[2].equals("OK")) {
                    current_state = 0;
                } else {
                    current_state = 1;
                }
                this.addHostParameterEntry(host, "current_state", "" + current_state);
                this.addHostParameterEntry(host, "host_name", "" + hostName);
                this.addHostParameterEntry(host, "last_check", elements[3]);
                this.addHostParameterEntry(host, "last_state_change", elements[4]);
                this.addHostParameterEntry(host, "problem_has_been_acknowledged", elements[5]);
                this.addHostParameterEntry(host, "last_time_up", elements[6]);
                this.addHostParameterEntry(host, "last_time_down", elements[7]);
                this.addHostParameterEntry(host, "last_time_unreachable", elements[8]);
                this.addHostParameterEntry(host, "last_notification", elements[9]);
                this.addHostParameterEntry(host, "current_notification_number", elements[10]);
                this.addHostParameterEntry(host, "notifications_enabled", elements[11]);
                this.addHostParameterEntry(host, "event_handler_enabled", elements[12]);
                this.addHostParameterEntry(host, "active_checks_enabled", elements[13]); /* in 2.0 it has been split up in passive and active */
                this.addHostParameterEntry(host, "flap_detection_enabled", elements[14]);
                this.addHostParameterEntry(host, "is_flapping", elements[15]);
                this.addHostParameterEntry(host, "percent_state_change", elements[16]);
                this.addHostParameterEntry(host, "scheduled_downtime_depth", elements[17]);
                this.addHostParameterEntry(host, "failure_prediction_enabled", elements[18]);
                this.addHostParameterEntry(host, "process_performance_data", elements[19]);
                this.addHostParameterEntry(host, "plugin_output", elements[20]);

                this.addHostParameterEntry(host, "state_type", "1"); // version 1 doesn't set this so always assume hard state
                // FIXME addHostParameterEntry(host, "performance_data", ""); RETRIEVE FROM PLUGIN_OUTPUT
            } else if (type.equals("SERVICE")) {
                if (elements.length != 31 && elements.length != 32) {
                    throw new Exception("Expecting 21 for a SERVICE-line, got " + elements.length + ": " + currentLine);
                }

                final Host host = this.addAndOrFindHost(nagiosSource, hostName);
                final Service service = host.addAndOrFindService(elements[2]);
                int current_state = 255;
                if (elements[3].equals("OK")) {
                    current_state = 0;
                } else if (elements[3].equals("WARNING")) {
                    current_state = 1;
                } else if (elements[3].equals("CRITICAL")) {
                    current_state = 2;
                } else if (elements[3].equals("UNKNOWN") || elements[3].equals("PENDING")) {
                    current_state = 3;
                }
                this.addServiceEntry(service, "current_state", "" + current_state);
                this.addServiceEntry(service, "retry_number", elements[4]);
                String stateType = "0";
                if (elements[5].equals("HARD")) {
                    stateType = "1";
                } else if (elements[5].equals("SOFT")) {
                    stateType = "0";
                } else {
                    stateType = elements[5];
                }
                this.addServiceEntry(service, "state_type", stateType);
                this.addServiceEntry(service, "host_name", hostName);
                this.addServiceEntry(service, "last_check", elements[6]);
                this.addServiceEntry(service, "next_check", elements[7]);
                this.addServiceEntry(service, "check_type", elements[8]);
                this.addServiceEntry(service, "active_checks_enabled", elements[9]);
                this.addServiceEntry(service, "accept_passive_checks", elements[10]);
                this.addServiceEntry(service, "event_handler_enabled", elements[11]);
                this.addServiceEntry(service, "last_state_change", elements[12]);
                this.addServiceEntry(service, "problem_has_been_acknowledged", elements[13]);
                this.addServiceEntry(service, "last_hard_state", elements[14]);
                this.addServiceEntry(service, "last_time_ok", elements[15]);
                this.addServiceEntry(service, "last_time_unknown", elements[16]);
                this.addServiceEntry(service, "last_time_warning", elements[17]);
                this.addServiceEntry(service, "last_time_critical", elements[18]);
                this.addServiceEntry(service, "last_notification", elements[19]);
                this.addServiceEntry(service, "current_notification_number", elements[20]);
                this.addServiceEntry(service, "notifications_enabled", elements[21]);
                this.addServiceEntry(service, "check_latency", elements[22]);
                this.addServiceEntry(service, "check_execution_time", elements[23]);
                this.addServiceEntry(service, "flap_detection_enabled", elements[24]);
                this.addServiceEntry(service, "is_flapping", elements[25]);
                this.addServiceEntry(service, "percent_state_change", elements[26]);
                this.addServiceEntry(service, "scheduled_downtime_depth", elements[27]);
                this.addServiceEntry(service, "failure_prediction_enabled", elements[28]);
                this.addServiceEntry(service, "process_performance_date", elements[29]);
                this.addServiceEntry(service, "obsess_over_service", elements[30]);
                assert elements.length == 31 || elements.length == 32;
                if (elements.length == 32) {
                    this.addServiceEntry(service, "plugin_output", elements[31]);
                } else {
                    this.addServiceEntry(service, "plugin_output", "");
                    // FIXME addHostParameterEntry(host, "performance_data", ""); RETRIEVE FROM PLUGIN_OUTPUT
                }
            }
        }
    }

    /**
     * Realiza leitura no List<String> com as informações obtidas à partir do status.dat.txt e cria ou atualiza os Hosts e suas características.
     *
     * @param nagiosSource
     *            - Nome do Nagios.
     * @param fileDump
     *            - List<String> com as informações obtidas à partir do status.dat.txt
     * @throws Exception
     */
    private void addFromNagios2And3(final String nagiosSource, final List<String> fileDump) throws Exception {
        String host_name = null;
        Host host = null;
        Service service = null;
        LineType lineType = LineType.ignore;

        for (final String currentLine : fileDump) {
            final int isIndex = currentLine.indexOf("=");

            if (currentLine.indexOf("{") != -1 && isIndex == -1) {
                String type = null;
                final int space = currentLine.indexOf(" ");
                if (space != -1) {
                    type = currentLine.substring(0, space);
                }

                if (type == null) {
                    lineType = LineType.ignore;
                } else if (type.equals("hoststatus") || type.equals("host")) {
                    lineType = LineType.host;
                } else if (type.equals("servicestatus") || type.equals("service")) {
                    lineType = LineType.service;
                } else if (type.equals("hostcomment")) {
                    lineType = LineType.host_comment;
                } else if (type.equals("servicecomment")) {
                    lineType = LineType.service_comment;
                } else {
                    lineType = LineType.ignore;
                }

                host = null;
                service = null;
            } else if (lineType != LineType.ignore) {
                String parameter = null, value = null;

                if (isIndex == -1) {
                    if (currentLine.indexOf("}") != -1 && isIndex == -1) {
                        lineType = LineType.ignore;
                        host_name = null;
                    }
                } else {
                    parameter = currentLine.substring(0, isIndex).trim();
                    value = currentLine.substring(isIndex + 1).trim();
                }

                if (parameter != null && value != null) {
                    if (parameter.equals("host_name")) {
                        host = this.addAndOrFindHost(nagiosSource, value);
                        host_name = value;
                        this.addHostParameterEntry(host, parameter, value);
                    } else if (parameter.equals("service_description")) {
                        service = host.addAndOrFindService(value);
                        this.addServiceEntry(service, "host_name", host_name);
                    } else if (lineType == LineType.host && host != null) {
                        // Aqui serão adicionados os parâmetros de host.
                        this.addHostParameterEntry(host, parameter, value);
                    } else if (lineType == LineType.service && host != null && service != null) {
                        this.addServiceEntry(service, parameter, value);
                    } else if (lineType == LineType.host_comment && host != null) {
                        if (parameter.equals("comment_data") || parameter.equals("entry_time")) {
                            this.addHostParameterEntry(host, parameter, value);
                        }
                    } else if (lineType == LineType.service_comment && service != null) {
                        if (parameter.equals("comment_data") || parameter.equals("entry_time")) {
                            this.addServiceEntry(service, parameter, value);
                        }
                    } else {
                        throw new Exception("expected host_name/service_description to be at the start of a definition, got line: " + currentLine);
                    }
                }
            }
        }
    }

    /**
     * Returns a list of hosts found in the Nagios status.
     *
     * @return A list of Host-objects.
     */
    public List<Host> getListOfHosts() {
        return hosts;
    }

    /**
     * Returns a Host-object by the hostname given. This hostname is the hostname as found in the Nagios configuration. E.g. hosts.cfg
     *
     * @param hostName
     *            Hostname.
     * @return A Host object.
     */
    public Host getHost(final String hostName) {
        for (final Host currentHost : hosts) {
            if (currentHost.getHostName().equals(hostName)) {
                return currentHost;
            }
        }

        return null;
    }

    /**
     * calculateStatistics returns a Totals object with statistics-totals. E.g. total number of services with critical/warning/ok states.
     *
     * @return Totals Object with totals.
     */
    public Totals calculateStatistics() {
        int nCritical = 0, nWarning = 0, nOk = 0;
        int nUp = 0, nDown = 0, nUnreachable = 0, nPending = 0;
        int nHosts = 0, nServices = 0;
        int nStateUnknownHost = 0, nStateUnknownService = 0;
        int nAcked = 0, nFlapping = 0;

        for (final Host currentHost : hosts) {
            String current_state = currentHost.getParameter("current_state");
            if (current_state == null) {
                nStateUnknownHost++;
                continue;
            }

            if (current_state.equals("0")) {
                nUp++;
            } else if (current_state.equals("1")) {
                nDown++;
            } else if (current_state.equals("2")) {
                nUnreachable++;
            } else if (current_state.equals("3")) {
                nPending++;
            }

            if (currentHost.getParameter("problem_has_been_acknowledged").equals("1")) {
                nAcked++;
            }

            if (currentHost.getParameter("is_flapping").equals("1")) {
                nFlapping++;
            }

            nHosts++;

            for (final Service currentService : currentHost.getServices()) {
                current_state = currentService.getParameter("current_state");
                if (current_state == null) {
                    nStateUnknownService++;
                    continue;
                }

                if (current_state.equals("0")) {
                    nOk++;
                } else if (current_state.equals("1")) {
                    nWarning++;
                } else if (current_state.equals("2")) {
                    nCritical++;
                }

                if (currentService.getParameter("problem_has_been_acknowledged").equals("1")) {
                    nAcked++;
                }

                if (currentService.getParameter("is_flapping").equals("1")) {
                    nFlapping++;
                }

                nServices++;
            }
        }

        return new Totals(nCritical, nWarning, nOk, nUp, nDown, nUnreachable, nPending, nHosts, nServices, nStateUnknownHost, nStateUnknownService, nAcked,
                nFlapping);
    }

    /**
     * Returns the number of hosts loaded.
     *
     * @return count
     */
    public int getNumberOfHosts() {
        return hosts.size();
    }

    /**
     * Find a host by hostname.
     *
     * @return Host object
     */
    public Host findHostByHostName(final String hostName) {
        for (final Host currentHost : hosts) {
            if (currentHost.getHostName().equals(hostName)) {
                return currentHost;
            }
        }

        return null;
    }

    /**
     * Checks if a host is down and if it is if it should be shown. That is decided by looking at parameters like state_type and checks enabled.
     *
     * @param host
     *            The host to check.
     * @param always_notify
     *            Also return true when notifications_enabled is set to false (in Nagios).
     * @param also_acknowledged
     *            Also return true when the problem has been acknowledged in Nagios.
     * @return true/false
     */
    public boolean shouldIShowHost(final Host host, final boolean always_notify, final boolean also_acknowledged, final boolean also_scheduled_downtime,
            final boolean also_soft_state, final boolean also_disabled_active_checks, final boolean show_flapping, final boolean display_unknown,
            final boolean display_down) {
        if (host.getParameters().size() == 0) {
            return false;
        }

        if (!display_unknown && host.getParameter("current_state").equals("3") == true) {
            return false;
        }

        if (!display_down && (host.getParameter("current_state").equals("1") == true || host.getParameter("current_state").equals("2") == true)) {
            return false;
        }

        if (!show_flapping && host.getParameter("is_flapping").equals("1") == true) {
            return false;
        }

        // System.out.println(host.getHostName() + " " + host.getParameter("state_type"));
        if (!also_soft_state && host.getParameter("state_type").equals("0") == true) {
            return false;
        }

        if (host.getParameter("current_state").equals("0") == true) {
            return false;
        }

        // if active_checks are not enabled and passive checks neither, do not show
        if (!also_disabled_active_checks && host.getParameter("active_checks_enabled").equals("0") == true
                && host.getParameter("passive_checks_enabled").equals("0") == true) {
            return false;
        }

        // downtime_depth == 0, do not show
        if (!also_scheduled_downtime && Double.valueOf(host.getParameter("scheduled_downtime_depth")) != 0.0) {
            System.out.println("scheduled_downtime_depth " + host.getParameter("scheduled_downtime_depth"));
            return false;
        }

        // notifications disabled, do not show
        if (!always_notify && host.getParameter("notifications_enabled").equals("0") == true) {
            return false;
        }

        // is has been acknowledged, do not show
        if (!also_acknowledged && host.getParameter("problem_has_been_acknowledged").equals("1") == true) {
            return false;
        }

        return true;
    }

    /**
     * Checks if a service is down and if it is if it should be shown. That is decided by looking at parameters like state_type and checks enabled.
     *
     * @param service
     *            The service to check.
     * @param always_notify
     *            Also return true when notifications_enabled is set to false (in Nagios).
     * @param also_acknowledged
     *            Also return true when the problem has been acknowledged in Nagios.
     * @return true/false
     */
    public boolean shouldIShowService(final Service service, final boolean always_notify, final boolean service_also_acknowledged,
            final boolean service_also_scheduled_downtime, final boolean also_soft_state, final boolean also_disabled_active_checks,
            final boolean show_flapping, final boolean display_unknown) {
        // System.out.print("--- " + service.getServiceName() + " ");
        if (!also_soft_state && service.getParameter("state_type").equals("1") == false) {
            // System.out.println("state_type != 1");
            return false;
        }

        if (!display_unknown && service.getParameter("current_state").equals("3") == true) {
            return false;
        }

        if (service.getParameter("current_state").equals("0") == true) {
            // System.out.println("current_state == 0");
            return false;
        }

        if (!show_flapping && service.getParameter("is_flapping").equals("1") == true) {
            // System.out.println("is_flapping");
            return false;
        }

        // System.out.println("p active_checks_enabled: " + service.getParameter("active_checks_enabled"));
        // System.out.println("p passive_checks_enabled: " + service.getParameter("passive_checks_enabled"));
        if (!also_disabled_active_checks && service.getParameter("active_checks_enabled").equals("0") == true
                && service.getParameter("passive_checks_enabled").equals("0") == true) {
            // System.out.println("checks enabled = false");
            return false;
        }

        if (!service_also_scheduled_downtime && Double.valueOf(service.getParameter("scheduled_downtime_depth")) != 0.0) {
            // System.out.println("scheduled downtime depth != 0");
            return false;
        }

        if (!always_notify && service.getParameter("notifications_enabled").equals("0") == true) {
            // System.out.println("notifications not enabled");
            return false;
        }

        if (!service_also_acknowledged && service.getParameter("problem_has_been_acknowledged").equals("1") == true) {
            // System.out.println("problem has been acked");
            return false;
        }

        System.out.println("show service true: ");

        return true;
    }

    public Double getAvgCheckLatency() {
        int n = 0;
        double tot = 0.0;

        for (final Host currentHost : hosts) {
            String check_latency = currentHost.getParameter("check_latency");
            if (check_latency != null) {
                n++;
                tot += Double.valueOf(check_latency);
            }

            for (final Service currentService : currentHost.getServices()) {
                check_latency = currentService.getParameter("check_latency");
                if (check_latency != null) {
                    n++;
                    tot += Double.valueOf(check_latency);
                }
            }
        }

        if (n == 0) {
            return null;
        }

        return tot / n;
    }

    /**
     * Realiza a leitura e atualização dos Host, Serviços e seus respectivos parâmetros e valores à partir do arquivo .dat.txt
     *
     * @param fileName
     *            - Caminho absoluto do arquivo.Ex. D:\Ambiente\jboss\server\default\deploy\status.dat.txt
     * @param nagiosVersion
     *            - Versão do Nagios.
     * @param prettyName
     *            - Nome do Nagios.
     * @throws Exception
     */
    public void loadNagiosData(final String fileName, final Integer nagiosVersion, String prettyName) throws Exception {
        final List<String> fileDump = new ArrayList<String>();
        final BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = in.readLine()) != null) {
            fileDump.add(line);
        }

        in.close();

        if (prettyName == null) {
            prettyName = fileName;
        }

        if (nagiosVersion == 1) {
            this.addFromNagios1(prettyName, fileDump);
        } else if (nagiosVersion == 2 || nagiosVersion == 3) {
            this.addFromNagios2And3(prettyName, fileDump);
        }
    }

    public void loadNagiosData(final String host, final int port, final Integer nagiosVersion, final boolean compressed, String prettyName) throws Exception {
        final List<String> fileDump = new ArrayList<String>();
        final Socket socket = this.connectToSocket(host, port);
        BufferedReader in;
        String line;

        if (compressed) {
            in = new BufferedReader(new InputStreamReader(new GZIPInputStream(socket.getInputStream())));
        } else {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        while ((line = in.readLine()) != null) {
            fileDump.add(line);
        }

        in.close();

        socket.close();

        if (prettyName == null) {
            prettyName = host;
        }

        if (nagiosVersion == 1) {
            this.addFromNagios1(prettyName, fileDump);
        } else if (nagiosVersion == 2 || nagiosVersion == 3) {
            this.addFromNagios2And3(prettyName, fileDump);
        }
    }

    // via LiveStatus
    public void loadNagiosDataLiveStatus(final String host, final int port, final String prettyName) throws Exception {
        this.loadNagiosDataLiveStatus_hosts(host, port, prettyName);

        this.loadNagiosDataLiveStatus_services(host, port, prettyName);
    }

    private void loadNagiosDataLiveStatus_hosts(final String host, final int port, String prettyName) throws Exception {
        final Socket socket = this.connectToSocket(host, port);
        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        final String[][] map = { {"accept_passive_checks", "passive_checks_enabled"}, {"acknowledged", "problem_has_been_acknowledged"},
                {"acknowledgement_type", "acknowledgement_type"}, {"active_checks_enabled", "active_checks_enabled"}, {"check_command", "check_command"},
                {"check_interval", "check_interval"}, {"check_options", "check_options"}, {"check_period", "check_period"}, {"check_type", "check_type"},
                {"current_attempt", "current_attempt"}, {"flap_detection_enabled", "flap_detection_enabled"}, {"has_been_checked", "has_been_checked"},
                {"is_flapping", "is_flapping"}, {"last_check", "last_check"}, {"last_hard_state", "last_hard_state"},
                {"last_hard_state_change", "last_hard_state_change"}, {"last_notification", "last_notification"}, {"last_state_change", "last_state_change"},
                {"long_plugin_output", "long_plugin_output"}, {"name", "host_name"}, {"next_check", "next_check"}, {"next_notification", "next_notification"},
                {"notification_period", "notification_period"}, {"notifications_enabled", "notifications_enabled"}, {"obsess_over_host", "obsess_over_host"},
                {"percent_state_change", "percent_state_change"}, {"perf_data", "performance_data"}, {"plugin_output", "plugin_output"},
                {"process_performance_data", "process_performance_data"}, {"retry_interval", "retry_interval"},
                {"scheduled_downtime_depth", "scheduled_downtime_depth"}, {"state", "current_state"}, {"state_type", "state_type"}};

        String request = "GET hosts\nSeparators: 10 124 44 125\nColumns:";
        int nameIndex = -1;
        int index = 0;
        for (final String[] curMapEntry : map) {
            if (curMapEntry[0].equals("name")) {
                nameIndex = index;
            }
            request += " " + curMapEntry[0];
            index++;
        }
        if (nameIndex == -1) {
            throw new Exception("Cannot parse LiveStatus stream: 'name' missing");
        }
        request += "\n";
        out.write(request, 0, request.length());
        out.flush();
        socket.shutdownOutput();

        if (prettyName == null) {
            prettyName = host;
        }

        for (;;) {
            final String line = in.readLine();
            if (line == null) {
                break;
            }

            final String[] fieldsCur = line.split("\\|");
            System.out.println("hostline: " + line);
            if (nameIndex >= fieldsCur.length) {
                throw new Exception("LiveStatus data mallformed?");
            }
            System.out.println("Adding host: " + fieldsCur[nameIndex]);
            final Host hostObj = this.addAndOrFindHost(prettyName, fieldsCur[nameIndex]);

            int fieldIndex = 0;
            for (final String[] mapEntry : map) {
                this.addHostParameterEntry(hostObj, mapEntry[1], fieldsCur[fieldIndex]);
                fieldIndex++;
            }
        }
    }

    private void loadNagiosDataLiveStatus_services(final String host, final int port, String prettyName) throws Exception {
        final Socket socket = this.connectToSocket(host, port);
        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        final String[][] map = { {"accept_passive_checks", "passive_checks_enabled"}, {"acknowledged", "problem_has_been_acknowledged"},
                {"acknowledgement_type", "acknowledgement_type"}, {"active_checks_enabled", "active_checks_enabled"}, {"check_command", "check_command"},
                {"check_interval", "check_interval"}, {"check_options", "check_options"}, {"check_period", "check_period"}, {"check_type", "check_type"},
                {"current_attempt", "current_attempt"}, {"current_notification_number", "current_notification_number"}, {"description", "service_description"},
                {"event_handler", "event_handler"}, {"event_handler_enabled", "event_handler_enabled"}, {"execution_time", "check_execution_time"},
                {"flap_detection_enabled", "flap_detection_enabled"}, {"has_been_checked", "has_been_checked"}, {"host_name", "host_name"},
                {"is_flapping", "is_flapping"}, {"last_check", "last_check"},
                {"last_hard_state", "last_hard_state"},
                {"last_hard_state_change", "last_hard_state_change"},
                {"last_notification", "last_notification"},
                {"last_state_change", "last_state_change"},
                {"latency", "check_latency"},
                // { "long_plugin_output", "long_plugin_output" },
                {"max_check_attempts", "max_attempts"}, {"next_check", "next_check"}, {"next_notification", "next_notification"},
                {"notification_period", "notification_period"}, {"notifications_enabled", "notifications_enabled"},
                {"obsess_over_service", "obsess_over_service"}, {"percent_state_change", "percent_state_change"}, {"perf_data", "performance_data"},
                {"plugin_output", "plugin_output"}, {"process_performance_data", "process_performance_data"}, {"retry_interval", "retry_interval"},
                {"scheduled_downtime_depth", "scheduled_downtime_depth"}, {"state", "current_state"}, {"state_type", "state_type"}};

        String request = "GET services\nSeparators: 10 124 44 125\nColumns:";
        int hostNameIndex = -1, serviceNameIndex = -1;
        int nFieldsRequested = 0, index = 0;
        for (final String[] curMapEntry : map) {
            if (curMapEntry[0].equals("host_name")) {
                hostNameIndex = index;
            } else if (curMapEntry[0].equals("description")) {
                serviceNameIndex = index;
            }
            request += " " + curMapEntry[0];
            nFieldsRequested++;
            index++;
        }
        request += "\n";
        out.write(request, 0, request.length());
        out.flush();
        socket.shutdownOutput();

        if (prettyName == null) {
            prettyName = host;
        }

        for (;;) {
            final String line = in.readLine();
            if (line == null) {
                break;
            }

            // System.out.println(line);
            final String[] fieldsCur = line.split("\\|");
            if (fieldsCur.length != nFieldsRequested) {
                throw new Exception("Cannot parse LiveStatus stream: number of elements mismatch (requested: " + nFieldsRequested + ", got: "
                        + fieldsCur.length + ")");
            }
            // System.out.println("Finding host: " + fieldsCur[hostNameIndex]);
            final Host hostObj = this.addAndOrFindHost(prettyName, fieldsCur[hostNameIndex]);
            // System.out.println("Adding service: " + fieldsCur[serviceNameIndex]);
            final Service service = hostObj.addAndOrFindService(fieldsCur[serviceNameIndex]);
            int fieldIndex = 0;
            for (final String[] mapEntry : map) {
                this.addServiceEntry(service, mapEntry[1], fieldsCur[fieldIndex]);
                fieldIndex++;
            }
        }

        socket.close();
    }

    /**
     * @param url
     * @param nagiosVersion
     * @param username
     * @param password
     * @param allowCompression
     * @param prettyName
     * @throws Exception
     */
    public void loadNagiosData(final URL url, final Integer nagiosVersion, final String username, final String password, final boolean allowCompression,
            String prettyName) throws Exception {
        final List<String> fileDump = new ArrayList<String>();

        final HttpURLConnection HTTPConnection = (HttpURLConnection) url.openConnection();
        HTTPConnection.setDefaultUseCaches(false);
        HTTPConnection.setUseCaches(false);
        HTTPConnection.setRequestProperty("User-Agent", userAgent);
        HTTPConnection.setFollowRedirects(true);
        if (allowCompression) {
            HTTPConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        }
        if (username != null) {
            HTTPConnection.setRequestProperty("Authorization", "Basic " + this.encodeBase64(username + ":" + password));
        }
        // establish connection, get response headers
        HTTPConnection.connect();
        // obtain the encoding returned by the server
        final String encoding = HTTPConnection.getContentEncoding();
        InputStream inputStream;
        // create the appropriate stream wrapper based on
        // the encoding type
        if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
            System.out.println("GZIPed stream!");
            inputStream = new GZIPInputStream(HTTPConnection.getInputStream());
        } else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
            System.out.println("Deflated stream!");
            inputStream = new InflaterInputStream(HTTPConnection.getInputStream(), new Inflater(true));
        } else {
            inputStream = HTTPConnection.getInputStream();
        }

        final BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while ((line = in.readLine()) != null) {
            fileDump.add(line);
        }

        in.close();

        if (prettyName == null) {
            prettyName = url.toString();
        }

        if (nagiosVersion == 1) {
            this.addFromNagios1(prettyName, fileDump);
        } else if (nagiosVersion == 2 || nagiosVersion == 3) {
            this.addFromNagios2And3(prettyName, fileDump);
        }
    }

    String encode3Chars(final String in) {
        String result = new String();
        final String encodingChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

        int tripple;
        tripple = in.charAt(0);
        tripple <<= 8;
        if (in.length() >= 2) {
            tripple += in.charAt(1);
        }
        tripple <<= 8;
        if (in.length() >= 3) {
            tripple += in.charAt(2);
        }

        for (int outputIndex = 0; outputIndex < 4; outputIndex++) {
            final int ecIndex = tripple % 64;
            result = encodingChars.substring(ecIndex, ecIndex + 1) + result;
            tripple /= 64;
        }

        return result;
    }

    public String encodeBase64(final String input) {
        String output = new String();
        int inputLength = input.length(), index = 0;

        while (inputLength > 0) {
            output += this.encode3Chars(input.substring(index, index + Math.min(3, inputLength)));

            index += 3;
            inputLength -= 3;
        }

        return output;
    }

    public String findHostDown(final long maxAge) throws Exception {
        final HashMap<String, Long> map = new HashMap<String, Long>();

        for (final Host currentHost : hosts) {
            final String current_state = currentHost.getParameter("current_state");
            if (current_state == null) {
                continue;
            }

            long mostRecent = 0;
            final Long dummy = map.get(currentHost.getNagiosSource());
            if (dummy != null) {
                mostRecent = dummy;
            }

            String last_check = currentHost.getParameter("last_check");
            mostRecent = Math.max(mostRecent, last_check != null ? Long.valueOf(last_check) : 0);

            String last_update = currentHost.getParameter("last_update");
            mostRecent = Math.max(mostRecent, last_update != null ? Long.valueOf(last_update) : 0);

            for (final Service currentService : currentHost.getServices()) {
                last_check = currentService.getParameter("last_check");
                mostRecent = Math.max(mostRecent, last_check != null ? Long.valueOf(last_check) : 0);

                last_update = currentService.getParameter("last_update");
                mostRecent = Math.max(mostRecent, last_update != null ? Long.valueOf(last_update) : 0);
            }

            map.put(currentHost.getNagiosSource(), new Long(mostRecent));
        }

        final Set<String> set = map.keySet();
        final Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            final String hostName = it.next();
            final long age = System.currentTimeMillis() / 1000 - map.get(hostName);
            if (age > maxAge) {
                System.out.println("check-max-age: " + hostName + "/" + map.get(hostName) + "/" + age + "/" + maxAge);

                final BufferedWriter out = new BufferedWriter(new FileWriter("max-check-age.log", true));
                final String ts = new SimpleDateFormat("E yyyy.MM.dd  hh:mm:ss a zzz").format(Calendar.getInstance().getTime());
                out.write(ts, 0, ts.length());
                out.newLine();
                final String s = "check-max-age: " + hostName + "/" + map.get(hostName) + "/" + age + "/" + maxAge;
                out.write(s, 0, s.length());
                out.newLine();
                out.close();

                return hostName;
            }
        }

        return null;
    }

    public long findMostRecentCheckAge() {
        long mostRecent = 0;

        for (final Host currentHost : hosts) {
            final String current_state = currentHost.getParameter("current_state");
            if (current_state == null) {
                continue;
            }

            String last_check = currentHost.getParameter("last_check");
            mostRecent = Math.max(mostRecent, last_check != null ? Long.valueOf(last_check) : 0);

            String last_update = currentHost.getParameter("last_update");
            mostRecent = Math.max(mostRecent, last_update != null ? Long.valueOf(last_update) : 0);

            for (final Service currentService : currentHost.getServices()) {
                last_check = currentService.getParameter("last_check");
                mostRecent = Math.max(mostRecent, last_check != null ? Long.valueOf(last_check) : 0);

                last_update = currentService.getParameter("last_update");
                mostRecent = Math.max(mostRecent, last_update != null ? Long.valueOf(last_update) : 0);
            }
        }

        return System.currentTimeMillis() / 1000 - mostRecent;
    }

    public JavNag() {}

    /**
     * Loads a Nagios statusfile. See "status_file" in nagios.cfg.
     *
     * @param fileName
     *            Path to status-file.
     * @param nagiosVersion
     *            Nagios-version this file is from.
     * @see NagiosVersion
     */
    public JavNag(final String fileName, final Integer nagiosVersion, final String prettyName) throws Exception {
        this.loadNagiosData(fileName, nagiosVersion, prettyName);
    }

    /**
     * Retrieves a Nagios status from a host/port (TCP).
     *
     * @param host
     *            Hostname (must be resolvable).
     * @param port
     *            Portnumber.
     * @param nagiosVersion
     *            Nagios-version this file is from.
     * @see NagiosVersion
     */
    public JavNag(final String host, final int port, final Integer nagiosVersion, final boolean compressed, final String prettyName) throws Exception {
        this.loadNagiosData(host, port, nagiosVersion, compressed, prettyName);
    }

    /**
     * Retrieves a Nagios status from an URL
     *
     * @param url
     *            URL
     * @param nagiosVersion
     *            Nagios-version this file is from.
     * @see NagiosVersion
     */
    public JavNag(final URL url, final Integer nagiosVersion, final String username, final String password, final boolean allowCompression,
            final String prettyName) throws Exception {
        this.loadNagiosData(url, nagiosVersion, username, password, allowCompression, prettyName);
    }

    /**
     * Loads the Nagios status via LiveStatus.
     *
     * @param host
     *            host
     * @param port
     *            port
     */
    public JavNag(final String host, final int port, final String prettyName) throws Exception {
        this.loadNagiosDataLiveStatus(host, port, prettyName);
    }

}
