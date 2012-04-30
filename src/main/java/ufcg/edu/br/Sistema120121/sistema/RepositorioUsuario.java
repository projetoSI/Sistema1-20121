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
	
	public void atualizaRepositorio() throws IOException {
		usersCadastrados = Arquivo.lerArquivo("Arquivos/arquivoUser.xml");
	}

	//pelo padrão Creator
	public static void addUser(String login, String senha, String nome,	String endereco,String email, String telefone) throws Exception{
		newUser = new User(login,senha,nome,endereco,email,telefone);
		
		if (getUsuarioEmail(email) == null && getUsuarioLogin(login) == null){
			usersCadastrados.add(newUser);
			Arquivo.setUsuarios(usersCadastrados);
		}else if(getUsuarioEmail(email) != null) throw new UserException("Já existe um usuário com este email");
		else if(getUsuarioLogin(login) != null) throw new UserException("Já existe um usuário com este login");
		
	}
	//padrão EXPERT
	public static List<User> getUsuarios() {
		return usersCadastrados;
		 
	}
	//padrão EXPERT
	public static User getUsuarioEmail(String email) throws Exception{
		if(email == null || email.isEmpty())
			throw new EmailErrorException("Email inválido");
		return foundUser(email);				
	}
	
	//padrão EXPERT	
	public static User getUsuarioLogin(String login) throws Exception{
		if(login == null || login.isEmpty())
			throw new LoginErrorException("Login inválido");
		return foundUser(login);		
	}
	
	private static User foundUser(String argumento){
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
