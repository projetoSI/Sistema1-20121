package ufcg.edu.br.Sistema120121.sistema;

public class IdentificadorCarona {
	
	private String loginMotorista;
	private Hora horaCarona;
	private Data dataCarona;
	
	
	public IdentificadorCarona(String login,Data data,Hora hora) {
		this.dataCarona = data;
		this.horaCarona = hora;
		this.loginMotorista = login;
	}
	
	public String getLoginMotorista() {
		return loginMotorista;
	}

	public Hora getHoraCarona() {
		return horaCarona;
	}

	public Data getDataCarona() {
		return dataCarona;
	}
		
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
	
	
	@Override
	public String toString() {
		return getLoginMotorista() + "|" + getDataCarona() + "|" + getHoraCarona();
	}
	

}
