package sistema;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import excecoes.DateErrorException;

public class Data{

	private String data;
	
	public Data(String data) throws DateErrorException{
		if (dataValida(data)){
			this.data = data;
		}else throw new DateErrorException("Data inválida");
	
	}
	
	public String getData(){
		return data;
	}
		
	public String getDia() {
		return data.substring(0, 2);
	}

	public String getMes() {
		return data.substring(3, 5);
	}


	public String getAno() {
		return data.substring(6, 10);
	}
	
	public boolean dataValida (String data){
		String dataAtual = getDataAtual();
		String dataEntrada = data;
		String dia, mes, ano;
		try{
		    dia = data.substring(0, 2);
			mes = data.substring(3, 5);
			ano = data.substring(6, 10);
		}catch (IndexOutOfBoundsException b){ return false;}
		 catch (NullPointerException c){return false;}
		
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date dataEntradaFormatada = format.parse(dataEntrada);
			Date dataAtualFormatada = format.parse(dataAtual);
			if (dataEntradaFormatada.before(dataAtualFormatada)) return false;	
		}catch (ParseException e){
			return false;
		}
		if (data.isEmpty() || data == null) return false;
		else if (!(verificaMeses31(dia, mes)) || !(verificaMeses30(dia, mes)) || !(verificaFevereiro(dia, mes, ano)) ||
			(Integer.parseInt(mes) <= 0) || (Integer.parseInt(mes) > 12)) return false;
		return true;
		
	}
	
	public boolean verificaMeses31(String dia, String mes){
		if (((mes == "01") || (mes == "03") || (mes == "05") || (mes == "07") ||
			(mes == "08") || (mes == "10") || (mes == "12")) && ((Integer.parseInt(dia) > 31) 
			|| Integer.parseInt(dia) <= 0)) return false;
		return true;
	}
	
	public boolean verificaMeses30(String dia, String mes){
		if (((mes == "04") || (mes == "06") || (mes == "09") || (mes == "11")) &&
			((Integer.parseInt(dia) > 30) || (Integer.parseInt(dia) <= 0))) return false;
		return true;
	}
	
	public boolean verificaFevereiro(String dia, String mes, String ano){
		GregorianCalendar calendario = new GregorianCalendar();
		if (mes == "02"){
			if (calendario.isLeapYear(Integer.parseInt(ano)) && ((Integer.parseInt(dia) > 29 && Integer.parseInt(dia) <= 0)) ||
				(!(calendario.isLeapYear(Integer.parseInt(ano))) && ((Integer.parseInt(dia) > 28 || Integer.parseInt(dia) <= 0)))) return false;	
		}
		return true;
	}

	public String getDataAtual(){
		Locale local = new Locale("pt","BR");
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' - 'HH':'mm'h'",local);
		String dataAtual = formatador.format(calendario.getTime()).substring(0, 10);
		return dataAtual;
	}
}