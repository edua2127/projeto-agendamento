package dao;

import factory.Factory;
import model.Especialidacao;
import model.Medico;
import model.Sexo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoDAO {
    Connection connection;

    public MedicoDAO() {
        this.connection = new Factory().getConection();
    }

    public void createTable() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS medico_id_seq;";
        sql += "CREATE TABLE IF NOT EXISTS medicos(medico_id BIGINT PRIMARY KEY DEFAULT nextval('medico_id_seq'), cadastro timestamp, atualizado timestamp, excluido timestamp , nome varchar(40) not null, telefone varchar(40), celular varchar(40) not null, cpf varchar(40) not null, rg varchar(40) not null, email varchar(40) not null, login varchar(40) not null, senha varchar(40), sexo varchar(40), CRM varchar(40) not null, porcentParticipacao decimal( 8, 3) not null, consultorio varchar(40) not null, id_especializacao BIGINT not null, CONSTRAINT fk_id_especializacao FOREIGN KEY (id_especializacao) REFERENCES especializacoes(especializacao_id))";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertTable(Medico medico) {
        String sql = "insert into medicos(cadastro, atualizado, excluido, nome, telefone, celular, cpf, rg, email, login, senha,\n" +
                "sexo, CRM, porcentParticipacao, consultorio, id_especializacao) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, medico.getCadastro());
            statement.setTimestamp(2, medico.getAtualizado());
            statement.setTimestamp(3, medico.getExcluido());
            statement.setString(4, medico.getNome());
            statement.setString(5, medico.getTelefone());
            statement.setString(6, medico.getCelular());
            statement.setString(7, medico.getCpf());
            statement.setString(8, medico.getRg());
            statement.setString(9, medico.getEmail());
            statement.setString(10, medico.getLogin());
            statement.setString(11, medico.getSenha());
            statement.setString(12, medico.getSexo().getDescricao());
            statement.setString(13, medico.getCRM());
            statement.setBigDecimal(14, medico.getPorcentParticipacao());
            statement.setString(15, medico.getConsultorio());
            statement.setLong(16, medico.getEspecialidacao().getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Medico selectById(long id) {
        String sql = "select * from medicos where medico_id = ?";
        Medico medico = new Medico();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                medico.setId(resultSet.getLong("medico_id"));
                medico.setCadastro(resultSet.getTimestamp("cadastro"));
                medico.setAtualizado(resultSet.getTimestamp("atualizado"));
                medico.setExcluido(resultSet.getTimestamp("excluido"));
                medico.setNome(resultSet.getString("nome"));
                medico.setTelefone(resultSet.getString("telefone"));
                medico.setCelular(resultSet.getString("celular"));
                medico.setCpf(resultSet.getString("cpf"));
                medico.setRg(resultSet.getString("rg"));
                medico.setEmail(resultSet.getString("email"));
                medico.setLogin(resultSet.getString("login"));
                medico.setSenha(resultSet.getString("senha"));
                medico.setSexo(Sexo.valueOf(resultSet.getString("sexo")));
                medico.setCRM(resultSet.getString("crm"));
                medico.setPorcentParticipacao(resultSet.getBigDecimal("porcentparticipacao"));
                medico.setConsultorio(resultSet.getString("consultorio"));
                EspecializacaoDAO especializacaoDAO = new EspecializacaoDAO();
                Especialidacao especialidacao = especializacaoDAO.selectById(resultSet.getInt("id_especializacao"));
                medico.setEspecialidacao(especialidacao);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medico;
    }
    public void editTable(Medico medico) {
        String sql = "UPDATE medicos set cadastro = ?, atualizado = ?, excluido = ?, nome = ?, telefone = ?, celular = ?, cpf = ?, rg = ?, email = ?, login = ?, senha = ?, sexo = ?, crm = ?, porcentparticipacao = ?, consultorio = ?, especialidacao = ? where medico_id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, medico.getCadastro());
            statement.setTimestamp(2, medico.getAtualizado());
            statement.setTimestamp(3, medico.getExcluido());
            statement.setString(4, medico.getNome());
            statement.setString(5, medico.getTelefone());
            statement.setString(6, medico.getCelular());
            statement.setString(7, medico.getCpf());
            statement.setString(8, medico.getRg());
            statement.setString(9, medico.getEmail());
            statement.setString(10, medico.getLogin());
            statement.setString(11, medico.getSenha());
            statement.setString(12, medico.getSexo().getDescricao());
            statement.setString(13, medico.getCRM());
            statement.setBigDecimal(14, medico.getPorcentParticipacao());
            statement.setString(15, medico.getConsultorio());
            statement.setLong(16, medico.getEspecialidacao().getId());
            statement.setLong(17, medico.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void desativarMedico(int id) {
        String sql = "UPDATE medicos set excluido = now() where medico_id = ?";
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
