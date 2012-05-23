package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ufcg.edu.br.Sistema120121.excecoes.*;
import ufcg.edu.br.Sistema120121.sistema.*;

public class TestaPontoDeEncontro {
	
	// O teste do metodo sugerirPonto, getSugestaoAtual, já foram feitos na classe User
	// O teste do metodo getPontoDeEncontro, já testado dentro do metodo testaAceitar()
	
	private PontoDeEncontro ponto;
	
	@Before
	public void setup(){
		ponto = new PontoDeEncontro();
	}
	
	@Test
	public void testaAceitar() throws MeetingErrorException, PontoDeEncontroException{
		ponto.sugerirPonto("Cajá");
		ponto.aceitar();
		assertEquals("Cajá", ponto.getPontoDeEcontro());
		
		ponto.sugerirPonto(null);
		ponto.aceitar();
		fail("Ponto de encontro inválido");
		
		ponto.sugerirPonto("");
		ponto.aceitar();
		fail("Ponto de encontro inválido");
		
		ponto.sugerirPonto("123");
		ponto.aceitar();
		fail("Ponto de encontro inválido");
	}
	
	@Test
	public void testaToString() throws MeetingErrorException, PontoDeEncontroException{
		ponto.sugerirPonto("Campina Grande");
		ponto.aceitar();
		assertEquals("Local do encontro: Campina Grande", ponto.toString());
	}
	
}
