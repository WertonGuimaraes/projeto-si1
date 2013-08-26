package model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Usuario {
	
	private String login,nome,email,endereco,telefone;
	private Set<Usuario> friends;
	
	public Usuario(String login, String nome, String email,String endereco, String telefone) throws InvalidParameterException {
		if(login == null || nome==null || email ==null || endereco ==null || telefone ==null){
			throw new InvalidParameterException("parametro(s) nulos");
		}
		if(Util.isEmpty(login)|| Util.isEmpty(nome) || Util.isEmpty(email) || Util.isEmpty(telefone) || Util.isEmpty(endereco)){
			throw new InvalidParameterException("parametro(s) vazios");
		}
		
		this.login=login;
		this.nome=nome;
		this.email=email;
		this.endereco=endereco;
		this.telefone=telefone;
		this.friends= new HashSet<Usuario>();
		
	}
	
	public boolean isFriendOf(Usuario user){
		return friends.contains(user);
	}
	
	public void addFriend(Usuario user) {
		friends.add(user);
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Usuario)){
			return false;
		}
		Usuario user = (Usuario) obj;
		return (user.getLogin().equals(this.getLogin()));
	}
	
	public Set<Usuario> getFriends() {
		return friends;
	}

	public void setFriends(Set<Usuario> friends) {
		this.friends = friends;
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
