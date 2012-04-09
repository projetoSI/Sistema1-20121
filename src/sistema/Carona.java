package sistema;
import excecoes.*;

public class Carona {

	private String origem;
	private String destino;
	private String data;
	private String hora;
	private String qntVagas;
	private User motorista;

	public Carona(String origem, String destino, String hora, String data, String qntVagas, User motorista) throws LocalErrorException, QuantityVacancyErrorException {
		setOrigem(origem);
		setDestino(destino);
		setHora(hora);
		setData(data);
		setQntVagas(qntVagas);
		this.motorista = motorista;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) throws LocalErrorException {
		if (!(origem == null) && (origem.matches("[A-Za-zÇ-ú\\s]*+")) && (!(origem.isEmpty()))) this.origem = origem;
		else throw new LocalErrorException("Origem inválida");
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) throws LocalErrorException {
		if (!(destino == null) && (destino.matches("[A-Za-z\\s]*+")) && (!(destino.isEmpty()))) this.destino = destino;
		else throw new LocalErrorException("Destino inválido");
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getQntVagas() {
		return qntVagas;
	}

	public void setQntVagas(String qntVagas) throws QuantityVacancyErrorException {
		if (!(qntVagas == null) && (qntVagas.matches("[0-9]*") && (Integer.parseInt(qntVagas) > 0))) this.qntVagas = qntVagas;
		else throw new QuantityVacancyErrorException("Vaga inválida");
	}

	public User getMotorista() {
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
				&& this.getMotorista().equals(carona1.getMotorista());
	}
}