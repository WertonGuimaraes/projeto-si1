package beans;

import java.util.HashSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Usuario;
import controller.Controller;
import controller.SessionController;

@SessionScoped
@ManagedBean(name="perfilBean")
public class PerfilBean {
	public static int ID;
	private  int id;
	private  Usuario usuario;	 

	
	public Usuario getUsuario() {
		return usuario;
	}

	public PerfilBean(){
		System.out.println("no construtorp");
		System.out.println(ID);
		if(ID != 0){
			this.usuario = SessionController.getInstance().searchSessionById(ID);
			this.id = ID;
		}
		
		this.usuario = SessionController.getInstance().searchSessionById(ID);
	}
	
	public void test(){
		System.out.println("iai?");
	}
	

}
