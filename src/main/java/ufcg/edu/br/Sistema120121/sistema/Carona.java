package ufcg.edu.br.Sistema120121.sistema;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.LocalErrorException;
import ufcg.edu.br.Sistema120121.excecoes.QuantityVacancyErrorException;

public class Carona {

	private String origem;
	private String destino;
	private Data data;
	private Hora hora;
	private int qntVagas;
	private User motorista;
	private IdentificadorCarona ID;
	private List<User> caroneiros;
	private PontoDeEncontro pontoDeEncontro;

	public Carona(String origem, String destino, Hora hora, Data data, int qntVagas, User motorista) throws LocalErrorException, QuantityVacancyErrorException {
		setOrigem(origem);
		setDestino(destino);
		setHora(hora);
		setData(data);
		setQntVagas(qntVagas);
		this.motorista = motorista;
		this.ID = new IdentificadorCarona(motorista.getLogin(), data, hora);
		caroneiros = new LinkedList<User>();
		pontoDeEncontro = new PontoDeEncontro();
	}

	public IdentificadorCarona getID(){
		return ID;
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
		if (!(destino == null) && (destino.matches("[A-Za-zÇ-ú\\s]*+")) && (!(destino.isEmpty()))) this.destino = destino;
		else throw new LocalErrorException("Destino inválido");
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora2) {
		this.hora = hora2;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data2) {
		this.data = data2;
	}

	public int getQntVagas() {
		return qntVagas;
	}

	public void setQntVagas(int qntVagas) throws QuantityVacancyErrorException {
		if (/*!(qntVagas == null) && (qntVagas.matches("[0-9]*") &&*/ qntVagas >= 0) this.qntVagas = qntVagas;
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

	@Override
	public String toString() {
		return getOrigem() + " para " + getDestino() + ", no dia " + getData().getData() + ", as " + getHora().getHoras();
	}
	
	//Verifica se ainda existe vaga na carona
	public boolean temVaga() {
		return qntVagas > 0;
	}
	
	//Adiciona um caroneiro logo apos o mesmo ser aprovado pelo motorista
	public void addCaroneiro(User user)throws Exception{
		if (!this.temVaga()) {
			throw new Exception("Numero de vagas esgotado");
		}else{
			qntVagas = (qntVagas -1 );
			caroneiros.add(user);
		
		}
	}
		
	//verifica se o caroneiro foi adionado a carona
	public boolean verificaCaroneiro(User user){
		return caroneiros.contains(user);
	}
	
	//Retorna uma String de todos os caroneiros aprovados
	public String getCaroneiros(){
		String CaroneirosAprovados = "";
		Iterator<User> it = caroneiros.iterator();
		if (it.hasNext()) {
			CaroneirosAprovados += it.next().getNome() + ", ";
		}
		return CaroneirosAprovados;
	}
	public PontoDeEncontro getPontoDeEncontro() {
		return pontoDeEncontro;
	}

}