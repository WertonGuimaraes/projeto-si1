package controller;

import java.util.HashSet;
import java.util.Set;

import model.Usuario;

public class Controller {

	private Set<Usuario> usuarios;
	private SessionController controladorDeSessoes;
	
	private static Controller controller; //singleton

	public static Controller getInstance() {
		if (controller != null) {
			return controller;
		}
		// controller = metodoPersistencia(); TODO implementar persistencia

		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private Controller() {
		usuarios = new HashSet<Usuario>();
		controladorDeSessoes = SessionController.getInstance();
	}

	public void criaConta(String login, String senha, String nome, String email, String endereco) {
		Usuario usuario = new Usuario(login, senha, nome, email, endereco);
		usuarios.add(usuario);
	}
	
	public Usuario searchUsuariobyLogin(String login){
		for (Usuario usr : usuarios) {
			if (usr.getLogin().equals(login)) {
				return usr;
			}
		}
		return null;
	}
	
	public void zerarSistema(){
		controladorDeSessoes = null;
		Controller.getInstance();
	}
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public SessionController getSessoes() {
		return controladorDeSessoes;
	}

	
	
}
