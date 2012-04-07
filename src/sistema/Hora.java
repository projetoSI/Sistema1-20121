package sistema;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import excecoes.HourErrorException;

public class Hora{
	
	private String minutos,hora;
	
	public Hora(String hora,String minutos) throws HourErrorException, NumberFormatException{
		if (horaValida(hora, minutos)){
			this.hora = hora;
			this.minutos = minutos;
		}
		else throw new HourErrorException("Hora inválida");
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
	
	public boolean horaValida(String hora, String minutos){
		try{
			if ((verificaHora(hora)) && (verificaMinutos(minutos))) return true;
		}catch (NumberFormatException e){
			return false;
		}
		return false;
	}
	
	public boolean verificaHora(String hora){
		if ((hora == null) || (hora.isEmpty()) || (hora.matches("[^0-9]*")) ||
			(Integer.parseInt(hora)) < 0 || (Integer.parseInt(hora)) > 23) return false;
		return true;
	}
	
	public boolean verificaMinutos(String minutos){
		if ((minutos == null) || (minutos.isEmpty()) || (minutos.matches("[^0-9]*")) ||
			(Integer.parseInt(minutos)) < 0 || (Integer.parseInt(minutos)) > 59) return false;
		return true;
	}
	
	public String getHoraAtual(){
		Locale local = new Locale("pt","BR");
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' - 'HH':'mm'h'",local);
		String horaAtual = formatador.format(calendario.getTime()).substring(13, 18);
		return horaAtual;
	}	
	
	public boolean comparaHora(Hora hora){
		if ((Integer.parseInt(this.getHora()) - Integer.parseInt(hora.getHora())) > 0 ) return true;
		else{
			if ((Integer.parseInt(this.getMinutos()) - Integer.parseInt(hora.getMinutos())) > 0) return true;
		}
		return false;
			
	}
}
