package sistema;

import java.util.ArrayList;
import java.util.List;
import excecoes.*;

public class UserMotorista extends User {

	

	List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
	
	//Cria um motorista
	public UserMotorista(String login, String senha, String nome,String endereco, String email, String telefone)throws AdressErrorException, EmailErrorException,PasswordErrorException, NameErrorException, PhoneErrorException,LoginErrorException {
		super(login, senha, nome, endereco, email, telefone);
	
	}
	
	
	public void addSolicitacao(Solicitacao solicitacao) {
		solicitacoes.add(solicitacao);
	}
	
	public void avaliarSolicitacao(Solicitacao solicitacao,boolean avalicao,String pontoDeEncontro) throws Exception{
		
		if (solicitacoes.contains(solicitacao)) {
			
			if (avalicao) {
				solicitacao.setValidadeVaga(true);
				solicitacao.setValidadePontoDeEncontro(true);
				solicitacao.confirmarCarona();
				
			}else{
					
				if (!pontoDeEncontro.isEmpty()) {
					solicitacao.setPontoDeEncontro(pontoDeEncontro);
					solicitacao.setValidadeVaga(true);
					solicitacao.setValidadePontoDeEncontro(false);
				}
					
			}
		}
		
		
	}
}