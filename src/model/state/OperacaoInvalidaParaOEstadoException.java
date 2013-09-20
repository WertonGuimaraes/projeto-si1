package model.state;

import java.io.Serializable;

public class OperacaoInvalidaParaOEstadoException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	public OperacaoInvalidaParaOEstadoException(String message){
		super(message);
	}

}
