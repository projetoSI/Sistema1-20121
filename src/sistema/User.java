package sistema;

import excecoes.*;

public class User {
	 
	private String login;
	private String endereco;
	private String email;
	private String telefone;
	private String nome;
	protected String senha;

	public User(String login,String senha,String nome,String endereco,String email,String telefone) throws AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException{
		if (login.isEmpty() || login.length() < 5) throw new LoginErrorException("Login Invalido");
		else this.login = login;
		setSenha(senha); 
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setTelefone(telefone);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) throws AdressErrorException{
		if (endereco.isEmpty() || endereco.matches("[0-9]*")) throw new AdressErrorException("Endere�o Invalido");
		else this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws EmailErrorException{
		if (email.matches("[\\w_.]+@\\w+[..](com|com[.-.]br)")) this.email = email;
		else throw new EmailErrorException("Email Invalido");
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) throws PhoneErrorException{
		if (!(telefone.matches("[0-9]*")) || telefone.isEmpty() || (telefone.length() != 10 && telefone.length() != 8)) throw new PhoneErrorException("Fone Invalido");
		else this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NameErrorException{
		if (nome.matches("[A-Za-z\\s]*+") && nome.length() >= 3)this.nome = nome;
		else throw new NameErrorException("Nome Invalido");
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws PasswordErrorException{
		if (senha.isEmpty() || senha.length() < 6) throw new PasswordErrorException("Senha Invalida");
		else this.senha = senha;
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
		return (this.getLogin().equals(user1.getLogin()) || this.getEmail().equals(user1.getEmail()));
	}	

	

}
