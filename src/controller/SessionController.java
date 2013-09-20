package controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import model.LoginInvalidoException;
import model.Usuario;
import model.Util;

public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private int contadorSessoes;
	private Map<Integer, Usuario> sessoesAbertas;

	private static SessionController sessoes; // singleton

	public static SessionController getInstance() {
		if (sessoes == null) {
			sessoes = new SessionController(); // verificar consistencia
		}
		return sessoes;
	}

	private SessionController() {
		sessoesAbertas = new HashMap<Integer, Usuario>();
		contadorSessoes = 0;
	}

	/**
	 * O método retorna o id correspondente à sessão do usuário(login).
	 * @param login
	 * @param senha
	 * @return
	 */
	public int abrirSessao(String login, String senha) {
		if (login == null || Util.isEmpty(login))
			throw new LoginInvalidoException("Login inválido");

		Usuario newUsr = Controller.getInstance().searchUsuariobyLogin(login);

		if (newUsr == null) {
			throw new RuntimeException("Usuário inexistente");
		}
		else if (newUsr.autenticateAccount(senha)) {
			int newId = newSessionId();
			sessoesAbertas.put(newId, newUsr);
			return newId;
		}
		throw new RuntimeException("Login inválido");
	}

	/**
	 * Procura uma sessão baseado no Id
	 * @param id
	 * @return usuario procurado
	 */
	public Usuario searchSessionById(String id) {
		int idUsuario;
		try {
			idUsuario = Integer.parseInt(id);
		} catch (RuntimeException e) {
			throw new RuntimeException("Sessão inexistente");
		}
		return sessoesAbertas.get(idUsuario);
	}

	/**
	 * Procura uma sessão baseado no login
	 * @param login
	 * @return int id da Sessão procurada
	 */
	public int searchSessionByLogin(String login) {
		for (Integer i : sessoesAbertas.keySet()) {
			if (sessoesAbertas.get(i).getLogin().equals(login)) {
				return i;
			}
		}
		throw new RuntimeException("Sessao inexistente");

	}

	/**
	 * Incrementa o contador de Id das sessões e retorna um Id
	 * @return id da sessão
	 */
	public int newSessionId() {
		contadorSessoes++;
		for (int index = 0; index <= contadorSessoes; index++) {
			if (!(sessoesAbertas.containsKey(index))) {
				return index;
			}
		}
		return contadorSessoes + 1;// ai ja soma aqui de novo, realmente
									// necessario, eu acho q ele nunca sai do
									// for sem passar pelo if
	}

	public int getContadorSessoes() {
		return contadorSessoes;
	}

	/**
	 * Zera as variáveis na classe, encerrando todas as sessões.
	 */
	public void zeraSessoes() {
		contadorSessoes = 0;
		sessoesAbertas = new HashMap<Integer, Usuario>();

	}

	/**
	 * Remove uma sessão em específico.
	 * @param key
	 */
	public void desconectarSessao(int key) {
		sessoesAbertas.remove(key);
	}
	
	public Map<Integer,Usuario>getSessoesAbertas(){
		return this.sessoesAbertas;
	}

}
