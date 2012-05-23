package ufcg.edu.br.Sistema120121.testes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import easyaccept.EasyAcceptFacade;

import ufcg.edu.br.Sistema120121.excecoes.SolicitacaoException;
import ufcg.edu.br.Sistema120121.excecoes.UserException;
import ufcg.edu.br.Sistema120121.sistema.*;

public class SistemaFacade {
	
	private User user;
	private Carona carona;
	private static Sistema sistema = Sistema.getInstance();
	private static SistemaFacade facade = new SistemaFacade();
	private Map<String, List<String>> refCaronasUsers = new TreeMap<String, List<String>>();
	
	public static SistemaFacade getInstance() {
		return facade;
	}
	
	private SistemaFacade() {
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		sistema.addUsuario(login, senha, nome, endereco, email, "112121212");
	}
	
	public void zerarsistema() throws IOException {
		user = null;
		sistema.zerarDados();
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

	public String abrirSessao(String login, String senha) throws Exception {
		user = sistema.acessarConta(login, senha);
		sistema.abreSessaoUser(login);
		return user.getID().toString();
	}

	public void encerrarSessao(String login){
		sistema.fechaSessaoUser(login);
	}
	
	public void encerrarsistema() throws IOException {
		sistema.guardarDados();
	}
	
	
	public String localizarCarona(String sessao, String origem, String destino) throws Exception{
		String result = "";
		
		if (origem.matches("[A-Za-zÇ-ú\\s]*+") && destino.matches("[A-Za-zÇ-ú\\s]*+")) {
			if (sistema.getCaronas(origem, destino).size() == 0) {
				result = "{}";
			} else {
				result = "{";

				for (Carona carona : sistema.getCaronas(origem, destino)) {
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
		
		boolean temSessao = false; 
		for (User aux : sistema.getUsuariosCadastrados()) {
			if(sessao.equals(aux.getID().toString())){
				temSessao = true;
				break;
			}
		}
		if (!temSessao)
			throw new Exception("Sessão inexistente");
		
		try {
			Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new Exception("Vaga inválida");
		}
		
		mudaUserAtual(sessao);
		
		Hora horaAux = new Hora(hora);
		Data dataAux = new Data(data);
		sistema.addCarona(origem, destino, horaAux, dataAux, Integer.parseInt(vagas), user);
		carona = new Carona(origem, destino, horaAux, dataAux, Integer.parseInt(vagas), user);

		if (refCaronasUsers.containsKey(user.getID().toString())){
			refCaronasUsers.get(user.getID().toString()).add(carona.getID().toString());
		}else{
			List<String> aux = new LinkedList<String>();
			aux.add(carona.getID().toString());
			refCaronasUsers.put(user.getID().toString(), aux);
		}
		
		return carona.getID();
	}
	
	private void mudaUserAtual(String sessao) {
		for (User aux : sistema.getUsuariosCadastrados()) {
			if (aux.getID().toString().equals(sessao)) {
				user = aux;
				break;
			}
		}
	}

	public String getAtributoCarona(String IDCarona, String atributo) throws Exception{
		String result = null;
		
		if(IDCarona == null || IDCarona.equals("")){
			throw new Exception("Identificador do carona é inválido");
		}
		
		Carona carona = sistema.getCaronaID(IDCarona); 
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
			carona = sistema.getCaronaID(IDCarona);
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
			carona = sistema.getCaronaID(id);
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
	
		sistema.getCaronaID(IDCarona).setPontoDeEncontro(pontos);
		result = pontos;
		
		return result;
	}
	
	public void responderSugestaoPontoEncontro(String IDSessao, String IdCarona, String IDSugestao, String pontos) throws Exception{
		if (pontos == null || pontos.isEmpty())
			throw new Exception("Ponto Inválido");
		if (!IDSugestao.equalsIgnoreCase(pontos)){
			sistema.getCaronaID(IdCarona).setPontoDeEncontro(pontos);
		}
	}
	
	public String solicitarVagaPontoEncontro(String IDSessao, String IDCarona, String ponto) throws Exception{
		if (ponto == null || ponto.isEmpty())
			throw new Exception("Ponto Inválido");

		Solicitacao s = new Solicitacao(sistema.getCaronaID(IDCarona), user, ponto);
		sistema.addSolicitacao(sistema.getCaronaID(IDCarona), user, ponto);
		return s.getSolicitacaoID();
	}
	
	public void aceitarSolicitacaoPontoEncontro(String IDSessao, String IDSolicitacao) throws Exception{
		sistema.solicitacaoAceita(IDSolicitacao);
		sistema.getSolicitacao(IDSolicitacao).confirmarSolicitacao();
	}
	
	public void desistirRequisicao(String IDSessao, String IDCarona, String IDSolicitacao) throws Exception{
		Carona aux = sistema.getSolicitacao(IDSolicitacao).getCaronaDesejada();
		if (sistema.getCaronaID(IDCarona).getID().equals(aux.getID())){
			sistema.apagaCarona(sistema.getCaronaID(IDCarona));
		} else{
			throw new Exception("Desistencia não confirmada!");
		}
	}
	
	public String getAtributoSolicitacao(String IDSolicitacao, String atributo) throws SolicitacaoException{
		String result = "";
		Solicitacao solicitacao = sistema.getSolicitacao(IDSolicitacao);
		
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
	
	public String solicitarVaga(String idSessao,String idCarona) throws Exception{
		mudaUserAtual(idSessao);
		
		Solicitacao solicitacao = new Solicitacao(sistema.getCaronaID(idCarona), user);
		sistema.addSolicitacao(sistema.getCaronaID(idCarona),user);
		
		return solicitacao.getSolicitacaoID();
	}
	
	public void aceitarSolicitacao(String idSessao,String idSolicitacao) throws Exception{
		sistema.solicitacaoAceita(idSolicitacao);
		sistema.getSolicitacao(idSolicitacao).confirmarSolicitacao();		 
	}
	
	public void rejeitarSolicitacao(String idSessao,String idSolicitacao) throws SolicitacaoException{
		sistema.solicitacaoRecusada(idSolicitacao);
	}
// US05
	
	public String getAtributoPerfil(String login,String atributo) throws Exception{
		Perfil pf= new Perfil(sistema.getUser(login));
		
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
		if(sistema.getUser(login) == null) throw new Exception("Login inválido");
		Perfil perfil = new Perfil(sistema.getUser(login));
		return perfil;
		
	}
//	US06
	
	public void reiniciarsistema() throws IOException{
		sistema.reiniciar();
	}
	
	public String getCaronaUsuario(String idSessao, String indexCarona) throws Exception{
		String idCarona = "";
		
		if (refCaronasUsers.containsKey(idSessao))
			idCarona = refCaronasUsers.get(idSessao).get(Integer.parseInt(indexCarona)-1);
		
		return idCarona;
	}
	
	public String getTodasCaronasUsuario(String idSessao) throws Exception{
		String result = "{";

		for (String idCarona : refCaronasUsers.get(idSessao)) {
			if (result.equals("{")) {
				result += sistema.getCaronaID(idCarona).getID().toString();
			} else {
				result += "," + sistema.getCaronaID(idCarona).getID().toString();
			}
		}
		result += "}";

		return result;
	}
	
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona){
		String result = "{";

		for (Solicitacao s : sistema.getSolicitacoesAceitas()) {
			if (s.getCaronaDesejada().getID().toString().equals(idCarona)) {
				if (result.equals("{")) {
					result += s.getSolicitacaoID();
				} else {
					result += "," + s.getSolicitacaoID();
				}
			}
		}
		result += "}";

		return result;
	}
	
	public String getSolicitacoesPendentes(String idSessao, String idCarona){
		return "";
	}
	
	public String getPontosSugeridos(String idSessao, String idCarona) throws Exception{
		String result = "";

		for (String idAux : refCaronasUsers.get(idSessao)) {
			if (idAux.equals(idCarona))
					result = sistema.getCaronaID(idAux).getPontoDeEncontro().getSugestaoAtual();
			}

		return result;
	}
	
	public String getPontosEncontro(String idSessao, String idCarona) throws Exception{
		return getPontosSugeridos(idSessao, idCarona);
	}

	
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
		files.add("scripts/US06.txt");
		// Instantiate the Monopoly Game façade
		SistemaFacade monopolyGameFacade = getInstance();
		// Instantiate EasyAccept façade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(monopolyGameFacade,files);
		// Execute the tests
		eaFacade.executeTests();
		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());
	}

}
