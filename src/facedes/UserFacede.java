package facedes;

import java.util.ArrayList;
import java.util.List;

import excecoes.AdressErrorException;
import excecoes.EmailErrorException;
import excecoes.LoginErrorException;
import excecoes.NameErrorException;
import excecoes.PasswordErrorException;
import excecoes.PhoneErrorException;
import sistema.User;

public class UserFacede {
	
	private User user;
	private static List<User> usuarios = new ArrayList<User>();
	private int idSessao = 0;
	
	public void criarUsuario(String login,String senha,String nome,String endereco,String email) throws AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException {
		
		user = new User(login,senha,nome,endereco,email,"28888888");
		usuarios.add(user);
	}
	
	public String getAtributoUsuario(String login,String atributo) throws Exception {
		boolean achou = true;
		if((login == null||login.isEmpty() || login.length() < 3)){
			throw new LoginErrorException("Login inv�lido");
		}
		
		if(!user.getLogin().equals(login)){
			for (User usuario : usuarios) {
				if(usuario.getLogin().equals(login)){
					user = usuario;
					achou = false;
				}
			}

			if (achou) {
				  throw new Exception("Usu�rio inexistente");
				}

		}
		
		
		if(atributo == null || atributo.isEmpty()){
			throw new Exception("Atributo inv�lido");
		}else if (atributo.equals("nome")) {
			return user.getNome();			
		}else if (atributo.equals("endereco")) {
			return user.getEndereco();			
		}else if (atributo.equals("email")) {
			return user.getEmail();			
		}else if (atributo.equals("login")) {
			return user.getLogin();			
		}else{
			throw new Exception("Atributo inexistente");
		}
		
		
	}
	
	public int abrirSessao(String login,String senha )throws Exception {
		boolean achou = true;
		if((login == null||login.isEmpty() || login.length() < 3)){
				throw new LoginErrorException("Login inv�lido");
		}
		if (senha.isEmpty() || senha.length() < 4) {
			throw new PasswordErrorException("Senha Invalida");
		}
			
		for (User usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				if(usuario.getSenha().equals(senha)){
					user = usuario;
					achou = false;
				}else{
					throw new LoginErrorException("Login inv�lido");
				}
			}
		}
		if (achou) {
		  throw new Exception("Usu�rio inexistente");
		}
		
		return idSessao++; 

	}
	
	
	
	
	
	
	
	
	
	

}
