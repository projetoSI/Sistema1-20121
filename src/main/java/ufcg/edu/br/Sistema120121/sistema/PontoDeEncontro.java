package ufcg.edu.br.Sistema120121.sistema;

import java.util.LinkedList;
import java.util.List;

public class PontoDeEncontro {

	
	private String sugestaoAtual;
	private List<String> blackList = new LinkedList<String>(); 
	private String pontoValido;
		
	public PontoDeEncontro() {
	}
	
	/**
	 * Retorna o ponto de encontro
	 * @return
	 * 		O ponto de encontro.
	 */
	public String getPontoDeEcontro() {
		return pontoValido;
	}
	
	public void aceitar(){
		pontoValido = sugestaoAtual;
	}
	
	public String getSugestaoAtual() {
		return sugestaoAtual;
	}

	/**
	 * Sugere um ponto de encontro.
	 * @param escolha
	 * 		true - caso o ponto de encontro seja confirmado.
	 * 		false - caso o ponto de econtro seja recusado.
	 * @throws Exception 
	 */
	public void sugerirPonto(String novaSugestao) throws Exception{
		if(blackList.contains(novaSugestao))throw new Exception("Ponto inválido");
		sugestaoAtual = novaSugestao;
	}
	
	public String toString(){
		return "Local do encontro: " + pontoValido;
	}
	
	
	
	
	
	
	
}
