package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ufcg.edu.br.Sistema120121.logica.*;

public class TestaPontoDeEncontro {
	
	// O teste do metodo sugerirPonto, getSugestaoAtual, já foram feitos na classe User
	// O teste do metodo getPontoDeEncontro, já testado dentro do metodo testaAceitar()
	
	private PontoDeEncontro ponto;
	
	@Before
	public void setup(){
		ponto = new PontoDeEncontro();
	}
	
	@Test
	public void testaAceitar() {
		try {
			ponto.sugerirPonto("Cajá");
			ponto.aceitar();
			assertEquals("Cajá", ponto.getPontoDeEcontro());
		} catch (PontoDeEncontroException e) {
			e.printStackTrace();
		}
		
		try {
			ponto.sugerirPonto(null);
			ponto.aceitar();
			fail("Ponto de encontro inválido");
		} catch (PontoDeEncontroException e) {
		}
		
		try {
			ponto.sugerirPonto("");
			ponto.aceitar();
			fail("Ponto de encontro inválido");
		} catch (PontoDeEncontroException e) {
		}
	}
	
	@Test
	public void testaToString() throws PontoDeEncontroException{
		ponto.sugerirPonto("Campina Grande");
		ponto.aceitar();
		assertEquals("Local do encontro: Campina Grande", ponto.toString());
	}
	
}