package br.com.projeto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class ListarAlunos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ListarAlunos() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(this.corpoPagina());
	}

	public String corpoPagina() {
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<style>\r\n"
				+ "table, th, td {\r\n"
				+ "  border:1px solid black;\r\n"
				+ "}\r\n"
				+ "</style>";
		html += "<meta charset=\"ISO-8859-1\">";
		html += "<title>listagem de alunos</title>";
		
		html += "<l®ink rel=\"stylesheet\" href=\"style.css\">";
		html += "</head>";
		html += "<body>";
		html += "<div class = \"centralizador\">";
		html += "	<a href=\"http://localhost:8080/projeto_alunos/cadastrarAluno.html\"><button>novo aluno</button></a>";
		html += "<table style=\"width:100%\">\r\n"
				+ "	<tr>\r\n"
				+ "		 	<th>id aluno</th>\r\n"
				+ "			<th>nome completo do aluno</th>\r\n"
				+ "			<th>turma do aluno</th>\r\n"
				+ "	</tr>";
		AlunoDao alunoDao = new AlunoDao();
		List<AlunoModel> alunos = alunoDao.listar();
		for(int i = 0; i < alunos.size(); i ++) {
			html += "<tr>";
			html += "<td>" + i +"</td>";
			html += "<td>" + alunos.get(i).getNome() +"</td>";
			html += "<td>" + alunos.get(i).getTurma() +"</td>";;
			html += "<td>";
			html += "<form method=\"get\" action=\"AlunoExcluir\">";
			html += "<input type = \"hidden\" value = \"" + i + "\" name = \"index-excluir\">";
			html += "<input type = \"submit\" value = \"excluir\" >";
			html += "</form>";
			
			html += "<form method=\"get\" action=\"AlunoEditar\">";
			html += "<input type = \"hidden\" value = \"" + i + "\" name = \"index-editar\">";
			html += "<input type = \"submit\" value = \"editar\" >";
			html += "</form>";
			html += "</td>";
			html += "</tr>";
		}
		html +="</table>";
		html += "</div>";
		html += "</body>";
		html += "</html>";
		
		
		html += "";
		return html;
	}
}
