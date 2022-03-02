package dao;

import factory.Factory;
import model.Convenio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConvenioDAO {
    Connection connection;

    public ConvenioDAO() {
        this.connection = new Factory().getConection();
    }
    public void createTable() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS convenio_id_seq;";
        sql += "CREATE TABLE IF NOT EXISTS convenios(convenio_id BIGINT PRIMARY KEY DEFAULT nextval('convenio_id_seq'),";
        sql += "cadastro timestamp, atualizado timestamp, excluido timestamp, nome varchar(40) not null,";
        sql += "custo decimal( 8, 3) not null)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void adicionarConvenio(Convenio convenio) {
        String sql = "insert into convenios (cadastro, atualizado, excluido, nome, custo) values(?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, convenio.getCadastro());
            statement.setTimestamp(2, convenio.getAtualizado());
            statement.setTimestamp(3, convenio.getExcluido());
            statement.setString(4, convenio.getNome());
            statement.setBigDecimal(5, convenio.getCusto());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editarConvenio(Convenio convenio) {
        String sql = "UPDATE convenios set cadastro = ?, atualizado = ?, excluido = ?, nome = ?, custo = ? where convenio_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, convenio.getCadastro());
            statement.setTimestamp(2, convenio.getAtualizado());
            statement.setTimestamp(3, convenio.getExcluido());
            statement.setString(4, convenio.getNome());
            statement.setBigDecimal(5, convenio.getCusto());
            statement.setLong(6, convenio.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void desativarConvenio(long id) {
        String sql = "UPDATE convenios set excluido = now() where convenio_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Convenio selectByID(long id) {
        Convenio convenio = new Convenio();
        String sql = "select * from convenios where convenio_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                convenio.setId(resultSet.getLong("convenio_id"));
                convenio.setCadastro(resultSet.getTimestamp("cadastro"));
                convenio.setAtualizado(resultSet.getTimestamp("atualizado"));
                convenio.setExcluido(resultSet.getTimestamp("excluido"));
                convenio.setNome(resultSet.getString("nome"));
                convenio.setCusto(resultSet.getBigDecimal("custo"));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return convenio;
    }

}
