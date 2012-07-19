package ufcg.edu.br.Sistema120121.logica;

public class Interesse {

	private final User user;
	private final String horaInicio;
	private final String horaFim;
	private final String origem;
	private final String destino;
	private final String data;

	public Interesse(User user, String origem, String destino, String dataAux,
			String horaInicio, String horaFim) {
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.data = dataAux;
		this.user = user;
		this.origem = origem;
		this.destino = destino;
	}

	public User getUser() {
		return user;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public String getData() {
		return data;
	}

	public String getID() {
		return user.getID() + "|" + getOrigem() + " - " + getDestino() + "|"
				+ getHoraInicio() + "-" + getHoraFim() + "|" + getData();
	}	
}
