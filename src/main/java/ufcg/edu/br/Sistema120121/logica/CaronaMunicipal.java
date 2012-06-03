package ufcg.edu.br.Sistema120121.logica;

public class CaronaMunicipal extends Carona {

	private String cidade = "default";
	
	public CaronaMunicipal(String origem, String destino,String cidade, Hora hora, Data data,
			int qntVagas, User motorista, boolean ehMunicipal)
			throws Exception {
		super(origem, destino, hora, data, qntVagas, motorista, true);
		this.setCidade(cidade);
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) throws Exception{
		if(!(cidade == null) && (cidade.matches("[A-Za-zÇ-ú\\s]*+"))
				&& (!(cidade.isEmpty())))
			this.cidade = cidade;
		else
			throw new Exception("Cidade inválida");
	}

}
