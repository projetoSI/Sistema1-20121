package ufcg.edu.br.Sistema120121.logica;

import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.dados.AcessaDados;
import ufcg.edu.br.Sistema120121.logica.Carona.Situacao;

/**
 * Objeto Usuário e seus atributos
 * 
 */
public class User {

	private String login;
	private String endereco;
	private String email;
	private String telefone;
	private String nome;
	private String senha;
	private int caronasSeguras;
	private int caronasNaoFuncionaram;
	private int faltas, presenca;
	private List<User> listaDeAmigos = new LinkedList<User>();
	private List<Carona> caronasOferecidas = new LinkedList<Carona>();
	private List<Solicitacao> solicitacoes;
	private IdentificadorSessaoUser ID;

	/**
	 * Construtor de um usuario.
	 * 
	 * @param login
	 *            Login do novo usuario.
	 * @param senha
	 *            Senha do novo usuario.
	 * @param nome
	 *            Nome do novo usuario.
	 * @param endereco
	 *            Endereço do novo usuario.
	 * @param email
	 *            Email do novo usuario.
	 * @param telefone
	 *            Telefone do novo usuario.
	 * @throws UserException
	 *             Caso o endereço esteja em um formato incorreto; Caso o email
	 *             esteja em um formato incorreto; Caso a senha inserida seja
	 *             invalida; Caso o nome esteja incorreto; Caso o telefone
	 *             esteja em um formato incorreto; Caso o login ja exista ou
	 *             esteja em um formato incorreto.
	 */
	public User(String login, String senha, String nome, String endereco,
			String email, String telefone) throws UserException {
		if (email == null || email.isEmpty()) {
			throw new UserException("Email inválido");
		}

		if (nome == null || nome.isEmpty()) {
			throw new UserException("Nome inválido");
		}

		if (login == null || senha == null || senha.isEmpty()
				|| login.isEmpty() || login.length() < 3) {
			throw new UserException("Login inválido");
		}

		this.login = login;
		setSenha(senha);
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setTelefone(telefone);
		ID = new IdentificadorSessaoUser(login, email);
		solicitacoes = new LinkedList<Solicitacao>();

	}

	/**
	 * Retorna o perfil do usuario.
	 * 
	 * @return O perfil do usuario.
	 * @throws UserException
	 *             Caso o endereço esteja em um formato incorreto; Caso o email
	 *             esteja em um formato incorreto; Caso o Endereco esteja em um
	 *             formato incorreto; Caso o nome esteja em um formato
	 *             incorreto; Caso o telefone esteja em um formato incorreto;
	 *             Caso o login esteja em um formato incorreto.
	 */

	public String vizualizaPerfil(User usuario) throws UserException {
		if (ehAmigo(usuario) || this.equals(usuario)) {
			return exibeCadastro(usuario) + "" + usuario.getListaAmigos() + ""
					+ usuario.exibeHistoricoDeCaronas() + ""
					+ usuario.exibeHistoricoDeVagas();
		}
		return "Impossivel vizualizar perfil";

	}

	/**
	 * Retorna o identificador de sesão do usuário
	 * 
	 * @return o identificador
	 */
	public IdentificadorSessaoUser getID() {
		return ID;
	}

	/**
	 * Retorna o endereço do usuario
	 * 
	 * @return O endereço do usuario.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Muda o endereço do usuario.
	 * 
	 * @param endereco
	 *            O novo endereço.
	 * @throws UserException
	 *             Caso o endereço esteja em um formato incorreto.
	 */
	public void setEndereco(String endereco) throws UserException {
		if (endereco.isEmpty() || endereco.matches("[0-9]*"))
			throw new UserException("Endereço inválido");
		else
			this.endereco = endereco;
	}

	/**
	 * Retorna o email do usuario.
	 * 
	 * @return O email do usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Altera o email do usuario.
	 * 
	 * @param email
	 *            O novo email.
	 * @throws UserException
	 *             Caso o Email esteja em um formato incorreto.
	 */
	public void setEmail(String email) throws UserException {
		if (email.matches("[\\w_.]+@\\w+[..](com|com[.-.]br)")) {
			this.email = email;
		} else {
			throw new UserException("Email inválido");
		}
	}

