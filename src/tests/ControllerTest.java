package tests;

import static org.junit.Assert.*;
import junit.framework.Assert;
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
		try {
			controlador.criaConta(login,senha, nome , email, endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(controlador.getUsuarios().size() > 0);
		
	}
	
	@Test 
	public void testaRecuperarUsuario(){
		String login = "chuck";
		String senha = "xpto";
		String nome = "C. Norris";
		String email = "chucknorris@ccc.ufcg.edu.br";
		String endereco = "olimpo";
		Usuario usr=null;
		try {
			usr = new Usuario(login, senha, nome, email, endereco); 
			controlador.criaConta(login, senha, nome, email, endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Usuario newUsr = controlador.searchUsuariobyLogin(login);
		
		assertEquals(usr,newUsr);
	}
	
	@SuppressWarnings("unused")
	@Test 
	public void testaTrocaPontosDeEncontro() throws Exception{
		String login = "chuck";
		String senha = "xpto";
		String nome = "C. Norris";
		String email = "chucknorris@ccc.ufcg.edu.br";
		String endereco = "olimpo";
		Usuario usr,usr2;

		try {
			controlador.criaConta(login, senha, nome, email, endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		usr = controlador.getInstance().searchUsuariobyLogin("chuck"); 
		
		login = "chuck2";
		senha = "xpto2";
		nome = "C. Norris2";
		email = "chucknorris2@ccc.ufcg.edu.br";
		endereco = "olimpo2";

		try {
			controlador.criaConta(login, senha, nome, email, endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		usr2 = controlador.searchUsuariobyLogin("chuck2"); 
		
		int idSessao1 = controlador.getSessoes().abrirSessao("chuck", "xpto");
		int idSessao2 = controlador.getSessoes().abrirSessao(login, senha);
		int idCarona = usr.adicionaCarona("campina", "joao pessoa", "12/12/2014", "12:23", "4");
		
		Assert.assertEquals(usr.getResponsesPontosPendentes().size(), 0);
		Assert.assertEquals(usr2.getRequisicoesPontosPendentes().size(), 0);
				
		int idSugestao;
		idSugestao = controlador.sugerirPontoEncontro(String.valueOf(idSessao2), idCarona, "rodoviaria;shopping");
		
		Assert.assertEquals(usr.getResponsesPontosPendentes().size(), 1);
		Assert.assertEquals(usr2.getRequisicoesPontosPendentes().size(), 1);
		
		//Preciso implementar os estados de request e response
		Assert.assertEquals(usr2.getRequisicaoPoint(idSugestao).getState().toString(), "Aguardando aprovacao");
		Assert.assertEquals(usr.getResponsePoint(idSugestao).getState().toString(), "Solicitacao recebida");
	
		controlador.respondeSolicitacaoMeetingPoint(idSessao1,  idCarona, idSugestao, "shopping;extra");
		
		Assert.assertEquals(usr.getResponsePoint(idSugestao).getState().toString(), "Solicitacao aprovada");
		Assert.assertEquals(usr2.getRequisicaoPoint(idSugestao).getState().toString(), "Motorista aprovou");
		
		
	}

}
