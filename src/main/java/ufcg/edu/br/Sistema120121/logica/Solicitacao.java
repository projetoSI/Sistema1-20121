package ufcg.edu.br.Sistema120121.logica;

/**
 * Solicitação de carona ao sistema
 * 
 */
public class Solicitacao {

	private PontoDeEncontro pontoDeEncontro = new PontoDeEncontro();
	private Carona caronaDesejada;
	private User caroneiro;
	private boolean pontoAlterado = false;
	private boolean rejeitada = false;

	/**
	 * Construtor de uma solicitação
	 * 
	 * @param carona
	 *            A carona que deseja solicitar.
	 * @param caroneiro
	 *            O motorista que esta oferecendo a carona.
	 * @param pontoDeEncontro
	 *            O ponto de encontro da carona.
	 * @throws PontoDeEncontroException
	 *             Retorna ponto inválido caso seja nulo, vazio ou esteja na
	 *             blacklist do ponto de encontro
	 */
	public Solicitacao(Carona carona, User caroneiro, String pontoDeEncontro)
			throws PontoDeEncontroException {
		this.caronaDesejada = carona;
		this.pontoDeEncontro.sugerirPonto(pontoDeEncontro);
		this.caroneiro = caroneiro;
	}

	/**
	 * Retorna o identificador da solicitação atual
	 * 
	 * @return o identificador
	 */
	public String getSolicitacaoID() {
		return caronaDesejada.getID() + "|" + caroneiro.getID();
	}

	/**
	 * Construtor de uma solicitação sem a necessidade de um ponto de encontro.
	 * 
	 * @param carona
	 *            A carona que deseja solicitar.
	 * @param caroneiro
	 *            O motorista que esta oferecendo a carona.
	 */
	public Solicitacao(Carona carona, User caroneiro) {
		this.caronaDesejada = carona;
		this.caroneiro = caroneiro;

	}

	/**
	 * Retorna o dono da carona.
	 * 
	 * @return O dono da carona.
	 */
	public String getDonoDaCarona() {
		return caronaDesejada.getMotorista().getNome();
	}

	/**
	 * O usuario que esta solicitando a carona
	 * 
	 * @return O usuario que esta solicitando a carona.
	 */
	public String getCaroneiro() {
		return caroneiro.getNome();
	}

	/**
	 * Retorna o ponto de encontro da carona.
	 * 
	 * @return O ponto de encontro da carona.
	 */
	public PontoDeEncontro getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	/**
	 * Retorna a carona solicitada.
	 * 
	 * @return A carona solicitada.
	 */
	public Carona getCaronaDesejada() {
		return caronaDesejada;
	}

	/**
	 * Confirma a solicitação da carona.
	 * 
	 * @throws CaronaException
	 * @throws Exception
	 *             Caso não exista mais vaga na carona.
	 */
	public void confirmarSolicitacao() throws SolicitacaoException,
			CaronaException {
		if (caronaDesejada.temVaga()) {
			caronaDesejada.addCaroneiro(caroneiro);
		} else {
			throw new SolicitacaoException("Nao há vagas");
		}
	}

	/**
	 * Rejeita a solicitação
	 */
	public void rejetirarSolicitacao() {
		rejeitada = true;
	}

	/**
	 * Retorna a situação da solicitação
	 * 
	 * @return true - se tiver sido rejeitada false - se não tiver sido
	 *         rejeitada
	 */
	public boolean getSituacaoSolicitacao() {
		return rejeitada;
	}

	/**
	 * Altera o ponto de encontro da carona.
	 * 
	 * @param novoLocal
	 *            O novo local de encontro.
	 * @throws PontoDeEncontroException
	 *             Retorna ponto inválido caso seja nulo, vazio ou esteja na
	 *             blacklist do ponto de encontro
	 */
	public void AlterarLocalDeEncontro(String novoLocal)
			throws PontoDeEncontroException {
		pontoDeEncontro.sugerirPonto(novoLocal);
		pontoAlterado = true;
	}

	/**
	 * Verifica se o ponto foi alterado
	 * 
	 * @return true - se sim false - se não
	 */
	public boolean VerificaPontoDeEcontro() {
		return pontoAlterado;
	}

	/**
	 * Retorna as informações da solicitação em forma de String.
	 * 
	 * @return As informações da solicitação.
	 */
	public String toString() {
		String localAterado = "Local nao foi alterado.";
		if (pontoAlterado)
			localAterado = "Local foi alterado.";
		return "\n->Caroneiro: " + caroneiro.toString() + "\n->Carona: "
				+ caronaDesejada.toString() + "\n->Ponto de Encontro: "
				+ pontoDeEncontro.toString() + "\n" + localAterado;
	}

	/**
	 * Verifica se duas solicitações são iguais
	 * 
	 * @param obj
	 *            O objeto a ser comparado.
	 * @return true - se as solicitações forem iguais. false - se as
	 *         solicitações não forem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Solicitacao)) {
			return false;
		}
		Solicitacao solAux = (Solicitacao) obj;
		return solAux.getCaronaDesejada().equals(this.getCaronaDesejada())
				&& solAux.getCaroneiro().equals(this.getCaroneiro());
	}
}
