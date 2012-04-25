package sistema;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import excecoes.HourErrorException;

public class Hora{
	
	private String Hora;
	
	public Hora(String hora) throws HourErrorException{
		if (horaValida(hora)){
			this.Hora = hora;
		}
		else throw new HourErrorException("Hora inválida");
	}
		
	public String getHoras(){
		return Hora;
	}
	
	
	public String getMinutos() {
		return Hora.substring(3,5);
	}
	
	public String getHora() {
		return Hora.substring(0, 2);
	}
	
	public boolean horaValida(String Hora){
		String hora, minutos;
		try{
		    hora = Hora.substring(0, 2);
			minutos = Hora.substring(3, 5);
		}catch (StringIndexOutOfBoundsException b){return false;}
		 catch (NullPointerException a){return false;}
		if (Hora.length() != 5) return false;
		if ((verificaHora(hora)) && (verificaMinutos(minutos)) && (Hora.length() == 5)) return true;
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

