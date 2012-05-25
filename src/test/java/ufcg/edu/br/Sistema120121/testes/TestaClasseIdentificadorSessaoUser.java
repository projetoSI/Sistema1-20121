package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ufcg.edu.br.Sistema120121.logica.*;

public class TestaClasseIdentificadorSessaoUser {
	private IdentificadorSessaoUser secao;
	private IdentificadorSessaoUser secao2;
	private IdentificadorSessaoUser secao3;
	private IdentificadorSessaoUser secao4;
	private IdentificadorSessaoUser secao5;
	
	@Before
	public void setup(){
		secao = new IdentificadorSessaoUser("pherivelton", "pherivelton@gmail.com");
		secao2 = new IdentificadorSessaoUser("pherivelton", "pherivelton@gmail.com");
		secao3 = new IdentificadorSessaoUser("pherivelton", "pherivelton2@gmail.com");
		secao4 = new IdentificadorSessaoUser("pherivelton2", "pherivelton@gmail.com");
		secao5 = new IdentificadorSessaoUser("jonh", "jonhnanthan@gmail.com");
		
	}
	
	@Test
	public void testaGetLogin(){
		assertTrue(secao.verificaLoginID("pherivelton"));
	}
	
	@Test
	public void testaAbreSessao(){
		assertFalse(secao.sessaoAtiva());
		secao.abreSessao();
		assertTrue(secao.sessaoAtiva());
	}
	
	@Test
	public void testaFechaSessao(){
		secao.abreSessao();
		assertTrue(secao.sessaoAtiva());
		secao.fechaSessao();
		assertFalse(secao.sessaoAtiva());
	}
	
	@Test
	public void testaVerificaLoginID(){
		assertTrue(secao.verificaLoginID("pherivelton"));
		assertFalse(secao.verificaLoginID("panda"));
	}
	
	@Test
	public void testaEquals(){
		assertTrue(secao.equals(secao2));
		assertFalse(secao.equals(secao3));
		assertFalse(secao.equals(secao4));
		assertFalse(secao.equals(secao5));
	}
	
	@Test
	public void testaToString(){
		assertEquals("pherivelton|pherivelton@gmail.com", secao.toString());
	}
	
	

}
