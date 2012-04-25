package testes;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TesteEasy {

	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();
//		files.add("scripts/US01.txt"); OK
//		files.add("scripts/US02.txt");
//		files.add("scripts/US03.txt"); OK
//		files.add("scripts/US04.txt");
//		files.add("scripts/US05.txt");

		
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(SistemaFacede.getInstanceFacede(),files);
		
		eaFacade.executeTests();
		
		System.out.println(eaFacade.getCompleteResults());
		
	}


}
