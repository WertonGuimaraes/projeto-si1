package controller;

import java.util.HashSet;
import java.util.Set;

import model.Usuario;

public class Controller {

	private Set<Usuario> usuarios;
	
	private static Controller controller; 

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
	}

	public void criaConta(String login, String nome, String email, String endereco) {
		Usuario usuario = new Usuario(login, nome, email, endereco);
		usuarios.add(usuario);
	}

	public Set<Usuario> getListaUsuarios() {
		return usuarios;
	}

	
	
	
}
