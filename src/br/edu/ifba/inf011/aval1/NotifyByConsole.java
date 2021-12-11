package br.edu.ifba.inf011.aval1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// CONCRETEOBSERVER (observer)
public class NotifyByConsole implements ObserverIF {
	
	String nome;

	public NotifyByConsole(String nome) {
		this.nome = new String(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void notificarCheckSave(List<Disciplina> disciplinas) {
		
		SimpleDateFormat fd = new SimpleDateFormat("hh:mm:ss:SSSS");
		String data = fd.format(new Date()); 
		
		for (Disciplina disc : disciplinas) {
			System.err.println("[CONSOLE -> " 	+ data + "] "
									+ "Checkpoint salvo. " 
									+ " Disciplina: " 
									+ disc.getNome() 
									+ " | Carga Horária Cursada: " 
									+ disc.getPcCumprido()
			);
		}
		
	}

	@Override
	public void notificarCheckRestore(List<Disciplina> disciplinas) {
		
		SimpleDateFormat fd = new SimpleDateFormat("hh:mm:ss:SSSS");
		String data = fd.format(new Date()); 
		
		for (Disciplina disc : disciplinas) {
			System.err.println("[CONSOLE -> "+ data + "] " 
									+ "Checkpoint restaurado. " 
									+ " Disciplina: " 
									+ disc.getNome() 
									+ " | Carga Horária Cursada: " 
									+ disc.getPcCumprido()
			);
		}
		
	}
	
}
