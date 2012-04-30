package ufcg.edu.br.Sistema120121.sistema;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.EmailErrorException;
import ufcg.edu.br.Sistema120121.excecoes.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoes.UserException;

public class RepositorioUsuario {

	private static List<User> usersCadastrados = new LinkedList<User>();
	private static User newUser;
	
	/**
	 * Atualiza o repositorio de usuario.
	 * @throws IOException
	 *		Caso não consiga ler o arquivo. 		
	 */
	public void atualizaRepositorio() throws IOException {
		usersCadastrados = Arquivo.lerArquivo("c://Arquivos/arquivoUser.xml");
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
	 * @throws Exception
	 */
	public static void addUser(String login, String senha, String nome,	String endereco,String email, String telefone) throws Exception{
		newUser = new User(login,senha,nome,endereco,email,telefone);
		
		if (getUsuarioEmail(email) == null && getUsuarioLogin(login) == null){
			usersCadastrados.add(newUser);
			Arquivo.setUsuarios(usersCadastrados);
		}else if(getUsuarioEmail(email) != null) throw new UserException("Já existe um usuário com este email");
		else if(getUsuarioLogin(login) != null) throw new UserException("Já existe um usuário com este login");
		
	}
	
	/**
	 * Retorna os usuarios do respositorio
	 * @return
	 * 		Os usuarios do repositorios.
	 */
	public static List<User> getUsuarios() {
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
	public static User getUsuarioEmail(String email) throws Exception{
		if(email == null || email.isEmpty())
			throw new EmailErrorException("Email inválido");
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
	public static User getUsuarioLogin(String login) throws Exception{
		if(login == null || login.isEmpty())
			throw new LoginErrorException("Login inválido");
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
	private static User foundUser(String argumento) throws Exception{
		User usuario = null;
		
		for (User user : usersCadastrados) {
			if (user.getLogin().equals(argumento) || user.getEmail().equals(argumento)) {
				usuario = user;
				break;
			}
		}
		return usuario;
	}
}
