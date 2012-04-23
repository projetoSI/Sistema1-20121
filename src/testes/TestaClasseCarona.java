package testes;

import static org.junit.Assert.*;
import sistema.Carona;
import sistema.Data;
import sistema.Hora;
import sistema.User;
import org.junit.*;

import excecoes.*;
public class TestaClasseCarona {
	
	private Carona carona1;
	private Carona carona2;
	private Carona carona3;
	private User motorista1;
	private User motorista2;
	
	@Before
	public void setup() throws Exception{
		motorista1 = new User("pherivelton", "1234567", "Pablo", "Rua Lalaa", "lol@lol.com","88888888");
		carona1 = new Carona("Joao Pessoa", "Campina Grande", new Hora("19:00"), new Data("01/05/2012"), 2, motorista1);
		carona2 = new Carona("Joao Pessoa", "Campina Grande", new Hora("13:00"), new Data("02/05/2012"), 2, motorista1);
	}
	
	@Test
	public void testaGetOrigem(){
		assertEquals("Joao Pessoa", carona1.getOrigem());
		assertFalse("eh pra dar erro", carona1.getOrigem().equals("Campina Grande"));
	}
	
	@Test
	public void testaSetOrigem() throws LocalErrorException{
		assertEquals("Joao Pessoa", carona1.getOrigem());
		carona1.setOrigem("Campina Grande");
		assertEquals("Campina Grande", carona1.getOrigem());
		try{
			carona1.setOrigem("");
			fail("Origem Inv�lida");
		}catch (LocalErrorException e){}
		
		try{
			carona1.setOrigem("1234");
			fail("Origem Inv�lida");
		}catch (LocalErrorException e){}
		
		try{
			carona1.setOrigem("Jo�o Pessoa123");
			fail("Origem Inv�lida");
		}catch (LocalErrorException e){}
	}
	
	@Test
	public void testaGetDestino() throws LocalErrorException{
		assertEquals("Campina Grande", carona1.getDestino());
		assertFalse("eh pra dar erro", carona1.getOrigem().equals("Caj�"));
	}
}
