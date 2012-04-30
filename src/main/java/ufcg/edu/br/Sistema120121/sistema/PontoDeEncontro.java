package ufcg.edu.br.Sistema120121.sistema;

import java.util.LinkedList;
import java.util.List;

public class PontoDeEncontro {

	
	private String sugestaoAtual;
	private List<String> blackList = new LinkedList<String>(); 
	private String pontoValido;
		
	/**
	 * Construtor de um ponto de encontro.
	 */
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
	
	/**
	 * Aceita um determinado ponto de encontro.
	 */
	public void aceitar(){
		pontoValido = sugestaoAtual;
	}
	
	/**
	 * Verifica a sugestão de ponto de encontro.
	 * @return
	 * 		O ponto de encontro.
	 */
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
		if(novaSugestao == null || novaSugestao.isEmpty() || blackList.contains(novaSugestao))throw new Exception("Ponto Inválido");
		blackList.add(sugestaoAtual);
		sugestaoAtual = novaSugestao;
	}
	
	/**
	 * Retorna as informações do ponto de encontro em forma de String.
	 */
	public String toString(){
		return "Local do encontro: " + pontoValido;
	}	
}
