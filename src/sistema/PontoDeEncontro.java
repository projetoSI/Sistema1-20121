package sistema;

public class PontoDeEncontro {

	private String pontoDeEncontro;
	private enum Validade{SIM,NÃO,ESPERA};
	private Validade avaliacao;

	public PontoDeEncontro(String sugestao) {
		this.pontoDeEncontro = sugestao;
		this.avaliacao = Validade.ESPERA;
	}

	
	
	public Validade isValidadePontoDeEncontro() {
		return avaliacao;
	}
	public void setValidadePontoDeEncontro(boolean condicao) {
		if (condicao){
			avaliacao = Validade.SIM;
		}else{
			avaliacao = Validade.NÃO;
		}
			
	}
	
	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}
	
	
	

}
