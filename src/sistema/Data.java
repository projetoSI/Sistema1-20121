package sistema;

import java.util.Date;
import java.net.DatagramPacket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import excecoes.*;

public class Data{

	//List<String> meses = new ArrayList<String>();
	String dia,mes,ano;
	
	
	public Data(String dia,String mes,String ano) throws DateErrorException{
		String dataAtual = getDataAtual();
		String dataEntrada = dia + "/" + mes + "/" + ano;
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date dataEntradaFormatada = format.parse(dataEntrada);
			Date dataAtualFormatada = format.parse(dataAtual);
			if (dataEntradaFormatada.equals(dataAtualFormatada) || dataEntradaFormatada.after(dataAtualFormatada)){
				this.dia = dia;
				this.mes = mes;
				this.ano = ano;
			}else{
				throw new DateErrorException("Data Invalida");
			}
		}catch (ParseException e){}
	
	}
	
	
	/*private void carregarMeses(){
		meses.add("Jan");meses.add("Feb");meses.add("Mar");meses.add("Apr");meses.add("May");meses.add("Jun");meses.add("Jul");meses.add("Aug");meses.add("Set");meses.add("Oct");meses.add("Nov");meses.add("Dec");
	}*/
	
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

	public String getDataAtual(){
		Locale local = new Locale("pt","BR");
		GregorianCalendar calendario = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' - 'HH':'mm'h'",local);
		String dataAtual = formatador.format(calendario.getTime()).substring(0, 10);
		return dataAtual;
	}	
	
	public boolean verificaData(String dia, String mes, String ano){
		
		if (dia.matches("^[0-9]*") || mes.matches("^[0-9]*") || ano.matches("^[0-9]*")){
			return false;
		}
		return true;
	}
//	private int ComparaAno(Data data){//Metodo para comparar Ano
//		return Integer.parseInt(this.getAno()) - Integer.parseInt(data.getAno());
//	}
//
//	private int ComparaMes(Data data){//Metodo para comparar Mes
//		return meses.indexOf(this.getMes()) - meses.indexOf(data.getMes());
//	}
//	
//	private int ComparaDia(Data data){//Metodo para comparar Dia
//		return  Integer.parseInt(this.getDia()) - Integer.parseInt(data.getDia());
//	}
	
//	public int compareTo(DataHora o) {
//		Data data = (Data) o;
//		return 0;
		
//		if (this.ComparaAno(data) != 0) {
//			return this.ComparaAno(data);
//		}else{			
//			if (this.ComparaMes(data) != 0) {
//				return this.ComparaMes(data);
//			}else{
//				return this.ComparaDia(data);
//			}
//		}
		
	}
