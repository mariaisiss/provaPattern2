package br.edu.ifba.inf011.aval1;

import java.util.LinkedList;
import java.util.List;
import br.edu.ifba.inf011.aval1.prototype.Prototipavel;	

//CONCRETESUBJECT (Observer)
public class Curso extends Produto{
	
	public class Snapshot {
		
		private Curso cursoSnap;
		private List<Disciplina> disciplinasSnap;
		private List<Livro> livrosSnap;
		private String codigoSnap;
		private String nomeSnap;
		
		//MEMENTO em um Memento
		private Snapshot(	Curso curso,
							List<Disciplina> disciplinas,
							List<Livro> livros,
							String codigo,
							String nome) {
			this.cursoSnap = curso;
			this.disciplinasSnap = new LinkedList<Disciplina>();
			for (Disciplina disc : disciplinas) {
				Disciplina newDisc = new Disciplina(disc);
				disciplinasSnap.add(newDisc);
			}
			
			this.livrosSnap = new LinkedList<Livro>(livros);
			for (Livro liv : livros) {
				Livro newLiv = new Livro(liv);
				livrosSnap.add(newLiv);
			}
			
			this.codigoSnap = codigo;
			this.nomeSnap = nome;
		}
		
		private void restore() {
			this.cursoSnap.setNome(nomeSnap);
			this.cursoSnap.setCodigo(codigoSnap);
			
			this.cursoSnap.disciplinas = new LinkedList<Disciplina>(this.disciplinasSnap);			
			this.cursoSnap.livros = new LinkedList<Livro>(this.livrosSnap);
		}
		
		public Disciplina getDisciplina(String nomeDisciplina) {
			Disciplina disciplina = null;
			
			for (Disciplina eachDisciplina : this.disciplinasSnap) {
				if (eachDisciplina.getNome() == nomeDisciplina) {
					return eachDisciplina;
				}
			}
			
			return disciplina;
		}
	}
//	Fim do memento
	
	private List<Disciplina> disciplinas;
	private List<Livro> livros;
	private List<ObserverIF> observers;
	
	private Curso(Curso curso) {
		super(curso);
		this.disciplinas = new LinkedList<Disciplina>();
		for(Disciplina d : curso.disciplinas)
			this.disciplinas.add((Disciplina)d.prototipar());
		this.livros = new LinkedList<Livro>();
		for(Livro l : curso.livros)
			this.livros.add((Livro)l.prototipar());
		this.observers = new LinkedList<ObserverIF>();
	}
	
	public Curso(String codigo, String nome) {
		super(codigo, nome);
		this.disciplinas = new LinkedList<Disciplina>();
		this.livros = new LinkedList<Livro>();
		this.observers = new LinkedList<ObserverIF>();
	}
	
	public Curso(String codigo, String nome, 
				 List<Disciplina> disciplinas,
				 List<Livro> livros) {
		super(codigo, nome);
		this.disciplinas = new LinkedList<Disciplina>(disciplinas);
		this.livros = new LinkedList<Livro>(livros);
		this.observers = new LinkedList<ObserverIF>();
	}	
	
	@Override
	public double getPreco() {
		return 0;
	}
	
	public String toString() {
		return "#Curso#" + super.toString();
	}

	@Override
	public Prototipavel prototipar() {
		return new Curso(this);
	}
	
	public Disciplina getDisciplina(String nomeDisciplina) {
		Disciplina disciplina = null;
		
		for (Disciplina eachDisciplina : this.disciplinas) {
			if (eachDisciplina.getNome() == nomeDisciplina) {
				return eachDisciplina;
			}
		}
		
		return disciplina;
	}

	public void avancar(String nomeDisciplina, double pctAvancar) {
		Disciplina disciplina = this.getDisciplina(nomeDisciplina);
		
		if(disciplina == null) {
			System.out.println("Disciplina não encontrada");
		} else {
			disciplina.avancar(pctAvancar);
		}
	}
	
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
//	BEGIN: Métodos do MEMENTO
//	Gera um snapshot
	public Snapshot checkPoint() {
		
		this.dispararCheckSave(this.disciplinas);
		return new Snapshot(this,
								this.disciplinas,
								this.livros,
								this.getCodigo(),
								this.getNome()
		);
	}

//	Restaura ao snap recebido
	public void restore(Snapshot snapshot) {
		snapshot.restore();
		this.dispararCheckRestore(this.disciplinas);
	}
//	END
	
//	BEGIN: Métodos do OBSERVER
//	Adiciona um OBSERVER ao vetor de observer
	public void addObserver(ObserverIF observer) {
		this.observers.add(observer); 
		
	}

//	Remove um OBSERVER ao vetor de observer
	public void removeObserver(ObserverIF observer) {
		this.observers.remove(observer); 
	}

//	Notifica os observadores
	public void dispararCheckSave(List<Disciplina> disciplinas) {

		for(ObserverIF observer : this.observers) {
			observer.notificarCheckSave(disciplinas);
		}
//		END	
		
	}
	
	public void dispararCheckRestore(List<Disciplina> disciplinas) {
		
		for(ObserverIF observer : this.observers) {
			observer.notificarCheckRestore(disciplinas);
		}
			
	}

}
