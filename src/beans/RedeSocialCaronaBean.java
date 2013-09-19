package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;

import controller.Controller;



@ManagedBean(name = "redeBean", eager = true)
@ApplicationScoped
public class RedeSocialCaronaBean {

	public RedeSocialCaronaBean(){
		criaUsuarios();
	}
	
	private void criaUsuarios(){
		Controller.getInstance().criaConta("r", "r", "r", "r", "r");
	}
	
}
