package controller;

import java.util.HashMap;
import java.util.Map;

import model.LoginInvalidoException;
import model.Usuario;
import model.Util;

public class SessionController {
	
	private int contadorSessoes;
	private Map<Integer, Usuario> sessoesAbertas;
	
	private static SessionController sessoes; //singleton
	
	public static SessionController getInstance() {
		if (sessoes != null) {
			return sessoes;
		}
		if (sessoes == null) {
			sessoes = new SessionController(); //verificar consistencia
		}
		return sessoes;
	}
	
	private SessionController(){
		sessoesAbertas = new HashMap<Integer, Usuario>();
		contadorSessoes = 0;
	}
	
	
	public int abrirSessao(String login, String senha){
		if( login == null || Util.isEmpty(login)) throw new LoginInvalidoException("Login inválido");
		
		Usuario newUsr = Controller.getInstance().searchUsuariobyLogin(login);
		
		if (newUsr == null) {throw new RuntimeException("Usuário inexistente"); } //FIXME criar/utilizar exceção do case
		else if (newUsr.autenticateAccount(senha)){
			int newId = newSessionId();
			sessoesAbertas.put(newId, newUsr);
			return newId;
		}
			throw new RuntimeException("Login inválido");
	}
	
	
	public int newSessionId(){
		contadorSessoes++;
		for (int index = 0; index <= contadorSessoes; index++) {
			if( !(sessoesAbertas.containsKey(index)) ){
				return index;
			}
		}
		return contadorSessoes+1;
	}

	public int getContadorSessoes() {
		return contadorSessoes;
	}


	public void zeraSessoes() {
		contadorSessoes = 0;
		sessoesAbertas = new HashMap<Integer, Usuario>();
				
	}
	
	
}
