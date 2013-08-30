package beans;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Carona;
import model.Usuario;
import controller.Controller;
import controller.SessionController;

@SessionScoped
@ManagedBean(name="perfilBean")
public class PerfilBean {
	public static String ID;
	private String id;
	private  Usuario usuario;	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public PerfilBean(){
		System.out.println("no construtorp");
		System.out.println(ID);
		if(ID != null){
			this.usuario = SessionController.getInstance().searchSessionById(ID);
			this.id = ID;
			
		}
		
		this.usuario = SessionController.getInstance().searchSessionById(ID);
	}
	
	public void test(){
		System.out.println("iai?");
	}
	
	public String logoffButton(){
		SessionController.getInstance().desconectarSessao(0); //FIXME o certo e passar id
		return "index.xhtml";
	}
	
	public Collection<Carona> getCaronas(){
		return usuario.getCaronas().values();
	}
	

}
