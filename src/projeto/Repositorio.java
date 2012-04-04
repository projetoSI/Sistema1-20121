package projeto;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

	private static List<Carona> caronasCadastradas = new ArrayList<Carona>();
	private static List<User> usersCadastrados = new ArrayList<User>();

	public static void adicionaCarona(Carona carona) {
		caronasCadastradas.add(carona);
	}

	public static void addUser(User newUser) {
		usersCadastrados.add(newUser);
	}

	public static boolean verificaExistencia(String login, String email) {//VERIFICAR A EXISTENCIA DE UM USUARIO COM O LOGIN/EMAIL PASSADO.
		User auxUser = new User(login, "default", "default", "default", email, "default");

		for (User user : usersCadastrados) {
			if (user.getLogin().equals(auxUser.getLogin()) || user.getEmail().equals(auxUser.getEmail())) {
				return  true;
			}
		}

		return false;
	}

	public static List<Carona> getCaronasCadastradas() {//CASO1: TODAS AS CARONAS CADASTRADAS
		return caronasCadastradas;
	}

	public static List<Carona> recuperaCaronaUser(User usuario) {//CASO2 : AS CARONAS DO USER
		List<Carona> caronasUser = new ArrayList<Carona>();

		for (Carona carona : caronasCadastradas) {
			if (carona.motorista().getLogin().equals(usuario.getLogin())) {
				caronasUser.add(carona);
			}
		}

		return caronasUser;
	}
	
	public static List<Carona> getCaronas(String origem, String destino, DataHora data, DataHora hora){//CASO 3: BUSCAR CARONAS POR ORIGEM E DESTINO,
																									   //E RETORNAR APENAS AS QUE IRÃO OCORRER
		List<Carona> auxCaronas = new ArrayList<Carona>();
		
		for (Carona carona : caronasCadastradas) {
			if (carona.getData().compareTo(data) >= 0 || carona.getData().compareTo(hora) >= 0){//COMPARAR A DATA/HORA ATUAL DO SISTEMA 
				
			if ((carona.getDestino().equals(destino) || carona.getOrigem().equals(origem))){
				auxCaronas.add(carona);															//TODAS AS CARONAS QUE TENHAM COMO DESTINO E ORIGEM, O QUE FOI PASSADO
				
			}
		
		}
	}
		return auxCaronas;

	}
}