	/**
	 * retorna o telefone do usuario.
	 * 
	 * @return O telefone do usuario.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Altera o telefone do usuario
	 * 
	 * @param telefone
	 *            O novo telefone.
	 * @throws UserException
	 *             Caso o telefone esteja em um formato incorreto.
	 */
	public void setTelefone(String telefone) throws UserException {
		if (!(telefone.matches("[0-9]*")) || telefone.isEmpty()
				|| telefone.length() < 8)
			throw new UserException("Fone Invalido");
		else
			this.telefone = telefone;
	}

	/**
	 * Retorna o nome do usuario.
	 * 
	 * @return O nome do usuario.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome do usuario
	 * 
	 * @param nome
	 *            O novo nome.
	 * @throws UserException
	 *             Caso o nome esteja em um formato incorreto.
	 */
	public void setNome(String nome) throws UserException {
		if (!(nome == null)
				&& (nome.matches("[A-Za-zÇ-ú\\s]*+") && nome.length() >= 3)
				&& (!(nome.isEmpty())))
			this.nome = nome;
		else
			throw new UserException("Nome inválido");
	}

	/**
	 * Retorna a senha do usuario.
	 * 
	 * @return A senha do usuario.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Altera a senha do usuario.
	 * 
	 * @param senha
	 *            A nova senha.
	 * @throws UserException
	 *             Caso a senha esteja em um formato incorreto.
	 */
	public void setSenha(String senha) throws UserException {
		if (senha.isEmpty() || senha.length() < 4)
			throw new UserException("Senha inválida");
		else
			this.senha = senha;
	}

	/**
	 * Retorna o login do usuario.
	 * 
	 * @return O login do usuario.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Verifica se dois usuarios são iguais.
	 * 
	 * @param obj
	 *            O usuario a ser comparado.
	 * @return true - para o caso dos usuarios serem iguais. false - para o caso
	 *         dos usuarios
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		User user1 = (User) obj;
		return (this.getLogin().equals(user1.getLogin()) || this.getEmail()
				.equals(user1.getEmail()));
	}

	/**
	 * Retorna a lista de amigos do usuario..
	 * 
	 * @return A lista de amigos.
	 */
	public List<User> getListaAmigos() {
		return listaDeAmigos;
	}

	/**
	 * Quantidade de caronas efetivadas
	 * 
	 * @return valor inteiro da quantidade
	 */
	public int getCaronasSeguras() {
		return caronasSeguras;
	}

	/**
	 * Incrementa a quantidade de caronas seguras
	 */
	public void addCaronasSeguras(){
		caronasSeguras++;
	}
	
	/**
	 * Qtde de presencas em caronas
	 * 
	 * @return quantidade de presencas
	 */
	public int getPresencas() {
		return presenca;
	}

	/**
	 * Conta uma presença na carona
	 */
	public void addPresenca() {
		presenca++;
	}

	/**
	 * Quantidade de caronas não efetivadas
	 * 
	 * @return valor inteiro da quantidade
	 */
	public int getCaronasNaoFuncionaram() {
		return caronasNaoFuncionaram;
	}

	/**
	 * Incrementa contador de caronas que não funcionaram
	 */
	public void addCaronasNaoFuncionaram(){
		caronasNaoFuncionaram++;
	}
	
	/**
	 * Caronas não comparecidas
	 * 
	 * @return quantidade
	 */
	public int exibeFaltas() {
		return faltas;
	}

	/**
	 * Conta uma falta
	 */
	public void addFalta() {
		faltas++;
	}

	/**
	 * Verifica se um determinado usuario esta na lista de amigos.
	 * 
	 * @param usuario
	 *            O usuario a ser verificado.
	 * @return true - caso o usuario esteja na lista. false - caso o usuario não
	 *         esteja na lista.
	 */
	public boolean ehAmigo(User usuario) {
		return (listaDeAmigos.contains(usuario));
	}

