package sistema;

import java.util.List;

public class AcessaDados {
	
	//ClASSE CONTROLLE DOS REPOSITORIOS
	
	
	public static void addUsuario(String login,String senha,String nome,String endereco,String email,String telefone) throws Exception{
		RepositorioUsuario.addUser(login, senha, nome,endereco,email,telefone);
	}
	
	public static void addCarona(String origem,String destino,Hora hora,Data data,int qntVagas,User motorista) throws Exception {
		RepositorioCaronas.addCarona(origem, destino, hora, data, qntVagas, motorista);
	}
	
	public static List<User> getUsuariosCadastrados() {
		return RepositorioUsuario.getUsuarios();
	}
	
	public static User getUser(String login) throws Exception{
		return RepositorioUsuario.getUsuarioLogin(login);
		
	}
	
	public static List<Carona> localizarCarona(String origem ,String destino) throws Exception {
		return RepositorioCaronas.getCaronas(origem, destino);

	}
	
	public static List<Carona> caronasCadastradas() {
		return RepositorioCaronas.getCaronasCadastradas();
	}
	
	public static List<Carona> getCaronasDoUsuario(User motorista) {
		return RepositorioCaronas.recuperaCaronaUser(motorista);
	}
	
	

}
