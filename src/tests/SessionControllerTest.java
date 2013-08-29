package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.SessionController;

public class SessionControllerTest {

	private SessionController controladorDeSessao;

	
	@Before
	public void setUp(){
		
		controladorDeSessao = SessionController.getInstance();
	}
	
	
	@Test
	public void testaSessionID() {
		controladorDeSessao.newSessionId();
		assertEquals(1, controladorDeSessao.getContadorSessoes());
		controladorDeSessao.newSessionId();
		controladorDeSessao.newSessionId();
		assertEquals(3, controladorDeSessao.getContadorSessoes());
	}

}
