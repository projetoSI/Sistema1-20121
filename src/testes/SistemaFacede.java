package testes;

import java.util.ArrayList;
import java.util.List;

import excecoes.UserException;

import sistema.*;

public class SistemaFacede {

	// Redistribuir alguns metodos em classes controllers
	//Mais metodos e mais testes
	

	private static SistemaFacede facede = new SistemaFacede();
	private Sistema sistema;
	private static List<User> usuarios;
	private User user;
	private Carona carona;
	private int idSessao;
	private int idCarona;

	public static SistemaFacede getInstanceFacede() {
		return facede;
	}
	
	private SistemaFacede() {
		sistema = new Sistema();
		usuarios = sistema.getUsuariosCadastrados();
		idSessao = 0;
		idCarona = idSessao;
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		sistema.addUsuario(login, senha, nome, endereco, email, "112121212");
	}
	
	public void criarUsuario(String login, String nome, String endereco, String email) throws Exception {
		sistema.addUsuario(login, "123", nome, endereco, email, "112121212");
	}

	public void zerarSistema() {
		sistema = new Sistema();
		usuarios = sistema.getUsuariosCadastrados();
		idSessao = 0;
		idCarona = idSessao;

		// ACREDITO QUE ISSO É O QUE DEVE SER FEITO, AFINAL É PRECISO
		// "REINICIAR" O SISTEMA -> Jonh...
		// acho q esse metodo deve tah relacionado com o goal "clean" do maven!!
		// -> Rafael
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		String result = "";

		user = sistema.getUser(login);
		if (atributo == null || atributo.isEmpty())
			throw new Exception("Atributo inválido");
		
		if (user == null)
			throw new UserException("Usuário inexistente");

		if (atributo.equals("nome"))
			result = user.getNome();
		else if (atributo.equals("endereco"))
			result = user.getEndereco();
		else if (atributo.equals("email"))
			result = user.getEmail();
		else if (atributo.equals("login"))
			result = user.getLogin();
		else
			throw new Exception("Atributo inexistente");

		return result;

	}

	public int abrirSessao(String login, String senha) throws Exception {
		int result = idSessao;
		user = sistema.acessarConta(login, senha);
		return result;
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
		
		if (origem.matches("[A-Za-zÇ-ú\\s]*+") && destino.matches("[A-Za-zÇ-ú\\s]*+")) {
			if (sistema.getCaronas(origem, destino).size() == 0) {
				result = "{}";
			} else {
				result = "{";

				for (int i = 0; i < sistema.getCaronas(origem, destino).size(); i++) {
					if (result.equals("{")) {
						result += idCarona;
					} else {
						result += "," + idCarona;
					}
				}
				result += "}";
			}
		} else {
			if (!origem.matches("[A-Za-zÇ-ú\\s]*+"))
				throw new Exception("Origem inválida");
			if (!destino.matches("[A-Za-zÇ-ú\\s]*+"))
				throw new Exception("Destino inválido");
		}
		
		return result;
	}
	
	public int cadastrarCarona(String sessao, String origem, String destino, String data, String hora, int vagas) throws Exception{
		int result = 0;
		
		if (sessao == null || sessao.isEmpty()){
			throw new Exception("Sessão inválida");
		}
		
		if (hora.length() == 5 && (data.length() == 10 || data.length() == 8) /*&& vagas.matches("[0-9]*")*/){
			Hora horaAux = new Hora(hora);
			Data dataAux = new Data(data);
			sistema.addCarona(origem, destino, horaAux, dataAux, vagas, user);
			idCarona = Integer.parseInt(sessao);
			carona = new Carona(origem, destino, horaAux, dataAux, vagas, user);
		}else{
			throw new Exception();
		}
		return result;
	}
	
	public String getAtributoCarona(int sessao, String atributo) throws Exception{
		String result = null;

		if (atributo == null || atributo.isEmpty())
			throw new Exception("Atributo inválido");
		
		if (atributo.equals("origem"))
				result = carona.getOrigem();
		else if (atributo.equals("destino"))
				result = carona.getDestino();
		else if (atributo.equals("hora"))
				result = carona.getHora().getHoras();
		else if (atributo.equals("data"))
				result = carona.getData().getData();
		else if (atributo.equals("vagas"))
				result = "" + carona.getQntVagas();
		else throw new Exception("Atributo inexistente");
		
		return result;
	}
	
	public String getTrajeto(String idCarona){
		return carona.getOrigem() + " - " + carona.getDestino();
	}
	
	public String getCarona(int id) {
		return carona.toString();
	}
	
//	ate aqui US03 + 02 + 01
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