	/**
	 * Adiciona um novo usuario a lista de amigos.
	 * 
	 * @param usuario
	 *            O novo usuario.
	 */
	public void addAmigo(User usuario) {
		if (!(this.equals(usuario))) {
			listaDeAmigos.add(usuario);
		}
	}

	/**
	 * Sugere um ponto de encontro para uma determinada carona.
	 * 
	 * @param carona
	 *            A carona desejada.
	 * @param pontoDeEncontro
	 *            O local de encontro.
	 * @throws PontoDeEncontroException
	 *             Para o caso de carona incorreta.
	 */
	public void sugerirPontoDeEncontro(Carona carona, String pontoDeEncontro)
			throws PontoDeEncontroException {
		carona.getPontoDeEncontro().sugerirPonto(pontoDeEncontro);

	}

	/**
	 * Retorna todas as solicitações que o motorista possui
	 * 
	 * @return todas as solicitações do motorista.
	 * @throws SolicitacaoException
	 *             Caso a solicitação não exista
	 */
	public List<Solicitacao> getSolicitacoes() throws SolicitacaoException {
		if (solicitacoes.isEmpty())
			throw new SolicitacaoException("Não existe solicitações");
		else
			return solicitacoes;
	}

	/**
	 * Detalha uma determinada carona que o usuario seja motorista ou caroneiro.
	 * 
	 * @param carona
	 *            A carona a ser detalhada.
	 * @return Os dados da carona.
	 */
	public String detalharCarona(Carona carona) {
		if (carona.getMotorista().equals(this)
				|| carona.verificaCaroneiro(this)) {
			return carona.detalharCarona();
		}

		return "Você não pode vizualizar os detalhes dessa carona.";
	}

	/**
	 * Verifica se uma carona está contida nas caronas oferecidas
	 * 
	 * @param carona
	 *            a carona a ser buscada
	 * @return True - se existe a carona False - se não existe
	 */
	public boolean containsCarona(Carona carona) {
		return caronasOferecidas.contains(carona);
	}

	/**
	 * Retorna se um usuario faltou ou nao a carona
	 * 
	 * @param carona
	 *            A carona a ser verificada.
	 * @param user
	 *            O usuario a ser verificado.
	 * @return Se o usuario foi ou não se encontrar com a carona.
	 */
	public Situacao getSitucao(Carona carona, User user) {
		return carona.getSituacaoCaroneiro(user);
	}

	/**
	 * Altera a situacao de um determinado usuario em relacao a uma carona
	 * 
	 * @param carona
	 *            A carona em questao.
	 * @param user
	 *            O usuario que vai ter a situacao alterada
	 * @param situacao
	 *            A atual situacao do usuario
	 */
	public void setSituacao(Carona carona, User user, Situacao situacao) {
		carona.setSituacao(situacao, user);
	}

	/**
	 * Solicitação de uma carona
	 * 
	 * @param motorista
	 *            o usuário que a oferece
	 * @param pontoDeEcontro
	 *            o ponto de encontro da carona
	 * @param carona
	 *            a carona que deseja ser verificada
	 * @throws UserException
	 *             se a carona não pertencer ao motorista informado
	 * @throws PontoDeEncontroException
	 *             se o ponto for inválido
	 */
	public void SolicitarCarona(User motorista, String pontoDeEcontro,
			Carona carona) throws UserException, PontoDeEncontroException {
		if (motorista.containsCarona(carona)) {

			if (pontoDeEcontro.isEmpty())
				motorista.addSolicitacao(new Solicitacao(carona, this));
			else
				motorista.addSolicitacao(new Solicitacao(carona, this,
						pontoDeEcontro));

		} else {
			throw new UserException("Motorista nao possui a carona informada.");
		}
	}

