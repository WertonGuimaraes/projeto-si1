package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.Controller;


public class Usuario {
	
	private String login,senha,nome,email,endereco,telefone;
	private Set<Usuario> friends;
	
	private Map<Integer, Carona> caronas;
	
	public Usuario(String login, String senha, String nome, String email, String endereco) {
		if(login == null || Util.isEmpty(login))      throw new LoginInvalidoException("Login inválido");
		if(endereco ==null || Util.isEmpty(endereco)) throw new LoginInvalidoException("Endereço invalido");
		if(nome == null || Util.isEmpty(nome))        throw new LoginInvalidoException("Nome inválido");
		if(email ==null || Util.isEmpty(email))       throw new LoginInvalidoException("Email inválido");
		
		this.login=login;
		this.setSenha(senha);
		this.nome=nome;
		this.email=email;
		this.endereco=endereco;
		this.telefone=""; //telefone vazio
		this.friends = new HashSet<Usuario>();
		
		caronas = new HashMap<Integer, Carona>();
	}
	
	public int adicionaCarona(String origem, String destino, String data, String hora, int vagas){
		Carona novaCarona = new Carona(origem, destino, data, hora, vagas);
		int id = Controller.getInstance().newCaronaId();
		getCaronas().put(id, novaCarona);
		
		return id;
	}
	
	

	public List<Integer> buscaCarona(String origem, String destino){
		return Controller.getInstance().buscaCarona(origem, destino);
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
	
	public boolean autenticateAccount(String senha){
		if (this.getSenha().equals(senha)) {
			return true;
		}
		return false;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Map<Integer, Carona> getCaronas() {
		return caronas;
	}



}
