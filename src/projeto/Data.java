package projeto;

import java.util.ArrayList;
import java.util.List;

public class Data implements DataHora {

	List<String> meses = new ArrayList<String>();
	String dia,mes,ano;
	
	
	public Data(String dia,String mes,String ano){
		carregarMeses();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	
	}
	
	
	private void carregarMeses(){
		meses.add("Jan");meses.add("Feb");meses.add("Mar");meses.add("Apr");meses.add("May");meses.add("Jun");meses.add("Jul");meses.add("Aug");meses.add("Set");meses.add("Oct");meses.add("Nov");meses.add("Dec");
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



	private int ComparaAno(Data data){//Metodo para comparar Ano
		return Integer.parseInt(this.getAno()) - Integer.parseInt(data.getAno());
	}

	private int ComparaMes(Data data){//Metodo para comparar Mes
		return meses.indexOf(this.getMes()) - meses.indexOf(data.getMes());
	}
	
	private int ComparaDia(Data data){//Metodo para comparar Dia
		return  Integer.parseInt(this.getDia()) - Integer.parseInt(data.getDia());
	}
	
//	@Override
	public int compareTo(DataHora o) {
		Data data = (Data) o;
		
		if (this.ComparaAno(data) != 0) {
			return this.ComparaAno(data);
		}else{
			
			if (this.ComparaMes(data) != 0) {
				return this.ComparaMes(data);
			}else{
				return this.ComparaDia(data);
			}
		}
		
	}

}
