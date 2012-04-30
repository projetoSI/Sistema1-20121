package ufcg.edu.br.Sistema120121.sistema;

import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PasswordErrorException;
import ufcg.edu.br.Sistema120121.excecoes.UserException;


public class Sistema {
	
	//Classe Controller,responsavel por gerenciar todo o sistema...
	
	/**
	 * Adiciona um novo usuario no sistemka.
	 * @param login
	 * 		Login do novo usuario.
	 * @param senha
	 * 		Senha do novo usuario.
	 * @param nome
	 * 		Nome do novo usuario.
	 * @param endereco
	 * 		Endereço do novo usuario.
	 * @param email
	 * 		Email do novo usuario.
	 * @param telefone
	 * 		Telefone do novo usuario.
	 * @throws Exception
	 */
	public void addUsuario(String login,String senha,String nome,String endereco,String email,String telefone) throws Exception{
		AcessaDados.addUsuario(login, senha, nome,endereco,email,telefone);
	}
	
	/**
	 * Adiciona uma nova carona ao sistema.
	 * @param origem
	 * 		Local de origem da carona.
	 * @param destino
	 * 		Local de destino da carona.
	 * @param hora
	 * 		A hora de saida.
	 * @param data
	 * 		A data da carona.
	 * @param qntVagas
	 * 		A quantidade de vagas de carona.
	 * @param motorista
	 * 		O motorista da carona.
	 * @throws Exception
	 */
	public void addCarona(String origem,String destino,Hora hora,Data data,int qntVagas,User motorista) throws Exception {
		AcessaDados.addCarona(origem, destino, hora, data, qntVagas, motorista);
	}
	
	/**
	 * Retorna a lista de usuarios cadastrados no sistema.
	 * @return
	 * 		A lista de usuarios cadastrados.
	 */
	public List<User> getUsuariosCadastrados() {
		return AcessaDados.getUsuariosCadastrados();
	}
	
	/**
	 * Busca um determinado usuario através de seu login.
	 * @param login
	 * 		O login do usuario a ser buscado.
	 * @return
	 * 		O usuario.
	 * @throws Exception
	 */
	public User getUser(String login) throws Exception{
		return AcessaDados.getUser(login);
		
	}
	
	/**
	 * Acessa a conta de um determinado usuario atraves de login e senha.
	 * @param login
	 * 		O login do usuario a ser buscado.
	 * @param senha
	 * 		A senha do usuario a ser buscado.
	 * @return
	 * 		O usuario.
	 * @throws Exception
	 */
	public User acessarConta(String login,String senha) throws Exception {
		User usuario = null;
		
		if (senha == null ||senha.isEmpty()) {//Tah correto,aki?!
			throw new PasswordErrorException("Senha inválida");
		}
		
		usuario = AcessaDados.getUser(login);
		if (usuario == null)
			throw new UserException("Usuário inexistente");
		
		if (!usuario.getSenha().equals(senha))
			throw new LoginErrorException("Login inválido");
		
		
		return usuario;
		
	}
	
	/**
	 * Retorna uma determinada carona através de sua origem e destino.
	 * @param origem
	 * 		A origem da carona.
	 * @param destino
	 * 		O destino da carona.
	 * @return
	 * 		A carona desejada.
	 * @throws Exception
	 */
	public List<Carona> getCaronas(String origem, String destino) throws Exception {
		return AcessaDados.localizarCarona(origem, destino);
	}
	
	//localizarCaronas -> FEITO!
	//solicitarVaga
	//sugerirPontodeEncontro
	
	
	

}