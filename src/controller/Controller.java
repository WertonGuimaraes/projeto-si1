package controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Carona;
import model.LoginInvalidoException;
import model.Usuario;
import model.Util;

public class Controller {
	
	int contadorCaronas;
	private Set<Usuario> usuarios;

	private SessionController controladorDeSessoes;
	private static Controller controller; 			//singleton

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
		for (Usuario usuarioExistente : usuarios) {
			if (usuarioExistente.getEmail().equals(email))
				throw new LoginInvalidoException("Já existe um usuário com este email");
			else if (usuarioExistente.getLogin().equals(login))
				throw new LoginInvalidoException("Já existe um usuário com este login");
		}
		Usuario usuario = new Usuario(login, senha, nome, email, endereco);
		usuarios.add(usuario);
	}
	
	public Usuario searchUsuariobyLogin(String login){
		if(login == null || Util.isEmpty(login)) throw new RuntimeException("Login inválido");
		for (Usuario usr : usuarios) {
			if (usr.getLogin().equals(login)) {
				return usr;
			}
		}
		throw new RuntimeException("Usuário inexistente");
	}
	
	
	
	public void zerarSistema(){
		usuarios = new HashSet<Usuario>();
		controladorDeSessoes.zeraSessoes();
	}
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public SessionController getSessoes() {
		return controladorDeSessoes;
	}

	public List<Integer> buscaCarona(String origem, String destino) {
		boolean condicao;
		List<Integer> caronasEncontradas = new LinkedList<Integer>();
		for (Usuario usr : usuarios) {
			for (int chave: usr.getCaronas().keySet() ){
				Carona caronaExistente = usr.getCaronas().get(chave);	

				if (Util.isEmpty(origem) && Util.isEmpty(destino))
					condicao = true;
				else if(Util.isEmpty(destino))
					condicao = caronaExistente.getOrigem().equals(origem);
				else if (Util.isEmpty(origem))
					condicao = caronaExistente.getDestino().equals(destino);
				else
					condicao = caronaExistente.getDestino().equals(destino)
							&& caronaExistente.getOrigem().equals(origem);
				if(condicao)
					caronasEncontradas.add(chave);
				}
			}
		return caronasEncontradas;
		}
	
	
	public Carona buscaCarona(int idCarona) {
		for (Usuario usr : usuarios) {
			for (int idCaronaExistente : usr.getCaronas().keySet()) {
				if (idCarona == idCaronaExistente) return usr.getCaronas().get(idCarona);
			}
		}
		return null;
	}
	
	public int newCaronaId() {
		return contadorCaronas++;
	}



}

	
	
