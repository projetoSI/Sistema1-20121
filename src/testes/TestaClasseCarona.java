package testes;

import static org.junit.Assert.*;
import sistema.Carona;
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
	public void setup() throws LocalErrorException, QuantityVacancyErrorException, AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException{
		motorista1 = new User("pherivelton", "1234567", "Pablo", "Rua Lalaa", "lol@lol.com","88888888");
		carona1 = new Carona("Joao Pessoa", "Campina Grande", "19:00", "01/05/2012", "02", motorista1);
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
			fail("Origem Inválida");
		}catch (LocalErrorException e){}
		
		try{
			carona1.setOrigem("1234");
			fail("Origem Inválida");
		}catch (LocalErrorException e){}
		
		try{
			carona1.setOrigem("João Pessoa123");
			fail("Origem Inválida");
		}catch (LocalErrorException e){}
	}
}
