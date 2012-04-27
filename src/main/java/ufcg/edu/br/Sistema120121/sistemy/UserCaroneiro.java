package ufcg.edu.br.Sistema120121.sistemy;

import java.util.ArrayList;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoe.AdressErrorException;
import ufcg.edu.br.Sistema120121.excecoe.EmailErrorException;
import ufcg.edu.br.Sistema120121.excecoe.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoe.NameErrorException;
import ufcg.edu.br.Sistema120121.excecoe.PasswordErrorException;
import ufcg.edu.br.Sistema120121.excecoe.PhoneErrorException;
import ufcg.edu.br.Sistema120121.sistemy.PontoDeEncontro.Situacao;

public class UserCaroneiro extends User {

	
	List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
	
	
	public UserCaroneiro(String login, String senha, String nome,String endereco, String email, String telefone)throws AdressErrorException, EmailErrorException,PasswordErrorException, NameErrorException, PhoneErrorException,LoginErrorException {
		super(login, senha, nome, endereco, email, telefone);
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
			throw new Exception("Parametros não devem ser nulos");
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
			throw new Exception("Parametros não devem ser nulos");
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
	public void avaliarPontoDeEncontro(Solicitacao solicitacao,boolean avaliacao) throws Exception{
		
		if (!solicitacoes.contains(solicitacao) || solicitacao.getPontoDeEncontro().getAvaliacao() != Situacao.MUDANCA) {
			throw new Exception("Não é possivel avaliar o ponto de encontro");
		}else{
			if (avaliacao) solicitacao.confirmarCarona() ;
			else solicitacao.cancelarCarona();solicitacoes.remove(solicitacao) ;				
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
