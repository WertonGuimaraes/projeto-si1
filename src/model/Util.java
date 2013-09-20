package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Util implements Serializable {

	private static final long serialVersionUID = 1L;

	
	/**
	 * this class is used for general stuff
	 */	
	
	private static String[] caracteresInvalidos = {"-","(", ")", "!", "?","."};
	
	/**
	 * Verifica se a String passada contem algum caracter invalido
	 * @param s
	 * @return
	 */
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
	
	
	/**
	 * Transforma String em um formato aceitavel pelo EasyAccept
	 * @param list
	 * @return
	 */
	public static String easyAccListParser(List<?> list){
		//if(list.isEmpty()) return "[]";
		
		String result = list.toString();
		result=result.replaceAll(", ",",");
		result=result.replace("[","{");
		result=result.replace("]","}");
		return result;
	}
	
	/**
	 * Extrai os pontos de encontro em uma lista de pontos de encontro
	 * separados por ;
	 * @param pontosDeEncontro
	 * @return
	 */
	public static List<String> extractListPoints(String pontosDeEncontro) {
		List<String> result = new ArrayList<String>();
		
		for(String ponto: pontosDeEncontro.split(";")){
			result.add(ponto);
		}
		
		return result;
	}

	
}
