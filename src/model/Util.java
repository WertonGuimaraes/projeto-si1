package model;

import java.util.List;

public class Util {

	
	/**
	 * this class is used for general stuff
	 */
	
	public static boolean isEmpty(String s){
		return s.equals("");
	}
	
	
	public static String easyAccListParser(List<?> list){
		String result = list.toString();
		return "{"+result.substring(1,result.length()-1)+"}";
	}
	
	
}
