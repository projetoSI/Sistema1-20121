package sistema;

import excecoes.AdressErrorException;
import excecoes.EmailErrorException;
import excecoes.LoginErrorException;
import excecoes.NameErrorException;
import excecoes.PasswordErrorException;
import excecoes.PhoneErrorException;

public class CadastraUser {

	User newUser;

	public CadastraUser() {
		

	}

	public void addNovoUser(String login, String senha, String nome,String endereco, String email, String telefone) throws Exception {
		if (verificaLogin(login) && verificaNome(nome) && verificaEmail(email)
				&& verificaTelefone(telefone)) {
				newUser = new User(login, senha, nome, endereco, email, telefone);
			Repositorio.addUser(newUser);
		}
	}
	
	private boolean verificaLogin(String login) throws Exception {
		if (!(login.replaceAll(" ", "").equals(""))
				&& !(Repositorio.verificaExistencia(login, "login"))) {
			return true;
		}
		return false;
	}

	private boolean verificaNome(String nome) {
		if (!(nome.replaceAll(" ", "").equals(""))) {
			return true;
		}
		return false;
	}

	private boolean verificaEmail(String email) throws Exception {
		if (!(email.replaceAll(" ", "").equals(""))
				&& !(Repositorio.verificaExistencia(email, "email"))) {
			return true;
		}
		return false;
	}

	private boolean verificaTelefone(String telefone) {
		if (telefone.matches("*[0-9]*")) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		String s = "aasa@email.com";
	if (s.matches("[\\w_.-]+@\\w+[.-.](com|com[.-.]br)")) {
		System.out.println("passou");
	}
	
	
	}


}
