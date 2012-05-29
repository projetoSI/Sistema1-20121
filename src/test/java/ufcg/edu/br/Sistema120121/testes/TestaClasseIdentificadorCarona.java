package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ufcg.edu.br.Sistema120121.logica.*;

public class TestaClasseIdentificadorCarona {
	private IdentificadorCarona id;
	private IdentificadorCarona id2;
	private IdentificadorCarona id3;
	
	@Before
	public void setup() throws DateErrorException, HourErrorException{
		id = new IdentificadorCarona("pherivelton", new Data("14/08/2012"), new Hora("23:00"));
		id2 = new IdentificadorCarona("pherivelton", new Data("14/08/2012"), new Hora("23:00"));
		id3 = new IdentificadorCarona("jonhnanthan", new Data("29/09/2012"), new Hora("14:00"));
	}
	
	@Test
	public void testaGetLoginMotorista(){
		assertEquals("pherivelton", id.getLoginMotorista());
	}
	
	@Test
	public void testaGetHoraCarona(){
		assertEquals("23:00", id.getHoraCarona());
	}
	
	@Test
	public void testaGetDataCarona(){
		assertEquals("14/08/2012", id.getDataCarona());
	}
	
	@Test
	public void testaEquals(){
		assertTrue(id2.equals(id));
		assertFalse(id.equals(id3));
	}
	
	@Test
	public void testaToString(){
		assertEquals("pherivelton|14/08/2012|23:00", id.toString());
		assertEquals("pherivelton|14/08/2012|23:00", id2.toString());
		assertEquals("jonhnanthan|29/09/2012|14:00", id3.toString());
	}
}
