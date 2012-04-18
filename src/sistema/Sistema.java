package sistema;

import java.util.List;
import excecoes.*;


public class Sistema {
	
	//Classe Controller,responsavel por gerenciar todo o sistema...
	
	
	public void addUsuario(String login,String senha,String nome,String endereco,String email,String telefone) throws Exception{
		AcessaDados.addUsuario(login, senha, nome,endereco,email,telefone);
	}
	
	public void addCarona(String origem,String destino,String hora,String data,String qntVagas,User motorista) throws Exception {
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
		;
		if(AcessaDados.getUser(login).getSenha().equals(senha)){
			usuario = AcessaDados.getUser(login);
		}else{
			throw new LoginErrorException("Login inválido");
		}
		
		
		return usuario;
		
	}

	public List<Carona> getCaronas(String origem, String destino) throws Exception {
		return AcessaDados.localizarCarona(origem, destino);
	}
	
	//localizarCaronas -> FEITO!
	//solicitarVaga
	//sugerirPontodeEncontro
	
	
	

}
