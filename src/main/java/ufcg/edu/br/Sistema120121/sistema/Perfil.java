package ufcg.edu.br.Sistema120121.sistema;

import java.util.List;

public class Perfil {

	private User usuario;
	
	/**
	 * Construtor de uma perfil para usuario.
	 * @param usuario
	 * 		O usuario a ter seu perfil construido.
	 */
	public Perfil (User usuario){
		this.usuario = usuario;
	}
	
	/**
	 * Exibe as informações de cadastro do usuario.
	 * @return
	 */
	private String exibeCadastro() {
		return  usuario.getNome() + " " + usuario.getLogin() + " " 
									+ usuario.getEmail() + " " 
									+ usuario.getEndereco() +  " " 
									+ usuario.getTelefone();
	}
	
	/**
	 * Exibe o perfil do usuario.
	 * @return
	 * 		O perfil do usuario.
	 */
	public String exibeMeuPerfil() {
		return exibeCadastro() + usuario.getListaAmigos() + exibeHistoricoDeCaronas(usuario) + exibeHitoricoDeVagas(usuario);

	}
	
	/**
	 * Exibe o historico de caronas dada pelo usuario.
	 * @param usuario
	 * 		O usuario a ter sua lista de caronas exibida.
	 * @return
	 * 		A lista de caronas de um determinado usuario.
	 */
	private List<Carona> exibeHistoricoDeCaronas(User usuario) {
		return RepositorioCaronas.recuperaCaronaUser(usuario);
	}

	/**
	 * Exibe o historico de caronas dada pelo usuario.
	 * @param usuario
	 * 		O usuario a ter sua lista de caronas exibida.
	 * @return
	 * 		A lista de caronas de um determinado usuario.
	 */
	private List<Carona> exibeHitoricoDeVagas(User usuario) {
		return RepositorioCaronas.recuperaVagaCaronaUser(usuario);
	}
	
}