	/**
	 * Adiciona uma nova solicitação ao sistema
	 * 
	 * @param solicitacao
	 *            a nova solicitação
	 */
	private void addSolicitacao(Solicitacao solicitacao) {
		solicitacoes.add(solicitacao);
	}

	/**
	 * Alterar o ponto de encontro de uma solicitação
	 * 
	 * @param solicitacao
	 *            a solicitação a ser modificada
	 * @param pontoDeEncontro
	 *            o novo ponto de encontro
	 * @throws SolicitacaoException
	 *             caso a solicitação não exista
	 * @throws PontoDeEncontroException
	 *             caso o ponto seja inválido
	 */
	public void alterarPontoDeEcontro(Solicitacao solicitacao,
			String pontoDeEncontro) throws SolicitacaoException,
			PontoDeEncontroException {

		if (this.getSolicitacoes().contains(solicitacao))
			solicitacao.AlterarLocalDeEncontro(pontoDeEncontro);
		else
			throw new SolicitacaoException("Voce nao possui essa solicitacao");
	}

	/**
	 * Confirmar uma solicitação
	 * 
	 * @param solicitacao
	 *            a solicitação a ser confirmada
	 * @throws SolicitacaoException
	 *             caso a solicitação não exista
	 * @throws CaronaException
	 *             caso a carona não exista
	 */
	public void confirmarSolicitacao(Solicitacao solicitacao)
			throws SolicitacaoException, CaronaException {
		if (this.getSolicitacoes().contains(solicitacao))
			solicitacao.confirmarSolicitacao();
		else
			throw new SolicitacaoException("Voce nao possui essa solicitacao");

	}

	/**
	 * Rejeitar uma solicitação
	 * 
	 * @param solicitacao
	 *            a solicitação a ser rejeitada
	 * @throws SolicitacaoException
	 *             caso a solicitação não exista
	 */
	public void rejeitarSolicitacao(Solicitacao solicitacao)
			throws SolicitacaoException {

		if (this.getSolicitacoes().contains(solicitacao)) {
			solicitacao.rejetirarSolicitacao();
			this.solicitacoes.remove(solicitacao);
		} else
			throw new SolicitacaoException("Voce nao possui essa solicitacao");

	}

	/**
	 * Confirmar novo local
	 * 
	 * @param solicitacao
	 *            a solicitação a confirmar
	 * @throws SolicitacaoException
	 *             caso ela não exista
	 * @throws CaronaException
	 *             caso a carona não exista para essa solicitação
	 */
	public void confirmarNovoLocal(Solicitacao solicitacao)
			throws SolicitacaoException, CaronaException {
		if (solicitacao.VerificaPontoDeEcontro()
				&& this.getSolicitacoes().contains(solicitacao))
			solicitacao.confirmarSolicitacao();
		else
			throw new SolicitacaoException(
					"Voce nao pode realizar a operacao pois o ponto nao foi alterado ou voce nao possui a solicitacao informada.");
	}

	/**
	 * Exibe as informações de cadastro do usuario.
	 * 
	 * @param - recebe um usuario
	 * @return string com todas as informações do user
	 */
	public String exibeCadastro(User usuario) {
		return usuario.getNome() + " " + usuario.getLogin() + " "
				+ usuario.getEmail() + " " + usuario.getEndereco() + " "
				+ usuario.getTelefone();
	}

	/**
	 * Exibe o historico de caronas dada pelo usuario.
	 * 
	 * @param usuario
	 *            O usuario a ter sua lista de caronas exibida.
	 * @return A lista de caronas de um determinado usuario.
	 */
	public List<Carona> exibeHistoricoDeCaronas() {
		return AcessaDados.getInstance().getCaronasDoMotorista(this);
	}

	/**
	 * Exibe o historico de caronas dada pelo usuario.
	 * 
	 * @param usuario
	 *            O usuario a ter sua lista de caronas exibida.
	 * @return A lista de caronas de um determinado usuario.
	 */
	public List<Carona> exibeHistoricoDeVagas() {
		return AcessaDados.getInstance().getCaronasDoCaroneiro(this);
	}

}