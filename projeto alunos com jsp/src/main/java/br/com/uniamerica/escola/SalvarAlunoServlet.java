package br.com.uniamerica.escola;

import java.io.IOException;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class SalvarAlunoServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	
	public void doPost(
			HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse
	) throws IOException {
		
		Aluno aluno = new Aluno();
		AlunoDAO alunoDAO = new AlunoDAO();
		
		
		int index = -1;
		
		if (httpServletRequest.getParameter("index") != null) {
			index = Integer.valueOf(httpServletRequest.getParameter("index"));
		}
		
		aluno.setNome(httpServletRequest.getParameter("nome"));
		aluno.setTurma(httpServletRequest.getParameter("turma"));
		
		
		if (index >= 0 ) {
			alunoDAO.atualizar(index, aluno);	
		}
		else {
			alunoDAO.cadastrar(aluno);
		}
		
		
		httpServletResponse.sendRedirect("listarAluno.jsp");
	}
}
