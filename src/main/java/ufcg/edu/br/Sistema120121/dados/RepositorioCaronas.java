package ufcg.edu.br.Sistema120121.dados;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.logica.*;
import ufcg.edu.br.Sistema120121.logica.Carona.Situacao;

public class RepositorioCaronas {

	private List<Carona> caronasCadastradas = new LinkedList<Carona>();
	private Carona novaCarona;

	/**
	 * Atualiza o repositorio de caronas.
	 * 
	 * @throws IOException
	 *             Caso não consiga ler o arquivo.
	 */
	public void atualizaRepositorio(LinkedList<Carona> novosDados)
			throws IOException {
		caronasCadastradas = novosDados;
	}

	/**
	 * Adiciona uma nova carona no repositorio
	 * 
	 * @param origem
	 *            Local de origem da carona.
	 * @param destino
	 *            Local de destino da carona.
	 * @param hora
	 *            A hora de saida.
	 * @param data
	 *            A data da carona.
	 * @param qntVagas
	 *            A quantidade de vagas de carona.
	 * @param motorista
	 *            O motorista da carona.
	 * @throws Exception
	 */
	public void addCarona(String origem, String destino, Hora hora, Data data,
			int qntVagas, User motorista, boolean ehMunicipal)
			throws CaronaException {
		novaCarona = new Carona(origem, destino, hora, data, qntVagas,
				motorista, ehMunicipal);
		if (recuperaCaronaUser(motorista).contains(novaCarona)) {
			throw new CaronaException(
					"Uma carona já foi cadastrada com essas informações");
		}
		caronasCadastradas.add(novaCarona);
	}

	/**
	 * Retorna a lista de caronas cadastradas.
	 * 
	 * @return A lista de caronas cadastradas.
	 */
	public List<Carona> getCaronasCadastradas() {// CASO1: TODAS AS CARONAS
													// CADASTRADAS
		return caronasCadastradas;
	}

