package br.edu.ifba.inf011.aval1;

import java.util.List;

public interface CursoState {
	
//	M�todos para mudan�a de state
	public CursoState StateTo_EmAndamento();
	public CursoState StateTo_Cancelar(Curso curso);
	public CursoState StateTo_Concluir(Curso curso);
	public CursoState StateTo_Suspender();

//	M�todos de a��o em STATE
	public void avancar(Curso curso, String nomeDisciplina, double pctAvancar);
	public Curso.Snapshot checkpoint(Curso curso);
	public void restore(Curso curso, Curso.Snapshot snapshot, List<Disciplina> disciplinas);
	public String emitirCertificado(String nomeAluno, String nomeCurso, int cargaHoraria, Boolean honra);
	public String getState();
}
