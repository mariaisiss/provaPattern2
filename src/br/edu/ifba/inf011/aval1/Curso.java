package br.edu.ifba.inf011.aval1;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import br.edu.ifba.inf011.aval1.Curso.Snapshot;
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
		public Snapshot(	Curso curso,
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
		
		public List<Disciplina> getDisciplinasSnap() {
			return disciplinasSnap;
		}

		public List<Livro> getLivrosSnap() {
			return livrosSnap;
		}

		protected void restore() {
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
	
//	Estrutura de registro dos snapshots
	private Stack<Snapshot> checks = new Stack<Curso.Snapshot>();
	
	private List<Disciplina> disciplinas;
	private List<Livro> livros;
	
//	OBSERVER estrutura
	private List<ObserverIF> observers;
	
//	STATE variável
	private CursoState state;
	
	private Curso(Curso curso) {
		super(curso);
		this.disciplinas = new LinkedList<Disciplina>();
		for(Disciplina d : curso.disciplinas)
			this.disciplinas.add((Disciplina)d.prototipar());
		this.livros = new LinkedList<Livro>();
		for(Livro l : curso.livros)
			this.livros.add((Livro)l.prototipar());
		
		this.observers = new LinkedList<ObserverIF>();
		this.state = new StateTypeEmAndamento();
		this.checks = new Stack<Curso.Snapshot>();
	}
	
	public Curso(String codigo, String nome) {
		super(codigo, nome);
		this.disciplinas = new LinkedList<Disciplina>();
		this.livros = new LinkedList<Livro>();
		
		this.observers = new LinkedList<ObserverIF>();
		this.state = new StateTypeEmAndamento();
		this.checks = new Stack<Curso.Snapshot>();
	}
	
	public Curso(String codigo, String nome, 
				 List<Disciplina> disciplinas,
				 List<Livro> livros) {
		super(codigo, nome);
		this.disciplinas = new LinkedList<Disciplina>(disciplinas);
		this.livros = new LinkedList<Livro>(livros);
		
		this.observers = new LinkedList<ObserverIF>();
		this.state = new StateTypeEmAndamento();
		this.checks = new Stack<Curso.Snapshot>();
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
	
	public Stack<Snapshot> getChecks() {
		return checks;
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
	
	public List<Disciplina> getListaDisciplinas() {
		return disciplinas;
	}
	

	public List<Livro> getListaLivros() {
		return livros;
	}

	public void avancar(String nomeDisciplina, double pctAvancar) {
		this.state.avancar(this, nomeDisciplina, pctAvancar);
	}
	
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	public int getCargaTodosCursos() {
		int cargaTotal = 0;
		
		for(Disciplina disc : this.getListaDisciplinas()) {
			cargaTotal += disc.getChTotal();
		}
		
		return cargaTotal;
	}
	
//	BEGIN: Métodos do MEMENTO
//	Gera um snapshot
	public Snapshot checkPoint() {
		
		return this.state.checkpoint(this);
//		this.dispararCheckSave(this.disciplinas);
//		return new Snapshot(this,
//								this.disciplinas,
//								this.livros,
//								this.getCodigo(),
//								this.getNome()
//		);
	}
	
	public Snapshot checkPointQ3() {
		Snapshot snapshot = this.state.checkpoint(this);
		checks.push(snapshot);
		return snapshot;
	}

//	Restaura ao snap recebido
	public void restore(Curso.Snapshot snapshot) {
		this.state.restore(this, snapshot, this.disciplinas);
	}
	
	public void restoreQ3() {
		Curso.Snapshot snapshot = this.checks.pop();
		this.state.restore(this, snapshot, this.disciplinas);
	}
	
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
	}
	
	public void dispararCheckRestore(List<Disciplina> disciplinas) {
		
		for(ObserverIF observer : this.observers) {
			observer.notificarCheckRestore(disciplinas);
		}
	}
//	END
	
//	BEGIN: Métodos State
	public void StateTo_EmAndamento(){
		this.state = this.state.StateTo_EmAndamento();
	}
	
	public void StateTo_Cancelar() {
		this.state = this.state.StateTo_Cancelar(this);
	}
	
	public void StateTo_Concluir() {
		this.state = this.state.StateTo_Concluir(this);
	}
	
	public void StateTo_Suspender() {
		this.state = this.state.StateTo_Suspender();
	}
	
	public String emitirCertificado() {
		return this.state.emitirCertificado("Fred de Alguma coisa", this.getNome(), this.getCargaTodosCursos(), true);
	}
//	END
}
