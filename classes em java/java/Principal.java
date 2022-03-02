import dao.*;
import model.Agenda;
import model.Especialidacao;
import model.Historico;
import model.Secretaria;

import java.sql.Timestamp;

public class Principal {
    public static void main(String[] args) {
        SecretariaDAO secretariaDAO = new SecretariaDAO();
        secretariaDAO.criarTabela();
        EspecializacaoDAO especializacaoDAO = new EspecializacaoDAO();
        especializacaoDAO.createTable();
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.createTable();
        ConvenioDAO convenioDAO = new ConvenioDAO();
        convenioDAO.createTable();
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.criarTabela();
        AgendaDAO agendaDAO = new AgendaDAO();
        agendaDAO.criarTabela();
        HistoricoDAO historicoDAO = new HistoricoDAO();
        historicoDAO.criarTabela();
        //inherits -> comando do sql que herda de outra tabela
//        Especialidacao especialidacao = new Especialidacao();
//        especialidacao.setId(1l);
//        especialidacao.setNome("engenharia de software");
//        especialidacao.setCadastro(Timestamp.valueOf("2055-05-18 05:33:26"));
//        especialidacao.setAtualizado(Timestamp.valueOf("2016-05-18 05:33:26"));
//        especialidacao.setExcluido(Timestamp.valueOf("2002-05-18 05:33:26"));
//        especializacaoDAO.editarEspecialacao(especialidacao);
    }
}
