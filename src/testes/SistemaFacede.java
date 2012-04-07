package testes;

import java.util.ArrayList;
import java.util.List;

import excecoes.AdressErrorException;
import excecoes.DateErrorException;
import excecoes.EmailErrorException;
import excecoes.LoginErrorException;
import excecoes.NameErrorException;
import excecoes.PasswordErrorException;
import excecoes.PhoneErrorException;
import sistema.Carona;
import sistema.LocalizarCarona;
import sistema.Sistema;
import sistema.User;

public class SistemaFacede {
	
	//Redistribuir alguns metodos em  classes controllers
	
	private Sistema sistema;
	private static List<User> usuarios;
	private User user;
	private int idSessao;
	
	public SistemaFacede() {
		sistema = new Sistema();
		usuarios = sistema.getUsuariosCadastrados();
		idSessao = 0;
	}
	
	public void criarUsuario(String login,String senha,String nome,String endereco,String email) throws Exception {
		sistema.addUsuario(login, senha, nome, endereco, email, "112121212");
	}
	
	public void zerarSistema() {
		//acho q esse metodo deve tah relacionado com o goal "clean" do maven!!
	}
	
	public String getAtributoUsuario(String login, String atributo)
			throws Exception {

		user = sistema.getUser(login);
		if(atributo == null || atributo.isEmpty()){
			throw new Exception("Atributo inválido");
		}else if (atributo.equals("nome")) {
			return user.getNome();
		} else if (atributo.equals("endereco")) {
			return user.getEndereco();
		} else if (atributo.equals("email")) {
			return user.getEmail();
		} else if (atributo.equals("login")) {
			return user.getLogin();
		} else {
			throw new Exception("Atributo inexistente");
		}

	}
	
	public int abrirSessao(String login,String senha )throws Exception {
		user = sistema.acessarConta(login, senha);
		return idSessao++; 
	}

	public void encerrarSistema() {
		//acho q esse metodo deve tah relacionado com algum goal do maven!!
	}
	
	//ate aki, US01.
	
	
	public List<Carona> localizarCarona(int idsessao,String origem,String destino) throws DateErrorException {
		return LocalizarCarona.getCaronas(origem,destino);
	}
	
}
