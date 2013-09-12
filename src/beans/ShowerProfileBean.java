package beans;

import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Carona;
import model.CaronaSolicitada;
import model.Usuario;


@ViewScoped
@ManagedBean(name = "ShowerProfileBean")
public class ShowerProfileBean {

	
	private Usuario user;
	
	public ShowerProfileBean(Usuario user){
		this.user=user;
	}
	
	public Usuario getUser(){
		return this.user;
	}
	
	public String getUserName(){
		return getUser().getNome();
	}
	
	public Set<Usuario> getFriends(){
		return getUser().getFriends();
	}
	
	public Map<Integer,Carona> getCaronasOfericidades(){
		return getUser().getCaronas();
	}
	
	public Map<Integer,CaronaSolicitada>getCaronasSolicitadas(){
		return getUser().getRequests();
	}
	
	public String redirectIndex(){
		return "index?faces-redirect=true";
	}
}
