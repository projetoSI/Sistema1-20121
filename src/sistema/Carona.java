package sistema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excecoes.*;

public class Carona {

	
	private String origem;
	private String destino;
	private String data;
	private String hora;
	private String qntVagas;
	private User motorista;
	protected enum Avaliacao {SIM,NAO,A_DEFINIR,MUDANCA_DE_LOCAL}//ENUM COM AS AVALIACOES(OBS : OLHAR MELHOR)
	private Map<User,String> sugestoes = new HashMap<User, String>(); //Mapa para quardar os usuarios e as sugestões de ponto de encontro
	private Map<User,Avaliacao> situacao = new HashMap<User, Avaliacao>(); //Mapa para quardar os usuarios e as avaliacoes de ponto de encontro(OBS : SO PRA TOMAR COMO BASE)
	private List<User> usuariosAprovados = new ArrayList<User>();//LISTA COM OS USUARIOS APROVADOS (TEMPORARIA)

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
	
	//INDICA UM PONTO DE ENCONTRO 
	public void setPontoDeEncontro(User caroneiro,String local,Avaliacao avaliacao){
		if (local.isEmpty()) {
			sugestoes.put(caroneiro, sugestoes.get(caroneiro));
		}else{
			sugestoes.put(caroneiro, local);
		}
		
		situacao.put(caroneiro, avaliacao);
	}
	
	//Verifica se a carona ainda tem vaga
	public boolean temVaga(){
		return Integer.parseInt(getQntVagas()) > 0;
	}
	
	//Adiciona usuarios que foram aprovados 
	public void addCaroneiro(User caroneiro){
		usuariosAprovados.add(caroneiro);
		qntVagas = (Integer.parseInt(qntVagas) - 1 ) + "";	
	}
	
	//RETORNA A SITUACAO DO USUARIO EM RELACAO A CARONA
	public Avaliacao getSituacao(User caroneiro){
		return situacao.get(caroneiro);
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