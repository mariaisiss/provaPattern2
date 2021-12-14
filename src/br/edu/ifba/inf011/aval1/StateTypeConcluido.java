package br.edu.ifba.inf011.aval1;

public class StateTypeConcluido extends AbstractCursoState implements CursoState {
	
	@Override
	public String getState() {
		return "ESTADO DO CURSO: CONCLUIDO";
	}
	
	@Override
	public String emitirCertificado(String nomeAluno, String nomeCurso, int cargaHoraria, Boolean honra) {
		
		Certificado certificado = new Certificado(nomeAluno, nomeCurso, cargaHoraria, honra);
		System.err.println(certificado.emitirCertificado());
		return certificado.emitirCertificado();
	}
}
