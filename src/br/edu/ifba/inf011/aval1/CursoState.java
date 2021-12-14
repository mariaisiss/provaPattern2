package br.edu.ifba.inf011.aval1;

import java.util.List;

// Declara métodos específicos para os estados focando naqueles que tenham comportamento específico ao estado

public interface CursoState {
	
//	Métodos para mudança de state
	public CursoState StateTo_EmAndamento();
	public CursoState StateTo_Cancelar(Curso curso);
	public CursoState StateTo_Concluir(Curso curso);
	public CursoState StateTo_Suspender();

//	Métodos de ação em STATE
	public void avancar(Curso curso, String nomeDisciplina, double pctAvancar);
	public Curso.Snapshot checkpoint(Curso curso);
	public void restore(Curso curso, Curso.Snapshot snapshot, List<Disciplina> disciplinas);
	public String emitirCertificado(String nomeAluno, String nomeCurso, int cargaHoraria, Boolean honra);
	public String getState();
}
