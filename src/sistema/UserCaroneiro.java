package sistema;

import java.util.ArrayList;
import java.util.List;


import excecoes.AdressErrorException;
import excecoes.EmailErrorException;
import excecoes.LoginErrorException;
import excecoes.NameErrorException;
import excecoes.PasswordErrorException;
import excecoes.PhoneErrorException;

public class UserCaroneiro extends User {

	
	List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
	
	
	//Cria um caroneiro
	public UserCaroneiro(String login, String senha, String nome,String endereco, String email, String telefone)throws AdressErrorException, EmailErrorException,PasswordErrorException, NameErrorException, PhoneErrorException,LoginErrorException {
		super(login, senha, nome, endereco, email, telefone);
	}
	
	
	public void pedirCarona(Carona carona,String pontoDeEncontro,UserMotorista motorista){
		Solicitacao solicitacao = new Solicitacao(carona,this, pontoDeEncontro);
		motorista.addSolicitacao(solicitacao);
	}
	
	
	public void avaliarPontoDeEncontro(Solicitacao solicitacao,boolean avaliacao) throws Exception{
		
		if (solicitacoes.contains(solicitacao)) {
			if (solicitacao.isValidadeVaga() && !solicitacao.isValidadePontoDeEncontro() && avaliacao) {
				solicitacao.setValidadePontoDeEncontro(true);
				solicitacao.confirmarCarona();
			}
		}
		
	}
	
	
}
