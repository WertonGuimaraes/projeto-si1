package model;

import java.util.List;

public class Util {

	
	/**
	 * this class is used for general stuff
	 */	
	
	private static String[] caracteresInvalidos = {"-","(", ")", "!", "?"};
	
	public static boolean containsInvalidChar(String s){
		
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
	
	
}
