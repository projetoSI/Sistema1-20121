package ufcg.edu.br.Sistema120121.dados;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import ufcg.edu.br.Sistema120121.logica.*;

public class RepositorioUsuario {

	private  List<User> usersCadastrados = new LinkedList<User>();
	private  User newUser;
	
	/**
	 * Atualiza o repositorio de usuario.
	 * @throws IOException
	 *		Caso não consiga ler o arquivo. 		
	 */
	public  void atualizaRepositorio(LinkedList<User> novosDados) throws IOException {
		usersCadastrados = novosDados;
	}
	/**
	 * Adicona um novo usuario ao repositorio.
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
	 * @throws LoginErrorException 
	 * @throws Exception
	 */
	public  void addUser(String login, String senha, String nome,	String endereco,String email, String telefone) throws UserException{
		newUser = new User(login,senha,nome,endereco,email,telefone);
		
		if (getUsuarioEmail(email) == null && getUsuarioLogin(login) == null){
			usersCadastrados.add(newUser);
		}else if(getUsuarioEmail(email) != null) throw new UserException("Já existe um usuário com este email");
		else if(getUsuarioLogin(login) != null) throw new UserException("Já existe um usuário com este login");
		
	}
	
	/**
	 * Retorna os usuarios do respositorio
	 * @return
	 * 		Os usuarios do repositorios.
	 */
	public  List<User> getUsuarios() {
		return usersCadastrados;
		 
	}
	
	/**
	 * Retorna um determinado usuario através do Email.
	 * @param email
	 * 		O email do usuario a ser buscado.
	 * @return
	 * 		O usuario.
	 * @throws Exception
	 */
	public  User getUsuarioEmail(String email) throws UserException{
		if(email == null || email.isEmpty())
			throw new UserException("Email inválido");
		return foundUser(email);				
	}
	
	/**
	 * Retorna um determinado usuario através do login
	 * @param login
	 * 		O login do usuario a ser buscado.
	 * @return
	 * 		O usuario.
	 * @throws Exception
	 */
	public  User getUsuarioLogin(String login) throws UserException{
		if(login == null || login.isEmpty())
			throw new UserException("Login inválido");
		return foundUser(login);		
	}
	
	/**
	 * Pesquisa um determinado usuario , atraves de seu login ou email.
	 * @param argumento
	 * 		O tipo de busca.
	 * @return
	 * 		O usuario.
	 * @throws Exception 
	 */
	private  User foundUser(String argumento) throws UserException{
		User usuario = null;
		
		for (User user : usersCadastrados) {
			if (user.getLogin().equals(argumento) || user.getEmail().equals(argumento)) {
				usuario = user;
				break;
			}
		}
		return usuario;
	}

	private  void sessaoUserAux(String login, boolean abre){
		for (int i = 0; i < usersCadastrados.size(); i++) {
			if (usersCadastrados.get(i).getLogin().equals(login)) {
				if (abre) {
					usersCadastrados.get(i).getID().abreSessao();
					break;
				} else {
					usersCadastrados.get(i).getID().fechaSessao();
					break;
				}
			}
		}
	}
	
	public  void abreSessaoUser(String login) {
		sessaoUserAux(login, true);
	}

	public  void fechaSessaoUser(String login) {
		sessaoUserAux(login, false);
	}
}
