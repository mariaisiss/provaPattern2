package br.edu.ifba.inf011.aval1;

import java.util.List;

public abstract class AbstractCursoState implements CursoState {

	@Override
	public CursoState StateTo_EmAndamento() {
		System.out.println("|| ATEN��O || N�O � POSS�VEL POR O CURSO EM ANDAMENTO. ||\n");
		return this;
	}

	@Override
	public CursoState StateTo_Cancelar(Curso curso) {
		System.out.println("|| ATEN��O || CANCELAMENTO ABORTADO. ANTES DE CANCELAR, SUSPENDA O CURSO. ||\n");
		return this;
	}

	@Override
	public CursoState StateTo_Concluir(Curso curso) {
		return this;
	}

	@Override
	public CursoState StateTo_Suspender() {
		return this;
	}

	@Override
	public void avancar(Curso curso, String nomeDisciplina, double pctAvancar) {
		System.out.println("|| ATEN��O || N�O FOI POSS�VEL AVAN�AR ||\n");
	}

	@Override
	public Curso.Snapshot checkpoint(Curso curso){
		System.out.println("|| ATEN��O || N�O FOI POSS�VEL REALIZAR O CHECKPOINT ||\n");
		return null;
	}

	@Override
	public void restore(Curso curso, Curso.Snapshot snapshot, List<Disciplina> disciplinas) {
		System.out.println("|| ATEN��O || N�O FOI POSS�VEL FAZER O RESTORE ||\n");
	}

	@Override
	public String emitirCertificado(String nomeAluno, String nomeCurso, int cargaHoraria, Boolean honra) {
		System.out.println("|| ATEN��O || N�O FOI POSS�VEL EMITIR O CERTIFICADO ||\n");
		return null;
	}
	
	public String getState() {
		System.out.println("|| ATEN��O || STATE GEN�RICO ||\n");
		return null;
	}
}
