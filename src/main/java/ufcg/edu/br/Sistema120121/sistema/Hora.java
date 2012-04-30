package ufcg.edu.br.Sistema120121.sistema;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import ufcg.edu.br.Sistema120121.excecoes.HourErrorException;

public class Hora{
	
	private String Hora;
	
	/**
	 * Construtor de uma hora.
	 * @param hora
	 * 		A hora.
	 * @throws HourErrorException
	 * 		Caso o formato do horario esteja incorreto.
	 */
	public Hora(String hora) throws HourErrorException{
		if (horaValida(hora)){
			this.Hora = hora;
		}
		else throw new HourErrorException("Hora inválida");
	}
		
	/**
	 * Retorna as horas.
	 * @return
	 * 		As horas.
	 */
	public String getHoras(){
		return Hora;
	}
	
	/**
	 * Os minutos de um objeto Hora.
	 * @return
	 * 		Os minutos
	 */
	public String getMinutos() {
		return Hora.substring(3,5);
	}
	
	/**
	 * As horas de um objeto Hora.
	 * @return
	 * 		As horas.
	 */
	public String getHora() {
		return Hora.substring(0, 2);
	}
	
	/**
	 * Verifica se uma determina hora é valida.
	 * @param Hora
	 * 		A hora a ser verificada.
	 * @return
	 * 		true - caso a hora seja valida.
	 * 		false - caso a hora não seja valida.
	 */
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
	
	/**
	 * Verifica se o formato da hora esta correto.
	 * @param hora
	 * 		a hora a ser verificada.
	 * @return
	 * 		true - para formato correto.
	 * 		false - para formato incorreto.  
	 */
	public boolean verificaHora(String hora){
		if ((hora == null) || (hora.isEmpty()) || (hora.matches("[^0-9]*")) ||
			(Integer.parseInt(hora)) < 0 || (Integer.parseInt(hora)) > 23) return false;
		return true;
	}
	
	/**
	 * Verifica se o formato dos minutos esta correto.
	 * @param hora
	 * 		os minutos a ser verificada.
	 * @return
	 * 		true - para formato correto.
	 * 		false - para formato incorreto.  
	 */
	public boolean verificaMinutos(String minutos){
		if ((minutos == null) || (minutos.isEmpty()) || (minutos.matches("[^0-9]*")) ||
			(Integer.parseInt(minutos)) < 0 || (Integer.parseInt(minutos)) > 59) return false;
		return true;
	}
	
	/**
	 * Retorna a hora atual.
	 * @return
	 * 		A hora atual.
	 */
	public String getHoraAtual(){
		Locale local = new Locale("pt","BR");
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' - 'HH':'mm'h'",local);
		String horaAtual = formatador.format(calendario.getTime()).substring(13, 18);
		return horaAtual;
	}	
	
	/**
	 * Compara dois objetos Hora.
	 * @param hora
	 * 		O objeto a ser comparado.
	 * @return
	 * 		0 - objetos iguais.
	 * 		>0 - para objeto maior que o objeto passado.
	 * 		<0 - para objeto menor que o objeto passado.
	 */
	public boolean comparaHora(Hora hora){
		if ((Integer.parseInt(this.getHora()) - Integer.parseInt(hora.getHora())) > 0 ) return true;
		else{
			if ((Integer.parseInt(this.getMinutos()) - Integer.parseInt(hora.getMinutos())) > 0) return true;
		}
		return false;
			
	}
}

