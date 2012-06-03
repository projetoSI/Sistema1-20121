package ufcg.edu.br.Sistema120121.logica;

import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.dados.AcessaDados;

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
	
	public void enviaMensagem() throws HourErrorException, DateErrorException, CaronaException {
		if (!buscaInteresse().isEmpty()) {
			for (Carona c : buscaInteresse()) {
				user.receberMensagem("Carona cadastrada no dia "+ c.getData()+", às "+ c.getHora() +" de acordo com os seus interesses registrados. Entrar em contato com " + c.getMotorista().getEmail());
			}			
		}
		
	}

	//Esse metodo eh aki?!
	private List<Carona> buscaInteresse() throws HourErrorException, DateErrorException, CaronaException {
		Hora horaInicio = new Hora(getHoraInicio());
		Hora horaFim = new Hora(getHoraFim());
		Data dataAux = new Data(getData());
		List<Carona> auxCaronas = AcessaDados.getInstance().localizarCarona(getOrigem(), getDestino());
		List<Carona> interesses = new LinkedList<Carona>();
		for (Carona carona : auxCaronas) {
//			AQUI VAI VERIFICAR DATA E HORA,PARA ADD A LISTA APENAS AS CARONAS QUE FAZEM PARTE DO INTERESSE
//			VOU IMPLEMENTAR QUANDO FOR RESOLVIDO O PROBLEMA DA DATA E HORA
//			if(carona.getData().compareTo(dataAux)){
//				
//			}
//			
			
			
		}
		return interesses;
		
 

	}
	
	
}
