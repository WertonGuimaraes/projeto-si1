package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import controller.Controller;
import controller.SessionController;

@ManagedBean(name="loginBean", eager = true)
@SessionScoped
public class LoginBean {
	String login;
	String password;
	String nome;
	String email;
	String endereco;
	String telefone;
	
	public LoginBean(){
		
	}
	
	/**Redireciona o usuario para a pagina de login
	 * @return pagina do perfil
	 */
	public String login(){
		PerfilBean.ID = String.valueOf(SessionController.getInstance().abrirSessao(login, password));	
		return "perfil?faces-redirect=true";
	}
	
	/** Redireciona o usuario para criar uma nova conta
	 * @return pagina em que é possivel criar uma nova conta 
	 */
	public String redirectCreateNewAccount(){
		return "nova_conta?faces-redirect=true";
	}
	
	/** Redireciona o usuario para o index
	 * @return pagina inicial
	 */
	public String redirectIndex(){
		return "index?faces-redirect=true";
	}

	/**
	 * Cria uma nova conta baseado nos parâmetros de LoginBean.
	 */
	public void createNewAccount(){
		try {
			Controller.getInstance().criaConta(this.login, this.password, this.nome, this.email, this.endereco);
		} catch (Exception e) {
			msg(e.getMessage());
		}
		Controller.getInstance().searchUsuariobyLogin(this.login).setTelefone(this.telefone);
		this.reset();
		msg("Usuario criado com sucesso");
		System.out.println(Controller.getInstance().getUsuarios().size());
		
	}

	/**
	 *  Reseta todos os parâmetros de LoginBean
	 */
	public void reset(){
		this.setNome("");
		this.setLogin("");
		this.setPassword("");
		this.setEmail("");
		this.setEndereco("");
		this.setTelefone("");
	}
	
	/** Lança mensagem para o usuario utilizando o contexto JSF
	 * @param text
	 */
	public void msg(String text){
		FacesContext context = FacesContext.getCurrentInstance();  
		
		context.addMessage(null, new FacesMessage(text));  
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
