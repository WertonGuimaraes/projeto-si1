package beans;

import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Carona;
import model.CaronaSolicitada;
import model.Perfil;
import model.Usuario;
import controller.Controller;

@ViewScoped
@ManagedBean(name = "showerProfileBean")
public class ShowerProfileBean {

	private Perfil perfil;
	public static String ID;
	

	/**
	 * Abre o perfil do usuario a partir do seu Id
	 */
	public ShowerProfileBean() {
		if (ID != null) {
			this.perfil=Controller.getInstance().searchPerfilById(Integer.parseInt(ID));
		}else {
			throw new RuntimeException("Erro no id do perfil");
		}	
	}
	
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	public String getUserName() {
		return getPerfil().getNome();
	}

	public Set<Usuario> getFriends() {
		return getPerfil().getFriends();
	}

	public Map<Integer, Carona> getCaronasOfericidas() {
		return getPerfil().getCaronasOfericidas();
	}

	public Map<Integer, CaronaSolicitada> getCaronasSolicitadas() {
		return getPerfil().getCaronasSolicitadas();
	}

	/**Redireciona usuario para a pagina inicial
	 * @return pagina inicial
	 */
	public String redirectIndex() {
		return "index?faces-redirect=true";
	}
}
