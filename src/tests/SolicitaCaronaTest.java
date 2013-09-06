package tests;

import static org.junit.Assert.*;
import junit.framework.Assert;
import model.Carona;
import model.Usuario;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;

public class SolicitaCaronaTest {

	private Controller controller;
	private Usuario user1,user2;

	@Before
	public void startUp() {
		try {
			controller=Controller.getInstance();
			controller.criaConta("xpto", "xpto", "xpto", "xpto@gmail.com", "xpto street");
			controller.criaConta("joao", "joao", "joao", "joao@gmail.com", "casa muito engracada");
			this.user1=controller.searchUsuariobyLogin("xpto");
			this.user2=controller.searchUsuariobyLogin("joao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSolicitarVaga(){
		try {
			controller.getSessoes().abrirSessao("xpto", "xpto");
			controller.getSessoes().abrirSessao("joao", "joao");
			
			int idCarona=user1.adicionaCarona("cg", "jp", "27/10/2013", "12:12", "3");
			Carona c = controller.buscaCarona(idCarona);
			
			assertTrue(c.getVagas()==3);
			
			int idVaga=controller.solicitaVaga(user2, c);
			
			assertTrue(user1.getRequests().size() != 0);
			
			user1.aceitaRequest(idVaga);
			
			assertTrue(c.getVagasLivres()==2);
			assertTrue(user1.getRequests().size() == 0);
			
			int sessionId=controller.getSessoes().searchSessionByLogin("xpto");
			controller.getSessoes().desconectarSessao(sessionId);
			sessionId=controller.getSessoes().searchSessionByLogin("joao");
			controller.getSessoes().desconectarSessao(sessionId);
			controller.getSessoes().zeraSessoes();
			controller.zerarSistema();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void TestErrorTwoAprovations(){
		controller.getSessoes().abrirSessao("xpto", "xpto");
		controller.getSessoes().abrirSessao("joao", "joao");
		
		int idCarona;
		try {
			idCarona = user1.adicionaCarona("cg", "jp", "27/10/2013", "12:12", "3");
			Carona c = controller.buscaCarona(idCarona);
			int idVaga=controller.solicitaVaga(user2, c);
			
			assertTrue(user1.getRequests().size() != 0);
			user1.aceitaRequest(idVaga);
			user1.aceitaRequest(idVaga);
		} catch (Exception e) {
			assertEquals(e.getMessage(),"Solicitação inexistente");
			
		}
		
	}
}
