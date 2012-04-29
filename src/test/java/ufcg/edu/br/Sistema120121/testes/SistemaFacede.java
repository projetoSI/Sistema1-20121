package ufcg.edu.br.Sistema120121.testes;

import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.UserException;
import ufcg.edu.br.Sistema120121.sistema.*;

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
	
	public int cadastrarCarona(String sessao, String origem, String destino, String data, String hora, String vagas) throws Exception{
		int result = 0;
		
		if (sessao == null || sessao.isEmpty())
			throw new Exception("Sessão inválida");
		
		try {
			if (Integer.parseInt(sessao) != idSessao)
				throw new Exception("Sessão inexistente");
		} catch (Exception e) {
			throw new Exception("Sessão inexistente");
		}
		
		try {
			Integer.parseInt(vagas); // se a vaga não for um número, null, ou vazia, é inválida e a conversão é o teste!
		} catch (Exception e) {
			throw new Exception("Vaga inválida");
		}
		
		Hora horaAux = new Hora(hora);
		Data dataAux = new Data(data);
		sistema.addCarona(origem, destino, horaAux, dataAux, Integer.parseInt(vagas), user);
		idCarona = Integer.parseInt(sessao);
		carona = new Carona(origem, destino, horaAux, dataAux, Integer.parseInt(vagas), user);

		return result;
	}
	
	public String getAtributoCarona(String IDCarona, String atributo) throws Exception{
		String result = null;

		if (atributo == null || atributo.isEmpty())
			throw new Exception("Atributo inválido");
		
		if (IDCarona == null || IDCarona.isEmpty())
			throw new Exception("Identificador do carona é inválido");

		try {
			if (IDCarona.isEmpty() || Integer.parseInt(IDCarona) != idCarona)
				throw new Exception("Item inexistente");
		} catch (Exception e) {
			throw new Exception("Item inexistente");
		}

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
	
	public String getTrajeto(String IDCarona) throws Exception{
		String result = "";
		
		if (IDCarona == null)
			throw new Exception("Trajeto Inválida"); // eu sei que tá errado a concordância, mas a culpa é do teste :P

		try {
			if (IDCarona.isEmpty() || Integer.parseInt(IDCarona) != idCarona)
				throw new Exception("Trajeto Inexistente");
		} catch (Exception e) {
			throw new Exception("Trajeto Inexistente");
		}
		
		result = carona.getOrigem() + " - " + carona.getDestino();
		
		return result;
	}
	
	public String getCarona(String id) throws Exception{
		if (id == null)
			throw new Exception("Carona Inválida");

		try {
			if (id.isEmpty() || Integer.parseInt(id) != idCarona)
				throw new Exception("Carona Inexistente");
		} catch (Exception e) {
			throw new Exception("Carona Inexistente");
		}

		return carona.toString();
	}
	
//	ate aqui US03 + 02 + 01
	
	
	
	
	
	
	
//	ate aqui US04
}
