package br.com.projeto;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {
	static List<AlunoModel> alunos = new ArrayList<AlunoModel>();
	
	public List<AlunoModel> listar() {
		return alunos;
	}
	
	public void removerAluno(int index) {
		alunos.remove(index);
	}
	public void adicionarAluno(AlunoModel aluno) {
		alunos.add(aluno);
	}
	
	public AlunoModel selectById(int index) {
		return alunos.get(index);
	}
	public void alunoEditar(int index, AlunoModel alunoModel) {
		alunos.set(index, alunoModel);
		
	}
}
