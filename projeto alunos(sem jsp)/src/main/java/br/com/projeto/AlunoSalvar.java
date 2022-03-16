package br.com.projeto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AlunoSalvar extends HttpServlet {
	private static final long serialVersionUID = 1L;
      AlunoDao alunoDao = new AlunoDao();
   
    public AlunoSalvar() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AlunoModel alunoModel = new AlunoModel();
		alunoModel.setNome(request.getParameter("nome"));
		alunoModel.setTurma(request.getParameter("turma"));	
		if(alunoModel.getNome().equals("")) {
			System.out.println("campo nome vazio");
			response.sendRedirect("/projeto_alunos/ListarAlunos");
		} else if (alunoModel.getTurma().equals("")) {
			System.out.println("campo turma vazio");
			response.sendRedirect("/projeto_alunos/ListarAlunos");
		} else {
			alunoDao.adicionarAluno(alunoModel);
			response.sendRedirect("/projeto_alunos/ListarAlunos");
		}
	}

}
