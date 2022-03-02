package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Agenda extends AbstractEntity{
   private Paciente paciente;
   private Medico medico;
   private StatusAgendamento statusAgendamento;
   private Timestamp dataAgendamento;
   private Boolean encaixe;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public StatusAgendamento getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }

    public Timestamp getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Timestamp dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Boolean getEncaixe() {
        return encaixe;
    }

    public void setEncaixe(Boolean encaixe) {
        this.encaixe = encaixe;
    }
}
