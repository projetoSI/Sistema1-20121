package sistema;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Carona> recuperaVagaCaronaUser(User usuario){
		
		List<Carona> vagaCaronaUser = new ArrayList<Carona>();
		
		for (Carona carona : caronasCadastradas) {
			
			if( carona.verificaCaroneiro(usuario)){
				vagaCaronaUser.add(carona);
			}
		}
		
		return vagaCaronaUser;
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
		
		if(destino.isEmpty() && !origem.isEmpty()){//apenas destino
			for (Carona carona : caronasCadastradas) {// todas as caronas com origem igual
				if (origem.equals(carona.getOrigem())) {
					auxCaronas.add(carona);
				}
			}			
		}
		
		if(!destino.isEmpty() && origem.isEmpty()){//apenas origem
			for (Carona carona : caronasCadastradas) {// todas as caronas com destino igual
				if (destino.equals(carona.getDestino())) {
					auxCaronas.add(carona);
				}
			}						
		}
		
		if (!destino.isEmpty() && !origem.isEmpty()) {
			for (Carona carona : caronasCadastradas) {// todas as caronas com destino e origem igual
				if (origem.equals(carona.getOrigem()) && destino.equals(carona.getDestino())) {
					auxCaronas.add(carona);
				}
			}
		}		

		return auxCaronas;

	}


	
}
