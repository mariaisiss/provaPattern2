package br.edu.ifba.inf011.aval1;

public class StateTypeCancelado extends AbstractCursoState implements CursoState {
	
//AP�S CANCELAMENTO, N�O FAZ MAIS NADA.
	public String getState() {
		return "ESTADO DO CURSO: CANCELADO";
	}
}
