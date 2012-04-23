package testes;
import sistema.Data;
import static org.junit.Assert.*;

import org.junit.*;

import excecoes.DateErrorException;

public class TestaClasseData {
	private Data data;
	
	@Before
	public void setup() throws DateErrorException{
		data = new Data("23/05/2012");
	}
	
	@Test
	public void testaGetDia(){
		assertEquals("23", data.getDia());
		assertFalse("eh pra dar erro", data.getDia().equals("22"));
	}
	
	@Test
	public void testaGetMes(){
		assertEquals("05", data.getMes());
		assertFalse("eh pra dar erro", data.getMes().equals("06"));
	}
	
	@Test
	public void testaGetAno(){
		assertEquals("2012", data.getAno());
		assertFalse("eh pra dar erro", data.getAno().equals("2013"));
	}
	
	@Test
	public void testaGetData(){
		assertEquals("23/05/2012", data.getData());
	}
	
	//LEMBRAR DE MUDAR A DATA NA HORA DE TESTAR.
//	@Test
//	public void testaDataAtual(){
//		assertEquals("21/04/2012", data.getDataAtual());
//	}
	
	@Test
	public void testaDataValida(){
		//Testa a aceitacao do dia
		assertFalse(data.dataValida("00/05/2012"));
		assertFalse(data.dataValida("32/05/2012"));
		assertFalse(data.dataValida("Pablo/05/2012"));
		assertFalse(data.dataValida("!@#$/05/2012"));
		
		//Testa a aceitacao do mes
		assertFalse(data.dataValida("23/00/2012"));
		assertFalse(data.dataValida("23/13/2012"));
		assertFalse(data.dataValida("23/Pablo/2012"));
		assertFalse(data.dataValida("23/!@#$/2012"));
		
		//Testa a aceitacao do ano
		assertFalse(data.dataValida("23/05/2011"));
    	assertFalse(data.dataValida("23/05/Pablo"));
		assertFalse(data.dataValida("23/05/!@#$"));
		assertFalse(data.dataValida("23/05/0000"));
		
		//Testa os meses que terminam em 30
//		assertFalse(data.dataValida("31/04/2013"));
//		assertFalse(data.dataValida("31/06/2013"));
//		assertFalse(data.dataValida("31/09/2013"));
		assertFalse(data.dataValida("31/11/2013"));
		assertTrue(data.dataValida("30/04/2013"));
		assertTrue(data.dataValida("30/06/2013"));
		assertTrue(data.dataValida("30/09/2013"));
		assertTrue(data.dataValida("30/11/2013"));
		
		//Testa os meses que terminam em 31
		assertTrue(data.dataValida("31/01/2013"));
		assertTrue(data.dataValida("31/03/2013"));
		assertTrue(data.dataValida("31/05/2013"));
		assertTrue(data.dataValida("31/07/2013"));
		assertTrue(data.dataValida("31/08/2013"));
		assertTrue(data.dataValida("31/10/2013"));
		assertTrue(data.dataValida("31/12/2013"));
		
		//Testa Fevereiro e Bissexto
		assertTrue(data.dataValida("28/02/2013"));
		assertTrue(data.dataValida("29/02/2016"));
//		assertFalse(data.dataValida("29/02/2013"));
		
		//Testa outras datas invalidas
		assertFalse(data.dataValida("-23/-05/-2012"));
		assertFalse(data.dataValida("23.0/02.0/2013.0"));
		assertFalse(data.dataValida("28,0/02,0/2013,0"));
		assertFalse(data.dataValida("28/02/13"));
		assertFalse(data.dataValida(""));
		assertFalse(data.dataValida(null));
		
		
    	//Testa algumas datas validas
		
		assertTrue(data.dataValida("01/01/2013"));
		assertTrue(data.dataValida("30/01/2013"));
		assertTrue(data.dataValida("01/02/2013"));
		assertTrue(data.dataValida("27/02/2013"));
		assertTrue(data.dataValida("01/03/2013"));
		assertTrue(data.dataValida("30/03/2013"));
		assertTrue(data.dataValida("01/04/2013"));
		assertTrue(data.dataValida("29/04/2013"));
		assertTrue(data.dataValida("01/05/2013"));
		assertTrue(data.dataValida("30/05/2013"));
		assertTrue(data.dataValida("01/06/2013"));
		assertTrue(data.dataValida("29/06/2013"));
		assertTrue(data.dataValida("01/07/2013"));
		assertTrue(data.dataValida("30/07/2013"));
		assertTrue(data.dataValida("01/08/2013"));
		assertTrue(data.dataValida("30/08/2013"));
		assertTrue(data.dataValida("01/09/2013"));
		assertTrue(data.dataValida("29/09/2013"));
		assertTrue(data.dataValida("01/10/2013"));
		assertTrue(data.dataValida("30/10/2013"));
		assertTrue(data.dataValida("01/11/2013"));
		assertTrue(data.dataValida("29/11/2013"));
		assertTrue(data.dataValida("01/12/2013"));
		assertTrue(data.dataValida("30/12/2013"));
	}
	
}