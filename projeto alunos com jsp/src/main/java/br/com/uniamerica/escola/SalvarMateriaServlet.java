package br.com.uniamerica.escola;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SalvarMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SalvarMateriaServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Materia materia = new Materia();
		MateriaDao materiaDao = new MateriaDao();
		
		int index = -1;
		
		if (request.getParameter("index") != null) {
			index = Integer.valueOf(request.getParameter("index"));
		}
		
		materia.setNome(request.getParameter("nome"));
		materia.setNomeProfessor(request.getParameter("nome_prof"));
		materia.setCargaHoraria(request.getParameter("carga_horaria"));
		
		if (index >= 0 ) {
			materiaDao.atualizar(index, materia);
		}
		else {
			materiaDao.adicionar(materia);
		}
		response.sendRedirect("listarMateria.jsp");
	}

}
