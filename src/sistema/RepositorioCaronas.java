package sistema;

import java.util.ArrayList;
import java.util.List;

import excecoes.DateErrorException;

public class RepositorioCaronas {

	private static List<Carona> caronasCadastradas = new ArrayList<Carona>();
	private static Carona novaCarona;
	
	//pelo padrão Creator
	public static void addCarona(String origem,String destino,Hora hora,Data data,int qntVagas,User motorista) throws Exception {
		novaCarona = new Carona(origem, destino, hora, data, qntVagas, motorista);
		caronasCadastradas.add(novaCarona);
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
	public static List<Carona> getCaronas(String origem, String destino) throws Exception{//CASO 3: BUSCAR CARONAS POR ORIGEM E DESTINO,
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
			
			
			//if (carona.getData().compareTo(data) >= 0 || carona.getData().compareTo(hora) >= 0){//COMPARAR COM A DATA/HORA ATUAL DO SISTEMA 
				
			if ((carona.getDestino().equals(destino) && carona.getOrigem().equals(origem))){//TODAS AS CARONAS QUE TENHAM COMO DESTINO E ORIGEM, O QUE FOI PASSADO
				auxCaronas.add(carona);																
			}else if((carona.getDestino().equals(destino) || carona.getOrigem().equals(origem))){
				auxCaronas.add(carona);																			
			}
		
		}

		return auxCaronas;

	}


	
}
