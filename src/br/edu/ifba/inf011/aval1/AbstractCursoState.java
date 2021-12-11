package br.edu.ifba.inf011.aval1;

import br.edu.ifba.inf011.aval1.Curso.Snapshot;

public abstract class AbstractCursoState implements CursoState {

	@Override
	public CursoState avancar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CursoState checkpoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void restore(Curso.Snapshot snapshot) {
		// TODO Auto-generated method stub
	}

	@Override
	public CursoState certificado() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}
}
