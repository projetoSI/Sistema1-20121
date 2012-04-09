package sistema;

import java.util.List;
import excecoes.*;


public class Sistema {
	
	//Classe Controller,responsavel por gerenciar todo o sistema...
	
	
	public void addUsuario(String login,String senha,String nome,String endereco,String email,String telefone) throws Exception{
		Repositorio.addUser(login, senha, nome,endereco,email,telefone);
	}
	
	public void addCarona(String origem,String destino,String hora,String data,String qntVagas,User motorista) throws LocalErrorException, QuantityVacancyErrorException {
		Repositorio.addCarona(origem, destino, hora, data, qntVagas, motorista);
	}
	
	public List<User> getUsuariosCadastrados() {
		return Repositorio.getUsuarios();
	}
	
	public User getUser(String login) throws Exception{
		return Repositorio.getUsuarioLogin(login);
		
	}
	//ISSO TAH CERTO?
	public User acessarConta(String login,String senha) throws Exception {
		User usuario = null;
		
		if (senha == null ||senha.isEmpty()) {//Tah correto,aki?!
			throw new PasswordErrorException("Senha inválida");
		}
		;
		if(Repositorio.getUsuarioLogin(login).getSenha().equals(senha)){
			usuario = Repositorio.getUsuarioLogin(login);
		}else{
			throw new LoginErrorException("Login inválido");
		}
		
		
		return usuario;
		
	}
	
	//localizarCaronas -> FEITO!
	//solicitarVaga
	//sugerirPontodeEncontro
	
	
	

}
