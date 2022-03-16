<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="height: 100%; justify-content: center; display: flex;">
		
			<form action="SalvarMateriaServlet" method="post" 
					style="width: 300px; 
						border: 1px solid #009a84;
						padding: 15px;
						border-radius: 10px 10px 10px 10px;" name = "formularioContato">
						
				<h1 style="text-align: center; color: #009a84;">Cadastrar Materia</h1>
				
				<hr> <br />
			
				<label style="color: #009a84">Nome: </label><br />
				<input type="text" name="nome" style="width: 100%">
				
				<br /><br />
				
				<label style="color: #009a84">Nome do Professor: </label> <br />
				<input type="text" name="nome_prof" style="width: 100%">
							
				<br /><br /><br />
				
				<label style="color: #009a84">carga horaria:  </label> <br />
				<input type="text" name="carga_horaria" style="width: 100%">	
						
				<br /><br /><br />
				<div style="width: 100%;">
					<div style="width: 50%; float: left;">
						
						<a href="listarMateria.jsp" style="
								background: #009a84;
							    font-size: 16px;
							    color: white;
							    width: 100%;
							    text-align: center;
							    padding: 10px 10px 10px 10px;
							    text-decoration: auto;
							    border-radius: 10px 10px 10px 10px;"> 
							Listar Materias
						</a>
						
					</div>
					<div style="width: 50%; float: right;">
						<input type="button" value="Cadastrar"
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
			<script src="scripts/validadorMateria.js"></script>
		</div>
</body>
</html>