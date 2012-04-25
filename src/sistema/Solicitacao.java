package sistema;

public class Solicitacao {
	
	private String pontoDeEncontro;
	private Carona caronaDesejada;
	private boolean validadeVaga = false;
	private boolean pontoDeEncontroCaroneiroValido = false;
	private boolean pontoDeEncontroMotoristaValido = false;
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

	public boolean isVagaValida() {
		return validadeVaga;
	}

	public void setValidadeVaga(boolean validadeVaga) {
		this.validadeVaga = validadeVaga;
	}

	public boolean isPontoDeEncontroValido() {
		return pontoDeEncontroCaroneiroValido && pontoDeEncontroMotoristaValido;
	} 

	public void setValidadePontoDeEncontroMotorista(boolean validadePontoDeEncontro) {
		this.pontoDeEncontroMotoristaValido = validadePontoDeEncontro;
	}
	
	public void setValidadePontoDeEncontroCaroneiro(boolean validadePontoDeEncontro) {
		this.pontoDeEncontroCaroneiroValido = validadePontoDeEncontro;
	}

	public Carona getCaronaDesejada() {
		return caronaDesejada;
	}
}
