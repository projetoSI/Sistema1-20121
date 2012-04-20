package sistema;

import java.util.ArrayList;
import java.util.List;
import excecoes.*;

public class UserMotorista extends User {

	
	List<Carona> caronas = new ArrayList<Carona>();
	
	//Cria um motorista
	public UserMotorista(String login, String senha, String nome,String endereco, String email, String telefone)throws AdressErrorException, EmailErrorException,PasswordErrorException, NameErrorException, PhoneErrorException,LoginErrorException {
		super(login, senha, nome, endereco, email, telefone);
	
	}
	//Adiciona uma carona ao motorista
	public void addCarona(Carona carona){
		caronas.add(carona);
	}
	
	//Verifica se o motorista possui determinada carona
	public boolean verificaCarona(Carona carona){
		return caronas.contains(carona);
	}
	/*
	 * Analisa determinado ponto de encontro passado pelo caroneiro.
	 * avaliacao = true => a carona foi aprovada
	 * avaliacao = false && pontoDeEcontro == "" => a carona foi reprovada
	 * avaliacao = false && pontoDeEcontro != "" => a carona foi reprovada mas o motorista decidiu mudar o ponto de encontro
	 * 
	 */
	public void analisarPontoDeEcontro(UserCaroneiro caroneiro,String pontoDeEncontro,boolean avaliacao) throws Exception{
		
		for (Carona carona : caronas ) {
			
			if (carona.verificaCandidato(caroneiro) && carona.temVaga()) {
				
				if (avaliacao) {
					carona.addCaroneiro(caroneiro);
					
				}else if (!avaliacao && !pontoDeEncontro.isEmpty()){
					caroneiro.setNovoPontoEncontro(carona, pontoDeEncontro);
				
				}else if (!avaliacao && pontoDeEncontro.isEmpty()){
					caroneiro.setNovoPontoEncontro(carona, pontoDeEncontro);
				}	
				
			}
		}
	}
	
	//Detalha a carona que um determinado motorista possui.
	public String detalharCarona(Carona carona){
		if (caronas.contains(carona)) {
			return "Data: " + carona.getData() + "\nOrigem: " + carona.getOrigem() + "\nDestino: " + carona.getDestino() + "\nHora: " + carona.getHora() + "\nNumero de Vagas Restantes:  " + carona.getQntVagas() + "\nMotorista: " + carona.getMotorista().getNome() + "\nCaroneiros:  " + carona.getCaroneiros();
			
		}else{
			return "Esse usuario não possui a carona informada";
		}
	}
	
	
}
