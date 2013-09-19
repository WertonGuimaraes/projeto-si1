package model;

import java.io.Serializable;

public class LoginInvalidoException extends RuntimeException implements Serializable {


	/**
	 * Classe para exceções no login do usuario
	 */
	private static final long serialVersionUID = 1L;

	public LoginInvalidoException(){
		
	}
	
	public LoginInvalidoException(String message){
		super(message);
	}
}
