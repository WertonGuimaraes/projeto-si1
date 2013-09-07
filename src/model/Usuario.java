package model;

import java.util.ArrayList;
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
	private Map<Integer,CaronaSolicitada> requests;
	private List<ResponseMeetingPoint> responsesPontosPendentes;
	private List<RequestMeetingPoint> requisicoesPontosPendentes;

	public Map<Integer,CaronaSolicitada> getRequests() {
		return requests;
	}

	public void setRequests(Map<Integer,CaronaSolicitada>  requests) {
		this.requests = requests;
	}

	public Usuario(String login, String senha, String nome, String email, String endereco) throws Exception{
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
		this.requests = new HashMap<Integer, CaronaSolicitada>();
		this.caronas = new HashMap<Integer, Carona>();
		this.responsesPontosPendentes =  new ArrayList<ResponseMeetingPoint>();
		this.requisicoesPontosPendentes = new ArrayList<RequestMeetingPoint>();
	}

	public int adicionaCarona(String origem, String destino, String data, String hora, String vagas) throws Exception{
		Integer intVagas;
		try {
			if (vagas == null || Util.isEmpty(vagas)) throw new RuntimeException("Vaga inválida");
			intVagas = Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new RuntimeException("Vaga inválida");
		}
		Carona novaCarona = new Carona(origem, destino, data, hora, intVagas);
		novaCarona.setMotorista(this);
		int id = Controller.getInstance().newCaronaId();
		this.getCaronas().put(id, novaCarona);

		return id;
	}

	public int adicionaRequest(CaronaSolicitada carona, int id) {
		this.getRequests().put(id,carona);
		return id;
	}

	public void aceitaRequest(int id){
		for(int i: this.getRequests().keySet()){
			if(this.getRequests().get(i).getId() == id){
				Usuario caroneiro = this.getRequests().get(i).getCaroneiro();
				this.getRequests().get(i).getCarona().addCaroneiro(caroneiro);
				this.getRequests().remove(i);
				return;
			}
		}

		throw new RuntimeException("Solicitação inexistente");	
	}

	public void rejeitarRequest(int id){}


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

	public List<ResponseMeetingPoint> getResponsesPontosPendentes() {
		return this.responsesPontosPendentes;
	}

	public List<RequestMeetingPoint> getRequisicoesPontosPendentes() {
		return this.requisicoesPontosPendentes;
	}

	public void addResponse(ResponseMeetingPoint response) {
		this.responsesPontosPendentes.add(response);
	}

	public void addRequest(RequestMeetingPoint request) {
		this.requisicoesPontosPendentes.add(request);
	}

	public ResponseMeetingPoint getResponsePoint(int idSugestao) {
		for(ResponseMeetingPoint response: this.responsesPontosPendentes){
			if(response.getIdSugestao() == idSugestao) return response;
		}
		
		return null;
	}

	public RequestMeetingPoint getRequisicaoPoint(int idSugestao) {
		for (RequestMeetingPoint request : this.requisicoesPontosPendentes) {
			if(request.getIdSugestao() == idSugestao) return request;
		}
		return null;
	}



}
