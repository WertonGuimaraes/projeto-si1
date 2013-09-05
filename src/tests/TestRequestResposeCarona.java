package tests;

import junit.framework.Assert;

import model.Usuario;

import org.junit.Before;

import controller.Controller;

public class TestRequestResposeCarona {
	private Controller controller;

	@Before
	public void init(){
		controller = Controller.getInstance();
		
		try {
			controller.criaConta("j", "j", "j", "j", "j");
		} catch (Exception e1) {
			Assert.fail();
		}
		
		try {
			controller.criaConta("j2", "j2", "j2", "j2", "j2");
		} catch (Exception e) {
			Assert.fail();
		}
		
		controller.getSessoes().abrirSessao("j", "j");
		controller.getSessoes().abrirSessao("j2", "j2");
		
		Usuario user1 = controller.searchUsuariobyLogin("j");
		
		user1.adicionaCarona("a", "a", "12/12/2013", "12:23", 4);
	}

}
