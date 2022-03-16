<%@page import="br.com.uniamerica.escola.Aluno"%>
<%@page import="br.com.uniamerica.escola.AlunoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Editar Aluno</title>
	</head>
	<body>
	
		<div style="height: 100%; justify-content: center; display: flex;">
		
			<form action="SalvarAlunoServlet" method="post" 
					style="width: 300px; 
						border: 1px solid #009a84;
						padding: 15px;
						border-radius: 10px 10px 10px 10px;" name = "formularioContato">
						
				<h1 style="text-align: center; color: #009a84;">Editar Aluno</h1>
				
				<hr> <br />
				
				<% Aluno aluno = AlunoDAO.aluno; int index = AlunoDAO.index; %>
				
				<label style="color: #009a84">ID: </label><br />
				<input type="text" name="index" value="<%= index %>" style="width: 100%">
			
				<br /> <br />
			
				<label style="color: #009a84">Nome: </label><br />
				<input type="text" name="nome" value="<%= aluno.getNome() %>" style="width: 100%">
				
				<br /><br />
				
				<label style="color: #009a84">Turma: </label> <br />
				<input type="text" name="turma" value="<%= aluno.getTurma() %>" style="width: 100%">
							
				<br /><br /><br />
							
				
				<div style="width: 100%;">
					<div style="width: 50%; float: left;">
						
						<a href="listarAluno.jsp" style="
								background: #009a84;
							    font-size: 16px;
							    color: white;
							    width: 100%;
							    text-align: center;
							    padding: 10px 10px 10px 10px;
							    text-decoration: auto;
							    border-radius: 10px 10px 10px 10px;"> 
							Listar Alunos
						</a>
						
					</div>
					<div style="width: 50%; float: right;">
						<input type="button" value="Editar"
							style="
							    background: #009a84;
							    font-size: 16px;
							    color: white;
							    width: 100%;
							    text-align: center;
							    padding: 8px 10px 8px 10px;
							    text-decoration: auto;
							    border-radius: 10px 10px 10px 10px;
							    margin-top: -10px;" onclick="validar()">
					</div>	
				</div>
				
			</form>
			<script src="scripts/validadorEditar.js"></script>
		</div>
	
	</body>
</html>