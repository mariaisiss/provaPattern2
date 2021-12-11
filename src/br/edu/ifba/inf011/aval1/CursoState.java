package br.edu.ifba.inf011.aval1;

import java.util.List;

public interface CursoState {
//	public double executar(double valor, List<Double> historico, double setpoint, double ganho, ControladorHandler handler);
//	public void restore(Snapshot snapshot);
	public CursoState avancar();
	public CursoState checkpoint();
	public void restore(Curso.Snapshot snapshot);
	public CursoState certificado();
	public String getState();
}
