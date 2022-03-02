package dao;

import factory.Factory;
import model.Convenio;
import model.Paciente;
import model.Sexo;
import model.TipoPaciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {
    Connection connection;

    public PacienteDAO() {
        this.connection = new Factory().getConection();
    }

    public void criarTabela() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS paciente_id_seq;";
        sql += "CREATE TABLE IF NOT EXISTS pacientes(paciente_id BIGINT PRIMARY KEY DEFAULT nextval('paciente_id_seq'),";
        sql += "cadastro timestamp, atualizado timestamp, excluido timestamp, nome varchar(40) not null, telefone varchar(40) not null,";
        sql += "celular varchar(40) not null, cpf varchar(40) not null, rg varchar(40) not null, email varchar(40) not null, login varchar(40) not null, senha varchar(40) not null, sexo varchar(40) not null,";
        sql += "tipoPaciente varchar(40) not null, numeroCartaoConvenio varchar(40) not null, dataVencimento timestamp not null, convenio_id BIGINT not null, ";
        sql += "CONSTRAINT fk_convenio_id FOREIGN KEY (convenio_id) REFERENCES convenios(convenio_id))";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarPaciente(Paciente paciente) {
        String sql = "insert into pacientes(cadastro, atualizado, excluido, nome, telefone, celular, cpf, rg, email, login, senha, sexo, tipoPaciente, numeroCartaoConvenio, dataVencimento, convenio_id) ";
        sql += "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, paciente.getCadastro());
            statement.setTimestamp(2, paciente.getAtualizado());
            statement.setTimestamp(3, paciente.getExcluido());
            statement.setString(4, paciente.getNome());
            statement.setString(5,paciente.getTelefone());
            statement.setString(6, paciente.getCelular());
            statement.setString(7, paciente.getCpf());
            statement.setString(8, paciente.getRg());
            statement.setString(9, paciente.getEmail());
            statement.setString(10, paciente.getLogin());
            statement.setString(11, paciente.getSenha());
            statement.setString(12, paciente.getSexo().getDescricao());
            statement.setString(13, paciente.getTipoPaciente().getDescricao());
            statement.setString(14, paciente.getNumeroCartaoConvenio());
            statement.setTimestamp(15, paciente.getDataVencimento());
            statement.setLong(16, paciente.getConvenio().getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editarPaciente(Paciente paciente) {
        String sql = "update pacientes set cadastro = ?, atualizado = ?, excluido = ?, nome = ?, telefone = ?, celular = ?, cpf = ?, rg = ?, email = ?, login = ?, senha = ?, sexo = ?, tipoPaciente = ?, numeroCartaoConvenio = ?, dataVencimento = ?, convenio_id = ? where paciente_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, paciente.getCadastro());
            statement.setTimestamp(2, paciente.getAtualizado());
            statement.setTimestamp(3, paciente.getExcluido());
            statement.setString(4, paciente.getNome());
            statement.setString(5,paciente.getTelefone());
            statement.setString(6, paciente.getCelular());
            statement.setString(7, paciente.getCpf());
            statement.setString(8, paciente.getRg());
            statement.setString(9, paciente.getEmail());
            statement.setString(10, paciente.getLogin());
            statement.setString(11, paciente.getSenha());
            statement.setString(12, paciente.getSexo().getDescricao());
            statement.setString(13, paciente.getTipoPaciente().getDescricao());
            statement.setString(14, paciente.getNumeroCartaoConvenio());
            statement.setTimestamp(15, paciente.getDataVencimento());
            statement.setLong(16, paciente.getConvenio().getId());
            statement.setLong(17, paciente.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Paciente selectById(long id) {
        String sql = "select * from pacientes where paciente_id = ?";
        Paciente paciente = new Paciente();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                paciente.setId(resultSet.getLong("paciente_id"));
                paciente.setCadastro(resultSet.getTimestamp("cadastro"));
                paciente.setAtualizado(resultSet.getTimestamp("atualizado"));
                paciente.setExcluido(resultSet.getTimestamp("excluido"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setTelefone(resultSet.getString("telefone"));
                paciente.setCelular(resultSet.getString("celular"));
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setRg(resultSet.getString("rg"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setLogin(resultSet.getString("login"));
                paciente.setSenha(resultSet.getString("senha"));
                paciente.setSexo(Sexo.valueOf(resultSet.getString("sexo")));
                paciente.setTipoPaciente(TipoPaciente.valueOf(resultSet.getString("tipoPaciente")));
                paciente.setNumeroCartaoConvenio(resultSet.getString("numeroCartaoConvenio"));
                paciente.setDataVencimento(resultSet.getTimestamp("dataVencimento"));
                ConvenioDAO convenioDAO = new ConvenioDAO();
                Convenio convenio = convenioDAO.selectByID(resultSet.getLong("convenio_id"));
                paciente.setConvenio(convenio);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }
    public void desativarPaciente(long id) {
        String sql = "UPDATE pacientes set excluido = now() where paciente_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
