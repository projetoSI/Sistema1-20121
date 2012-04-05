package sistema;

import java.util.List;

public class LocalizarCarona {

	public static List<Carona> recuperaCaronaUser(User usuario){
		return Repositorio.recuperaCaronaUser(usuario);
	}
}
