package ufcg.edu.br.Sistema120121.logica;

/**
 * Identificador de sessão de um usuário
 * 
 */
public class IdentificadorSessaoUser {

	private String login, email;
	private boolean sessao = false;

	/**
	 * Construtor de um identificador de sessão
	 * 
	 * @param login
	 *            login do usuario
	 * @param email
	 *            email do usuario
	 */
	public IdentificadorSessaoUser(String login, String email) {
		this.login = login;
		this.email = email;
	}

	/**
	 * Recupera o login passado para o identificador
	 * 
	 * @return o login
	 */
	private String getLogin() {
		return login;
	}

	/**
	 * Recupera o email passado para o identificador
	 * 
	 * @return o email
	 */
	private String getEmail() {
		return email;
	}

	/**
	 * Abre a sessão atual
	 */
	public void abreSessao() {
		this.sessao = true;
	}

	/**
	 * Fecha a sessão atual
	 */
	public void fechaSessao() {
		this.sessao = false;
	}

	/**
	 * Verifica se a sessão do usuário está ativada
	 * 
	 * @return true - se ativa false - se inativa
	 */
	public boolean sessaoAtiva() {
		boolean result = false;

		if (sessao)
			result = true;

		return result;
	}

	/**
	 * Verifica se o login passado está no identificador
	 * 
	 * @param s
	 *            login passado
	 * @return true - se o login está no identificador false - se o login nao
	 *         está no identificador
	 */
	public boolean verificaLoginID(String s) {
		boolean result = false;

		if (s.equals(getLogin()))
			result = true;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IdentificadorSessaoUser)) {
			return false;
		}
		IdentificadorSessaoUser other = (IdentificadorSessaoUser) obj;
		return this.getEmail().equals(other.getEmail())
				&& this.getLogin().equals(other.getLogin());
	}

	@Override
	public String toString() {
		return login + "|" + email;
	}

}
