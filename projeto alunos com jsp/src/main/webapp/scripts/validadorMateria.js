/**
 * 
 */
 
  function validar() {
	let nome = formularioContato.nome.value
	let nome_prof = formularioContato.nome_prof.value
	let carga_horaria = formularioContato.carga_horaria.value
	
	if(nome === "") {
		alert('Preencha o campo nome')
		formularioContato.nome.focus
		return false
	} else if(nome_prof === "") {
		alert('Preencha o campo turma')
		formularioContato.nome.focus
		return false
	} else if(carga_horaria === ""){
		alert('Preencha o campo carga horaria')
		formularioContato.nome.focus
		return false
	} else {
		document.forms["formularioContato"].submit()
	}
}