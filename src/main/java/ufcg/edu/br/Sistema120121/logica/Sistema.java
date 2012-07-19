package ufcg.edu.br.Sistema120121.logica;

import java.io.IOException;
import java.util.List;

import ufcg.edu.br.Sistema120121.dados.*;


public class Sistema {
	
	private static Sistema sistema = new Sistema();
	private static AcessaDados controlaDados = AcessaDados.getInstance();
	
	
	private Sistema(){
	}
	
	
	public static Sistema getInstance() {
		return sistema;
	}
	
	
	/**
	 * Adiciona um novo usuario no sistemka.
	 * @param login
	 * 		Login do novo usuario.
	 * @param senha
	 * 		Senha do novo usuario.
	 * @param nome
	 * 		Nome do novo usuario.
	 * @param endereco
	 * 		Endereço do novo usuario.
	 * @param email
	 * 		Email do novo usuario.
	 * @param telefone
	 * 		Telefone do novo usuario.
	 * @throws UserException 
	 */
	public void addUsuario(String login,String senha,String nome,String endereco,String email,String telefone) throws UserException {
		controlaDados.addUsuario(login, senha, nome,endereco,email,telefone);
	}
	
	
	public void zerarDados() throws IOException{
		controlaDados.zeraRepositorios();
		controlaDados.limparArquivo();
	}
	
	public void guardarDados() throws IOException{
		controlaDados.escreverArquivo();
	}
	
	
	/**
	 * Adiciona uma nova carona ao sistema.
	 * @param origem
	 * 		Local de origem da carona.
	 * @param destino
	 * 		Local de destino da carona.
	 * @param hora
	 * 		A hora de saida.
	 * @param data
	 * 		A data da carona.
	 * @param qntVagas
	 * 		A quantidade de vagas de carona.
	 * @param motorista
	 * 		O motorista da carona.
	 * @param ehMunicipal 
	 * @throws CaronaException 
	 */
	public void addCarona(String origem,String destino,Hora hora,Data data,int qntVagas,User motorista, boolean ehMunicipal) throws CaronaException {
		controlaDados.addCarona(origem, destino, hora, data, qntVagas, motorista,ehMunicipal);
	}
	
	public void addCarona(String origem,String destino,String cidade,Hora hora,Data data,int qntVagas,User motorista, boolean ehMunicipal) throws Exception {
		controlaDados.addCarona(origem, destino,cidade, hora, data, qntVagas, motorista,ehMunicipal);
	}
	
	
	/**
	 * Retorna a lista de usuarios cadastrados no sistema.
	 * @return
	 * 		A lista de usuarios cadastrados.
	 */
	public List<User> getUsuariosCadastrados() {
		return controlaDados.getUsuariosCadastrados();
	}
	
	/**
	 * Busca um determinado usuario através de seu login.
	 * @param login
	 * 		O login do usuario a ser buscado.
	 * @return
	 * 		O usuario.
	 * @throws UserException 
	 */
	public User getUser(String login) throws UserException {
		return controlaDados.getUser(login);
		
	}
	
	/**
	 * Acessa a conta de um determinado usuario atraves de login e senha.
	 * @param login
	 * 		O login do usuario a ser buscado.
	 * @param senha
	 * 		A senha do usuario a ser buscado.
	 * @return
	 * 		O usuario.
	 * @throws Exception
	 */
	public User acessarConta(String login,String senha) throws UserException {
		User usuario = null;
		
		if (senha == null ||senha.isEmpty()) {
			throw new UserException("Senha inválida");
		}
		
		usuario = controlaDados.getUser(login);
		if (usuario == null)
			throw new UserException("Usuário inexistente");
		
		if (!usuario.getSenha().equals(senha))
			throw new UserException("Login inválido");
		
		
		return usuario;
		
	}
	
	/**
	 * Retorna uma determinada carona através de sua origem e destino.
	 * @param origem
	 * 		A origem da carona.
	 * @param destino
	 * 		O destino da carona.
	 * @return
	 * 		A carona desejada.
	 * @throws CaronaException 
	 */
	public List<Carona> getCaronas(String origem, String destino) throws CaronaException {
		return controlaDados.localizarCarona(origem, destino);
	}
	
	public List<Carona> getCaronasMunicipais(String cidade, String origem, String destino) throws CaronaException {
		return controlaDados.localizarCaronaMunicipal(cidade, origem, destino);
	}
		
	
	
	public Carona getCaronaID(String id) throws CaronaException {
		return controlaDados.getCaronaID(id);
		
	}

	public void abreSessaoUser(String login) {
		controlaDados.abreSessaoUser(login);
	}

	public void fechaSessaoUser(String login) {
		controlaDados.fechaSessaoUser(login);
	}

	public Solicitacao getSolicitacao(String IdSolicitacao) throws SolicitacaoException {
		return controlaDados.getSolicitacao(IdSolicitacao);
	}

	public void addSolicitacao(Carona caronaID, User user, String ponto) throws PontoDeEncontroException {
		controlaDados.addSolicitacao(caronaID, user, ponto);
	}
	
	public void addSolicitacao(Carona caronaID, User user){
		controlaDados.addSolicitacao(caronaID, user);
	}

	public void apagaCarona(Carona carona) {
		controlaDados.apagaCarona(carona);
	}

	public void solicitacaoAceita(String IDSolicitacao) throws SolicitacaoException {
		controlaDados.aceitaSolicitacao(IDSolicitacao);
	}
	
	public void solicitacaoRecusada(String IDSolicitacao) throws SolicitacaoException {
		controlaDados.recusaSolicitacao(IDSolicitacao);
	}

	public void reiniciar() throws IOException {
		controlaDados.atualizaDados();
	}

	public List<Solicitacao> getSolicitacoesAceitas() {
		return controlaDados.getSolicitacoesAceitas();
	}


	public List<Carona> getCaronasMunicipais(String cidade) throws CaronaException {
		return controlaDados.localizarCaronaMunicipal(cidade);
	}


	public void addInteresse(User user, String origem, String destino, String data, String horaInicial, String horaFinal) {
		controlaDados.addInteresse(user, origem, destino, data, horaInicial, horaFinal);
	}


	public boolean enviarEmail(String nome, String emailDestino, String mensagem) {
		return controlaDados.enviarEmail(nome, emailDestino, mensagem);
	}


	public void verificaInteresses(Carona c) {
		controlaDados.verificaInteresses(c);
	}

}
