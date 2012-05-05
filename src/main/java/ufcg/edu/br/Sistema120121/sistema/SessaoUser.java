package ufcg.edu.br.Sistema120121.sistema;

public class SessaoUser {

	private String login, email;
	private boolean sessao = false;
	
	public SessaoUser(String login, String email) {
		this.login = login;
		this.email = email;
	}

	public String getLogin() {
		return login;
	}
	
	public String getEmail() {
		return email;
	}

	public void abreSessao(){
		this.sessao = true;
	}
	
	public void fechaSessao() {
		this.sessao = false;
	}
	
	public boolean sessaoAtiva(){
		boolean result = false;
		
		if (sessao){
			result = true;
		}
		
		return result;
	}
	
	public boolean verificaLoginID(String s){
		boolean result = false;
		
		if (s.equals(getLogin())){
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof SessaoUser)){
			return false;
		}
		SessaoUser other = (SessaoUser) obj;
		return this.getEmail().equals(other.getEmail()) && this.getLogin().equals(other.getLogin());
	}

	@Override
	public String toString() {
		return login + "|" + email;
	}

	
}
