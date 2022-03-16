package br.com.projeto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AlunoExcluir extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AlunoDao alunoDao = new AlunoDao();
  
    public AlunoExcluir() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int indexExcluir = Integer.valueOf(request.getParameter("index-excluir"));
		alunoDao.removerAluno(indexExcluir);
		response.sendRedirect("/projeto_alunos/ListarAlunos");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
