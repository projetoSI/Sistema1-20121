package sistema;

import java.util.List;

public class Perfil {

	private User usuario;
	
	public Perfil (User usuario){
		this.usuario = usuario;
	}

	
	private String exibeCadastro() {
		return  usuario.getNome() + " " + usuario.getLogin() + " " 
									+ usuario.getEmail() + " " 
									+ usuario.getEndereco() +  " " 
									+ usuario.getTelefone();
	}
	
	public String exibeMeuPerfil() {
		return exibeCadastro() + usuario.getListaAmigos() + exibeHistoricoDeCaronas(usuario) + exibeHitoricoDeVagas(usuario);

	}
	
	private List<Carona> exibeHistoricoDeCaronas(User usuario) {
		return RepositorioCaronas.recuperaCaronaUser(usuario);
	}

	private List<Carona> exibeHitoricoDeVagas(User usuario) {
		return RepositorioCaronas.recuperaVagaCaronaUser(usuario);
	}
	
}
