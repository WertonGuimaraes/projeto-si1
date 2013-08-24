package model;

public class Usuario {
	
	private String login,nome,email,endereco,telefone;
	
	public Usuario(String login, String nome, String email,String endereco, String telefone) {
		this.login=login;
		this.nome=nome;
		this.email=email;
		this.endereco=endereco;
		this.telefone=telefone;
		
	}
	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
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
