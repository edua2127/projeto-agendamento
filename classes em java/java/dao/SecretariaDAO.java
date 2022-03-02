package dao;

import factory.Factory;
import model.Secretaria;
import model.Sexo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecretariaDAO {
    Connection connection;

    public SecretariaDAO() {
        this.connection = new Factory().getConection();
    }
    public void criarTabela() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS secretaria_id_seq;";
        sql += "CREATE TABLE IF NOT EXISTS secretarias(secretaria_id BIGINT PRIMARY KEY DEFAULT nextval('secretaria_id_seq'), cadastro timestamp, atualizado timestamp, excluido timestamp, nome varchar(40) not null, telefone varchar(40) not null, celular varchar(40) not null, cpf varchar(40) not null, rg varchar(40) not null, email varchar(40) not null, login varchar(40) not null, senha varchar(40) not null, sexo varchar(40) not null, salario decimal( 8, 3) not null, data_contratacao decimal( 8, 3) not null, pis varchar(50) not null)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionar(Secretaria secretaria) {
        String sql = "insert into secretarias(cadastro, atualizado, excluido, nome, telefone, celular, cpf, rg, email, login, senha,\n" +
                "sexo, salario, data_contratacao, pis) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setTimestamp(1, secretaria.getCadastro());
            statement.setTimestamp(2, secretaria.getAtualizado());
            statement.setTimestamp(3, secretaria.getExcluido());
            statement.setString(4, secretaria.getNome());
            statement.setString(5, secretaria.getTelefone());
            statement.setString(6, secretaria.getCelular());
            statement.setString(7, secretaria.getCpf());
            statement.setString(8, secretaria.getRg());
            statement.setString(9, secretaria.getEmail());
            statement.setString(10, secretaria.getLogin());
            statement.setString(11, secretaria.getSenha());
            statement.setString(12, secretaria.getSexo().getDescricao());
            statement.setBigDecimal(13, secretaria.getSalario());
            statement.setBigDecimal(14, secretaria.getDataContracacao());
            statement.setString(15, secretaria.getPis());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Secretaria selectById(long id) {
        String sql = "select * from secretarias where id_secretaria = ?";
        Secretaria secretaria1 = new Secretaria();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                secretaria1.setId(resultSet.getLong("id_secretaria"));
                secretaria1.setCadastro(resultSet.getTimestamp("cadastro"));
                secretaria1.setAtualizado(resultSet.getTimestamp("atualizado"));
                secretaria1.setExcluido(resultSet.getTimestamp("excluido"));
                secretaria1.setNome(resultSet.getString("nome"));
                secretaria1.setTelefone(resultSet.getString("telefone"));
                secretaria1.setCelular(resultSet.getString("celular"));
                secretaria1.setCpf(resultSet.getString("cpf"));
                secretaria1.setRg(resultSet.getString("rg"));
                secretaria1.setEmail(resultSet.getString("email"));
                secretaria1.setLogin(resultSet.getString("login"));
                secretaria1.setSenha(resultSet.getString("senha"));
                secretaria1.setSexo(Sexo.valueOf(resultSet.getString("sexo")));
                secretaria1.setSalario(resultSet.getBigDecimal("salario"));
                secretaria1.setDataContracacao(resultSet.getBigDecimal("data_contratacao"));
                secretaria1.setPis(resultSet.getString("pis"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return secretaria1;
    }

    public void editarSecretaria(Secretaria secretaria) {
        String sql = "UPDATE secretarias set cadastro = ?, atualizado = ?, excluido = ?, nome = ?, telefone = ?, celular = ?, cpf = ?, rg = ?, email = ?, login = ?, senha = ?, sexo = ?, salario = ?, data_contratacao = ?, pis = ? where id_secretaria = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, secretaria.getCadastro());
            statement.setTimestamp(2, secretaria.getAtualizado());
            statement.setTimestamp(3, secretaria.getExcluido());
            statement.setString(4, secretaria.getNome());
            statement.setString(5, secretaria.getTelefone());
            statement.setString(6, secretaria.getCelular());
            statement.setString(7, secretaria.getCpf());
            statement.setString(8, secretaria.getRg());
            statement.setString(9, secretaria.getEmail());
            statement.setString(10, secretaria.getLogin());
            statement.setString(11, secretaria.getSenha());
            statement.setString(12, secretaria.getSexo().getDescricao());
            statement.setBigDecimal(13, secretaria.getSalario());
            statement.setBigDecimal(14, secretaria.getDataContracacao());
            statement.setString(15, secretaria.getPis());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desativarSecretaria(Secretaria secretaria) {
        String sql = "UPDATE secretarias set excluido = now() where id_secretaria = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, secretaria.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
