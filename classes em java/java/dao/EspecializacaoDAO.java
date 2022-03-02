package dao;

import factory.Factory;
import model.Especialidacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EspecializacaoDAO {

    Connection connection;

    public EspecializacaoDAO() {
        this.connection = new Factory().getConection();
    }

    public void createTable() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS especializacao_id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS especializacoes(especializacao_id BIGINT PRIMARY KEY DEFAULT nextval('especializacao_id_seq'), cadastro timestamp, atualizado timestamp, excluido timestamp, nome varchar(40) not null)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Especialidacao selectById(int id) {
        String sql = "select * from especializacoes where especializacao_id = ?";
        Especialidacao especialidacao = new Especialidacao();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                especialidacao.setId(resultSet.getLong("especializacao_id"));
                especialidacao.setCadastro(resultSet.getTimestamp("cadastro"));
                especialidacao.setAtualizado(resultSet.getTimestamp("atualizado"));
                especialidacao.setExcluido(resultSet.getTimestamp("excluido"));
                especialidacao.setNome(resultSet.getString("nome"));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return especialidacao;
    }
    public void insertEspecializacao(Especialidacao especializacao) {
        String sql = "insert into especializacoes(cadastro, atualizado, excluido, nome) values(?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, especializacao.getCadastro());
            statement.setTimestamp(2, especializacao.getAtualizado());
            statement.setTimestamp(3, especializacao.getExcluido());
            statement.setString(4, especializacao.getNome());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editarEspecialacao(Especialidacao especialidacao) {
        String sql = "UPDATE especializacoes set cadastro = ?, atualizado = ?, excluido = ?, nome = ? where especializacao_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, especialidacao.getCadastro());
            statement.setTimestamp(2, especialidacao.getAtualizado());
            statement.setTimestamp(3, especialidacao.getExcluido());
            statement.setString(4, especialidacao.getNome());
            statement.setLong(5, especialidacao.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void desativarEspecialacao(int id) {
        String sql = "UPDATE especializacoes set excluido = now() where especializacao_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
