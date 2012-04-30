package ufcg.edu.br.Sistema120121.sistema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.AdressErrorException;
import ufcg.edu.br.Sistema120121.excecoes.EmailErrorException;
import ufcg.edu.br.Sistema120121.excecoes.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoes.NameErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PasswordErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PhoneErrorException;

public class UserMotorista extends User {

	

	List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
	private LinkedList<Solicitacao> solicitacoesAceitas;
	/**
	 * Construtor de um usuario motorista.
	 * @param login
	 * 		Login do novo usuario.
	 * @param senha
	 * 		Senha do novo usuario.
	 * @param nome
	 * 		Nome do novo usuario.
	 * @param endereco
	 * 		Endereço do novo usuario.
	 * @param email
	 * 		Email do novo usuario.
	 * @param telefone
	 * 		Telefone do novo usuario.
	 * @throws AdressErrorException
	 * 		Caso o endereço esteja em um formato incorreto.
	 * @throws EmailErrorException
	 * 		Caso o email esteja em um formato incorreto
	 * @throws PasswordErrorException
	 * 		Caso a senha inserida seja invalida.
	 * @throws NameErrorException
	 * 		Caso o nome esteja incorreto
	 * @throws PhoneErrorException
	 * 		Caso o telefone esteja em um formato incorreto.
	 * @throws LoginErrorException
	 * 		Caso o login ja exista ou esteja em um formato incorreto.
	 */
	public UserMotorista(String login, String senha, String nome,String endereco, String email, String telefone)throws AdressErrorException, EmailErrorException,PasswordErrorException, NameErrorException, PhoneErrorException,LoginErrorException {
		super(login, senha, nome, endereco, email, telefone);
		solicitacoes = new LinkedList<Solicitacao>();
		solicitacoesAceitas = new LinkedList<Solicitacao>();
	
	}
	
	/**
	 * Adiciona uma nova solicitação ao motorista
	 * @param solicitacao
	 * 		Solicitação enviada por um caroneiro
	 * @throws Exception
	 * 		Caso a solicitação seja nula
	 */
	public void addSolicitacao(Solicitacao solicitacao)throws Exception {
		if (solicitacao == null) throw new Exception("Solicitação inválida");
		solicitacoes.add(solicitacao);
	}
	
	public void rejeitaSolicitacao(Solicitacao solicitacao) throws Exception {
		if(solicitacoes.contains(solicitacao)){
			solicitacoes.remove(solicitacao);			
		}else{
			throw new Exception("Solicitacao Inexistente");
		}
		
	}
	
	/**
	 * Aceita a solicitação de um determinado caroneiro.Retirando da lista de solicitações.
	 * @param solicitacao
	 * 		A solicitação a ser aceita.
	 * @throws Exception
	 * 		Pro caso da solicitação ser invalida.
	 */
	public void avaliarSolicitacao(Solicitacao solicitacao) throws Exception{
		if (solicitacao == null || !solicitacoes.contains(solicitacao)) {
			throw new Exception("Solicitação inexistente");
		} else {
			solicitacao.confirmarCarona();
			solicitacoes.remove(solicitacao);
			solicitacoesAceitas.add(solicitacao);
		}	
	}
	
	
}