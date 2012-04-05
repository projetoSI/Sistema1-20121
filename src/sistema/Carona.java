package sistema;

public class Carona {

	private String origem;
	private String destino;
	private DataHora hora, data;
	private int qntVagas;
	private User motorista;

	public Carona(String origem, String destino, DataHora hora, DataHora data,int qntVagas, User motorista) {
		this.origem = origem;
		this.destino = destino;
		this.hora = hora;
		this.data = data;
		this.qntVagas = qntVagas;
		this.motorista = motorista;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public DataHora getHora() {
		return hora;
	}

	public void setHora(DataHora hora) {
		this.hora = hora;
	}

	public DataHora getData() {
		return data;
	}

	public void setData(DataHora data) {
		this.data = data;
	}

	public int getQntVagas() {
		return qntVagas;
	}

	public void setQntVagas(int qntVagas) {
		this.qntVagas = qntVagas;
	}

	public User motorista() {
		return this.motorista;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof User)){
			return false;
		}
		Carona carona1 = (Carona) obj;
		return this.getOrigem().equals(carona1.getOrigem())
				&& this.getDestino().equals(carona1.getDestino())
				&& this.getData().equals(carona1.getData())
				&& this.getHora().equals(carona1.getHora())
				&& this.motorista().equals(carona1.motorista());
	}
}
