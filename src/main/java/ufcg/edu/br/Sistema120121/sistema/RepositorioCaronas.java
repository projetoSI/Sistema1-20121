package ufcg.edu.br.Sistema120121.sistema;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RepositorioCaronas {

	private static List<Carona> caronasCadastradas = new LinkedList<Carona>();
	private static Carona novaCarona;
	
	/**
	 * Atualiza o repositorio de caronas.
	 * @throws IOException
	 *		Caso não consiga ler o arquivo. 		
	 */
	public void atualizaRepositorio() throws IOException {
		caronasCadastradas = Arquivo.lerArquivo("Arquivos/arquivoCarona.xml");
	}
	
	/**
	 * Adiciona uma nova carona no repositorio
	 * @param origem
	 * 		Local de origem da carona.
	 * @param destino
	 * 		Local de destino da carona.
	 * @param hora
	 * 		A hora de saida.
	 * @param data
	 * 		A data da carona.
	 * @param qntVagas
	 * 		A quantidade de vagas de carona.
	 * @param motorista
	 * 		O motorista da carona.
	 * @throws Exception
	 */
	public static void addCarona(String origem,String destino,Hora hora,Data data,int qntVagas,User motorista) throws Exception {
		novaCarona = new Carona(origem, destino, hora, data, qntVagas, motorista);
		if (recuperaCaronaUser(motorista).contains(novaCarona)) {
			throw new Exception("Uma carona já foi cadastrada com essas informações");
		}
		caronasCadastradas.add(novaCarona);
		Arquivo.setCaronas(caronasCadastradas);
	}
	
	/**
	 * Retorna a lista de caronas cadastradas.
	 * @return
	 * 		A lista de caronas cadastradas.
	 */
	public static List<Carona> getCaronasCadastradas() {//CASO1: TODAS AS CARONAS CADASTRADAS
		return caronasCadastradas;
	}
	
	/**
	 * Recupera as caronas de um determinado usuario.
	 * @param usuario
	 * 		O usuario a ter suas caronas retornada.
	 * @return
	 * 		A lista de caronas de um determinado usuario.
	 */
	public static List<Carona> recuperaCaronaUser(User usuario) {//CASO2 : AS CARONAS DO USER
		List<Carona> caronasUser = new LinkedList<Carona>();

		for (Carona carona : caronasCadastradas) {
			if (carona.getMotorista().getLogin().equals(usuario.getLogin())) {
				caronasUser.add(carona);
			}
		}

		return caronasUser;
	}
	
	/**
	 * Recupera as caronas que um determinado usuario pegou..
	 * @param usuario
	 * 		O usuario a ter seu historico de vagas exibido.
	 * @return
	 */
	public static List<Carona> recuperaVagaCaronaUser(User usuario){
		
		List<Carona> vagaCaronaUser = new LinkedList<Carona>();
		
		for (Carona carona : caronasCadastradas) {
			
			if( carona.verificaCaroneiro(usuario)){
				vagaCaronaUser.add(carona);
			}
		}
		
		return vagaCaronaUser;
	}
	/**
	 * Retorna a lista de caronas cadastradas no repositorio.
	 * @param origem
	 * 		O local de origem da carona.
	 * @param destino
	 * 		O local de destino da carona.
	 * @return
	 * 		A lista contendo as caronas do repositorio.
	 * @throws Exception
	 * 	
	 */
	public static List<Carona> getCaronas(String origem, String destino) throws Exception{//CASO 3: BUSCAR CARONAS POR ORIGEM E DESTINO,
																									   //E RETORNAR APENAS AS QUE IRÃO OCORRER
		List<Carona> auxCaronas = new LinkedList<Carona>();
		
		if (origem == null){
			throw new Exception("Origem inválida");
		}
		if (destino == null){
			throw new Exception("Destino inválido");
		}
		
		if (destino.isEmpty() && origem.isEmpty()){
			return getCaronasCadastradas();
		
		}else{
			for (Carona carona : caronasCadastradas) {
				if (destino.isEmpty() && origem.equals(carona.getOrigem())) //todas as caronas com origem igual e destino vazio
					auxCaronas.add(carona);
				if(origem.isEmpty() && destino.equals(carona.getDestino())) //todas as caronas com destino igual e origem vazia
					auxCaronas.add(carona);
				if(origem.equals(carona.getOrigem()) && destino.equals(carona.getDestino())) //todas as caronas com destino e origem igual
					auxCaronas.add(carona);
			}			
		}
		
		return auxCaronas;

	}


	
}