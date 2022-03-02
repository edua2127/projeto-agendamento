package dao;

import factory.Factory;
import model.Historico;
import model.Paciente;
import model.Secretaria;
import model.StatusAgendamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoricoDAO {

    Connection connection;

    public HistoricoDAO() {
        this.connection = new Factory().getConection();
    }

    public void criarTabela() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS historico_id_seq;";
        sql += "CREATE TABLE IF NOT EXISTS historicos(historico_id BIGINT PRIMARY KEY DEFAULT nextval('historico_id_seq'),";
        sql += "cadastro timestamp, atualizado timestamp, excluido timestamp,";
        sql += "agenda_id BIGINT not null, observacao varchar(200) not null, secretaria_id BIGINT, paciente_id BIGINT, datav decimal not null, statusAgenda varchar(50) not null,";
        sql += "CONSTRAINT fk_paciente_id FOREIGN KEY (paciente_id) REFERENCES pacientes(paciente_id),";
        sql += "CONSTRAINT fk_secretaria_id FOREIGN KEY (secretaria_id) REFERENCES secretarias(secretaria_id),";
        sql += "CONSTRAINT fk_agenda_id FOREIGN KEY (agenda_id) REFERENCES Agendas(agenda_id))";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarTabela(Historico historico) {
        String sql = "insert into historicos(cadastro, atualizado, excluido, agenda_id, observacao, secretaria_id, paciente_id, datav, statusAgenda) ";
        sql += "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, historico.getCadastro());
            statement.setTimestamp(2, historico.getAtualizado());
            statement.setTimestamp(3, historico.getExcluido());
            statement.setLong(4, historico.getAgenda().getId());
            statement.setString(5, historico.getObservacao());
            statement.setLong(6, historico.getSecretaria().getId());
            statement.setLong(7, historico.getPaciente().getId());
            statement.setBigDecimal(8, historico.getData());
            statement.setString(9, historico.getStatusAgenda().getDescricao());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editarHistorico(Historico historico) {
        String sql = "update historicos set cadastro = ?, atualizado = ?, excluido = ?, agenda_id = ?, observacao = ?, secretaria_id = ?,  paciente_id = ?, datav = ?, statusAgenda = ? where historico_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, historico.getCadastro());
            statement.setTimestamp(2, historico.getAtualizado());
            statement.setTimestamp(3, historico.getExcluido());
            statement.setLong(4, historico.getAgenda().getId());
            statement.setString(5, historico.getObservacao());
            statement.setLong(6, historico.getSecretaria().getId());
            statement.setLong(7, historico.getPaciente().getId());
            statement.setBigDecimal(8, historico.getData());
            statement.setString(9, historico.getStatusAgenda().getDescricao());
            statement.setLong(10, historico.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Historico selectById(long id) {
        Historico historico = new Historico();
        String sql = "select * from historicos where historico_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historico.setId(id);
                historico.setCadastro(resultSet.getTimestamp("cadastro"));
                historico.setAtualizado(resultSet.getTimestamp("atualizado"));
                historico.setExcluido(resultSet.getTimestamp("excluido"));
                historico.setObservacao(resultSet.getString("observacao"));
                SecretariaDAO secretariaDAO = new SecretariaDAO();
                Secretaria secretaria = secretariaDAO.selectById(resultSet.getLong("secretaria_id"));
                historico.setSecretaria(secretaria);
                PacienteDAO pacienteDAO = new PacienteDAO();
                Paciente paciente = pacienteDAO.selectById(resultSet.getLong("paciente_id"));
                historico.setPaciente(paciente);
                historico.setData(resultSet.getBigDecimal("datav"));
                historico.setStatusAgenda(StatusAgendamento.valueOf(resultSet.getString("statusAgenda")));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historico;
    }
    public void desativarHistorico(long id) {
        String sql = "update  historicos set excluido = now() where historico_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
