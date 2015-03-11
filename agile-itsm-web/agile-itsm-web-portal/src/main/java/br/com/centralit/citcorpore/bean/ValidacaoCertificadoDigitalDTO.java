package br.com.centralit.citcorpore.bean;

import java.security.cert.X509Certificate;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.cripto.InfoCertificadoDigital;

public class ValidacaoCertificadoDigitalDTO extends BaseEntity {

    private static final long serialVersionUID = -4566460320507373015L;
    private String fileName;
    private String caminhoCompleto;
    private InfoCertificadoDigital infoCertificadoDigital;
    private X509Certificate cert;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InfoCertificadoDigital getInfoCertificadoDigital() {
        return infoCertificadoDigital;
    }

    public void setInfoCertificadoDigital(InfoCertificadoDigital infoCertificadoDigital) {
        this.infoCertificadoDigital = infoCertificadoDigital;
    }

    public String getCaminhoCompleto() {
        return caminhoCompleto;
    }

    public void setCaminhoCompleto(String caminhoCompleto) {
        this.caminhoCompleto = caminhoCompleto;
    }

    public X509Certificate getCert() {
        return cert;
    }

    public void setCert(X509Certificate cert) {
        this.cert = cert;
    }

}