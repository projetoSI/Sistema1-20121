package ufcg.edu.br.Sistema120121.testes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

import ufcg.edu.br.Sistema120121.excecoes.UserException;
import ufcg.edu.br.Sistema120121.sistema.Arquivo;
import ufcg.edu.br.Sistema120121.sistema.Carona;
import ufcg.edu.br.Sistema120121.sistema.Data;
import ufcg.edu.br.Sistema120121.sistema.Hora;
import ufcg.edu.br.Sistema120121.sistema.IdentificadorCarona;
import ufcg.edu.br.Sistema120121.sistema.Sistema;
import ufcg.edu.br.Sistema120121.sistema.Solicitacao;
import ufcg.edu.br.Sistema120121.sistema.User;

public class SistemaFacede {

	// Redistribuir alguns metodos em classes controllers
	//Mais metodos e mais testes
	

	private static SistemaFacede facede = new SistemaFacede();
	private Sistema sistema;
	private static List<User> usuarios;
	private User user;
	private Carona carona;
	private int idSessao;
	private Solicitacao solicitacao;

	public static SistemaFacede getInstanceFacede() {
		return facede;
	}
	
	private SistemaFacede() {
		sistema = new Sistema();
		usuarios = sistema.getUsuariosCadastrados();
		idSessao = 0;
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		sistema.addUsuario(login, senha, nome, endereco, email, "112121212");
	}
	
	public void zerarSistema() throws IOException {
		idSessao = 0;
		Arquivo.zeraArquivos();
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
		idSessao++;
		int result = idSessao;
		user = sistema.acessarConta(login, senha);
		return result;
	}
	
	public void encerrarSessao(String login){
		if (user != null && login.equals(user.getLogin())){
			user = null;
			solicitacao = null;
		}
	}

	public void encerrarSistema() throws IOException {
		Arquivo.escreveArquivo();
	}
	
	
	public String localizarCarona(int sessão, String origem, String destino) throws Exception{
		String result = "";
		
		if (origem.matches("[A-Za-zÇ-ú\\s]*+") && destino.matches("[A-Za-zÇ-ú\\s]*+")) {
			if (sistema.getCaronas(origem, destino).size() == 0) {
				result = "{}";
			} else {
				result = "{";

				for (Carona carona:sistema.getCaronas(origem, destino)) {
					if (result.equals("{")) {
						result += carona.getID().toString();
					} else {
						result += "," + carona.getID().toString();
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
	
	public IdentificadorCarona cadastrarCarona(String sessao, String origem, String destino, String data, String hora, String vagas) throws Exception{
		
		if (sessao == null || sessao.isEmpty())
			throw new Exception("Sessão inválida");
		
		try {
			if (Integer.parseInt(sessao) != idSessao)
				throw new Exception("Sessão inexistente");
		} catch (Exception e) {
			throw new Exception("Sessão inexistente");
		}
		
		try {
			Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new Exception("Vaga inválida");
		}
		
		Hora horaAux = new Hora(hora);
		Data dataAux = new Data(data);
		sistema.addCarona(origem, destino, horaAux, dataAux, Integer.parseInt(vagas), user);
		carona = new Carona(origem, destino, horaAux, dataAux, Integer.parseInt(vagas), user);

		return carona.getID();
	}
	
	public String getAtributoCarona(String IDCarona, String atributo) throws Exception{
		String result = null;
		
		if(IDCarona == null || IDCarona.equals("")){
			throw new Exception("Identificador do carona é inválido");
		}
		Carona carona = Sistema.getCaronaID(IDCarona); 
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
	
	public String getTrajeto(String IDCarona) throws Exception{
		String result = "";
		Carona carona;
		
		if (IDCarona == null)
			throw new Exception("Trajeto Inválida");

		try {
			carona = Sistema.getCaronaID(IDCarona);
			if (IDCarona.isEmpty())
				throw new Exception("Trajeto Inexistente");
		} catch (Exception e) {
			throw new Exception("Trajeto Inexistente");
		}
		
		result = carona.getOrigem() + " - " + carona.getDestino();
		
		return result;
	}
	
	public String getCarona(String id) throws Exception{
		Carona carona;
		if (id == null)
			throw new Exception("Carona Inválida");

			if (id.isEmpty())
				throw new Exception("Carona Inexistente");
			
			try {
				carona = Sistema.getCaronaID(id);
			} catch (Exception e) {
				throw new Exception("Carona Inexistente");
			}
		return carona.toString();
	}
	
//	ate aqui US03 + 02 + 01
	
	public String sugerirPontoEncontro(String IDSessao, String IDCarona, String pontos) throws Exception{
		String result = "";
	
		Sistema.getCaronaID(IDCarona).setPontoDeEncontro(pontos);
		result = pontos;
		
		return result;
	}
	
	public void responderSugestaoPontoEncontro(String IDSessao, String IdCarona, String IDSugestao, String pontos) throws Exception{
		if (!IDSugestao.equalsIgnoreCase(pontos)){
			Sistema.getCaronaID(IdCarona).setPontoDeEncontro(pontos);
		}
	}
	
	public Solicitacao solicitarVagaPontoEncontro(String IDSessao, String IDCarona, String ponto) throws Exception{
		if (IDSessao != null && IDCarona != null && user != null)
			solicitacao = new Solicitacao(Sistema.getCaronaID(IDCarona), user, ponto);
		return solicitacao;
	}
	
	public String getAtributoSolicitacao(String IDSolicitacao, String atributo) throws Exception{
		String result = "";
		
		if (atributo.equals("origem"))
			result = solicitacao.getCaronaDesejada().getOrigem();
		else if (atributo.equals("destino"))
			result = solicitacao.getCaronaDesejada().getDestino();
		else if (atributo.equals("Dono da carona"))
			result = solicitacao.getDonoDaCarona();
		else if (atributo.equals("Dono da solicitacao"))
			result = solicitacao.getCaroneiro();
		else if (atributo.equals("Ponto de Encontro"))
			result = solicitacao.getPontoDeEncontro().getSugestaoAtual();
		else
			throw new Exception("Atributo inexistente");
		
		return result;
	}
	
	public void aceitarSolicitacaoPontoEncontro(String IDSessao, String IDSolicitacao){
		
	}
	
	public void desistirRequisicao(String IDSessao, String IDCarona, String IDSolicitacao){
		
	}
	
//	US04
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
		files.add("scripts/US04.txt");
		// Instantiate the Monopoly Game façade
		SistemaFacede monopolyGameFacade = getInstanceFacede();
		// Instantiate EasyAccept façade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(monopolyGameFacade,
				files);
		// Execute the tests
		eaFacade.executeTests();
		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());
	}
}
