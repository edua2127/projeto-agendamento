package br.com.uniamerica.escola;

import java.util.ArrayList;

public class AlunoDAO {
	
	
	public static ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	public static Aluno aluno = new Aluno();
	public static int index = -1;
	
	
	public AlunoDAO() {}
	
	
	
	public void cadastrar(Aluno aluno) {
		alunos.add(aluno);
	}
	
	public void atualizar(int index, Aluno aluno) {
		alunos.set(index, aluno);
	}
	
	public void excluir(int index) {
		alunos.remove(index);
	}
	
	public Aluno findByIndex(int index) {
		return alunos.get(index);
	}
}
