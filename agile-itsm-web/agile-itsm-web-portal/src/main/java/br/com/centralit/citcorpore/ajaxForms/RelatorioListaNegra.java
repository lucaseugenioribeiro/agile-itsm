package br.com.centralit.citcorpore.ajaxForms;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citcorpore.bean.RelatorioListaNegraDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.negocio.SoftwaresListaNegraService;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.LogoRel;
import br.com.centralit.citcorpore.util.UtilRelatorio;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;

/**
 * @author mario.haysaki
 *
 */
public class RelatorioListaNegra extends AjaxFormAction { 
	
	UsuarioDTO usuario;
	private String localeSession = null;
	
	@Override
	public void load(DocumentHTML document, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 usuario = WebUtil.getUsuario(request);
		if (usuario == null) {
			document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
			document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
			return;
		}
		
	}
	
	public void imprimirRelatorioListaNegra(DocumentHTML document, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = ((HttpServletRequest) request).getSession();
		RelatorioListaNegraDTO listaNegraDto = (RelatorioListaNegraDTO) document.getBean();
		usuario = WebUtil.getUsuario(request);
		SoftwaresListaNegraService softwareListaNegraService = (SoftwaresListaNegraService) ServiceLocator.getInstance().getService(SoftwaresListaNegraService.class, null);
		
		Collection<RelatorioListaNegraDTO> listSoftwareListaNegra = softwareListaNegraService.listaRelatorioListaNegra(listaNegraDto);

		if (listSoftwareListaNegra != null) {
			if (listaNegraDto != null) {

				Date dt = new Date();

				String strCompl = "" + dt.getTime();
				String caminhoJasper = CITCorporeUtil.CAMINHO_REAL_APP + Constantes.getValue("CAMINHO_RELATORIOS") + "RelatorioListaNegra.jasper";
				String diretorioReceita = CITCorporeUtil.CAMINHO_REAL_APP + "/tempFiles";
				String diretorioRelativoOS = Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO") + "/tempFiles";

				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros = UtilRelatorio.trataInternacionalizacaoLocale(session, parametros);

				parametros.put("TITULO_RELATORIO", UtilI18N.internacionaliza(request, "citcorporeRelatorio.softwaresListaNegra"));
				parametros.put("CIDADE", UtilI18N.internacionaliza(request, "citcorpore.comum.relatorioCidade"));
				parametros.put("DATA_HORA", UtilDatas.getDataHoraAtual());
				parametros.put("NOME_USUARIO", usuario.getNomeUsuario());
				parametros.put("dataInicio", listaNegraDto.getDataInicio()); 
				parametros.put("dataFim", listaNegraDto.getDataFim());
				parametros.put("Logo", LogoRel.getFile());

				if (listaNegraDto.getFormatoArquivoRelatorio().equalsIgnoreCase("pdf")) {

					JRDataSource dataSource = new JRBeanCollectionDataSource(listSoftwareListaNegra);

					JasperPrint print = JasperFillManager.fillReport(caminhoJasper, parametros, dataSource);

					JasperExportManager.exportReportToPdfFile(print, diretorioReceita + "/RelatorioListaNegra" + strCompl + "_" + usuario.getIdUsuario() + ".pdf");

					document.executeScript("window.open('" + Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO") + "/printPDF/printPDF.jsp?url=" + diretorioRelativoOS
							+ "/RelatorioListaNegra" + strCompl + "_" + usuario.getIdUsuario() + ".pdf')");

				} else {

					JRDataSource dataSource = new JRBeanCollectionDataSource(listSoftwareListaNegra);

					JasperDesign desenho = JRXmlLoader.load(CITCorporeUtil.CAMINHO_REAL_APP + Constantes.getValue("CAMINHO_RELATORIOS") + "RelatorioListaNegraXls.jrxml");

					JasperReport relatorio = JasperCompileManager.compileReport(desenho);

					JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, dataSource);

					JRXlsExporter exporter = new JRXlsExporter();
					exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, impressao);
					exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
					exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
					exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, diretorioReceita + "/RelatorioListaNegra" + strCompl + "_" + usuario.getIdUsuario() + ".xls");

					exporter.exportReport();

					document.executeScript("window.open('" + Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO") + "/printPDF/printPDF.jsp?url=" + diretorioRelativoOS
							+ "/RelatorioListaNegra" + strCompl + "_" + usuario.getIdUsuario() + ".xls')");

				}

			} else {
				document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.relatorioVazio"));
				document.getJanelaPopupById("JANELA_AGUARDE_MENU").hide();
				return;
			}
		} else {
			document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.relatorioVazio"));
			document.getJanelaPopupById("JANELA_AGUARDE_MENU").hide();
			return;
		}

		document.getJanelaPopupById("JANELA_AGUARDE_MENU").hide();

	}

	@Override
	public Class getBeanClass() {
		return RelatorioListaNegraDTO.class;
	}

}
