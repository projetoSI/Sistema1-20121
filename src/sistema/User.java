package sistema;

import sistema.Carona.Avaliacao;
import excecoes.*;

public class User {
	 
	private String login;
	private String endereco;
	private String email;
	private String telefone;
	private String nome;
	protected String senha;

	public User(String login,String senha,String nome,String endereco,String email,String telefone) throws AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException{
		if (email == null || email.isEmpty()){
			throw new EmailErrorException("Email inválido");
		}
		
		if (nome == null || nome.isEmpty()){
			throw new NameErrorException("Nome inválido");
		}
		
		if (login == null || senha == null || senha.isEmpty() || login.isEmpty() || login.length() < 3){
			throw new LoginErrorException("Login inválido");
		}

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

	public void setEndereco(String endereco) throws AdressErrorException{
		if (endereco.isEmpty() || endereco.matches("[0-9]*")) throw new AdressErrorException("Endereço inválido");
		else this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws EmailErrorException{
		if (email.matches("[\\w_.]+@\\w+[..](com|com[.-.]br)")) {
			this.email = email;
		}	else{
			throw new EmailErrorException("Email inválido");
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) throws PhoneErrorException{
		if (!(telefone.matches("[0-9]*")) || telefone.isEmpty()) throw new PhoneErrorException("Fone Invalido");
		else this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NameErrorException{
		if (!(nome == null) && (nome.matches("[A-Za-z\\s]*+") && nome.length() >= 3) && (!(nome.isEmpty()))) this.nome = nome;
		else throw new NameErrorException("Nome inválido");
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws PasswordErrorException{
		if (senha.isEmpty() || senha.length() < 4) throw new PasswordErrorException("Senha inválida");
		else this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	//SOLICITA UMA VAGA EM UMA DETERMINADA CARONA RECEBENDO O PONTO DE ENCONTRO (OBS: FAZER MUDANÇAS AQUI)
	public void pedirCarona(Carona carona,String pontoDeEncontro){
		if (carona.temVaga()) {
			carona.setPontoDeEncontro(this,pontoDeEncontro,Avaliacao.A_DEFINIR);
		}
	}

	//METODO ONDE O MOTORISTA VAI ACEITAR OU NÃO A VAGA DE DETERMINADO CARONEIRO
	public void aprovarCarona(Carona carona,User caroneiro,boolean aprovacao,String pontoDeEncontro) throws QuantityVacancyErrorException{
		
		if (RepositorioCaronas.recuperaCaronaUser(this).contains(carona)) {
			
			if (!pontoDeEncontro.isEmpty() && aprovacao) {
				carona.setPontoDeEncontro(caroneiro, pontoDeEncontro,Avaliacao.MUDANCA_DE_LOCAL);
				
			}else if (pontoDeEncontro.isEmpty() && aprovacao){
				carona.setPontoDeEncontro(caroneiro, pontoDeEncontro,Avaliacao.SIM);
				carona.addCaroneiro(caroneiro);
				
			}else{
				carona.setPontoDeEncontro(caroneiro, pontoDeEncontro,Avaliacao.NAO);
			}
		}
		
	}
	
	//O caroneiro aprovar ou não o ponto de encontro sugerido pelo motorista
	public void aprovarNovoPontoDeEcontro(Carona carona,boolean aprovacao){
		if (aprovacao) {
			carona.addCaroneiro(this);
		}
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
