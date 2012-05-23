package ufcg.edu.br.Sistema120121.dados;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import ufcg.edu.br.Sistema120121.logica.*;

public class AcessaDados {
	
	private static AcessaDados instance = new AcessaDados();
	private RepositorioCaronas dadosCaronas = new RepositorioCaronas() ;
	private RepositorioUsuario dadosUser = new RepositorioUsuario();
	private RepositorioSolicitacoes  dadosSolicitacao = new RepositorioSolicitacoes();
	private Arquivo arquivo = Arquivo.getInstance();

	
	private AcessaDados() {
	}
	
	public static AcessaDados getInstance(){
		return instance;
	}
	/**
	 * Adiciona um novo usuario ao sistema.
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
	 * @throws LoginErrorException 
	 * @throws UserException 
	 */
	public void addUsuario(String login,String senha,String nome,String endereco,String email,String telefone) throws UserException{
		dadosUser.addUser(login, senha, nome,endereco,email,telefone);
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
	 * @throws CaronaException 
	 */
	public void addCarona(String origem,String destino,Hora hora,Data data,int qntVagas,User motorista) throws CaronaException{
		dadosCaronas.addCarona(origem, destino, hora, data, qntVagas, motorista);
	}
	
	/**
	 * Retorna a lista de usuarios cadastrados no sistema.
	 * @return
	 * 		A lista de usuarios cadastrados no sistema.
	 */
	public List<User> getUsuariosCadastrados() {
		return dadosUser.getUsuarios();
	}
	
	public void escreverArquivo() throws IOException {
		arquivo.geraArquivo("arquivoUser.xml",getUsuariosCadastrados());
		arquivo.geraArquivo("arquivoCarona.xml",caronasCadastradas());
	}
	public void limparArquivo() throws IOException{
		arquivo.zeraArquivos();
	}
	
	/**
	 *Retorna um determinado usuario dado o login. 
	 * @param login
	 * 		O login do usuario.
	 * @return
	 * 		O usuario.
	 * @throws UserException 
	 */
	public User getUser(String login) throws UserException{
		return dadosUser.getUsuarioLogin(login);
		
	}
	
	/**
	 * Localiza uma determinada carona atraves de sua origem e destino.
	 * @param origem
	 * 		Local de partida da carona.
	 * @param destino
	 * 		Local de chegada da carona.
	 * @return
	 * 		A carona.
	 * @throws CaronaException 
	 */
	public List<Carona> localizarCarona(String origem ,String destino) throws CaronaException{
		return dadosCaronas.getCaronas(origem, destino);

	}
	
	public List<Carona> getCaronasDoCaroneiro(User caroneiro){
		return dadosCaronas.recuperaVagaCaronaUser(caroneiro);
	}

	
	/**
	 * Retorna a lista de caronas cadastradas.
	 * @return
	 * 		A lista de caronas cadastradas.
	 */
	public List<Carona> caronasCadastradas() {
		return dadosCaronas.getCaronasCadastradas();
	}
	
	/**
	 * Retorna a lista de caronas de um determinado usuario.
	 * @param motorista
	 * 		O usuario a ter sua lista de caronas exibida.
	 * @return
	 * 		A lista de caronas de um determinado usuario.
	 */
	public List<Carona> getCaronasDoMotorista(User motorista) {
		return dadosCaronas.recuperaCaronaUser(motorista);
	}
	
	public Carona getCaronaID(String id) throws CaronaException{
		return dadosCaronas.getCarona(id);
	}
	
	public List<Carona> historicoCarona(User usuario){
		return dadosCaronas.getTodasCaronas(usuario);
	}

	public void abreSessaoUser(String login) {
		dadosUser.abreSessaoUser(login);
	}

	public void fechaSessaoUser(String login) {
		dadosUser.fechaSessaoUser(login);
	}

	public void atualizaDados() throws IOException {
		dadosCaronas.atualizaRepositorio((LinkedList<Carona>) arquivo.<Carona>lerArquivo("arquivoUser.xml"));
		dadosUser.atualizaRepositorio((LinkedList<User>) arquivo.<User>lerArquivo("arquivoCarona.xml"));
	}

	public Solicitacao getSolicitacao(String idSolicitacao) throws SolicitacaoException {
		return dadosSolicitacao.getSolicitacao(idSolicitacao);
	}

	public void addSolicitacao(Carona caronaID, User user, String ponto) throws PontoDeEncontroException {
		dadosSolicitacao.addSolicitacao(caronaID, user, ponto);
	}

	public void addSolicitacao(Carona caronaID, User user) {
		dadosSolicitacao.addSolicitacao(caronaID, user);
	}
	public void apagaCarona(Carona carona) {
		dadosCaronas.apagaCarona(carona);
	}

	public void aceitaSolicitacao(String IDSolicitacao) throws SolicitacaoException {
		dadosSolicitacao.aceitaSolicitacao(IDSolicitacao);
	}
	
	public void recusaSolicitacao(String IDSolicitacao) throws SolicitacaoException {
		dadosSolicitacao.recusaSolicitacao(IDSolicitacao);
	}

	public List<Solicitacao> getSolicitacoesAceitas() {
		return dadosSolicitacao.getSolicitacoesAceitas();
	}
	
}
