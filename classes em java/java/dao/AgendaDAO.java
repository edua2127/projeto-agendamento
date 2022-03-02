package dao;

import factory.Factory;
import model.Agenda;
import model.Medico;
import model.Paciente;
import model.StatusAgendamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgendaDAO {
    Connection connection;

    public AgendaDAO() {
        this.connection = new Factory().getConection();
    }

    public void criarTabela() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS agenda_id_seq;";
        sql += "CREATE TABLE IF NOT EXISTS agendas(agenda_id BIGINT PRIMARY KEY DEFAULT nextval('agenda_id_seq'),";
        sql += "cadastro timestamp, atualizado timestamp, excluido timestamp,";
        sql += "paciente_id BIGINT not null, medico_id BIGINT not null, statusAgendamento varchar(40) not null, dataAgendamento timestamp not null, encaixe boolean not null, ";
        sql += "CONSTRAINT fk_paciente_id FOREIGN KEY (paciente_id) REFERENCES pacientes(paciente_id),";
        sql += "CONSTRAINT fk_medico_id FOREIGN KEY (medico_id) REFERENCES medicos(medico_id))";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void adicionarAgenda(Agenda agenda) {
        String sql = "insert into agendas(cadastro, atualizado, excluido, paciente_id, medico_id, statusAgendamento, dataAgendamento, encaixe)";
        sql += " values(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, agenda.getCadastro());
            statement.setTimestamp(2, agenda.getAtualizado());
            statement.setTimestamp(3, agenda.getExcluido());
            statement.setLong(4, agenda.getPaciente().getId());
            statement.setLong(5, agenda.getMedico().getId());
            statement.setString(6, agenda.getStatusAgendamento().getDescricao());
            statement.setTimestamp(7, agenda.getDataAgendamento());
            statement.setBoolean(8, agenda.getEncaixe());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editarAgenda(Agenda agenda) {
        String sql = "UPDATE agendas set cadastro = ?, atualizado = ?, excluido = ?, paciente_id = ?, medico_id = ?, statusAgendamento = ?, dataAgendamento = ?, encaixe = ? where agenda_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, agenda.getCadastro());
            statement.setTimestamp(2, agenda.getAtualizado());
            statement.setTimestamp(3, agenda.getExcluido());
            statement.setLong(4, agenda.getPaciente().getId());
            statement.setLong(5, agenda.getMedico().getId());
            statement.setString(6, agenda.getStatusAgendamento().getDescricao());
            statement.setTimestamp(7, agenda.getDataAgendamento());
            statement.setBoolean(8, agenda.getEncaixe());
            statement.setLong(9, agenda.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Agenda selectByID(long id) {
        Agenda agenda = new Agenda();
        String sql = "select * from agendas where agenda_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                agenda.setId(resultSet.getLong("agenda_id"));
                agenda.setCadastro(resultSet.getTimestamp("cadastro"));
                agenda.setAtualizado(resultSet.getTimestamp("atualizado"));
                agenda.setExcluido(resultSet.getTimestamp("excluido"));
                PacienteDAO pacienteDAO = new PacienteDAO();
                Paciente paciente = pacienteDAO.selectById(resultSet.getLong("paciente_id"));
                agenda.setPaciente(paciente);
                MedicoDAO medicoDAO = new MedicoDAO();
                Medico medico = medicoDAO.selectById(resultSet.getLong("medico_id"));
                agenda.setMedico(medico);
                agenda.setStatusAgendamento(StatusAgendamento.valueOf(resultSet.getString("statusAgendamento")));
                agenda.setDataAgendamento(resultSet.getTimestamp("dataAgendamento"));
                agenda.setEncaixe(resultSet.getBoolean("encaixe"));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return agenda;
    }
    public void desativarAgenda(long id) {
        String sql = "UPDATE agendas set excluido = now() where agenda_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
