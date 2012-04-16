package sistema;

import java.util.ArrayList;
import java.util.List;

import excecoes.AdressErrorException;
import excecoes.EmailErrorException;
import excecoes.LocalErrorException;
import excecoes.LoginErrorException;
import excecoes.NameErrorException;
import excecoes.PasswordErrorException;
import excecoes.PhoneErrorException;
import excecoes.QuantityVacancyErrorException;

public class RepositorioUsuario {

	private static List<User> usersCadastrados = new ArrayList<User>();
	private static User newUser;
	

	//pelo padrão Creator
	public static void addUser(String login, String senha, String nome,	String endereco,String email, String telefone) throws Exception {
		
		//Verificar se ja existe um login ou um email cadastrado
		
		newUser = new User(login,senha,nome,endereco,email,telefone); 
		usersCadastrados.add(newUser);
	}
	//padrão EXPERT
	public static List<User> getUsuarios() {
		return usersCadastrados;
		 
	}
	//padrão EXPERT
	public static User getUsuarioEmail(String email) throws Exception{
		return getUsusarioAux(email, "Email inválido");				
	}
	
	//padrão EXPERT	
	public static User getUsuarioLogin(String login) throws Exception{
		return getUsusarioAux(login, "Login inválido");		
	}
	
	private static User getUsusarioAux(String argumento, String mensagem) throws Exception{
		User usuario = null;
		
		if(argumento == null || argumento.isEmpty()){
			throw new Exception(mensagem);
		}
		
		usuario = foundUser(argumento);
		
		if (usuario == null) {
			throw new Exception("Usuário inexistente");
		}
		
		return usuario;
	}
	
	private static User foundUser(String argumento) {
		User usuario = null;
		
		for (User user : usersCadastrados) {
			if (user.getLogin().equals(argumento) || user.getEmail().equals(argumento)) {// VERIFICA OS DOIS ARGUMENTOS, SE ALGUM BATER ELE JÁ IDENTIFICOU O USER
				usuario = user;
				break;
			}
		}
		
		return usuario;
	}
	
}
