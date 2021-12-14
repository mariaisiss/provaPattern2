package br.edu.ifba.inf011.aval1;

public class Certificado {
	
	private String nomeAluno;
	private String curso;
	private int cargaHTotal;
	private boolean honraAoMerito; 
	
	public Certificado(	String nomeAluno,
						String curso,
						int cargaHTotal,
						boolean honraAoMerito ) {
		
		this.nomeAluno = nomeAluno;
		this.curso = curso;
		this.cargaHTotal = cargaHTotal;
		this.honraAoMerito = honraAoMerito;
	}
	
	public String emitirCertificado() {
		
		String certificado = new String();
		
		certificado = 	"#############\n" + 
						"CERTIFICAMOS QUE O ALUNO\n\n" + 
						this.nomeAluno + 
						"\n\nCONCLUIU O CURSO\n\n" + 
						this.curso + 
						"\n\nTENDO CONCLUIDO AS " +
						this.cargaHTotal + 
						" HORAS TOTAIS DO CURSO";
		
		if (honraAoMerito)
			certificado = certificado.concat(" COM HONRAS AOS MÉRITO");
			
		certificado = certificado.concat(".\n\n#############\n");
		
		return certificado;
	}
}
