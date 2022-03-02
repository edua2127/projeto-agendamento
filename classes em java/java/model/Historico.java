package model;

import java.math.BigDecimal;

public class Historico extends AbstractEntity{
    private Agenda agenda;
    private String observacao;
    private Secretaria secretaria;
    private Paciente paciente;
    private BigDecimal data;
    private StatusAgendamento statusAgenda;

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }

    public StatusAgendamento getStatusAgenda() {
        return statusAgenda;
    }

    public void setStatusAgenda(StatusAgendamento statusAgenda) {
        this.statusAgenda = statusAgenda;
    }
}
