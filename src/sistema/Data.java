package sistema;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import excecoes.DateErrorException;

public class Data{

	String dia,mes,ano;
	
	
	public Data(String dia,String mes,String ano) throws DateErrorException{
		if (dataValida(dia, mes, ano)){
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
		}else throw new DateErrorException("Data inválida");
	
	}
	
	public String getData(){
		return dia + "/" + mes + "/" + ano;
	}
		
	public String getDia() {
		return dia;
	}

	public String getMes() {
		return mes;
	}


	public String getAno() {
		return ano;
	}
	
	public boolean dataValida (String dia, String mes, String ano){
		String dataAtual = getDataAtual();
		String dataEntrada = dia + "/" + mes + "/" + ano;
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date dataEntradaFormatada = format.parse(dataEntrada);
			Date dataAtualFormatada = format.parse(dataAtual);
			if (dataEntradaFormatada.before(dataAtualFormatada)) return false;	
		}catch (ParseException e){
			return false;
		}
		if (!(verificaMeses31(dia, mes)) || !(verificaMeses30(dia, mes)) || !(verificaFevereiro(dia, mes, ano)) ||
			(Integer.parseInt(mes) <= 0) || (Integer.parseInt(mes) > 12)) return false;
		else if ((dia.isEmpty()) || (mes.isEmpty()) || (ano.isEmpty())) return false;
		else if ((dia == null) || (mes == null) || (ano == null)) return false;
		return true;
		
	}
	
	public boolean verificaMeses31(String dia, String mes){
		if (((mes == "01") || (mes == "03") || (mes == "05") || (mes == "07") ||
			(mes == "08") || (mes == "10") || (mes == "12")) && ((Integer.parseInt(dia) > 31) || Integer.parseInt(dia) <= 0)) return false;
		return true;
	}
	
	public boolean verificaMeses30(String dia, String mes){
		if (((mes == "04") || (mes == "06") || (mes == "09") || (mes == "11")) &&
			((Integer.parseInt(dia) > 30) || Integer.parseInt(dia) <= 0)) return false;
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