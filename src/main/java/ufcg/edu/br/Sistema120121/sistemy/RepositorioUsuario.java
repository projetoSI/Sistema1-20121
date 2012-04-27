package ufcg.edu.br.Sistema120121.sistemy;

import java.util.ArrayList;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoe.*;

public class RepositorioUsuario {

	private static List<User> usersCadastrados = new ArrayList<User>();
	private static User newUser;
	
	
	//ADDUSER as verificações de existencia de login e email
	

	//pelo padrão Creator
	public static void addUser(String login, String senha, String nome,	String endereco,String email, String telefone) throws Exception{
		newUser = new User(login,senha,nome,endereco,email,telefone);
		
		if (getUsuarioEmail(email) == null && getUsuarioLogin(login) == null)
			usersCadastrados.add(newUser);
		else if(getUsuarioEmail(email) != null) throw new UserException("Já existe um usuário com este email");
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
