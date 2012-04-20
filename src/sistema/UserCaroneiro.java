package sistema;

import java.util.HashMap;
import java.util.Map;

import excecoes.AdressErrorException;
import excecoes.EmailErrorException;
import excecoes.LoginErrorException;
import excecoes.NameErrorException;
import excecoes.PasswordErrorException;
import excecoes.PhoneErrorException;

public class UserCaroneiro extends User {

	
	Map<Carona,String> pontosDeEncontro = new HashMap<Carona, String>();
	
	//Cria um caroneiro
	public UserCaroneiro(String login, String senha, String nome,String endereco, String email, String telefone)throws AdressErrorException, EmailErrorException,PasswordErrorException, NameErrorException, PhoneErrorException,LoginErrorException {
		super(login, senha, nome, endereco, email, telefone);
	}
	
	//Solicita uma carona a um determinado motorista passando um ponto de econtro ou não
	public void pedirCarona(UserMotorista motorista,Carona carona,String pontoDeEncontro){
		
		if (motorista.verificaCarona(carona) && carona.temVaga()) {
			pontosDeEncontro.put(carona, pontoDeEncontro);
			carona.addCandidato(this);
		}
	}
	
	//Verifica se o caroneiro se candidatou a determinada carona
	public boolean containsCarona(Carona c){
		return pontosDeEncontro.containsKey(c);
	}
	//Retorna o ponto de encontro de uma carona que o caroneiro é candidato
	public String getPontoDeEcontro(Carona carona){
		return pontosDeEncontro.get(carona);
	}
	//Muda o ponto de encontro da carona
	public void setNovoPontoEncontro(Carona carona,String novoPonto){
		if (pontosDeEncontro.containsKey(carona)) {
			pontosDeEncontro.put(carona, novoPonto);
		}
	}
	//Caso o motorista mude o ponto de encontro cabe ao caroneiro decidir se quer aceitar ou nao
	public void avaliarNovoPonto(Carona carona,boolean avaliacao)throws Exception{
		if (pontosDeEncontro.get(carona).isEmpty()) {
			throw new Exception("Sua carona foi recusada");
		}else{
			
			if (avaliacao) {
				carona.addCaroneiro(this);
			}else{
				carona.removeCandidato(this);
			}
		}
		
		
	}
	//Retorna as informações da carona
	public String detalharCarona(Carona carona){
		if (carona.verificaCaroneiro(this)) {
			return "Data: " + carona.getData() + "\nOrigem: " + carona.getOrigem() + "\nDestino: " + carona.getDestino() + "\nHora: " + carona.getHora() + "\nNumero de Vagas Restantes:  " + carona.getQntVagas() + "\nMotorista: " + carona.getMotorista().getNome() + "\nCaroneiros:  " + carona.getCaroneiros();
			
		}else{
			return "Esse usuario não possui a carona informada";
		}
	}
	
	
	
	
}
