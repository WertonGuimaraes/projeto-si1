package tests;

import static org.junit.Assert.assertTrue;

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
		String nome = "Rafael";
		String email = "crafards@gmail.com";
		String endereco = "rua do ze";
		controlador.criaConta(login, nome , email, endereco);
		assertTrue(controlador.getListaUsuarios().size() > 0);
		
	}

}
