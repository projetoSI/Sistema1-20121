package ufcg.edu.br.Sistema120121.testes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import easyaccept.EasyAcceptFacade;

public class TesteEasy {
	@Test
	public void testarEasyAcceptScript() {

		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
		files.add("scripts/US02.txt");

		// Instantiate the Monopoly Game fa�ade
		SistemaFacede fachadaBigu = SistemaFacede.getInstanceFacede();
		
		// Instantiate EasyAccept fa�ade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(fachadaBigu, files);

		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());


		assertTrue(eaFacade.getTotalNumberOfNotPassedTests() == 0);
	}


}
