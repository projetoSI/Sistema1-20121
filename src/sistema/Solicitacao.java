package sistema;

public class Solicitacao {
	
	private String pontoDeEncontro;
	private Carona caronaDesejada;
	private boolean validadeVaga = false;
	private boolean validadePontoDeEncontro = false;
	
	public Solicitacao(Carona carona,String pontoDeEncontro) {
		this.caronaDesejada = carona;
		this.pontoDeEncontro = pontoDeEncontro;
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
}
