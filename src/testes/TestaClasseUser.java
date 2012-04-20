package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import sistema.Carona;
import sistema.Data;
import sistema.Hora;
import sistema.RepositorioCaronas;
import sistema.User;
import org.junit.*;

import excecoes.*;


public class TestaClasseUser {
	private User user1;
	private User user2;
	private User user3;
	private Carona carona1;
	private Carona carona2;
	private List<User> listaAmigos = new ArrayList<User>();
	private List<Carona> caronasCadastradas = new ArrayList<Carona>();
	
	
	@Before
	public void setup() throws AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException{
		user1 = new User("pherivelton", "123456", "Pablo","Rua lalala", "pherivelton@gmail.com","88888888");
		user2 = new User("pherivelton", "123456", "Pablo","Rua lalala", "pherivelton@gmail.com","88888888");
		user3 = new User("pherivelton2", "123456", "PabloHerivelton","Rua dos Olivedos, 182", "pherivelton2@hotmail.com.br","8888888888");
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
			fail("Endere�o Invalido");
		}catch (AdressErrorException e){}
		
		try{
			user1.setEndereco("12345");
			fail("Endere�o Invalido");
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
	public void testEhAmigo(){
		user1.addAmigo(user2);
		user2.addAmigo(user1);
		user2.addAmigo(user3);
		
		
		assertFalse(user1.ehAmigo(user3));
		assertTrue(user1.ehAmigo(user2));
		assertTrue(user2.ehAmigo(user1));
		
	}
	
	@Test
	public void testExibeCadastro(){
		
		assertEquals("erro de asser", "Pablo pherivelton pherivelton@gmail.com Rua lalala 88888888", user1.exibeCadastro(user1));
		assertEquals("erro de asser","PabloHerivelton pherivelton2 pherivelton2@hotmail.com.br Rua dos Olivedos, 182 8888888888", user3.exibeCadastro(user3));
		
	}
	
	@Test
	public void testExibeListaAmigos(){
		
		user1.addAmigo(user2);
		user2.addAmigo(user1);
		user2.addAmigo(user3);
		
		assertTrue(user1.exibeListaDeAmigos(user1).contains(user2));
		assertFalse(user1.exibeListaDeAmigos(user1).contains(user3));
		assertTrue(user2.exibeListaDeAmigos(user2).contains(user1));
		assertFalse(user3.exibeListaDeAmigos(user3).contains(user2));
		assertTrue(user2.exibeListaDeAmigos(user2).contains(user3));
		
		assertEquals("erro de contagem de lita", 1, user1.exibeListaDeAmigos(user1).size());
		assertEquals("erro de contagem de lita", 2, user2.exibeListaDeAmigos(user2).size());
		assertEquals("erro de contagem de lita", 0, user3.exibeListaDeAmigos(user3).size());
		
	}

	
	@Test
	public void testExibeHistoricoCaronas() throws NumberFormatException, LocalErrorException, QuantityVacancyErrorException, HourErrorException, DateErrorException, AdressErrorException, EmailErrorException, PasswordErrorException, NameErrorException, PhoneErrorException, LoginErrorException{
		
		User motorista1 = new User("pherivelton", "1234567", "Pablo", "Rua Lalaa", "lol@lol.com","88888888");
		carona1 = new Carona("Joao Pessoa", "Campina Grande", new Hora("19", "00"), new Data("01","05","2012"), 2, motorista1);
		carona2 = new Carona("Joao Pessoa", "Campina Grande", new Hora("13", "00"), new Data("02","05","2012"), 2, motorista1);
		
		
		caronasCadastradas.add(carona1);
		caronasCadastradas.add(carona2);
		
		//motorista1.
		
		System.out.println(caronasCadastradas);
		
		RepositorioCaronas carona = null;
		
		System.out.println(carona.recuperaCaronaUser(user1));
		System.out.println(user1.exibeHistoricoDeCaronas(user1));
		
		
	}
	
	@Test
	public void testExibePerfil(){
		
		user1.addAmigo(user2);
		user2.addAmigo(user1);
		user2.addAmigo(user3);
		
		//o usuario 3 nao pode visualizar o perfil do user2 pq não é seu amigo.
		assertEquals("erro", "Impossivel visualizar perfil", user3.exibePerfil(user2));
		
		//o usuario 3 nao pode visualizar o perfil do user1 pq não é seu amigo.
		assertEquals("erro", "Impossivel visualizar perfil", user3.exibePerfil(user1));
		
		//o usuario 1 nao pode visualizar o perfil do user3 pq não é seu amigo.
		assertEquals("erro", "Impossivel visualizar perfil", user1.exibePerfil(user3));
		
		
		// O usuario 1 pode visualizar o perfil do usuario2, ja que o usuario 2 consta na sua lista de amigos.
		assertTrue(user1.exibePerfil(user2).contains(user2.getNome()));
		assertTrue(user1.exibePerfil(user2).contains(user2.getEmail()));
		assertTrue(user1.exibePerfil(user2).contains(user2.getLogin()));
		assertTrue(user1.exibePerfil(user2).contains(user2.getEndereco()));
		assertTrue(user1.exibePerfil(user2).contains(user2.getTelefone()));
		assertTrue(user1.exibePerfil(user2).contains(user2.exibeListaDeAmigos(user2).toString()));
		
		//o usuario2 pode pode visualizar o perfil do usuario1, ja que o usuario 1 consta na sua lista de amigos.
		assertTrue(user2.exibePerfil(user1).contains(user1.getNome()));
		assertTrue(user2.exibePerfil(user1).contains(user1.getEmail()));
		assertTrue(user2.exibePerfil(user1).contains(user1.getLogin()));
		assertTrue(user2.exibePerfil(user1).contains(user1.getEndereco()));
		assertTrue(user2.exibePerfil(user1).contains(user1.getTelefone()));
		assertTrue(user2.exibePerfil(user1).contains(user1.exibeListaDeAmigos(user1).toString()));
		
		// o usuario2 pode pode visualizar o perfil do usuario3, ja que o usuario 13 consta na sua lista de amigos.
		assertTrue(user2.exibePerfil(user3).contains(user3.getNome()));
		assertTrue(user2.exibePerfil(user3).contains(user3.getEmail()));
		assertTrue(user2.exibePerfil(user3).contains(user3.getLogin()));
		assertTrue(user2.exibePerfil(user3).contains(user3.getEndereco()));
		assertTrue(user2.exibePerfil(user3).contains(user3.getTelefone()));
		assertTrue(user2.exibePerfil(user3).contains(user3.exibeListaDeAmigos(user3).toString()));
		
	}
	
	
}
