package testes;

import java.util.List;

import sistema.*;

public class SistemaFacede {

	// Redistribuir alguns metodos em classes controllers

	private Sistema sistema;
	private static List<User> usuarios;
	private User user;
	private Carona carona;
	private int idSessao;
	private int idCarona;

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
	
	public void cadastrarCarona(String sessao, String origem, String destino, String data, String hora, String vagas) throws Exception{
		if (hora.length() == 5 && (data.length() == 10 || data.length() == 8) && vagas.matches("[0-9]*")){
			Hora horaAux = new Hora(hora.substring(0, 2), hora.substring(3));
			Data dataAux = new Data(data.substring(0, 2), data.substring(3, 5), data.substring(6));
			sistema.addCarona(origem, destino, horaAux, dataAux, Integer.parseInt(vagas), null);
			idCarona = Integer.parseInt(sessao);
			
		}else{
//			TODO
		}
	}
	
	public String getAtributoCarona(int sessao, String atributo){
		return null;
	}
	
	public String getTrajeto(String idCarona){
		return null;
	}
	
//	ate aqui US02 + 01
	public static void main(String[] args) {

//		SistemaFacede n = new SistemaFacede();
//		try {
//			n.localizarCarona(0,"Jp", "Cg");
//			n.criarUsuario(null, null, null, null, null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String hora = "12:34";
		System.out.println(hora.substring(0, 2));
		System.out.println(hora.substring(3));
		String data = "12/12/2012";
		System.out.println(data.substring(0, 2));
		System.out.println(data.substring(3, 5));
		System.out.println(data.substring(6));
	}
}
