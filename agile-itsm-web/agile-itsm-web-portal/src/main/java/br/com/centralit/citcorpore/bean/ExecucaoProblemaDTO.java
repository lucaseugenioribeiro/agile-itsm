package br.com.centralit.citcorpore.bean;

import br.com.centralit.bpm.dto.ExecucaoFluxoDTO;

public class ExecucaoProblemaDTO extends ExecucaoFluxoDTO {

    private Integer idProblema;
    private Integer idFase;
    private Integer prazoHH;
    private Integer prazoMM;
    private Integer seqReabertura;

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(final Integer parm) {
        idFase = parm;
    }

    public Integer getPrazoHH() {
        return prazoHH;
    }

    public void setPrazoHH(final Integer parm) {
        prazoHH = parm;
    }

    public Integer getPrazoMM() {
        return prazoMM;
    }

    public void setPrazoMM(final Integer parm) {
        prazoMM = parm;
    }

    public Integer getSeqReabertura() {
        return seqReabertura;
    }

    public void setSeqReabertura(final Integer seqReabertura) {
        this.seqReabertura = seqReabertura;
    }

}
