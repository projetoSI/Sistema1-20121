package ufcg.edu.br.Sistema120121.sistema;

import java.util.List;

public class Perfil {

	private User usuario;
	private int caronasSeguras;
	private int caronasNaoFuncionaram;
	private int faltas;
	
	/**
	 * Construtor de uma perfil para usuario.
	 * @param usuario
	 * 		O usuario a ter seu perfil construido.
	 */
	public Perfil (User usuario){
		this.usuario = usuario;
	}
	
	public String exibeNome() {
		return usuario.getNome();
	}
	
	public String exibeEndereco() {
		return usuario.getEndereco();
	}	
	
	public String exibeEmail() {
		return usuario.getEmail();
	}
	
	public int exibeCaronasSeguras(){
		return caronasSeguras;
	}
	
	public int exibeCaronasNaoFuncionaram(){
		return caronasNaoFuncionaram;
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
	 * Exibe o historico de caronas dada pelo usuario.
	 * @param usuario
	 * 		O usuario a ter sua lista de caronas exibida.
	 * @return
	 * 		A lista de caronas de um determinado usuario.
	 */
	public String exibeHistoricoDeCaronas() {
		List<Carona> historico = RepositorioCaronas.recuperaCaronaUser(usuario);
		historico.addAll(AcessaDados.getCaronasDoUsuario(usuario));
		return historico.toString();
	}

	/**
	 * Exibe o historico de caronas dada pelo usuario.
	 * @param usuario
	 * 		O usuario a ter sua lista de caronas exibida.
	 * @return
	 * 		A lista de caronas de um determinado usuario.
	 */
	public List<Carona> exibeHistoricoDeVagas() {
		return RepositorioCaronas.recuperaVagaCaronaUser(usuario);
	}

	public int exibeFaltas() {
		return faltas;
	}
	
}
