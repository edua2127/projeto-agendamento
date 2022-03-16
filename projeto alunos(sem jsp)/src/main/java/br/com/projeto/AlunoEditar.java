package br.com.projeto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AlunoEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    AlunoDao alunoDao = new AlunoDao();
    public AlunoEditar() {
        super();
      
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    	int indexEditar = Integer.valueOf(request.getParameter("index-editar"));
    	AlunoModel alunoModel = alunoDao.selectById(indexEditar);
    	PrintWriter out = response.getWriter();
    	out.println(this.corpoEditar(alunoModel, indexEditar));
    }
    
    
    public String corpoEditar(AlunoModel alunoModel, int index) {

    	
    	String html = "";
    	html += "<!DOCTYPE html>";
    	html += "<html>";
    	html += "<head>";
    	html += "<title>pagina de edicao de dados</title>";
    	html += "<link rel=\"stylesheet\" href=\"style.css\">";
    	html += "</head>";
    	html += "<body>";
    	html += "	<div class = \"centralizador\">";
    	html += "				<form method=\"post\" action=\"AlunoEditar\" name = \"formularioContato\">";
    	html += "					<label>nome completo</label>";
    	html += "							<input type=\"text\" name=\"nome\" value = \""+alunoModel.getNome()+"\">";
    	html += "					<br>";
    	html += "					<label>turma</label>";
    	html += "							<input type=\"text\" name=\"turma\" value = \""+alunoModel.getTurma()+"\">";
    	html += "					<br>";
    	html += "							<input type=\"hidden\" name=\"index-editar\" value =\""+index+"\">";
    	html += "							<input type=\"button\" value = \"editar\" onclick=\"validar()\" >";
    	html += "				</form>";
    	html += "<script src=\"scripts/validador.js\"></script>";
    	html += "	</div>";
    	html += "</body>";
    	html += "</html>";
    	return html;
    }
  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	AlunoModel alunoModel = new AlunoModel();
    	
    	int index = Integer.valueOf(req.getParameter("index-editar"));
    	alunoModel.setNome(req.getParameter("nome"));
    	alunoModel.setTurma(req.getParameter("turma"));
    	
    	if(alunoModel.getNome().equals("")) {
    		resp.sendRedirect("/projeto_alunos/ListarAlunos");
    		System.out.println("campo nome vazio");
    	} else if(alunoModel.getTurma().equals("")) {
    		System.out.println("campo turma vazio");
    		resp.sendRedirect("/projeto_alunos/ListarAlunos");
    		
    	} else {
    		alunoDao.alunoEditar(index, alunoModel);
    		resp.sendRedirect("/projeto_alunos/ListarAlunos");
    	}
  
    }
}
