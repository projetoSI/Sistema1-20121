package sistema;

import java.util.List;

import excecoes.DateErrorException;

public class LocalizarCarona {

	public static List<Carona> recuperaCaronaUser(User usuario){
		return Repositorio.recuperaCaronaUser(usuario);
	}
	
	public static List<Carona> getCaronas(String origem,String destino) throws Exception{
		Data  partida = new Data("06","03","2012");
		return Repositorio.getCaronas(origem, destino,partida,partida);
		
	}
	
}
