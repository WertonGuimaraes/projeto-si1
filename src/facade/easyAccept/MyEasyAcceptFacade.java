package facade.easyAccept;

import java.util.List;

import model.Carona;
import model.Usuario;
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
		controller.zerarSistema();
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
	
	public String localizarCarona(int idSessao, String origem, String destino){
		Usuario usr = controller.getSessoes().searchSessionById(idSessao);
		List<Integer> caronas = usr.buscaCarona(origem, destino);
		
		return Util.easyAccListParser(caronas);
	}
	
	public int cadastrarCarona(int idSessao, String origem, String destino, String data, String hora, int vagas  ){
		Usuario usr = Controller.getInstance().getSessoes().searchSessionById(idSessao);
		int idCarona = usr.adicionaCarona(origem, destino, data, hora, vagas);
		
		return idCarona;
	}
	
	public String getAtributoCarona(int idCarona, String atributo){
		if( atributo.equals("origem")) return Controller.getInstance().buscaCarona(idCarona).getOrigem();
		if( atributo.equals("destino")) return Controller.getInstance().buscaCarona(idCarona).getDestino();
		if( atributo.equals("data")) return Controller.getInstance().buscaCarona(idCarona).getDate();
		if( atributo.equals("vagas")) return ""+Controller.getInstance().buscaCarona(idCarona).getVagas();
		
		return atributo;
	}
	
	public String getTrajeto(int idCarona){
		return Controller.getInstance().buscaCarona(idCarona).getTrajeto();
	}
	
	public String getCarona(int idCarona){
		return Controller.getInstance().buscaCarona(idCarona).toString();
	}
	
	public void encerrarSistema(){
		controller.zerarSistema();
	}
	
	
}
