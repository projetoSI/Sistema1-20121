package ufcg.edu.br.Sistema120121.sistema;

import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PasswordErrorException;
import ufcg.edu.br.Sistema120121.excecoes.UserException;


public class Sistema {
	
	//Classe Controller,responsavel por gerenciar todo o sistema...
	
	
	public void addUsuario(String login,String senha,String nome,String endereco,String email,String telefone) throws Exception{
		AcessaDados.addUsuario(login, senha, nome,endereco,email,telefone);
	}
	
	public void addCarona(String origem,String destino,Hora hora,Data data,int qntVagas,User motorista) throws Exception {
		AcessaDados.addCarona(origem, destino, hora, data, qntVagas, motorista);
	}
	
	public List<User> getUsuariosCadastrados() {
		return AcessaDados.getUsuariosCadastrados();
	}
	
	public User getUser(String login) throws Exception{
		return AcessaDados.getUser(login);
		
	}
	//ISSO TAH CERTO?
	public User acessarConta(String login,String senha) throws Exception {
		User usuario = null;
		
		if (senha == null ||senha.isEmpty()) {//Tah correto,aki?!
			throw new PasswordErrorException("Senha inválida");
		}
		
		usuario = AcessaDados.getUser(login);
		if (usuario == null)
			throw new UserException("Usuário inexistente");
		
		if (!usuario.getSenha().equals(senha))
			throw new LoginErrorException("Login inválido");
		
		
		return usuario;
		
	}

	public List<Carona> getCaronas(String origem, String destino) throws Exception {
		return AcessaDados.localizarCarona(origem, destino);
	}
	
	//localizarCaronas -> FEITO!
	//solicitarVaga
	//sugerirPontodeEncontro
	
	
	

}
