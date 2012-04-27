package ufcg.edu.br.Sistema120121.sistemy;

public class PontoDeEncontro {

	
	private String pontoDeEcontro;
	public enum Situacao{SIM,NAO,ESPERA,MUDANCA};
	private Situacao avaliacao;
		
	public PontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEcontro = pontoDeEncontro;
		avaliacao = Situacao.ESPERA;
	}
	
	/**
	 * Retorna o ponto de encontro
	 * @return
	 * 		O ponto de encontro.
	 */
	public String getPontoDeEcontro() {
		return pontoDeEcontro;
	}

	/**
	 * Altera o local do ponto de encontro.
	 * @param pontoDeEcontro
	 * 		O novo ponto de encontro.
	 * @throws Exception
	 * 		Se o novo ponto de encontro for vazio.
	 */
	public void alterarPontoDeEncontro(String pontoDeEcontro)throws Exception {
		if(pontoDeEcontro.isEmpty()) throw new Exception("O novo ponto de encontro não pode ser vazio");
		this.pontoDeEcontro = pontoDeEcontro;
		this.avaliacao = Situacao.MUDANCA;
	}

	/**
	 * A situacao da carona,se ela ja foi avaliada ou não.
	 * @return
	 * 		A situação da carona
	 */
	public Situacao getAvaliacao() {
		return avaliacao;
	}

	/**
	 * Avalia o ponto de encontro.
	 * @param escolha
	 * 		true - caso o ponto de encontro seja confirmado.
	 * 		false - caso o ponto de econtro seja recusado.
	 */
	public void avaliarPonto(boolean escolha){
		if(escolha) avaliacao = Situacao.SIM; else avaliacao = Situacao.NAO;
	}
	
	public String toString(){
		return "Local do encontro: " + pontoDeEcontro + "Situacao da avaliacao: " + avaliacao;
	}
	
	
	
	
	
	
	
}
