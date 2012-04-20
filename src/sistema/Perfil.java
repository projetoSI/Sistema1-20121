package sistema;

import java.util.List;

public interface Perfil {

	
	public String exibeCadastro(User usuario);
	public List<User> exibeListaDeAmigos(User usuario);
	public List<Carona> exibeHistoricoDeCaronas(User usuario);
	public List<Carona> exibeHitoricoDeVagas(User usuario);
	public String exibePerfil(User usuario);
		
}
