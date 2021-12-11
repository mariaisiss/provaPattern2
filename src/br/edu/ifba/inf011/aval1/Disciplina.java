package br.edu.ifba.inf011.aval1;

import br.edu.ifba.inf011.aval1.prototype.Prototipavel;

public class Disciplina extends Produto{
	private int chTotal;
	private double pcCumprido;
	private double preco;

	public Disciplina(Disciplina disciplina) {
		super(disciplina);
		this.chTotal = disciplina.chTotal;
		this.pcCumprido = disciplina.pcCumprido;
		this.preco = disciplina.preco;
	}	
	
	public Disciplina(String codigo, String nome) {
		super(codigo, nome);
	}
	
	public Disciplina(String codigo, String nome, 
					  int chTotal,
					  double pcCumprido, double preco) {
		super(codigo, nome);
		this.chTotal = chTotal;
		this.pcCumprido = pcCumprido;
		this.preco = preco;
	}

	@Override
	public double getPreco() {
		return this.preco;
	}
	
	public String toString() {
		return "#Disciplina#" + super.toString();
	}

	@Override
	public Prototipavel prototipar() {
		return new Disciplina(this);
	}

	public double getPcCumprido() {
		return pcCumprido;
	}

	public void setPcCumprido(double pcCumprido) {
		if (pcCumprido <= this.chTotal) {
			this.pcCumprido = pcCumprido;
		} else {
			System.out.println("Percentual cumprido maior que a carga horária total.");
		}
		
	}

	public int getChTotal() {
		return chTotal;
	}

	public void setChTotal(int chTotal) {
		this.chTotal = chTotal;
	} 
	
	public void avancar(double pctAvancar) {
		if (pctAvancar > 0) {
			this.pcCumprido = this.chTotal * pctAvancar + this.pcCumprido;
			System.out.println("\nDisciplina '" + this.getNome() + "' avançada em " + (pctAvancar*100) + " porcento.");
		} else {
			System.out.println("Não é possível avançar. Percentual inválido.");
		}
	}
	
}
