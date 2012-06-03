package ufcg.edu.br.Sistema120121.testes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import easyaccept.EasyAcceptFacade;
import ufcg.edu.br.Sistema120121.logica.*;
import ufcg.edu.br.Sistema120121.logica.Carona.Situacao;

public class SistemaFacade {

	private User user;
	private Carona carona;
	private static Sistema sistema = Sistema.getInstance();
	private static SistemaFacade facade = new SistemaFacade();
	private static Map<String, List<String>> refCaronasUsers = new TreeMap<String, List<String>>();

	public static SistemaFacade getInstance() {
		return facade;
	}

	private SistemaFacade() {
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		sistema.addUsuario(login, senha, nome, endereco, email, "112121212");
	}

	public void zerarSistema() throws IOException {
		user = null;
		sistema.zerarDados();
		refCaronasUsers = new TreeMap<String, List<String>>();
	}

	public String getAtributoUsuario(String login, String atributo)	throws Exception {
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

	public String abrirSessao(String login, String senha) throws UserException {
		user = sistema.acessarConta(login, senha);
		sistema.abreSessaoUser(login);
		return user.getID().toString();
	}

	public void encerrarSessao(String login) {
		sistema.fechaSessaoUser(login);
	}

	public void encerrarSistema() throws IOException {
		sistema.guardarDados();
	}
	
	public String localizarCaronaMunicipal(String sessao,String cidade, String origem, String destino)	throws Exception {
		String result = "";

		if (cidade == null || cidade.isEmpty() || !cidade.matches("[A-Za-zÇ-ú\\s]*+")) 
			throw new CaronaException("Cidade inexistente");
		
		if (cidade.matches("[A-Za-zÇ-ú\\s]*+")  &&  origem.matches("[A-Za-zÇ-ú\\s]*+") && destino.matches("[A-Za-zÇ-ú\\s]*+")) {
			if (sistema.getCaronasMunicipais(cidade,origem,destino).size() == 0) {
				result = "{}";
			} else {
				result = "{";

				for (Carona carona : sistema.getCaronasMunicipais(cidade,origem, destino)) {
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
	public String localizarCaronaMunicipal(String sessao,String cidade) throws CaronaException{
		
		if (cidade == null || cidade.isEmpty() || !cidade.matches("[A-Za-zÇ-ú\\s]*+")) 
			throw new CaronaException("Cidade inexistente");
		String result;
		if (sistema.getCaronasMunicipais(cidade).size() == 0) {
			result = "{}";
		} else {
			result = "{";

			for (Carona carona : sistema.getCaronasMunicipais(cidade)) {
				if (result.equals("{")) {
					result += carona.getID().toString();
				} else {
					result += "," + carona.getID().toString();
				}
			}
			result += "}";
		}
		
		
		
		
		return result;
	}
	
	
	public String localizarCarona(String sessao, String origem, String destino)	throws Exception {
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
	
	private void isSessaoValida(String sessao) throws Exception {
		if (sessao == null || sessao.isEmpty())
			throw new Exception("Sessão inválida");

		boolean temSessao = false;
		for (User aux : sistema.getUsuariosCadastrados()) {
			if (sessao.equals(aux.getID().toString())) {
				temSessao = true;
				break;
			}
		}
		
		if (!temSessao)
			throw new Exception("Sessão inexistente");

		mudaUserAtual(sessao);
	}
	
	private void atualizaMapa(Carona c) {
		if (refCaronasUsers.containsKey(user.getID().toString())) {
			refCaronasUsers.get(user.getID().toString()).add(c.getID().toString());
		} else {
			List<String> aux = new LinkedList<String>();
			aux.add(c.getID().toString());
			refCaronasUsers.put(user.getID().toString(), aux);
		}
	}

	public IdentificadorCarona cadastrarCarona(String sessao, String origem, String destino, String data, String hora, String vagas) throws Exception {
		try {
			Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new Exception("Vaga inválida");
		}
		Hora horaAux = new Hora(hora);
		Data dataAux = new Data(data);
		Carona c = new Carona(origem, destino,horaAux, dataAux, Integer.parseInt(vagas), user,false);
		isSessaoValida(sessao);
		sistema.addCarona(c.getOrigem(),c.getDestino(), c.getHora(), c.getData(), c.getQntVagas(), c.getMotorista(),c.isMunicipal());
		atualizaMapa(c);
		return c.getID();
	}
	
	
	public IdentificadorCarona cadastrarCaronaMunicipal(String sessao, String origem, String destino,String cidade, String data, String hora, String vagas) throws Exception {
		try {
			Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new Exception("Vaga inválida");
		}
		Hora horaAux = new Hora(hora);
		Data dataAux = new Data(data);
		Carona c = new CaronaMunicipal(origem, destino,cidade, horaAux, dataAux, Integer.parseInt(vagas), user,true);
		isSessaoValida(sessao);
		sistema.addCarona(c.getOrigem(),c.getDestino(), ((CaronaMunicipal) c).getCidade(), c.getHora(), c.getData(), c.getQntVagas(), c.getMotorista(),c.isMunicipal());
		atualizaMapa(c);
		return c.getID();
		
		
	}
	
	private void mudaUserAtual(String sessao) {
		for (User aux : sistema.getUsuariosCadastrados()) {
			if (aux.getID().toString().equals(sessao)) {
				user = aux;
				break;
			}
		}
	}

	public String getAtributoCarona(String IDCarona, String atributo) throws Exception {
		String result = null;

		if (IDCarona == null || IDCarona.equals("")) {
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
		else if (atributo.equals("ehMunicipal"))
			result = "" + carona.isMunicipal();
		else
		    throw new Exception("Atributo inexistente");

		return result;
	}

	public String getTrajeto(String IDCarona) throws Exception {
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

	public String getCarona(String id) throws Exception {
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

	public String sugerirPontoEncontro(String IDSessao, String IDCarona, String pontos) throws Exception {
		String result = "";

		if (pontos == null || pontos.isEmpty())
			throw new Exception("Ponto Inválido");

		sistema.getCaronaID(IDCarona).setPontoDeEncontro(pontos);
		result = pontos;

		return result;
	}

	public void responderSugestaoPontoEncontro(String IDSessao, String IdCarona, String IDSugestao, String pontos) throws Exception {
		if (pontos == null || pontos.isEmpty())
			throw new Exception("Ponto Inválido");
		if (!IDSugestao.equalsIgnoreCase(pontos)) {
			sistema.getCaronaID(IdCarona).setPontoDeEncontro(pontos);
		}
	}

	public String solicitarVagaPontoEncontro(String IDSessao, String IDCarona, String ponto) throws Exception {
		if (ponto == null || ponto.isEmpty())
			throw new Exception("Ponto Inválido");

		Solicitacao s = new Solicitacao(sistema.getCaronaID(IDCarona), user, ponto);
		sistema.addSolicitacao(sistema.getCaronaID(IDCarona), user, ponto);
		return s.getSolicitacaoID();
	}

	public void aceitarSolicitacaoPontoEncontro(String IDSessao, String IDSolicitacao) throws SolicitacaoException, CaronaException {
		aceitarSolicitacao(IDSessao, IDSolicitacao);
	}

	public void desistirRequisicao(String IDSessao, String IDCarona, String IDSolicitacao) throws Exception {
		Carona aux = sistema.getSolicitacao(IDSolicitacao).getCaronaDesejada();
		if (sistema.getCaronaID(IDCarona).getID().equals(aux.getID())) {
			sistema.apagaCarona(sistema.getCaronaID(IDCarona));
		} else {
			throw new Exception("Desistencia não confirmada!");
		}
	}

	public String getAtributoSolicitacao(String IDSolicitacao, String atributo)	throws SolicitacaoException {
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

	public String solicitarVaga(String idSessao, String idCarona) throws Exception {
		mudaUserAtual(idSessao);

		Solicitacao solicitacao = new Solicitacao(sistema.getCaronaID(idCarona), user);
		sistema.addSolicitacao(sistema.getCaronaID(idCarona), user);

		return solicitacao.getSolicitacaoID();
	}

	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws SolicitacaoException, CaronaException {
		sistema.solicitacaoAceita(idSolicitacao);
		sistema.getSolicitacao(idSolicitacao).confirmarSolicitacao();
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws SolicitacaoException {
		sistema.solicitacaoRecusada(idSolicitacao);
	}

	public String getAtributoPerfil(String login, String atributo) throws Exception {
		User perfilUsuario = sistema.getUser(login);

		String result = null;
		if (atributo.equals("nome"))
			result = perfilUsuario.getNome();
		else if (atributo.equals("endereco"))
			result = perfilUsuario.getEndereco();
		else if (atributo.equals("email"))
			result = perfilUsuario.getEmail();
		else if (atributo.equals("historico de caronas")) {
			result = "[";
			for (Carona c : perfilUsuario.exibeHistoricoDeCaronas()) {
				if (result.equals("[")) {
					result += c.getID().toString();
				} else {
					result += "," + c.getID().toString();
				}
			}
			result += "]";
		} else if (atributo.equals("historico de vagas em caronas")){
			result = "[";
			for (Carona c : perfilUsuario.exibeHistoricoDeVagas()) {
				if (result.equals("[")) {
					result += c.getID().toString();
				} else {
					result += "," + c.getID().toString();
				}
			}
			result += "]";
		}else if (atributo.equals("caronas seguras e tranquilas"))
			result = "" + perfilUsuario.getCaronasSeguras();
		else if (atributo.equals("caronas que não funcionaram"))
			result = "" + perfilUsuario.getCaronasNaoFuncionaram();
		else if (atributo.equals("faltas em vagas de caronas"))
			result = "" + perfilUsuario.exibeFaltas();
		else if (atributo.equals("presenças em vagas de caronas"))
			result = "" + perfilUsuario.getPresencas();
		else
			throw new Exception("Atributo inexistente");

		return result;

	}

	public User visualizarPerfil(String idSessao, String login)	throws Exception {
		if (sistema.getUser(login) == null)
			throw new Exception("Login inválido");
		User perfilUsuario = sistema.getUser(login);
		return perfilUsuario;

	}

	public void reiniciarSistema() throws IOException {
		sistema.reiniciar();
	}

	public String getCaronaUsuario(String idSessao, String indexCarona)	throws Exception {
		String idCarona = "";

		if (refCaronasUsers.containsKey(idSessao))
			idCarona = refCaronasUsers.get(idSessao).get(Integer.parseInt(indexCarona) - 1);

		return idCarona;
	}

	public String getTodasCaronasUsuario(String idSessao) throws Exception {
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

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
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

	public String getSolicitacoesPendentes(String idSessao, String idCarona) {
		return "";
	}

	public String getPontosSugeridos(String idSessao, String idCarona) throws Exception {
		String result = "";

		for (String idAux : refCaronasUsers.get(idSessao)) {
			if (idAux.equals(idCarona))
				result = sistema.getCaronaID(idAux).getPontoDeEncontro().getSugestaoAtual();
		}

		return result;
	}

	public String getPontosEncontro(String idSessao, String idCarona) throws Exception {
		return getPontosSugeridos(idSessao, idCarona);
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona, String login, String review) throws Exception{
		
		if (review.equals("faltou")) {
			if (sistema.getCaronaID(idCarona).verificaCaroneiro(sistema.getUser(login))){
				sistema.getCaronaID(idCarona).setSituacao(Situacao.FALTOU, sistema.getUser(login));
			}
		}else if(review.equals("não faltou")){
			if (sistema.getCaronaID(idCarona).verificaCaroneiro(sistema.getUser(login))){
				sistema.getCaronaID(idCarona).setSituacao(Situacao.NAO_FALTOU, sistema.getUser(login));
			}
		}else if(review.equals("não funcionou")){
			if (!sistema.getCaronaID(idCarona).verificaCaroneiro(sistema.getUser(login))) {
				throw new Exception("Usuário não possui vaga na carona.");
			} else{
				sistema.getUser(login).addCaronasNaoFuncionaram();
			}
		}else{
			throw new Exception("Opção inválida.");
		}
	
	}
	
	public void reviewCarona(String idSessao, String idCarona, String review) throws Exception{
		mudaUserAtual(idSessao);

		if (review.equals("segura e tranquila")) {
			if (user.getID().sessaoAtiva())
			sistema.getCaronaID(idCarona).getMotorista().addCaronasSeguras();
		}else if(review.equals("não funcionou")){
			if (!sistema.getCaronaID(idCarona).verificaCaroneiro(sistema.getUser(user.getLogin())))
				throw new Exception("Usuário não possui vaga na carona.");
			else
				sistema.getCaronaID(idCarona).getMotorista().addCaronasNaoFuncionaram();		
		}else
			throw new Exception("Opção inválida.");
	}
	
	
	
	public String cadastrarInteresse(String sessao,String origem,String destino, String data,String horaInicial,String horaFinal) throws Exception {
//		Aqui verifica se a data e hora passada sao validas...
		
		if (origem == null || !origem.matches("[A-Za-zÇ-ú\\s]*+"))
			throw new Exception("Origem inválida");
		if (destino == null || !destino.matches("[A-Za-zÇ-ú\\s]*+"))
			throw new Exception("Destino inválido");
		
		
		
		if(horaInicial.isEmpty())
			horaInicial = "00:00";
		if(horaFinal.isEmpty())
			horaFinal = "23:59";
		if(data != null && data.isEmpty()){
		 //data = DATA ATUAL
		data = "30/06/2012";//so para testes
		}else if (data == null){
			throw new Exception("Data inválida");
		}
		
		
		Hora horaInicio = new Hora(horaInicial);
		Hora horaFim = new Hora(horaFinal);
		Data dataAux = new Data(data);
		Interesse interesse = new Interesse(user,origem,destino,data,horaInicial,horaFinal);
		sistema.addInteresse();
		return interesse.getID();
	}
	
	
	public String verificaMensagensPerfil(String sessao) {
		return user.visualizarMensagens();
	}
	
	
	public boolean enviarEmail(String idSessao, String emailDestino, String mensagem){
		mudaUserAtual(idSessao);
		return sistema.enviarEmail(user.getEmail(), )
	}
	
	
	
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

//		files.add("scripts/US01.txt");
//		files.add("scripts/US02.txt");
//		files.add("scripts/US03.txt");
//		files.add("scripts/US04.txt");
//		files.add("scripts/US05.txt");
//		files.add("scripts/US06.txt");
//		files.add("scripts/US07.txt");
//		files.add("scripts/US08.txt");
//		files.add("scripts/US09.txt");
//		files.add("scripts/US10.txt");
//		files.add("scripts/US11.txt");
		files.add("scripts/US12.txt");

		SistemaFacade monopolyGameFacade = getInstance();
		
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(monopolyGameFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
