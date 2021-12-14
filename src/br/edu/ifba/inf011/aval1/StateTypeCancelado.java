package br.edu.ifba.inf011.aval1;

public class StateTypeCancelado extends AbstractCursoState implements CursoState {
	
//APÓS CANCELAMENTO, NÃO FAZ MAIS NADA.
	public String getState() {
		return "ESTADO DO CURSO: CANCELADO";
	}
}
