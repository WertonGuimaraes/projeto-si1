package model;

public class LoginInvalidoException extends RuntimeException {

	/**
	 * Classe para exceções no login do usuario
	 */
	private static final long serialVersionUID = -8537124074016363415L;

	public LoginInvalidoException(){
		
	}
	
	public LoginInvalidoException(String message){
		super(message);
	}
}
