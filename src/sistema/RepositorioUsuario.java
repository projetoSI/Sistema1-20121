package sistema;

import java.util.ArrayList;
import java.util.List;
import excecoes.*;

public class RepositorioUsuario {

	private static List<User> usersCadastrados = new ArrayList<User>();
	private static User newUser;
	
	
	//ADDUSER as verificações de existencia de login e email
	

	//pelo padrão Creator
	public static void addUser(String login, String senha, String nome,	String endereco,String email, String telefone) throws Exception{
		newUser = new User(login,senha,nome,endereco,email,telefone);
		usersCadastrados.add(newUser);
		
	}
	//padrão EXPERT
	public static List<User> getUsuarios() {
		return usersCadastrados;
		 
	}
	//padrão EXPERT
	public static User getUsuarioEmail(String email) throws EmailErrorException,Exception{
		return foundUser(email, "Email inválido");				
	}
	
	//padrão EXPERT	
	public static User getUsuarioLogin(String login) throws LoginErrorException,Exception{
		return foundUser(login, "Login inválido");		
	}
	
	private static User foundUser(String argumento, String mensagem) throws LoginErrorException,EmailErrorException,Exception{
		User usuario = null;
		
		if(argumento == null || argumento.isEmpty()){
			throw new EmailErrorException(mensagem);
		}
		
		for (User user : usersCadastrados) {
			if (user.getLogin().equals(argumento) || user.getEmail().equals(argumento)) {
				usuario = user;
				break;
			}
		}
		
		if (usuario == null) {
			throw new Exception("Usuário inexistente");
		}
		
		return usuario;
	}
	
}
