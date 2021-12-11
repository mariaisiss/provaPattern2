package br.edu.ifba.inf011.aval1;

public class StateTypeSuspenso extends AbstractCursoState implements CursoState {
	
	@Override
	public CursoState checkpoint() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getState() {
		return "ESTADO DO CURSO: SUSPENSO.";
	}
}
