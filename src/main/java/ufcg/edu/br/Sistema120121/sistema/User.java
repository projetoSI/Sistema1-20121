package ufcg.edu.br.Sistema120121.sistema;

import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.AdressErrorException;
import ufcg.edu.br.Sistema120121.excecoes.EmailErrorException;
import ufcg.edu.br.Sistema120121.excecoes.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoes.NameErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PasswordErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PhoneErrorException;
import ufcg.edu.br.Sistema120121.sistema.Carona.Situacao;

public class User{
	 
	private String login;
	private String endereco;
	private String email;
	private String telefone;
	private String nome;
	private String senha;
	private List<User> listaDeAmigos = new LinkedList<User>();
	private List<Solicitacao> solicitacoes;
	private SessaoUser ID;

	/**
	 * Construtor de um usuario.
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
	 * @throws AdressErrorException
	 * 		Caso o endereço esteja em um formato incorreto.
	 * @throws EmailErrorException
	 * 		Caso o email esteja em um formato incorreto
	 * @throws PasswordErrorException
	 * 		Caso a senha inserida seja invalida.
	 * @throws NameErrorException
	 * 		Caso o nome esteja incorreto
	 * @throws PhoneErrorException
	 * 		Caso o telefone esteja em um formato incorreto.
	 * @throws LoginErrorException
	 * 		Caso o login ja exista ou esteja em um formato incorreto.
	 */
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
		ID = new SessaoUser(login, email);
		solicitacoes = new LinkedList<Solicitacao>();

	}
	
	/**
	 * Retorna o perfil do usuario.
	 * @return
	 * 		O perfil do usuario.
	 * @throws AdressErrorException
	 * 		Caso o endereço esteja em um formato incorreto.
	 * @throws EmailErrorException
	 * 		Caso o email esteja em um formato incorreto.
	 * @throws PasswordErrorException
	 * 		Caso o Endereco esteja em um formato incorreto.
	 * @throws NameErrorException
	 * 		Caso o nome esteja em um formato incorreto.
	 * @throws PhoneErrorException
	 * 		Caso o telefone esteja em um formato incorreto.
	 * @throws LoginErrorException
	 * 		Caso o login esteja em um formato incorreto.
	 */
	public Perfil getPerfil() throws AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException{
		return new Perfil(new User(login, senha, nome, endereco, email, telefone));
	}

	public SessaoUser getID() {
		return ID;
	}

	/**
	 * Retorna o endereço do usuario
	 * @return
	 * 		O endereço do usuario.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Muda o endereço do usuario.
	 * @param endereco
	 * 		O novo endereço.
	 * @throws AdressErrorException
	 * 		Caso o endereço esteja em um formato incorreto.
	 */
	public void setEndereco(String endereco) throws AdressErrorException{
		if (endereco.isEmpty() || endereco.matches("[0-9]*")) throw new AdressErrorException("Endereço inválido");
		else this.endereco = endereco;
	}

	/**
	 * Retorna o email do usuario.
	 * @return
	 * 		O email do usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Altera o email do usuario.
	 * @param email
	 * 		O novo email.
	 * @throws EmailErrorException
	 * 		Caso o Email esteja em um formato incorreto.
	 */
	public void setEmail(String email) throws EmailErrorException{
		if (email.matches("[\\w_.]+@\\w+[..](com|com[.-.]br)")) {
			this.email = email;
		}	else{
			throw new EmailErrorException("Email inválido");
		}
	}

	/**
	 * retorna o telefone do usuario.
	 * @return
	 * 		O telefone do usuario.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Altera o telefone do usuario
	 * @param telefone
	 * 		O novo telefone.
	 * @throws PhoneErrorException
	 * 		Caso o telefone esteja em um formato incorreto.
	 */
	public void setTelefone(String telefone) throws PhoneErrorException{
		if (!(telefone.matches("[0-9]*")) || telefone.isEmpty() || telefone.length() < 8) throw new PhoneErrorException("Fone Invalido");
		else this.telefone = telefone;
	}

	/**
	 * Retorna o nome do usuario.
	 * @return
	 * 		O nome do usuario.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome do usuario
	 * @param nome
	 * 		O novo nome.
	 * @throws NameErrorException
	 * 		Caso o nome esteja em um formato incorreto.
	 */
	public void setNome(String nome) throws NameErrorException{
		if (!(nome == null) && (nome.matches("[A-Za-zÇ-ú\\s]*+") && nome.length() >= 3) && (!(nome.isEmpty()))) this.nome = nome;
		else throw new NameErrorException("Nome inválido");
	}

	/**
	 * Retorna a senha do usuario.
	 * @return
	 * 		A senha do usuario.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Altera a senha do usuario.
	 * @param senha
	 * 		A nova senha.
	 * @throws PasswordErrorException
	 * 		Caso a senha esteja em um formato incorreto.
	 */
	public void setSenha(String senha) throws PasswordErrorException{
		if (senha.isEmpty() || senha.length() < 4) throw new PasswordErrorException("Senha inválida");
		else this.senha = senha;
	}

	/**
	 * Retorna o login do usuario.
	 * @return
	 * 		O login do usuario.
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Verifica se dois usuarios são iguais.
	 * @param obj
	 * 		O usuario a ser comparado.
	 * @return
	 * 		true - para o caso dos usuarios serem iguais.
	 * 		false - para o caso dos usuarios 
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof User)){
			return false;
		}
		User user1 = (User) obj;
		return (this.getLogin().equals(user1.getLogin()) || this.getEmail().equals(user1.getEmail()));
	}	


	/**
	 * Retorna a lista de amigos do usuario..
	 * @return
	 * 		A lista de amigos.
	 */
	public List<User> getListaAmigos(){
		return listaDeAmigos;
	}
	
	/**
	 * Verifica se um determinado usuario esta na lista de amigos.
	 * @param usuario
	 * 		O usuario a ser verificado.
	 * @return
	 * 		true - caso o usuario esteja na lista.
	 * 		false - caso o usuario não esteja na lista.
	 */
	public boolean ehAmigo(User usuario){
		return (listaDeAmigos.contains(usuario));
	}

	/**
	 * Adiciona um novo usuario a lista de amigos.
	 * @param usuario
	 * 		O novo usuario.
	 */
	public void addAmigo(User usuario){
		if (!(this.equals(usuario))) listaDeAmigos.add(usuario);
	}
	
	/**
	 * Sugere um ponto de encontro para uma determinada carona.
	 * @param carona
	 * 		A carona desejada.
	 * @param pontoDeEncontro
	 * 		O local de encontro.
	 * @throws Exception
	 * 		Para o caso de carona incorreta.
	 */
	public void sugerirPontoDeEncontro(Carona carona,String pontoDeEncontro) throws Exception{
		carona.getPontoDeEncontro().sugerirPonto(pontoDeEncontro);
		
	}
	
	/**
	 * Retorna todas as solicitações que o motorista possui
	 * @return
	 * 		todas as solicitações do motorista.
	 * @throws Exception 
	 */
	public List<Solicitacao> getSolicitacoes() throws Exception{
		if (solicitacoes.isEmpty()) throw new Exception("Não existe solicitações");else return solicitacoes; 
	}
	
	/**
	 * Detalha uma determinada carona que o usuario seja motorista ou caroneiro.
	 * @param carona
	 * 		A carona a ser detalhada.
	 * @return
	 * 		Os dados da carona.
	 */
	public String detalharCarona(Carona carona){
		if (carona.getMotorista().equals(this) || carona.verificaCaroneiro(this)) {
			return carona.detalharCarona();
		}
		
		return "Você não pode vizualizar os detalhes dessa carona.";
	}

	/**
	 * Retorna se um usuario faltou ou nao a carona
	 * @param carona
	 * 		A carona a ser verificada.
	 * @param user
	 * 		O usuario a ser verificado.
	 * @return
	 * 		Se o usuario foi ou não se encontrar com a carona.
	 */
	public Situacao getSitucao(Carona carona,User user){
		return carona.getSituacaoCaroneiro(user);
	}
	
	/**
	 * Altera a situacao de um determinado usuario em realcao a uma carona
	 * @param carona
	 * 		A carona em questao.
	 * @param user
	 * 		O usuario que vai ter a situacao alterada
	 * @param situacao
	 * 		A atual situacao do usuario
	 */
	public void setSituacao(Carona carona,User user,Situacao situacao){
		carona.setSituacao(situacao, user);
	}
	
	
}

	
	
	


