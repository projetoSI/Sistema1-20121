package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ufcg.edu.br.Sistema120121.excecoes.AdressErrorException;
import ufcg.edu.br.Sistema120121.excecoes.DateErrorException;
import ufcg.edu.br.Sistema120121.excecoes.EmailErrorException;
import ufcg.edu.br.Sistema120121.excecoes.HourErrorException;
import ufcg.edu.br.Sistema120121.excecoes.LocalErrorException;
import ufcg.edu.br.Sistema120121.excecoes.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoes.NameErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PasswordErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PhoneErrorException;
import ufcg.edu.br.Sistema120121.excecoes.QuantityVacancyErrorException;
import ufcg.edu.br.Sistema120121.sistema.User;


public class TestaClasseUser {
	private User user1;
	private User user2;
	private User user3;
	private User user4;

	
	@Before
	public void setup() throws AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException, NumberFormatException, LocalErrorException, QuantityVacancyErrorException, HourErrorException, DateErrorException{
		user1 = new User("pherivelton", "123456", "Pablo","Rua lalala", "pherivelton@gmail.com","88888888");
		user2 = new User("pherivelton", "123456", "Pablo","Rua lalala", "pherivelton@gmail.com","88888888");
		user3 = new User("pherivelton2", "123456", "PabloHerivelton","Rua dos Olivedos, 182", "pherivelton2@hotmail.com.br","8888888888");
		user4 = new User("rafael", "panda", "Rafael Ribeiro", "Rua perto da UFCG", "rafael@gmail.com", "12345678");
		
	
	
	}
	
	@Test
	public void testaGetEndereco(){
		assertEquals("Rua lalala", user1.getEndereco());
		assertFalse("eh pra dar erro", user1.getEndereco().equals("Lalala"));
	}
	
	@Test
	public void testaSetEndereco() throws AdressErrorException{
		assertEquals("Rua lalala", user1.getEndereco());
		user1.setEndereco("Galo da Borborema");
		assertEquals("Galo da Borborema", user1.getEndereco());
		try{
			user1.setEndereco("");
			fail("Endereï¿½o Invalido");
		}catch (AdressErrorException e){}
		
		try{
			user1.setEndereco("12345");
			fail("Endereï¿½o Invalido");
		}catch (AdressErrorException e){}

	}
	
	@Test
	public void testaGetNome(){
		assertEquals("Pablo", user1.getNome());
		assertFalse("eh pra dar erro", user1.getNome().equals("Lalala"));
	}
	
	@Test
	public void testaSetNome() throws NameErrorException{
		assertEquals("Pablo", user1.getNome());
		user1.setNome("Barao do Cristo");
		assertEquals("Barao do Cristo", user1.getNome());
		try{
			user1.setNome("");
			fail("Nome Invalido");
		}catch (NameErrorException e){}
		
		try{
			user1.setNome("12345");
			fail("Nome Invalido");
		}catch (NameErrorException e){}
		try{
			user1.setNome("!@#$$%");
			fail("Nome Invalido");
		}catch (NameErrorException e){}
		
		try{
			user1.setNome("Pablo12345");
			fail("Nome Invalido");
		}catch (NameErrorException e){}
		
		try{
			user1.setNome("Ab");
			fail("Nome Invalido");
		}catch (NameErrorException e){}
		
	}
	
	@Test
	public void testaGetEmail(){
		assertEquals("pherivelton@gmail.com", user1.getEmail());
		assertFalse("eh pra dar erro", user1.getEmail().equals("Lalala"));
	}
	
	@Test
	public void testaSetEmail() throws EmailErrorException{
		assertEquals("pherivelton@gmail.com", user1.getEmail());
		user1.setEmail("pherivelton2@gmail.com");
		assertEquals("pherivelton2@gmail.com", user1.getEmail());
		try{
			user1.setEmail("");
			fail("E-mail Invalido");
		}catch (EmailErrorException e){}
		
		try{
			user1.setEmail("pherivelt@n@gmail.com");
			fail("E-mail Invalido");
		}catch (EmailErrorException e){}
		
		try{
			user1.setEmail("@gmail.com");
			fail("E-mail Invalido");
		}catch (EmailErrorException e){}
	}
	
	@Test
	public void testaGetTelefone(){
		assertEquals("88888888", user1.getTelefone());
		assertFalse("eh pra dar erro", user1.getTelefone().equals("Lalala"));
	}
	
	@Test
	public void testaSetTelefone() throws PhoneErrorException{
		assertEquals("88888888", user1.getTelefone());
		user1.setTelefone("8888888888");
		assertEquals("8888888888", user1.getTelefone());
		try{
			user1.setTelefone("8888-8888");
			fail("Telefone Errado");
		}catch (PhoneErrorException e){}
		
		try{
			user1.setTelefone("");
			fail("Telefone Errado");
		}catch (PhoneErrorException e){}
		
		try{
			user1.setTelefone("Pablo");
			fail("Telefone Errado");
		}catch (PhoneErrorException e){}
		
		try{
			user1.setTelefone("!@#$%");
			fail("Telefone Errado");
		}catch (PhoneErrorException e){}
		
		try{
			user1.setTelefone("123456");
			fail("Telefone Errado");
		}catch (PhoneErrorException e){}
	}
	
	@Test
	public void testaGetSenha(){
		assertEquals("123456", user1.getSenha());
		assertFalse("eh pra dar erro", user1.getSenha().equals("Lalala"));
	}
	
	@Test
	public void testaSetSenha() throws PasswordErrorException{
		assertEquals("123456", user1.getSenha());
		user1.setSenha("1234567");
		assertEquals("1234567", user1.getSenha());
		try{
			user1.setSenha("");
			fail("Senha Invalida");
		}catch (PasswordErrorException e){}
	}
	
	@Test
	public void testaGetLogin(){
		assertEquals("pherivelton",user1.getLogin());
		assertFalse("eh pra dar erro", user1.getLogin().equals("Raposeiro"));
		
	}
	
	@Test
	public void testaEquals(){
		assertTrue(user1.equals(user2));
		assertFalse(user1.equals(user3));
	}
	
	@Test
	public void testaEhAmigo(){
		user1.addAmigo(user1);
		assertFalse(user1.ehAmigo(user1));
		assertTrue(user1.getListaAmigos().isEmpty());
		user1.addAmigo(user4);
		assertFalse(user1.getListaAmigos().isEmpty());
		assertTrue(user1.ehAmigo(user4));
	}
	
	@Test
	public void testaAddAmigo(){
		assertTrue(user1.getListaAmigos().isEmpty());
		user1.addAmigo(user2);
		assertTrue(user1.getListaAmigos().isEmpty());
		user1.addAmigo(user4);
		assertFalse(user1.getListaAmigos().isEmpty());
	}
	
}