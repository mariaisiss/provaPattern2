package br.edu.ifba.inf011.aval1;

public class StateTypeConcluido extends AbstractCursoState implements CursoState {
	
	@Override
	public String getState() {
		return "ESTADO DO CURSO: CONCLUIDO";
	}
	
	@Override
	public CursoState certificado() {
		// TODO Auto-generated method stub
		return null;
	}
}
