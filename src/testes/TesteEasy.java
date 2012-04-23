package testes;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TesteEasy {

	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();
//		files.add("US01.txt");
		files.add("US02.txt");
//		files.add("US03.txt");
//		files.add("US04.txt");
//		files.add("US05.txt");

		
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(SistemaFacede.getInstanceFacede(),files);
		
		eaFacade.executeTests();
		
		System.out.println(eaFacade.getCompleteResults());
		
	}


}
