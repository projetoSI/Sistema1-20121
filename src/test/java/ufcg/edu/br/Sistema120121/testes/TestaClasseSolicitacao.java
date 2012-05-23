package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ufcg.edu.br.Sistema120121.sistema.*;

public class TestaClasseSolicitacao {
	private Carona carona;
	private User caroneiro;
	private User motorista;
	private Solicitacao solicitacao;
	
	@Before
	public void setup() throws Exception{
		motorista = new User("pherivelton", "123456", "Pablo","Rua lalala", "pherivelton@gmail.com","88888888");
		caroneiro = new User("rafael", "panda", "Rafael Ribeiro", "Rua perto da UFCG", "rafael@gmail.com", "12345678");
		carona = new Carona("Joao Pessoa", "Campina Grande", new Hora("19:00"), new Data("29/09/2012"), 2, motorista);
		solicitacao = new Solicitacao(carona, caroneiro, "Caja");
	}
	
	@Test
	public void testaGetDonoDaCarona(){
		assertEquals("Pablo", solicitacao.getDonoDaCarona());
	}
	
	@Test
	public void testaGetCaroneiro(){
		assertEquals("Rafael Ribeiro", solicitacao.getCaroneiro());
	}
	
	//@Test
	//public void testaGetPontoDeEncontro(){
	//	assertEquals("Caja", solicitacao.getPontoDeEncontro().getPontoDeEcontro());
	//}
	
	@Test
	public void testaGetCaronaDesejada(){
		assertEquals("Joao Pessoa para Campina Grande, no dia 29/09/2012, as 19:00", solicitacao.getCaronaDesejada().toString());
	}
	
	@Test
	public void testaConfirmarCarona() throws Exception{
		assertTrue(carona.temVaga());
		solicitacao.confirmarSolicitacao();
		assertEquals(1, carona.getQntVagas());
	}
	
	//@Test
	//public void testaAlteraLocalDeEncontro() throws Exception{
	//	solicitacao.AlterarLocalDeEncontro("Manzuá");
	//	assertEquals("Manzuá", solicitacao.getPontoDeEncontro().getPontoDeEcontro());
	//}
}
