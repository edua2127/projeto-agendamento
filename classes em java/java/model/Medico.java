package model;

import java.math.BigDecimal;

public class Medico extends Pessoa{
    private String CRM;
    private BigDecimal porcentParticipacao;
    private String consultorio;
    private Especialidacao especialidacao;




    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public BigDecimal getPorcentParticipacao() {
        return porcentParticipacao;
    }

    public void setPorcentParticipacao(BigDecimal porcentParticipacao) {
        this.porcentParticipacao = porcentParticipacao;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public Especialidacao getEspecialidacao() {
        return especialidacao;
    }

    public void setEspecialidacao(Especialidacao especialidacao) {
        this.especialidacao = especialidacao;
    }
}
