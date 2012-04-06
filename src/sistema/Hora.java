package sistema;

public class Hora implements DataHora {
	
	private String minutos,hora;
	
	public Hora(String hora,String minutos){
		this.hora = hora;
		this.minutos = minutos;
	}
		
	public String getHoras(){
		return hora + ":" + minutos;
	}
	
	
	public String getMinutos() {
		return minutos;
	}
	
	public String getHora() {
		return hora;
	}
	
	private int comparaHora(Hora hora){//Compara as horas
		return Integer.parseInt(this.getHora()) - Integer.parseInt(hora.getHora()) ;
	}
	
	private int comparaMinutos(Hora hor){//Compara os minutos
		return Integer.parseInt(this.getMinutos()) - Integer.parseInt(hor.getMinutos());
	}
	

	public int compareTo(DataHora o) {
		Hora hora = (Hora) o;
		if (this.comparaHora(hora) != 0) {
			return this.comparaHora(hora);
		}else{
			return this.comparaMinutos(hora);
		}
		
	}
	
}
