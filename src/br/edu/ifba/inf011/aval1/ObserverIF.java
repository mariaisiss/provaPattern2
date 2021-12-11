package br.edu.ifba.inf011.aval1;

import java.util.List;

//OBSERVER (observer) 
public interface ObserverIF {
	
	public void notificarCheckSave(List<Disciplina> disciplinas);
	public void notificarCheckRestore(List<Disciplina> disciplinas);
	public String getNome();
}
