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

public class UserCaroneiro extends User {

	
	
	/**
	 * Contrutor de um usuario caroneiro
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
	public UserCaroneiro(String login, String senha, String nome,String endereco, String email, String telefone)throws AdressErrorException, EmailErrorException,PasswordErrorException, NameErrorException, PhoneErrorException,LoginErrorException {
		super(login, senha, nome, endereco, email, telefone);
		solicitacoes = new LinkedList<Solicitacao>();
	}
	
	
	/**
	 * Solicita uma novo carona a um determinado motorista.
	 * @param carona
	 * 		A carona que deseja pegar.
	 * @param pontoDeEncontro
	 * 		O local onde deseja encontrar com o motorista , podendo ser alterado pelo mesmo ou não.
	 * @throws Exception
	 * 		Para caso da carona ser nula.		
	 */
	public void pedirCarona(Carona carona,String pontoDeEncontro)throws Exception{
		if (carona == null) {
			throw new Exception("Carona inexistente");
		}
		Solicitacao solicitacao = new Solicitacao(carona,this, pontoDeEncontro);
		UserMotorista motorista = (UserMotorista) carona.getMotorista();
		motorista.addSolicitacao(solicitacao);
		 
	}
	
	/**
	 * Solicita uma carona sem a necessidade de passar um ponto de encontro
	 * @param carona
	 * 		A carona que o usuario deseja pegar
	 * @throws Exception
	 * 		Para o caso da carona ser nula.
	 */
	public void pedirCarona(Carona carona)throws Exception{
		if (carona == null) {
			throw new Exception("Carona inexistente");
		}
		
		Solicitacao solicitacao = new Solicitacao(carona,this);
		UserMotorista motorista = (UserMotorista) carona.getMotorista();
		motorista.addSolicitacao(solicitacao);
		
	}
	
	
	/**
	 * Avalia um o ponto de encontro caso ele tenha sido alterado pelo motorista
	 * @param solicitacao
	 * 		Solicitacao a ser avaliada
	 * @param avaliacao
	 * 		false - não aceita o novo ponto de encontro
	 * 		true - aceita o novo ponto de encontro
	 * @throws Exception
	 * 		Caso o caroneiro não possua essa solicitação ou o motorista não tenha alterado o ponto de encontro
	 */
	public void aceitarPontoDeEncontro(Carona carona) throws Exception{
		carona.getPontoDeEncontro().aceitar();
	}
	
}
