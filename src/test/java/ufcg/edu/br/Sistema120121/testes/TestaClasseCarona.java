package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ufcg.edu.br.Sistema120121.logica.*;

public class TestaClasseCarona {
	
	private Carona carona1;
	private User motorista1;
	
	@Before
	public void setup() throws Exception{
		motorista1 = new User("pherivelton", "1234567", "Pablo", "Rua Lalaa", "lol@lol.com","88888888");
		carona1 = new Carona("Joao Pessoa", "Campina Grande", new Hora("19:00"), new Data("01/05/2014"), 2, motorista1, false);
	}
	
	@Test
	public void testaGetOrigem(){
		assertEquals("Joao Pessoa", carona1.getOrigem());
		assertFalse("eh pra dar erro", carona1.getOrigem().equals("Campina Grande"));
	}
	
	@Test
	public void testaSetOrigem() throws CaronaException{
		assertEquals("Joao Pessoa", carona1.getOrigem());
		carona1.setOrigem("Campina Grande");
		assertEquals("Campina Grande", carona1.getOrigem());
		try{
			carona1.setOrigem("");
			fail("Origem Inválida");
		}catch (CaronaException e){}
		
		try{
			carona1.setOrigem("1234");
			fail("Origem Inválida");
		}catch (CaronaException e){}
		
		try{
			carona1.setOrigem("João Pessoa123");
			fail("Origem Inválida");
		}catch (CaronaException e){}
	}
	
	@Test
	public void testaGetDestino() throws CaronaException{
		assertEquals("Campina Grande", carona1.getDestino());
		assertFalse("eh pra dar erro", carona1.getOrigem().equals("Cajá"));
	}
}
