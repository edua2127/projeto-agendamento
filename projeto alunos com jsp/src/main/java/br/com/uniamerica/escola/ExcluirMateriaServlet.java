package br.com.uniamerica.escola;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ExcluirMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ExcluirMateriaServlet() {
        super();
       
    }

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int index = Integer.valueOf(request.getParameter("index"));
			
			MateriaDao materiaDao = new MateriaDao();
			materiaDao.remover(index);
			
		
			response.sendRedirect("listarMateria.jsp");
	}

}
