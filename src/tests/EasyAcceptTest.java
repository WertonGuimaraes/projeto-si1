package tests;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;
import facade.easyAccept.OurEasyAcceptFacade;

public class EasyAcceptTest {

	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		
		files.add("US01.txt");
//		files.add("US02.txt");
//		files.add("US03.txt"); //US da primeira entrega =p
//		files.add("US04.txt");
//		files.add("US05.txt");
	
		
		//Instanciate aplication Facade
		OurEasyAcceptFacade yourTestFacade = new OurEasyAcceptFacade(); //TODO deve receber uma instancia do sistema
	
		//Instanciate EasyAcceptFacade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(yourTestFacade, files);
		
		//Execute USs
		eaFacade.executeTests();
		
		//Print the results
		System.out.println(eaFacade.getCompleteResults());
	}

}
