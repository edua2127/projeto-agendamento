package br.com.uniamerica.escola;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class EditarMateriaServlet
 */
public class EditarMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditarMateriaServlet() {
        super();
       
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MateriaDao.index = Integer.valueOf(request.getParameter("index"));
		MateriaDao.materia = MateriaDao.findByIndex(MateriaDao.index);
		
		response.sendRedirect("editarMateria.jsp");
		
	}

}
