package testes;

import java.util.List;

import sistema.Sistema;
import sistema.User;

public class SistemaFacede {

	// Redistribuir alguns metodos em classes controllers

	private Sistema sistema;
	private static List<User> usuarios;
	private User user;
	private int idSessao;

	public SistemaFacede() {
		sistema = new Sistema();
		usuarios = sistema.getUsuariosCadastrados();
		idSessao = 0;
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		sistema.addUsuario(login, senha, nome, endereco, email, "112121212");
	}
	
	public void criarUsuario(String login, String nome, String endereco, String email) throws Exception {
		sistema.addUsuario(login, null, nome, endereco, email, "112121212");
	}

	public void zerarSistema() {
		sistema = new Sistema();
		usuarios = sistema.getUsuariosCadastrados();
		idSessao = 0;

		// ACREDITO QUE ISSO É O QUE DEVE SER FEITO, AFINAL É PRECISO
		// "REINICIAR" O SISTEMA -> Jonh...
		// acho q esse metodo deve tah relacionado com o goal "clean" do maven!!
		// -> Rafael
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		String result = "";

		user = sistema.getUser(login);
		if (atributo == null || atributo.isEmpty()) {
			throw new Exception("Atributo inválido");

		} else if (atributo.equals("nome")) {
			result = user.getNome();

		} else if (atributo.equals("endereco")) {
			result = user.getEndereco();

		} else if (atributo.equals("email")) {
			result = user.getEmail();

		} else if (atributo.equals("login")) {
			result = user.getLogin();

		} else {
			throw new Exception("Atributo inexistente");
		}

		return result;

	}

	public int abrirSessao(String login, String senha) throws Exception {
		user = sistema.acessarConta(login, senha);
		return idSessao++;
	}

	public void encerrarSistema() {
		// TOMANDO O NOME DO METODO COMO BASE, NÃO SERIA APENAS UM
		// System.exit(1) ?? -> Jonh
		// acho q esse metodo deve tah relacionado com algum goal do maven!! ->
		// Rafael
	}

	// ate aki, US01.
	
	
	public String localizarCarona(int sessão, String origem, String destino) throws Exception{
		String result = "";
		
		if (sistema.getCaronas(origem, destino).size() == 0){
			result = "{}";
		} else{
			result = sistema.getCaronas(origem, destino).toString();
		}
		
		return result;
	}
	
	public void cadastrarCarona(int sessao, String origem, String destino, String data, String hora, String vagas) throws Exception{
		sistema.addCarona(origem, destino, hora, data, vagas, null);
	}
	
	public String getAtributoCarona(int sessao, String atributo){
		return null;
	}
	
	public String getTrajeto(){
		return null;
	}
	
//	ate aqui US02 + 01
	public static void main(String[] args) {

		SistemaFacede n = new SistemaFacede();
		try {
			n.localizarCarona(0,"Jp", "Cg");
			n.criarUsuario(null, null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
