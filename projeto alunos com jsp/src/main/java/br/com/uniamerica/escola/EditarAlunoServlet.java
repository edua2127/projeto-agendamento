package br.com.uniamerica.escola;



import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EditarAlunoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	AlunoDAO alunoDAO = new AlunoDAO();
	
	
	public void doPost(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse
	) throws IOException {
		
		AlunoDAO.index = Integer.valueOf(httpServletRequest.getParameter("index"));
		AlunoDAO.aluno = alunoDAO.findByIndex(AlunoDAO.index);
		
		httpServletResponse.sendRedirect("editarAluno.jsp");
	}

}
