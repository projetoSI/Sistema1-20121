package ufcg.edu.br.Sistema120121.sistema;

import java.util.ArrayList;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.AdressErrorException;
import ufcg.edu.br.Sistema120121.excecoes.EmailErrorException;
import ufcg.edu.br.Sistema120121.excecoes.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoes.NameErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PasswordErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PhoneErrorException;

public class UserMotorista extends User {

	

	List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
	
	public UserMotorista(String login, String senha, String nome,String endereco, String email, String telefone)throws AdressErrorException, EmailErrorException,PasswordErrorException, NameErrorException, PhoneErrorException,LoginErrorException {
		super(login, senha, nome, endereco, email, telefone);
	
	}
	
	/**
	 * Adiciona uma nova solicitação ao motorista
	 * @param solicitacao
	 * 		Solicitação enviada por um caroneiro
	 * @throws Exception
	 * 		Caso a solicitação seja nula
	 */
	public void addSolicitacao(Solicitacao solicitacao)throws Exception {
		if (solicitacao == null) throw new Exception("Solicitação não pode ser nula");
		solicitacoes.add(solicitacao);
	}
	
	/**
	 * Avalia a solicitação de um determinado caroneiro,podendo aceitar,recusar ou alterar o ponto de encontro da solicitação.
	 * @param solicitacao
	 * 		A solicitação a ser avaliada.
	 * @param avalicao
	 * 		true - caso o motorista aceite os termos da solicitacao
	 * 		false - caso o motorista recuse ou deseje mudar o local de encontro. 
	 * @param pontoDeEncontro
	 * 		Caso o motorista aceite os termos da solicitação mas deseje alterar o ponto de encontro.
	 * @throws Exception
	 * 		Pro caso da solicitação ser nula ou não existir essa solicitação.
	 */
	public void avaliarSolicitacao(Solicitacao solicitacao,boolean avalicao,String pontoDeEncontro) throws Exception{
		if (solicitacao == null || !solicitacoes.contains(solicitacao)) throw new Exception("Não existe esta solicitação");
		
		if (avalicao){
			solicitacao.confirmarCarona();
		}else{
			if (!pontoDeEncontro.isEmpty()) solicitacao.AlterarLocalDeEncontro(pontoDeEncontro);
			else solicitacao.cancelarCarona();
		}		
		
	}
	
	/**
	 * Retorna todas as solicitações que o motorista possui
	 * @return
	 * 		todas as solicitações do motorista.
	 */
	public String getSolicitacoesRealizadas(){
		String realizadas = "";
		for (Solicitacao solicitacao : solicitacoes) {
			realizadas += solicitacao.toString();
		}
		if (realizadas.isEmpty()) return "Não existe solicitações";else return realizadas; 
	}
	
}