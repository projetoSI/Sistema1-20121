package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import ufcg.edu.br.Sistema120121.excecoes.DateErrorException;
import ufcg.edu.br.Sistema120121.excecoes.HourErrorException;
import ufcg.edu.br.Sistema120121.excecoes.LocalErrorException;
import ufcg.edu.br.Sistema120121.excecoes.QuantityVacancyErrorException;
import ufcg.edu.br.Sistema120121.sistema.Carona;
import ufcg.edu.br.Sistema120121.sistema.Data;
import ufcg.edu.br.Sistema120121.sistema.Hora;
import ufcg.edu.br.Sistema120121.sistema.IdentificadorCarona;
import ufcg.edu.br.Sistema120121.sistema.PontoDeEncontro;
import ufcg.edu.br.Sistema120121.sistema.User;


public class TestaClasseCarona {
	
	private Carona carona1;
	private Carona carona2;
	private User motorista1;
	private User motorista2;
	//private User motorista3;
	private IdentificadorCarona id1;
	private IdentificadorCarona id2;
	private PontoDeEncontro ponto1;
	//private PontoDeEncontro ponto2;
	
	
	@Before
	public void setup() throws Exception{
		
		motorista1 = new User("pherivelton", "1234567", "Pablo", "Rua Lalaa", "lol@lol.com","88888888");
		motorista2 = new User("luishenrique","1234567", "Luis", "Rua Indios Cariri", "luis@gmail.com", "66666666");
		//motorista3 = new User("luish2","1234565", "Henrique", "Rua Indios Potiguaras", "lh@gmail.com", "96666667");
		
		carona1 = new Carona("Joao Pessoa", "Campina Grande", new Hora("19:00"), new Data("01/05/2012"), 2, motorista1);
		id1 = new IdentificadorCarona(motorista1.getLogin(), carona1.getData(), carona1.getHora());
		
		carona2 = new Carona("Joao Pessoa", "Campina Grande", new Hora("13:00"), new Data("02/05/2012"), 4, motorista1);	
		id2 = new IdentificadorCarona(motorista1.getLogin(), carona2.getData(), carona2.getHora());
		
		ponto1 = new PontoDeEncontro();
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
	
	@Test
	public void testaSetDestino()throws LocalErrorException{
		assertEquals("Campina Grande", carona1.getDestino());
		carona1.setOrigem("Joao Pessoa");
		assertEquals("Campina Grande", carona1.getDestino());
		try{
			carona1.setOrigem("");
			fail("Destino Invalido");
		}catch (LocalErrorException e){}
		
		try{
			carona1.setOrigem("1234");
			fail("Destino Invalido");
		}catch (LocalErrorException e){}
		
		try{
			carona1.setOrigem("Joao Pessoa123");
			fail("Destino Invalido");
		}catch (LocalErrorException e){}
	}
	
	@Test
	public void testaGetID(){
		
		assertEquals("erro de assert", "pherivelton", id1.getLoginMotorista());
		assertEquals("erro de assert", "01/05/2012", id1.getDataCarona().getData());
		assertEquals("erro de assert", "19:00", id1.getHoraCarona().getHoras());
		
		assertEquals("erro de assert", "pherivelton", id2.getLoginMotorista());
		assertEquals("erro de assert", "02/05/2012", id2.getDataCarona().getData());
		assertEquals("erro de assert", "13:00", id2.getHoraCarona().getHoras());
		
		
	}
	@Test
	public void testaSetHora() throws HourErrorException{
		assertEquals("19:00", carona1.getHora().getHoras());
		carona1.setHora(new Hora("20:00"));
		assertEquals("20:00", carona1.getHora().getHoras());
		try{
			carona1.setHora(new Hora (""));
			fail("Hora Invalida");
		}catch (HourErrorException e){}
		
		try{
			carona1.setHora(new Hora ("raposa"));
			fail("Hora Invalida");
		}catch (HourErrorException e){}
	}
	
	@Test
	public void testaGetHora(){
		assertEquals("erro de assert", "19:00", carona1.getHora().getHoras());
		assertEquals("erro de assert", "13:00", carona2.getHora().getHoras());
		
	}
	
	@Test
	public void testaSetData() throws DateErrorException{
		assertEquals("01/05/2012", carona1.getData().getData());
		carona1.setData(new Data("01/09/2012"));
		assertEquals("01/09/2012", carona1.getData().getData());
		try{
			carona1.setData(new Data (""));
			fail("Data Invalida");
		}catch (DateErrorException e){}
		
		try{
			carona1.setData(new Data ("raposa"));
			fail("Data Invalida");
		}catch (DateErrorException e){}
	}
	
	@Test
	public void testaGetData(){
		assertEquals("erro de assert", "01/05/2012", carona1.getData().getData());
		assertEquals("erro de assert", "02/05/2012", carona2.getData().getData());
	}
	
	@Test
	public void testaGetQntVagas(){
		assertEquals("erro de assert", 2, carona1.getQntVagas());
		assertEquals("erro de assert", 4, carona2.getQntVagas());
	}
	
	@Test
	public void testaGetMotorista(){
		assertEquals("erro de assert", "pherivelton", carona1.getMotorista().getLogin());
		assertEquals("erro de assert", "pherivelton", carona2.getMotorista().getLogin());
	}
	
	@Test
	public void testaSetQuantVagas() throws QuantityVacancyErrorException{
		assertTrue(carona1.temVaga());
		carona1.setQntVagas(3);
		assertEquals(3, carona1.getQntVagas());
		
		carona1.setQntVagas(0);
		assertEquals(0, carona1.getQntVagas());
		try{
			carona1.setQntVagas(-1);
			fail("Vaga inválida");
		}catch (QuantityVacancyErrorException e){}
		
		try{
			carona1.setQntVagas(-4);
			fail("Vaga inválida");
		}catch (QuantityVacancyErrorException e){}
		
	}
	
	@Test
	public void testTemVaga() throws QuantityVacancyErrorException{
		assertTrue(carona1.temVaga());
		assertTrue(carona2.temVaga());
		
		carona1.setQntVagas(0);
		assertFalse(carona1.temVaga());
		
	}
	
	@Test
	public void testaGetPontoEncontro() throws Exception{
		ponto1.sugerirPonto("Minha casa");
		ponto1.aceitar();
		carona1.setPontoDeEncontro("Minha casa");
		//carona1.
		System.out.println(carona1.getPontoDeEncontro());
		

		
	}
	
//	@Test
//	public void testAddCaroneiro() throws Exception{
//		
//		//carona1 tem duas vagas
//		carona1.addCaroneiro(motorista2);
//		System.out.println(carona1.getCaroneiros());
//		//System.out.println(carona1.getSituacaoCaroneiro(motorista2));
//		assertTrue(carona1.temVaga());
//		//carona1.addCaroneiro(motorista3);
//		
//		//System.out.println(carona1.verificaCaroneiro(motorista2));
//		System.out.println(carona1.getQntVagas());
//		assertTrue(carona1.temVaga());
//		
//		//System.out.println(carona1.getCaroneiros());
//	
//	}
	

}
