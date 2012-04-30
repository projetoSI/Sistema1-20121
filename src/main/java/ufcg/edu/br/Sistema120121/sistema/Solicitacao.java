package ufcg.edu.br.Sistema120121.sistema;

public class Solicitacao {
	
	private PontoDeEncontro pontoDeEncontro;
	private Carona caronaDesejada;
	private User caroneiro;
	
	/**
	 * Construtor de uma solicitação 
	 * @param carona
	 * 		A carona que deseja solicitar.
	 * @param caroneiro
	 * 		O motorista que esta oferecendo a carona.
	 * @param pontoDeEncontro
	 * 		O  ponto de encontro da carona.
	 * @throws Exception
	 */
	public Solicitacao(Carona carona,User caroneiro,String pontoDeEncontro) throws Exception {
		this.caronaDesejada = carona;
		this.pontoDeEncontro.sugerirPonto(pontoDeEncontro);
		this.caroneiro = caroneiro;
	}
	
	/**
	 * Construtor de uma carona sem a necessidade de um ponto de encontro.
	 * @param carona
	 * 		A carona que deseja solicitar.
	 * @param caroneiro
	 * 		O motorista que esta oferecendo a carona.
	 */
	public Solicitacao(Carona carona,User caroneiro){
		this.caronaDesejada = carona;
		this.caroneiro = caroneiro;
		
	}
	
	/**
	 * Retorna o dono da carona.
	 * @return
	 * 		O dono da carona.
	 */
	public String getDonoDaCarona(){
		return caronaDesejada.getMotorista().getNome();
	}

	/**
	 * O usuario que esta solicitando a carona
	 * @return
	 * 		O usuario que esta solicitando a carona.
	 */
	public String getCaroneiro() {
		return caroneiro.getNome();
	}
	
	/**
	 * Retorna o ponto de encontro da carona.
	 * @return
	 * 		O ponto de encontro da carona.
	 */
	public PontoDeEncontro getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	/**
	 * Retorna a carona solicitada.
	 * @return
	 * 		A carona solicitada.
	 */
	public Carona getCaronaDesejada() {
		return caronaDesejada;
	}

	/**
	 * Confirma a solicitação da carona.
	 * @throws Exception
	 * 		Caso não exista mais vaga na carona.
	 */
	public void confirmarCarona() throws Exception {
		if (caronaDesejada.temVaga()) {
			caronaDesejada.addCaroneiro(caroneiro);
		}else{
			throw new Exception("Nao há vagas");
		}
	}
	
	/**
	 * Altera o ponto de encontro da carona.
	 * @param novoLocal
	 * 		O novo local de encontro.
	 * @throws Exception
	 */
	public void AlterarLocalDeEncontro(String novoLocal) throws Exception{
		pontoDeEncontro.sugerirPonto(novoLocal);
	}

	/**
	 * Retorna as informações da solicitação em forma de String.
	 * @return	
	 * 		As informações da solicitação.
	 */
	public String toString(){
		return "\n->Caroneiro: " + caroneiro.toString() + "\n->Carona: " + caronaDesejada.toString() + "\n->Ponto de Encontro: " + pontoDeEncontro.toString();
	}
	
	/**
	 * Verifica se duas solicitações são iguais
	 * @param obj
	 * 		O objeto a ser comparado.
	 * @return
	 * 		true - se as solicitações forem iguais.
	 * 		false - se as solicitações não forem iguais.
	 */
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Solicitacao)) {
			return false;
		}
		Solicitacao solAux = (Solicitacao) obj;
		return solAux.getCaronaDesejada().equals(this.getCaronaDesejada()) && solAux.getCaroneiro().equals(this.getCaroneiro());
	}
}
