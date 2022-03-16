/**
 * 
 */
  function validar() {
	let nome = formularioContato.nome.value
	let turma = formularioContato.turma.value
	let index = formularioContato.index.value
	if(index === "") {
		alert('Preencha o campo id')
		formularioContato.index.focus
		return false
	}
	if(nome === "") {
		alert('Preencha o campo nome')
		formularioContato.nome.focus
		return false
	} else if(turma === "") {
		alert('Preencha o campo turma')
		formularioContato.nome.focus
		return false
	} else {
		document.forms["formularioContato"].submit()
	}
}