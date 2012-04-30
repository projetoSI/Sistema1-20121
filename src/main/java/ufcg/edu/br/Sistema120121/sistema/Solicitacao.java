package ufcg.edu.br.Sistema120121.sistema;

public class Solicitacao {
	
	private PontoDeEncontro pontoDeEncontro;
	private Carona caronaDesejada;
	private User caroneiro;
	
	public Solicitacao(Carona carona,User caroneiro,String pontoDeEncontro) throws Exception {
		this.caronaDesejada = carona;
		this.pontoDeEncontro.sugerirPonto(pontoDeEncontro);
		this.caroneiro = caroneiro;
	}
	
	public Solicitacao(Carona carona,User caroneiro){
		this.caronaDesejada = carona;
		this.caroneiro = caroneiro;
		
	}
	
	public String getDonoDaCarona(){
		return caronaDesejada.getMotorista().getNome();
	}

	public String getCaroneiro() {
		return caroneiro.getNome();
	}
	
	public PontoDeEncontro getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	public Carona getCaronaDesejada() {
		return caronaDesejada;
	}

	public void confirmarCarona() throws Exception {
		if (caronaDesejada.temVaga()) {
			caronaDesejada.addCaroneiro(caroneiro);
		}else{
			throw new Exception("Nao há vagas");
		}
	}
	
	public void AlterarLocalDeEncontro(String novoLocal) throws Exception{
		pontoDeEncontro.sugerirPonto(novoLocal);
	}

	public void cancelarCarona() {
		
	}
	
	public String toString(){
		return "\n->Caroneiro: " + caroneiro.toString() + "\n->Carona: " + caronaDesejada.toString() + "\n->Ponto de Encontro: " + pontoDeEncontro.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Solicitacao)) {
			return false;
		}
		Solicitacao solAux = (Solicitacao) obj;
		return solAux.getCaronaDesejada().equals(this.getCaronaDesejada()) && solAux.getCaroneiro().equals(this.getCaroneiro());
	}
}
