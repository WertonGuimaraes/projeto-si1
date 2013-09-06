package controller;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import model.LoginInvalidoException;
import model.Usuario;
import model.Util;

public class SessionController {
	
	private int contadorSessoes;
	private Map<Integer, Usuario> sessoesAbertas;
	
	private static SessionController sessoes; //singleton
	
	public static SessionController getInstance() {
		if (sessoes == null) {
			sessoes = new SessionController(); //verificar consistencia
		}
		return sessoes;
	}
	
	private SessionController(){
		sessoesAbertas = new HashMap<Integer, Usuario>();
		contadorSessoes = 0;
	}
	
	
	/**
	 * O método retorna o id correspondente à sessão do usuário(login).
	 * @param login
	 * @param senha
	 * @return
	 */
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
	
	public Usuario searchSessionById(String id){
		int idUsuario;
		try {
			idUsuario = Integer.parseInt(id);
		} catch (RuntimeException e) {
			throw new RuntimeException("Sessão inexistente");
		}
		return sessoesAbertas.get(idUsuario);
	}
	
	public int searchSessionByLogin(String login){
		for (Integer i: sessoesAbertas.keySet()){
			if(sessoesAbertas.get(i).getLogin().equals(login)){
				return i;
			}
		}
		throw new RuntimeException("Sessao inexistente");
	
	}
	
	
	public int newSessionId(){
		contadorSessoes++;
		for (int index = 0; index <= contadorSessoes; index++) { //acho q aqui da set of bounds pq vc add + 1 ao contador e tenta procurar elementos, como e set num tem q usar iterator nao?
			if( !(sessoesAbertas.containsKey(index)) ){
				return index;
			}
		}
		return contadorSessoes+1;//ai ja soma aqui de novo, realmente necessario, eu acho q ele nunca sai do for sem passar pelo if
	}

	public int getContadorSessoes() {
		return contadorSessoes;
	}


	public void zeraSessoes() {
		contadorSessoes = 0;
		sessoesAbertas = new HashMap<Integer, Usuario>();
				
	}
	
	public void desconectarSessao(int key){
		sessoesAbertas.remove(key);
	}
	
	
}
