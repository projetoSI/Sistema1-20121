package ufcg.edu.br.Sistema120121.sistema;

import java.io.IOException;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.SolicitacaoException;

public class AcessaDados {
	
	//ClASSE CONTROLLE DOS REPOSITORIOS
	
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
	 * @throws Exception
	 */
	public static void addUsuario(String login,String senha,String nome,String endereco,String email,String telefone) throws Exception{
		RepositorioUsuario.addUser(login, senha, nome,endereco,email,telefone);
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
	 * @throws Exception
	 */
	public static void addCarona(String origem,String destino,Hora hora,Data data,int qntVagas,User motorista) throws Exception {
		RepositorioCaronas.addCarona(origem, destino, hora, data, qntVagas, motorista);
	}
	
	/**
	 * Retorna a lista de usuarios cadastrados no sistema.
	 * @return
	 * 		A lista de usuarios cadastrados no sistema.
	 */
	public static List<User> getUsuariosCadastrados() {
		return RepositorioUsuario.getUsuarios();
	}
	
	/**
	 *Retorna um determinado usuario dado o login. 
	 * @param login
	 * 		O login do usuario.
	 * @return
	 * 		O usuario.
	 * @throws Exception
	 */
	public static User getUser(String login) throws Exception{
		return RepositorioUsuario.getUsuarioLogin(login);
		
	}
	
	/**
	 * Localiza uma determinada carona atraves de sua origem e destino.
	 * @param origem
	 * 		Local de partida da carona.
	 * @param destino
	 * 		Local de chegada da carona.
	 * @return
	 * 		A carona.
	 * @throws Exception
	 */
	public static List<Carona> localizarCarona(String origem ,String destino) throws Exception {
		return RepositorioCaronas.getCaronas(origem, destino);

	}
	
	/**
	 * Retorna a lista de caronas cadastradas.
	 * @return
	 * 		A lista de caronas cadastradas.
	 */
	public static List<Carona> caronasCadastradas() {
		return RepositorioCaronas.getCaronasCadastradas();
	}
	
	/**
	 * Retorna a lista de caronas de um determinado usuario.
	 * @param motorista
	 * 		O usuario a ter sua lista de caronas exibida.
	 * @return
	 * 		A lista de caronas de um determinado usuario.
	 */
	public static List<Carona> getCaronasDoUsuario(User motorista) {
		return RepositorioCaronas.recuperaCaronaUser(motorista);
	}
	
	public static Carona getCaronaID(String id) throws Exception{
		return RepositorioCaronas.getCarona(id);
	}
	
	public static List<Carona> historicoCarona(User usuario){
		return RepositorioCaronas.getTodasCaronas(usuario);
	}

	public static void abreSessaoUser(String login) {
		RepositorioUsuario.abreSessaoUser(login);
	}

	public static void fechaSessaoUser(String login) {
		RepositorioUsuario.fechaSessaoUser(login);
	}

	public static void atualizaDados() throws IOException {
		RepositorioCaronas.atualizaRepositorio();
		RepositorioUsuario.atualizaRepositorio();
	}

	public static Solicitacao getSolicitacao(String idSolicitacao) throws SolicitacaoException {
		return RepositorioSolicitacoes.getSolicitacao(idSolicitacao);
	}

	public static void addSolicitacao(Carona caronaID, User user, String ponto) throws Exception {
		RepositorioSolicitacoes.addSolicitacao(caronaID, user, ponto);
	}

	public static void addSolicitacao(Carona caronaID, User user) {
		RepositorioSolicitacoes.addSolicitacao(caronaID, user);
	}
	public static void apagaCarona(Carona carona) {
		RepositorioCaronas.apagaCarona(carona);
	}

	public static void aceitaSolicitacao(String IDSolicitacao) throws SolicitacaoException {
		RepositorioSolicitacoes.aceitaSolicitacao(IDSolicitacao);
	}
	
	public static void recusaSolicitacao(String IDSolicitacao) throws SolicitacaoException {
		RepositorioSolicitacoes.recusaSolicitacao(IDSolicitacao);
	}

	public static List<Solicitacao> getSolicitacoesAceitas() {
		return RepositorioSolicitacoes.getSolicitacoesAceitas();
	}
	
}
