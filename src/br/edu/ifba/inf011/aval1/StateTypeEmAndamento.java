package br.edu.ifba.inf011.aval1;

import java.util.List;
import java.util.Stack;

import br.edu.ifba.inf011.aval1.Curso.Snapshot;

public class StateTypeEmAndamento extends AbstractCursoState implements CursoState {
	
	@Override
	public void avancar(Curso curso, String nomeDisciplina, double pctAvancar) {
		Disciplina disciplina = curso.getDisciplina(nomeDisciplina);
		
		if(disciplina == null) {
			System.out.println("Disciplina não encontrada");
		} else {
			disciplina.avancar(pctAvancar);
		}
	}

	@Override
	public Curso.Snapshot checkpoint(Curso curso) {
		
		Stack<Snapshot> checks = curso.getChecks();
		curso.dispararCheckSave(curso.getListaDisciplinas());
		
		Curso.Snapshot snapshot = curso.new Snapshot(curso,
				curso.getListaDisciplinas(),
				curso.getListaLivros(),
				curso.getCodigo(),
				curso.getNome());
		
		checks.push(snapshot);	
		return snapshot;
	}

	@Override
	public void restore(Curso curso, Curso.Snapshot snapshot, List<Disciplina> disciplinas) {
		snapshot.restore();
		curso.dispararCheckRestore(snapshot.getDisciplinasSnap());
	}
	
	@Override
	public CursoState StateTo_Concluir(Curso curso) {
		
		List<Disciplina> disciplinas = curso.getListaDisciplinas();
		boolean checkConclusao = true;
		
		for (Disciplina disc : disciplinas) {
		
			if (!disc.CheckConclusao()) {
				checkConclusao = disc.CheckConclusao();
				System.out.println("Disciplina '" + disc.getNome() + "' ainda não concluída.");
			}
				
		}
		
		if (!checkConclusao) {
			System.out.println("|| ATENÇÃO || NÃO FOI POSSÍVEL CONCLUIR. DISCIPLINA(S) AINDA EM ABERTO. ||\n");
			return this;
		}
		else {
			System.out.println("Estado do curso alterado para CONCLUÍDO. \n");
			return new StateTypeConcluido();
		}
	}

	@Override
	public CursoState StateTo_Suspender() {
		System.out.println("Estado do curso alterado para SUSPENSO. \n");
		return new StateTypeSuspenso();
	}

	public String getState() {
		return "ESTADO DO CURSO: EM ANDAMENTO.";
	}
}
