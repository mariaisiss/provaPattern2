package br.edu.ifba.inf011.aval1;

import java.util.List;

public abstract class AbstractCursoState implements CursoState {

	@Override
	public CursoState StateTo_EmAndamento() {
		System.out.println("|| ATENÇÃO || NÃO É POSSÍVEL POR O CURSO EM ANDAMENTO. ||\n");
		return this;
	}

	@Override
	public CursoState StateTo_Cancelar(Curso curso) {
		System.out.println("|| ATENÇÃO || CANCELAMENTO ABORTADO. ANTES DE CANCELAR, SUSPENDA O CURSO. ||\n");
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
		System.out.println("|| ATENÇÃO || NÃO FOI POSSÍVEL AVANÇAR ||\n");
	}

	@Override
	public Curso.Snapshot checkpoint(Curso curso){
		System.out.println("|| ATENÇÃO || NÃO FOI POSSÍVEL REALIZAR O CHECKPOINT ||\n");
		return null;
	}

	@Override
	public void restore(Curso curso, Curso.Snapshot snapshot, List<Disciplina> disciplinas) {
		System.out.println("|| ATENÇÃO || NÃO FOI POSSÍVEL FAZER O RESTORE ||\n");
	}

	@Override
	public String emitirCertificado(String nomeAluno, String nomeCurso, int cargaHoraria, Boolean honra) {
		System.out.println("|| ATENÇÃO || NÃO FOI POSSÍVEL EMITIR O CERTIFICADO ||\n");
		return null;
	}
	
	public String getState() {
		System.out.println("|| ATENÇÃO || STATE GENÉRICO ||\n");
		return null;
	}
}
