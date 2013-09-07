package model;

import java.util.ArrayList;
import java.util.List;

public class Util {

	
	/**
	 * this class is used for general stuff
	 */	
	
	private static String[] caracteresInvalidos = {"-","(", ")", "!", "?","."};
	
	public static boolean containsInvalidChar(String s){
		for(String invalid: caracteresInvalidos){
			if(s.contains(invalid)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isEmpty(String s){
		return s.equals("");
	}
	
	
	public static String easyAccListParser(List<?> list){
		String result = list.toString();
		result=result.replaceAll(", ",",");
		result=result.replace("[","{");
		result=result.replace("]","}");
		return result;
	}
	
	public static List<String> extractListPoints(String pontosDeEncontro) {
		List<String> result = new ArrayList<String>();
		
		for(String ponto: pontosDeEncontro.split(";")){
			result.add(ponto);
		}
		
		return result;
	}

	
	
}
