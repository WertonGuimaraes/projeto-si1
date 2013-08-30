package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import controller.Controller;

@SessionScoped
@ManagedBean(name="loginBean")
public class LoginBean {
	String login;
	String password;
	String nome;
	String email;
	String endereco;
	String telefone;
	
	
	public String loginButton(){
		return "main.hxtml";
	}
	
	public String redirectCreateNewAccount(){
		return "nova_conta.xhtml";
	}

	public void createNewAccount(){
		Controller.getInstance().criaConta(this.login, this.password, this.nome, this.email, this.endereco);
		Controller.getInstance().searchUsuariobyLogin(this.login).setTelefone(this.telefone);
		reset();
		System.out.println("here");
		msg("Usuario criado com sucesso");
		
	}

	public void reset(){
		setNome("");
		setLogin("");
		setPassword("");
		setEmail("");
		setEndereco("");
		setTelefone("");
	}
	
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
