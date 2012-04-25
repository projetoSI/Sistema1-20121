package sistema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import excecoes.*;

public class Carona {

	private String origem;
	private String destino;
	private Data data;
	private Hora hora;
	private int qntVagas;
	private User motorista;
	private IdentificadorCarona ID;
	private List<User> caroneiros;

//	protected enum Avaliacao {SIM,NAO,A_DEFINIR,MUDANCA_DE_LOCAL}//ENUM COM AS AVALIACOES(OBS : OLHAR MELHOR)
//	private Map<User,String> sugestoes = new HashMap<User, String>(); //Mapa para quardar os usuarios e as sugestões de ponto de encontro
//	private Map<User,Avaliacao> situacao = new HashMap<User, Avaliacao>(); //Mapa para quardar os usuarios e as avaliacoes de ponto de encontro(OBS : SO PRA TOMAR COMO BASE)
//	private List<User> usuariosAprovados = new ArrayList<User>();//LISTA COM OS USUARIOS APROVADOS (TEMPORARIA)

	public Carona(String origem, String destino, Hora hora, Data data, int qntVagas, User motorista) throws LocalErrorException, QuantityVacancyErrorException {
		setOrigem(origem);
		setDestino(destino);
		setHora(hora);
		setData(data);
		setQntVagas(qntVagas);
		this.motorista = motorista;
		this.ID = new IdentificadorCarona(motorista.getLogin(), data, hora);
		caroneiros = new ArrayList<User>();
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
	
	//INDICA UM PONTO DE ENCONTRO 
//	public void setPontoDeEncontro(User caroneiro,String local,Avaliacao avaliacao){
//		if (local.isEmpty()) {
//			sugestoes.put(caroneiro, sugestoes.get(caroneiro));
//		}else{
//			sugestoes.put(caroneiro, local);
//		}
//		
//		situacao.put(caroneiro, avaliacao);
//	}

	
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

}