package tests;

import static org.junit.Assert.*;
import model.Usuario;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;

public class ControllerTest {
	private Controller controlador;
	
	@Before
	public void iniciaSistema(){
		controlador = Controller.getInstance();
	}
	
	@Test
	public void testaCriarConta() {
		String login = "rafael";
		String senha = "xpto";
		String nome = "Rafael";
		String email = "crafards@gmail.com";
		String endereco = "rua do ze";
		controlador.criaConta(login,senha, nome , email, endereco);
		assertTrue(controlador.getUsuarios().size() > 0);
		
	}
	
	@Test 
	public void testaRecuperarUsuario(){
		String login = "chuck";
		String senha = "xpto";
		String nome = "C. Norris";
		String email = "chucknorris@ccc.ufcg.edu.br";
		String endereco = "olimpo";
		Usuario usr = new Usuario(login, senha, nome, email, endereco); 
		controlador.criaConta(login, senha, nome, email, endereco);
		Usuario newUsr = controlador.searchUsuariobyLogin(login);
		
		assertEquals(usr,newUsr);
	}

}
