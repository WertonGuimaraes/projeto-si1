package facade.easyAccept;

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
		return atributo;
	}
	
	public void encerrarSistema(){
		controller.zerarSistema();
	}
	
	
}
