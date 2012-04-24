package testes;

import sistema.Hora;
import static org.junit.Assert.*;

import org.junit.*;
import excecoes.HourErrorException;

public class TestaClasseHora {
	
	private Hora hora;
	private Hora hora1;
	private Hora hora2;
	
	@Before
	public void setup() throws HourErrorException, NumberFormatException{
		hora = new Hora("23:23");
		hora1 = new Hora("23:59");
		hora2 = new Hora("22:00");
		
	}
	
	@Test
	public void testaGetHora(){
		assertEquals("23", hora.getHora());
		assertFalse("eh pra dar erro", hora.getHora().equals("24"));
	}
	
	@Test
	public void testaGetMinutos(){
		assertEquals("23", hora.getMinutos());
		assertFalse("eh pra dar erro", hora.getMinutos().equals("24"));
	}
	
	@Test
	public void testaGetHorario(){
		assertEquals("23:23", hora.getHoras());
		assertFalse("eh pra dar erro", hora.getHora().equals("22:22"));
	}
	
	@Test
	public void testaHoraValida(){
		
		//Testa Horas Invalidas
		assertFalse(hora.horaValida("-01:00"));
		assertFalse(hora.horaValida("24:00"));
		assertFalse(hora.horaValida("23:60"));
		assertFalse(hora.horaValida(""));
		assertFalse(hora.horaValida(null));
		assertFalse(hora.horaValida("@#:@#"));
		assertFalse(hora.horaValida("23.0:23.0"));
		assertFalse(hora.horaValida("23,0:23,0"));
		assertFalse(hora.horaValida("1:3"));
		
		
		//Testa Horas Validas
		assertTrue(hora.horaValida("00:00"));
		assertTrue(hora.horaValida("23:59"));
		assertTrue(hora.horaValida("22:30"));
		assertTrue(hora.horaValida("13:13"));
		assertTrue(hora.horaValida("01:00"));
		
		
	}
	
	@Test
	public void testaComparaHora(){
		assertFalse(hora.comparaHora(hora1));
		assertTrue(hora.comparaHora(hora2));
	}
}
