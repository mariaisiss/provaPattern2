package br.edu.ifba.inf011.aval1;

public class StateTypeSuspenso extends AbstractCursoState implements CursoState {
	
	@Override
	public Curso.Snapshot checkpoint(Curso curso) {
		curso.dispararCheckSave(curso.getListaDisciplinas());
		
		Curso.Snapshot snapshot = curso.new Snapshot(curso,
				curso.getListaDisciplinas(),
				curso.getListaLivros(),
				curso.getCodigo(),
				curso.getNome());
		
		return snapshot;
	}
	
	
	
	@Override
	public CursoState StateTo_EmAndamento() {
		System.out.println("Estado do curso alterado para EM ANDAMENTO. \n");
		return new StateTypeEmAndamento();
	}

	@Override
	public CursoState StateTo_Cancelar(Curso curso) {
		System.out.println("Estado do curso alterado para CANCELADO. \n");
		return new StateTypeCancelado();

	}

	public String getState() {
		return "ESTADO DO CURSO: SUSPENSO.";
	}
	
}
