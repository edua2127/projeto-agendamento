package br.com.uniamerica.escola;

import java.util.ArrayList;


public class MateriaDao {
	
	public static ArrayList<Materia> materias = new ArrayList<Materia>();
	public static Materia materia = new Materia();
	public static int index = -1;
	
	
	public MateriaDao() {
		
	}
	
	
	public static Materia getMateria() {
		return materia;
	}
	public static void setMateria(Materia materia) {
		MateriaDao.materia = materia;
	}
	public static int getIndex() {
		return index;
	}
	public static void setIndex(int index) {
		MateriaDao.index = index;
	}
	
	public void adicionar(Materia materia) {
		materias.add(materia);
	}
	public void remover(int index) {
		materias.remove(index);
	}
	public void atualizar(int index, Materia materia) {
		materias.set(index, materia);
	}
	public static Materia findByIndex(int index) {
		return materias.get(index);
	}
}	
