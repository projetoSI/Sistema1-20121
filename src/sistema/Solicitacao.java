package sistema;

public class Solicitacao {
	
	private PontoDeEncontro pontoDeEncontro;
	private Carona caronaDesejada;
	private UserCaroneiro caroneiro;
	
	public Solicitacao(Carona carona,UserCaroneiro caroneiro,String pontoDeEncontro) {
		this.caronaDesejada = carona;
		this.pontoDeEncontro = new PontoDeEncontro(pontoDeEncontro);
		this.caroneiro = caroneiro;
	}
	
	public Solicitacao(Carona carona,UserCaroneiro caroneiro){
		this.pontoDeEncontro = new PontoDeEncontro("");
		this.caronaDesejada = carona;
		this.caroneiro = caroneiro;
		
	}

	public UserCaroneiro getCaroneiro() {
		return caroneiro;
	}

	public void setCaroneiro(UserCaroneiro caroneiro) {
		this.caroneiro = caroneiro;
	}
	
	public PontoDeEncontro getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	public Carona getCaronaDesejada() {
		return caronaDesejada;
	}

	public boolean confirmarCarona() throws Exception {
		if (caronaDesejada.temVaga()) {
			caronaDesejada.addCaroneiro(caroneiro);
			pontoDeEncontro.avaliarPonto(true);
			return true;
		}
		return false;
	}
	
	public void AlterarLocalDeEncontro(String novoLocal) throws Exception{
		pontoDeEncontro.alterarPontoDeEncontro(novoLocal);
	}

	public void cancelarCarona() {
		pontoDeEncontro.avaliarPonto(false);
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
