package beans;

import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Carona;
import model.Usuario;
import controller.Controller;
import controller.SessionController;

@ViewScoped
@ManagedBean(name="perfilBean")
public class PerfilBean {
	public static String ID;
	private String id;
	String origem;
	String destino;
	String data;
	String hora;
	String vagas;
	int size;

	public void setSize(int size) {
		this.size = size;
	}

	private  Usuario usuario;	
	private List<Carona> caronasDisponiveis;


	public Usuario getUsuario() {
		return usuario;
	}

	public PerfilBean(){	
		if(ID != null){
			this.usuario = SessionController.getInstance().searchSessionById(ID);
			this.id = ID;					
		}else throw new RuntimeException("Erro no id do perfil"); 
	}

	public String logoffButton(){
		SessionController.getInstance().desconectarSessao(0); //FIXME o certo e passar id
		return "index.xhtml";
	}

	public String redirectPerfil(){
		return "perfil.xhtml";
	}

	public Collection<Carona> getCaronas(){
		return usuario.getCaronas().values();
	}

	public void localizaCaronasDisponiveis(){
		List<Integer> idsCaronas = Controller.getInstance().buscaCarona(this.origem, this.destino);

		for(Integer id: idsCaronas){
			this.caronasDisponiveis.add(Controller.getInstance().buscaCarona(id));
		}

	}
	public int getSize(){
		int size=0;
		if(caronasDisponiveis != null){
			size= caronasDisponiveis.size();
		}
		return size;
	}

	public void cadastrarCarona(){
		try {
			usuario.adicionaCarona(this.origem, this.destino, this.data, this.hora, this.vagas);
		} catch (Exception e) {
			msg(e.getMessage());
		}
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public List<Carona> getCaronasDisponiveis() {
		return caronasDisponiveis;
	}

	public void setCaronasDisponiveis(List<Carona> caronasDisponiveis) {
		this.caronasDisponiveis = caronasDisponiveis;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}
	
	public void msg(String text){
		FacesContext context = FacesContext.getCurrentInstance();  
		
		context.addMessage(null, new FacesMessage(text));  
	}

}
