package facade.easyAccept;

import model.Util;
import controller.Controller;

public class MyEasyAcceptFacade {
	
//	private final String NOME = "nome";
//	private final String EMAIL = "email";

//	private final String DATACRIACAO = "dataCriacao";

	
	private Controller controller;

	public MyEasyAcceptFacade() {
		this.controller = Controller.getInstance();
	}
	
	public void zerarSistema(){
		
	}
	
	public int abrirSessao(String login, String senha){
		return controller.getSessoes().abrirSessao(login, senha);
	}
	
	public void criarUsuario(String login, String senha, String nome, String endereco, String email){
		controller.criaConta(login, senha, nome, email, endereco);
	}
	

	public String getAtributoUsuario(String login, String atributo){
		if ( atributo == null || Util.isEmpty(atributo)) throw new RuntimeException("Atributo inválido"); 	
		
		else if (atributo.equals("nome")) return controller.searchUsuariobyLogin(login).getNome();
		else if (atributo.equals("endereco")) return controller.searchUsuariobyLogin(login).getEndereco();
		
		
		throw new RuntimeException("Atributo inexistente");
	}
	
	public void encerrarSistema(){
		controller.zerarSistema();
	}
	
	
}
