package ufcg.edu.br.Sistema120121.sistema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	private Map<User,Situacao> caroneiros;
	private PontoDeEncontro pontoDeEncontro;
	public enum Situacao{FALTOU,NAO_FALTOU};
	
	
	/**
	 * Construtor de uma carona.
	 * @param origem
	 * 		Local de origem da carona.
	 * @param destino
	 * 		Local de destino da carona.
	 * @param hora
	 * 		A hora de saida.
	 * @param data
	 * 		A data da carona.
	 * @param qntVagas
	 * 		A quantidade de vagas de carona.
	 * @param motorista
	 * 		O motorista da carona.
	 * @throws LocalErrorException
	 * 		Caso a origem ou destino estejam em um formato incorreto.
	 * @throws QuantityVacancyErrorException
	 * 		Caso a quantidade de vagas passada seja negativa.
	 * 		
	 */
	public Carona(String origem, String destino, Hora hora, Data data, int qntVagas, User motorista) throws LocalErrorException, QuantityVacancyErrorException {
		setOrigem(origem);
		setDestino(destino);
		setHora(hora);
		setData(data);
		setQntVagas(qntVagas);
		this.motorista = motorista;
		this.ID = new IdentificadorCarona(motorista.getLogin(), data, hora);
		caroneiros = new TreeMap<User,Situacao>();
		pontoDeEncontro = new PontoDeEncontro();
	}

	/**
	 * Retorna o identificador da carona.
	 * @return
	 * 		O identificador da carona.
	 */
	public IdentificadorCarona getID(){
		return ID;
	}
	
	/**
	 * Retorna a origem da carona.
	 * @return
	 * 		A origem da carona.
	 */
	public String getOrigem() {
		return origem;
	}
	
	/**
	 * Altera a origem da carona.
	 * @param origem
	 * 		O novo local de partida.
	 * @throws LocalErrorException
	 * 		Caso o novo local esteja em um formato incorreto.
	 */
	public void setOrigem(String origem) throws LocalErrorException {
	
		if (!(origem == null) && (origem.matches("[A-Za-zÇ-ú\\s]*+")) && (!(origem.isEmpty()))) this.origem = origem;
		else throw new LocalErrorException("Origem inválida");
	}

	/**
	 * Retorna o destino da carona.
	 * @return
	 * 		O destino da carona.
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Altera o destino da carona.
	 * @param destino
	 * 		O novo local de chegada.
	 * @throws LocalErrorException
	 * 		Caso o novo local esteja em um formato incorreto.
	 */
	public void setDestino(String destino) throws LocalErrorException {
		if (!(destino == null) && (destino.matches("[A-Za-zÇ-ú\\s]*+")) && (!(destino.isEmpty()))) this.destino = destino;
		else throw new LocalErrorException("Destino inválido");
	}

	/**
	 * Retorna a hora da carona.
	 * @return
	 * 		A hora da carona.
	 */
	public Hora getHora() {
		return hora;
	}

	/**
	 * Altera a hora da carona.
	 * @param hora2
	 * 		A nova hora de partida.
	 */
	public void setHora(Hora hora2) {
		this.hora = hora2;
	}

	/**
	 * Retorna a data da carona.
	 * @return
	 * 		A data da carona.
	 */
	public Data getData() {
		return data;
	}

	/**
	 * Altera a data da carona.
	 * @param data2
	 * 		A nova data da carona.
	 */
	public void setData(Data data2) {
		this.data = data2;
	}

	/**
	 * Retorna a quantidade de vagas da carona.
	 * @return
	 * 		A quantidade de vagas da carona.
	 */
	public int getQntVagas() {
		return qntVagas;
	}

	/**
	 * Altera a quantidade de vagas da carona.
	 * @param qntVagas
	 * 		A nova quantidade de vagas.
	 * @throws QuantityVacancyErrorException
	 * 		Para o caso da quantidade de vagas passada ser negativa.
	 */
	public void setQntVagas(int qntVagas) throws QuantityVacancyErrorException {
		if (qntVagas >= 0) this.qntVagas = qntVagas;
		else throw new QuantityVacancyErrorException("Vaga inválida");
	}

	/**
	 * Retorna o motorista da carona.
	 * @return
	 * 		O motorista da carona.
	 */
	public User getMotorista() {
		return this.motorista;
	}
		
	/**
	 * Compara duas caronas e verifica se são iguais.
	 * @param obj
	 * 		A carona a ser comparada.
	 * @return
	 * 		true - para o caso das caronas serem iguais.
	 * 		false - para o caso das caronas não serem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Carona)){
			return false;
		}
		Carona carona = (Carona) obj;
		return this.getOrigem().equals(carona.getOrigem())
				&& this.getDestino().equals(carona.getDestino())
				&& this.getData().equals(carona.getData())
				&& this.getHora().equals(carona.getHora())
				&& this.getMotorista().equals(carona.getMotorista());
	}

	/**
	 * Retorna as informações da carona.
	 * @return
	 * 	As informaÃ§Ãµes da carona.
	 */
	@Override
	public String toString() {
		return getOrigem() + " para " + getDestino() + ", no dia " + getData().getData() + ", as " + getHora().getHoras();
	}
	
	/**
	 * Retorna o numero de vagas na carona.
	 * @return
	 * 		O numero de vagas na carona.
	 */
	public boolean temVaga() {
		return qntVagas > 0;
	}
	
	/**
	 * Adiciona um novo caroneiro.
	 * @param user
	 * 		O novo caroneiro.
	 * @throws Exception
	 * 		Caso a carona não possua mais vagas.
	 */
	public void addCaroneiro(User user)throws Exception{
		if (!this.temVaga()) {
			throw new Exception("Numero de vagas esgotado");
		}else{
			qntVagas = (qntVagas -1 );
			caroneiros.put(user,Situacao.NAO_FALTOU);
		
		}
	}
		
	/**
	 * Verifica se um determinado usuario possui uma vaga.
	 * @param user
	 * 		O usuario a ser verificado.
	 * @return
	 * 		true - caso para o caso do usuario possuir uma vaga.
	 * 		false - caso o para o caso do usuario não possuir uma vaga.
	 */
	public boolean verificaCaroneiro(User user){
		return caroneiros.containsKey(user);
	}
	
	/**
	 * Retorna os caroneiros da carona.
	 * @return
	 * 		Os caroneiros da carona.
	 */
	public String getCaroneiros(){
		String CaroneirosAprovados = "";
		Iterator<User> it = caroneiros.keySet().iterator();
		if (it.hasNext()) {
			CaroneirosAprovados += it.next().getNome() + ", ";
		}
		return CaroneirosAprovados;
	}
	
	/**
	 * Retorna o ponto de encontro da carona.
	 * @return
	 * 		O ponto de encontro da carona.
	 */
	public PontoDeEncontro getPontoDeEncontro() {
		return pontoDeEncontro;
	}
	
	
	
	public void setPontoDeEncontro(String pontoDeEncontro) throws Exception {
		this.pontoDeEncontro.sugerirPonto(pontoDeEncontro);
	}

	/**
	 * Retorna todas as informações da carona.
	 * @return
	 * 		Todas as informações da carona.
	 */
	public String detalharCarona(){
		return "Informacoes da carona: " + toString() + "Motorista: " + getMotorista() + "Caroneiros: " + getCaroneiros();
		
	}
	
	/**
	 * Retorna a situacao de um determinado caroneiro.
	 * @param caroneiro
	 * 		O caroneiro que tera a situacao retornada.
	 * @return
	 * 		A situacao do caroneiro.
	 */
	public Situacao getSituacaoCaroneiro(User caroneiro){
		return caroneiros.get(caroneiro);
	}
	
	/**
	 * Altera a situacao de um determinado usuario em relacao a carona
	 * @param situacao
	 * 		A nova situacao do usuario
	 * @param caroneiro
	 * 		O caroneiro que tera a situacao alterada.
	 */
	public void setSituacao(Situacao situacao,User caroneiro){
		caroneiros.put(caroneiro, situacao);
	}

}