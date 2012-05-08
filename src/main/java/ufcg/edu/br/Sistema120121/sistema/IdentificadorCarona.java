package ufcg.edu.br.Sistema120121.sistema;

public class IdentificadorCarona {
	
	private String loginMotorista;
	private Hora horaCarona;
	private Data dataCarona;
	
	/**
	 * Contrutor de um indentificador para carona.
	 * @param login
	 * 		Login do motorista da carona
	 * @param data
	 * 		A data da carona.
	 * @param hora
	 * 		A hora da carona.
	 */
	public IdentificadorCarona(String login,Data data,Hora hora) {
		this.dataCarona = data;
		this.horaCarona = hora;
		this.loginMotorista = login;
	}
	
	/**
	 *Retorna o login do motorista dono da carona.. 
	 * @return
	 * 		O login do motorista.
	 */
	public String getLoginMotorista() {
		return loginMotorista;
	}

	/**
	 * Retorna a hora da carona.
	 * @return
	 * 		A hora da carona.
	 */
	public Hora getHoraCarona() {
		return horaCarona;
	}

	/**
	 * Retorna a data da carona
	 * @return
	 */
	public Data getDataCarona() {
		return dataCarona;
	}
		
	/**
	 * Compara dois identificadores e verifica se são iguais.
	 * @param obj
	 * 		O id a ser comparada.
	 * @return
	 * 		true - para o caso dos identificadores serem iguais.
	 * 		false - para o caso dos identificadores não serem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof IdentificadorCarona)){
			return false;
		}
		IdentificadorCarona id = (IdentificadorCarona) obj;

		return this.getLoginMotorista().equals(id.getLoginMotorista())
				&& this.getDataCarona().equals(id.getDataCarona())
				&& this.getHoraCarona().equals(id.getHoraCarona());
	}
	
	
	/**
	 * Retorna o id em forma de String.
	 * @return
	 * 		O id em forma de String.
	 */
	@Override
	public String toString() {
		return getLoginMotorista() + "|" + getDataCarona().getDataAtual() + "|" + getHoraCarona().getHoraAtual();
	}
	

}
