package sistema;

import java.util.ArrayList;
import java.util.List;

import excecoes.AdressErrorException;
import excecoes.EmailErrorException;
import excecoes.LocalErrorException;
import excecoes.LoginErrorException;
import excecoes.NameErrorException;
import excecoes.PasswordErrorException;
import excecoes.PhoneErrorException;
import excecoes.QuantityVacancyErrorException;

public class Repositorio {

	private static List<Carona> caronasCadastradas = new ArrayList<Carona>();
	private static List<User> usersCadastrados = new ArrayList<User>();
	private static Carona novaCarona;
	private static User newUser;
	

	//pelo padrão Creator
	public static void addCarona(String origem,String destino,String hora,String data,String qntVagas,User motorista) throws LocalErrorException, QuantityVacancyErrorException {
		novaCarona = new Carona(origem, destino, hora, data, qntVagas, motorista);
		caronasCadastradas.add(novaCarona);
	}
	//pelo padrão Creator
	public static void addUser(String login, String senha, String nome,	String endereco,String email, String telefone) throws AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException {
		newUser = new User(login,senha,nome,endereco,email,telefone); 
		usersCadastrados.add(newUser);
	}
	//padrão EXPERT
	public static List<User> getUsuarios() {
		return usersCadastrados;
		 
	}
	//padrão EXPERT
	public static User getUsuarioEmail(String email) throws Exception{
		return getUsusarioAux(email, "Email inválido");				
	}
	
	//padrão EXPERT	
	public static User getUsuarioLogin(String login) throws Exception{
		return getUsusarioAux(login, "Login inválido");		
	}
	
	private static User getUsusarioAux(String argumento, String mensagem) throws Exception{
		User usuario = null;
		
		if(argumento == null || argumento.isEmpty()){
			throw new Exception(mensagem);
		}
		for (User user : usersCadastrados) {
			if (user.getLogin().equals(argumento)) {
				usuario = user;
			}
		}
		
		if (usuario == null) {
			throw new Exception("Usuário inexistente");
		}
		
		return usuario;
	}
	
	
	//padrão EXPERT
	public static List<Carona> getCaronasCadastradas() {//CASO1: TODAS AS CARONAS CADASTRADAS
		return caronasCadastradas;
	}
	
	//padrão EXPERT
	public static List<Carona> recuperaCaronaUser(User usuario) {//CASO2 : AS CARONAS DO USER
		List<Carona> caronasUser = new ArrayList<Carona>();

		for (Carona carona : caronasCadastradas) {
			if (carona.getMotorista().getLogin().equals(usuario.getLogin())) {
				caronasUser.add(carona);
			}
		}

		return caronasUser;
	}
	//padrão EXPERT
	public static List<Carona> getCaronas(String origem, String destino, Data partida, Data chegada) throws Exception{//CASO 3: BUSCAR CARONAS POR ORIGEM E DESTINO,
																									   //E RETORNAR APENAS AS QUE IRÃO OCORRER
		List<Carona> auxCaronas = new ArrayList<Carona>();
		
		if (origem == null){
			throw new Exception("Origem Inválida!");
		}
		if (destino == null){
			throw new Exception("Destino Inválido!");
		}
		
		if (destino.isEmpty() && origem.isEmpty()){
			return getCaronasCadastradas();
		}
		
		for (Carona carona : caronasCadastradas) {
			//if (carona.getData().compareTo(data) >= 0 || carona.getData().compareTo(hora) >= 0){//COMPARAR A DATA/HORA ATUAL DO SISTEMA 
				
			if ((carona.getDestino().equals(destino) || carona.getOrigem().equals(origem))){
				auxCaronas.add(carona);															//TODAS AS CARONAS QUE TENHAM COMO DESTINO E ORIGEM, O QUE FOI PASSADO
				
			}
		
		}
//	}
		return auxCaronas;

	}
}
