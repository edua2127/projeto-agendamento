package br.com.uniamerica.escola;

import java.io.IOException;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ExcluirAlunoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	public void doPost(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse
	) throws IOException {
		
		int index = Integer.valueOf(httpServletRequest.getParameter("index"));
		
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.excluir(index);
		
		httpServletResponse.sendRedirect("listarAluno.jsp");
	}

}
