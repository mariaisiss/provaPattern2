package br.edu.ifba.inf011.aval1;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Stack;

import br.edu.ifba.inf011.aval1.Curso.Snapshot;
import br.edu.ifba.inf011.aval1.builder.CursoBuilder;
import br.edu.ifba.inf011.aval1.builder.EmentaBuilder;
import br.edu.ifba.inf011.aval1.fm.ProdutoFactory;
import br.edu.ifba.inf011.aval1.fm.TipoProduto;
import br.edu.ifba.inf011.aval1.prototype.CatalogoCursos;


public class Aplicacao {
	
	private void rodarQ1() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Stack<Snapshot> checks = new Stack<Curso.Snapshot>();
		
		Produto prod01 = ProdutoFactory.novoProduto(TipoProduto.LIVRO, "BOOK_CRIA01", "Factory Method");
		Produto prod02 = ProdutoFactory.novoProduto(TipoProduto.DISCIPLINA, "DISC_CRIA01", "Factory Method");
		Produto prod03 = ProdutoFactory.novoProduto(TipoProduto.DISCIPLINA, "DISC_CRIA02","Builder");

		
		Curso curso = CursoBuilder.factory()
								  .reset()
								  .addDisciplina((Disciplina)prod03)
								  .addLivro((Livro)prod01)
								  .setNome("Padrões Criacionais")
								  .setCodigo("CURSO_CRIALL")
								  .addDisciplina((Disciplina)prod02)
								  .build();
		

		
		curso.getDisciplina("Factory Method").setChTotal(100);
		curso.getDisciplina("Factory Method").setPcCumprido(20);
		CatalogoCursos.getCatalogo().registrar(curso);
		Curso cursoA = CatalogoCursos.getCatalogo().recuperar("CURSO_CRIALL");
		cursoA.addObserver(new NotifyByConsole("CONSOLE"));
		
		Curso cursoB = CatalogoCursos.getCatalogo().recuperar("CURSO_CRIALL");
		
//		execução
		checks.push(cursoA.checkPoint());		
		System.out.println("Percentual da disciplina " 	+ cursoA.getDisciplina("Factory Method").getNome()
														+ " é igual a "
														+ cursoA.getDisciplina("Factory Method").getPcCumprido() + ".\n");
		
//		avançando percentual da disciplina
		cursoA.avancar("Factory Method", 0.05);
		checks.push(cursoA.checkPoint());
		
		cursoA.avancar("Factory Method", 0.10);
		
		System.out.println("Percentual da disciplina " 	+ cursoA.getDisciplina("Factory Method").getNome()
														+ " é igual a "
														+ cursoA.getDisciplina("Factory Method").getPcCumprido() + ".\n");
		cursoA.restore(checks.pop());
		System.out.println("Percentual da disciplina após restore do checkpoint " 	+ cursoA.getDisciplina("Factory Method").getNome()
				+ " é igual a "
				+ cursoA.getDisciplina("Factory Method").getPcCumprido() +".\n");
		
	}	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Aplicacao app = new Aplicacao();
		app.rodarQ1();
		
	}

}
