package sistema;

public class Solicitacao {
	
	private String pontoDeEncontro;
	private Carona caronaDesejada;
	private User caroneiro;
	private boolean validadeVaga = false;
	private boolean validadePontoDeEncontro = false;
	
	public Solicitacao(Carona carona,User caroneiro,String pontoDeEncontro) {
		this.caronaDesejada = carona;
		this.pontoDeEncontro = pontoDeEncontro;
		this.caroneiro = caroneiro;
	}

	public User getCaroneiro() {
		return caroneiro;
	}

	public void setCaroneiro(UserCaroneiro caroneiro) {
		this.caroneiro = caroneiro;
	}

	public String getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}

	public boolean isValidadeVaga() {
		return validadeVaga;
	}

	public void setValidadeVaga(boolean validadeVaga) {
		this.validadeVaga = validadeVaga;
	}

	public boolean isValidadePontoDeEncontro() {
		return validadePontoDeEncontro;
	}

	public void setValidadePontoDeEncontro(boolean validadePontoDeEncontro) {
		this.validadePontoDeEncontro = validadePontoDeEncontro;
	}

	public Carona getCaronaDesejada() {
		return caronaDesejada;
	}

	public boolean confirmarCarona() throws Exception {
		if (caronaDesejada.temVaga()) {
			caronaDesejada.addCaroneiro(caroneiro);
			return true;
		}
		return false;
	}
}
