package ufcg.edu.br.Sistema120121.sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import ufcg.edu.br.Sistema120121.excecoes.DateErrorException;

public class Data{

	private String data;
	
	/**
	 * Construtor de uma data.
	 * @param data
	 * 		String representando uma data
	 * @throws DateErrorException
	 * 		Caso o formato da data esteja incorreto.
	 */
	public Data(String data) throws DateErrorException{
		if (dataValida(data)){
			this.data = data;
		}else throw new DateErrorException("Data inválida");
	
	}
	
	/**
	 * Retorna a data.
	 * @return
	 * 		A data.
	 */
	public String getData(){
		return data;
	}
	
	/**
	 * Retorna o dia de um objeto data.
	 * @return
	 * 		O dia.
	 */
	public String getDia() {
		return data.substring(0, 2);
	}

	/**
	 * Retorna o mes de um objeto data.
	 * @return
	 * 		O mes.
	 */
	public String getMes() {
		return data.substring(3, 5);
	}

	/**
	 * O ano de um objeto data.
	 * @return
	 * 		O ano.
	 */
	public String getAno() {
		return data.substring(6, 10);
	}
	
	/**
	 * Verifica se o formato da data esta correto
	 * @param data
	 * 		A data a ser verificada..
	 * @return
	 * 		true - caso o formato esteja correto.
	 * 		false - caso o formato não esteja correto.
	 */
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
	
	/**
	 * Verifica os meses que possui 31 dias.
	 * @param dia
	 * 		O dia do mes.
	 * @param mes
	 * 		O mes.
	 * @return
	 * 		true - caso esteja correto.
	 * 		false - caso não esteja correto.
	 */
	public boolean verificaMeses31(String dia, String mes){
		if (((mes.equals("01")) || (mes.equals("03")) || (mes.equals("05")) || (mes.equals("07")) ||
			(mes.equals("08")) || (mes.equals("10")) || (mes.equals("12"))) && ((Integer.parseInt(dia) > 31) 
			|| Integer.parseInt(dia) <= 0)) return false;
		return true;
	}
	
	
	/**
	 * Verifica os meses que possui 31 dias.
	 * @param dia
	 * 		O dia do mes.
	 * @param mes
	 * 		O mes.
	 * @return
	 * 		true - caso esteja correto.
	 * 		false - caso não esteja correto.
	 */
	public boolean verificaMeses30(String dia, String mes){
		if (((mes.equals("04")) || (mes.equals("06")) || (mes.equals("09")) || (mes.equals("11"))) &&
			((Integer.parseInt(dia) > 30) || (Integer.parseInt(dia) <= 0))) return false;
		return true;
	}
	
	/**
	 * Verifica o mes de fevereiro.
	 * @param dia
	 * 		O dia do mes.
	 * @param mes
	 * 		O mes.
	 * @return
	 * 		true - caso esteja correto.
	 * 		false - caso não esteja correto.
	 */
	public boolean verificaFevereiro(String dia, String mes, String ano){
		GregorianCalendar calendario = new GregorianCalendar();
		if (mes.equals("02")){
			if (calendario.isLeapYear(Integer.parseInt(ano)) && ((Integer.parseInt(dia) > 29 && Integer.parseInt(dia) <= 0)) ||
				(!(calendario.isLeapYear(Integer.parseInt(ano))) && ((Integer.parseInt(dia) > 28 || Integer.parseInt(dia) <= 0)))) return false;	
		}
		return true;
	}

	/**
	 * Retorna a data atual.
	 * @return
	 * 		A data atual.
	 */
	public String getDataAtual(){
		Locale local = new Locale("pt","BR");
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' - 'HH':'mm'h'",local);
		String dataAtual = formatador.format(calendario.getTime()).substring(0, 10);
		return dataAtual;
	}
}