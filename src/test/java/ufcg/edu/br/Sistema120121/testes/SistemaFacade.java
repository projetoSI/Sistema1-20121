package ufcg.edu.br.Sistema120121.testes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

import ufcg.edu.br.Sistema120121.excecoes.SolicitacaoException;
import ufcg.edu.br.Sistema120121.excecoes.UserException;
import ufcg.edu.br.Sistema120121.sistema.Arquivo;
import ufcg.edu.br.Sistema120121.sistema.Carona;
import ufcg.edu.br.Sistema120121.sistema.Data;
import ufcg.edu.br.Sistema120121.sistema.Hora;
import ufcg.edu.br.Sistema120121.sistema.IdentificadorCarona;
import ufcg.edu.br.Sistema120121.sistema.Perfil;
import ufcg.edu.br.Sistema120121.sistema.Sistema;
import ufcg.edu.br.Sistema120121.sistema.Solicitacao;
import ufcg.edu.br.Sistema120121.sistema.User;

public class SistemaFacade {

	// Redistribuir alguns metodos em classes controllers
	//Mais metodos e mais testes
	

	private static SistemaFacade facade = new SistemaFacade();
	private User user;
	private Carona carona;
	
	public static SistemaFacade getInstanceFacade() {
		return facade;
	}
	
	private SistemaFacade() {
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		Sistema.addUsuario(login, senha, nome, endereco, email, "112121212");
	}
	
	public void zerarSistema() throws IOException {
		user = null;
		Arquivo.zeraArquivos();
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		String result = "";

		user = Sistema.getUser(login);
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

	public String abrirSessao(String login, String senha) throws Exception {
		user = Sistema.acessarConta(login, senha);
		Sistema.abreSessaoUser(login);
		return user.getID().toString();
	}

	public void encerrarSessao(String login){
		Sistema.fechaSessaoUser(login);
	}
	
	public void encerrarSistema() throws IOException {
		Arquivo.escreveArquivo();
	}
	
	
	public String localizarCarona(String sessao, String origem, String destino) throws Exception{
		String result = "";
		
		if (origem.matches("[A-Za-zÇ-ú\\s]*+") && destino.matches("[A-Za-zÇ-ú\\s]*+")) {
			if (Sistema.getCaronas(origem, destino).size() == 0) {
				result = "{}";
			} else {
				result = "{";

				for (Carona carona : Sistema.getCaronas(origem, destino)) {
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
		
		for (User aux : Sistema.getUsuariosCadastrados()) {
			if (!sessao.equals(aux.getID().toString()))
				throw new Exception("Sessão inexistente");
		}
		
		try {
			Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new Exception("Vaga inválida");
		}
		
		Hora horaAux = new Hora(hora);
		Data dataAux = new Data(data);
		Sistema.addCarona(origem, destino, horaAux, dataAux, Integer.parseInt(vagas), user);
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
	
// até aqui US03 + 02 + 01
	
	public String sugerirPontoEncontro(String IDSessao, String IDCarona, String pontos) throws Exception{
		String result = "";
		
		if (pontos == null || pontos.isEmpty())
			throw new Exception("Ponto Inválido");
	
		Sistema.getCaronaID(IDCarona).setPontoDeEncontro(pontos);
		result = pontos;
		
		return result;
	}
	
	public void responderSugestaoPontoEncontro(String IDSessao, String IdCarona, String IDSugestao, String pontos) throws Exception{
		if (pontos == null || pontos.isEmpty())
			throw new Exception("Ponto Inválido");
		if (!IDSugestao.equalsIgnoreCase(pontos)){
			Sistema.getCaronaID(IdCarona).setPontoDeEncontro(pontos);
		}
	}
	
	public String solicitarVagaPontoEncontro(String IDSessao, String IDCarona, String ponto) throws Exception{
		if (ponto == null || ponto.isEmpty())
			throw new Exception("Ponto Inválido");

		Solicitacao s = new Solicitacao(Sistema.getCaronaID(IDCarona), user, ponto);
		Sistema.addSolicitacao(Sistema.getCaronaID(IDCarona), user, ponto);
		return s.getSolicitacaoID();
	}
	
	public void aceitarSolicitacaoPontoEncontro(String IDSessao, String IDSolicitacao) throws Exception{
		Sistema.solicitacaoAceita(IDSolicitacao);
		Sistema.getSolicitacao(IDSolicitacao).confirmarCarona();
	}
	
	public void desistirRequisicao(String IDSessao, String IDCarona, String IDSolicitacao) throws Exception{
		Carona aux = Sistema.getSolicitacao(IDSolicitacao).getCaronaDesejada();
		if (Sistema.getCaronaID(IDCarona).getID().equals(aux.getID())){
			Sistema.apagaCarona(Sistema.getCaronaID(IDCarona));
		} else{
			throw new Exception("Desistencia não confirmada!");
		}
	}
	
	public String getAtributoSolicitacao(String IDSolicitacao, String atributo) throws SolicitacaoException{
		String result = "";
		Solicitacao solicitacao = Sistema.getSolicitacao(IDSolicitacao);
		
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
			throw new SolicitacaoException("Atributo inexistente");
		
		return result;
	}
	
// US04	
	
	public String getAtributoPerfil(String login,String atributo) throws Exception{
		Perfil pf= new Perfil(Sistema.getUser(login));
		
		String result;
		if (atributo.equals("nome"))
			result = pf.exibeNome();
		else if (atributo.equals("endereco"))
			result = pf.exibeEndereco();
		else if (atributo.equals("email"))
			result = pf.exibeEmail();
		else if (atributo.equals("historico de caronas"))
			result = pf.exibeHistoricoDeCaronas();
		else if (atributo.equals("historico de vagas em caronas"))
			result = "" + pf.exibeHistoricoDeVagas();
		else if (atributo.equals("caronas seguras e tranquilas"))
			result = "" + pf.exibeCaronasSeguras();
		else if (atributo.equals("caronas que não funcionaram"))
			result = "" + pf.exibeCaronasNaoFuncionaram();
		else if (atributo.equals("faltas em vagas de caronas"))
			result = "" + pf.exibeFaltas();
		else if (atributo.equals("presenças em vagas de caronas"))
			result = "" + (pf.exibeHistoricoDeVagas().size() - pf.exibeFaltas());		
		else
			throw new Exception("Atributo inexistente");

		return result;
		
	}
	
	public Perfil visualizarPerfil(String idSessao,String login) throws Exception{
		if(Sistema.getUser(login) == null) throw new Exception("Login inválido");
		Perfil perfil = new Perfil(Sistema.getUser(login));
		return perfil;
		
	}
	
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
		files.add("scripts/US02.txt");
		// Instantiate the Monopoly Game façade
		SistemaFacade monopolyGameFacade = getInstanceFacade();
		// Instantiate EasyAccept façade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(monopolyGameFacade,
				files);
		// Execute the tests
		eaFacade.executeTests();
		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());
	}

}
