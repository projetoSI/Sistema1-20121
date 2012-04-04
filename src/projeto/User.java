package projeto;

public class User {
	 
	private String login;
	private String endereco;
	private String email;
	private String telefone;
	private String nome;
	protected String senha;

	public User(String login,String senha,String nome,String endereco,String email,String telefone) {
		this.login = login;
		setSenha(senha); 
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setTelefone(telefone);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}


	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof User)){
			return false;
		}
		User user1 = (User) obj;
		return this.equals(user1.getLogin()) || this.equals(user1.getEmail());
	}	

	

}