	/**
	 * Recupera as caronas de um determinado usuario.
	 * 
	 * @param usuario
	 *            O usuario a ter suas caronas retornada.
	 * @return A lista de caronas de um determinado usuario.
	 */
	public List<Carona> recuperaCaronaUser(User usuario) {// CASO2 : AS CARONAS
															// DO USER
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
	 * 
	 * @param usuario
	 *            O usuario a ter seu historico de vagas exibido.
	 * @return lista com todas as caronas que o usuario eh caroneiro
	 */
	public List<Carona> recuperaVagaCaronaUser(User usuario) {
		List<Carona> vagaCaronaUser = new LinkedList<Carona>();

		for (Carona carona : caronasCadastradas) {
			if (carona.verificaCaroneiro(usuario)) {
				vagaCaronaUser.add(carona);
			}
		}

		return vagaCaronaUser;
	}

	/**
	 * Retorna a lista de caronas cadastradas no repositorio.
	 * 
	 * @param origem
	 *            O local de origem da carona.
	 * @param destino
	 *            O local de destino da carona.
	 * @return A lista contendo as caronas do repositorio.
	 * @throws Exception
	 * 
	 */
	public List<Carona> getCaronas(String origem, String destino)
			throws CaronaException {
		List<Carona> auxCaronas = new LinkedList<Carona>();

		if (origem == null || !origem.matches("[A-Za-zÇ-ú\\s]*+")) {
			throw new CaronaException("Origem inválida");
		}
		if (destino == null || !destino.matches("[A-Za-zÇ-ú\\s]*+")) {
			throw new CaronaException("Destino inválido");
		}

		if (destino.isEmpty() && origem.isEmpty()) {
			return getCaronasCadastradas();

		} else {
			for (Carona carona : caronasCadastradas) {
				if (destino.isEmpty() && origem.equals(carona.getOrigem())) // todas
																			// as
																			// caronas
																			// com
																			// origem
																			// igual
																			// e
																			// destino
																			// vazio
					auxCaronas.add(carona);
				if (origem.isEmpty() && destino.equals(carona.getDestino())) // todas
																				// as
																				// caronas
																				// com
																				// destino
																				// igual
																				// e
																				// origem
																				// vazia
					auxCaronas.add(carona);
				if (origem.equals(carona.getOrigem())
						&& destino.equals(carona.getDestino())) // todas as
																// caronas com
																// destino e
																// origem igual
					auxCaronas.add(carona);
			}
		}

		return auxCaronas;

	}

	public List<Carona> getCaronasMunicipais(String cidade, String origem,
			String destino) throws CaronaException {
		List<Carona> auxCaronas = new LinkedList<Carona>();

		if (cidade == null || !cidade.matches("[A-Za-zÇ-ú\\s]*+")) {
			throw new CaronaException("Cidade inexistente");
		} else if (origem == null || !origem.matches("[A-Za-zÇ-ú\\s]*+")) {
			throw new CaronaException("Origem inválida");
		} else if (destino == null || !destino.matches("[A-Za-zÇ-ú\\s]*+")) {
			throw new CaronaException("Destino inválido");
		} else {

			for (Carona carona : caronasCadastradas) {
				if (carona.isMunicipal()) {
					if (((CaronaMunicipal) carona).getCidade().equals(cidade)
							&& origem.equals(carona.getOrigem())
							&& destino.equals(carona.getDestino())){
						
						auxCaronas.add(carona);
					}
				}

			}

			return auxCaronas;
		}
	}

	public List<Carona> getCaronasMunicipais(String cidade)
			throws CaronaException {
		List<Carona> auxCaronas = new LinkedList<Carona>();
		for (Carona carona : caronasCadastradas) {
			if (carona.isMunicipal()) {
				if (((CaronaMunicipal) carona).getCidade().equals(cidade))
					auxCaronas.add(carona);
			}

		}
		return auxCaronas;
	}

	/**
	 * Recupera uma carona pelo seu identificador
	 * 
	 * @param id
	 *            identificador da carona
	 * @return a carona procurada
	 * @throws CaronaException
	 *             caso o identificador seja vazio ou caso a carona não exista
	 */
	public Carona getCarona(String id) throws CaronaException {
		if (id == null | id.equals("")) {
			throw new CaronaException("Identificador do carona é inválido");
		}
		Carona carona = null;
		for (Carona c : caronasCadastradas) {
			if (c.getID().toString().equals(id)) {
				carona = c;
				break;
			}
		}

		if (carona == null) {
			throw new CaronaException("Item inexistente");
		}
		return carona;
	}

	/**
	 * Recupera todas as caronas de um usuário
	 * 
	 * @param usuario
	 *            o usuário que se deseja as caronas
	 * @return todas as caronas
	 */
	public List<Carona> getTodasCaronas(User usuario) {
		List<Carona> historico = new LinkedList<Carona>();

		for (Carona carona : recuperaCaronaUser(usuario)) {
			if (carona.getCaroneiros().contains((CharSequence) usuario)
					&& carona.getSituacaoCaroneiro(usuario).equals(
							Situacao.NAO_FALTOU)
					|| carona.getMotorista().equals(usuario)) {
				historico.add(carona);
			}
		}
		return historico;
	}

	/**
	 * Apaga do repositório a carona passada
	 * 
	 * @param carona
	 *            a carona a ser removida
	 */
	public void apagaCarona(Carona carona) {
		caronasCadastradas.remove(carona);
	}

	/**
	 * Zera o repositório
	 */
	public void zeraRepositorioCaronas(){
		caronasCadastradas = new LinkedList<Carona>();
	}

	public void addCarona(String origem, String destino, String cidade,
			Hora hora, Data data, int qntVagas, User motorista,
			boolean ehMunicipal) throws Exception {
		novaCarona = new CaronaMunicipal(origem, destino, cidade, hora, data,
				qntVagas, motorista, ehMunicipal);
		if (recuperaCaronaUser(motorista).contains(novaCarona)) {
			throw new CaronaException(
					"Uma carona já foi cadastrada com essas informações");
		}
		caronasCadastradas.add(novaCarona);
	}
}
