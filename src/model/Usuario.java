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
	private List<HistoricoCarona> historicos;
	private List<CaronaSolicitada> caronasSolicitadas;
	
	/***
	 * Método acessador para as caronas que foram solicitadas a esse usuario
	 * @return map requisicioes de caronas feitas
	 */
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
		this.historicos = new ArrayList<HistoricoCarona>();
		this.caronasSolicitadas = new ArrayList<CaronaSolicitada>();
	}
	
	/***
	 * Permite ao usuario cadastrar uma carona no sistema
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return o id da carona
	 * @throws Exception se vagas == 0
	 */
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
		novaCarona.setId(id);
		this.getCaronas().put(id, novaCarona);

		return id;
	}


	/***
	 * Permite ao usuario solicitar vaga em uma carona
	 * @param carona que o usuario quer solicitar vaga
	 * @return id da solicitacao
	 */
	public int solicitaVaga(Carona carona){
		if(carona.getMotorista().login.equals(this.login)) throw new RuntimeException("Você não pode solicitar vaga em sua própria carona!!");
		
		int id = Controller.getInstance().newRequestID();
		CaronaSolicitada solicitacao = new CaronaSolicitada(carona, this);
		solicitacao.setId(id);
		Controller.getInstance().adicionaRequest(solicitacao,id);
		caronasSolicitadas.add(solicitacao);
		return id;
	}

	/***
	 * Permite ao usuario solicitar vaga em uma carona e m
	 * ponto de encontro
	 * @param carona que o usuario quer solicitar vaga
	 * @param pontoDeEncontro ponto de encontro da carona
	 * @return id da solicitacao
	 */
	public int solicitaVaga(Carona carona, String pontoDeEncontro){
		int id = Controller.getInstance().newRequestID();
		CaronaSolicitada solicitacao = new CaronaSolicitada(carona, this);
		solicitacao.setPontoEncontro(pontoDeEncontro);
		solicitacao.setId(id);
		Controller.getInstance().adicionaRequest(solicitacao,id);
		return id;
	}
	
	/**
	 * Aceita requisicao de carona
	 * @param id da solicitacao de carona
	 */
	public void aceitaRequest(int id){
		for(int i: this.getRequests().keySet()){
			if(this.getRequests().get(i).getId() == id){
				Usuario caroneiro = this.getRequests().get(i).getCaroneiro();
				this.getRequests().get(i).getCarona().addCaroneiro(caroneiro);
				caroneiro.addHistorico(this.getRequests().get(i).getCarona());
				this.getRequests().remove(i);
				return;
			}
		}

		throw new RuntimeException("Solicitação inexistente");	
	}

	/**
	 * Adiciona carona ao historico
	 * @param carona
	 */
	public void addHistorico(Carona carona) {
		HistoricoCarona historico = new HistoricoCarona(carona);
		this.historicos.add(historico);
	}
	
	/**
	 * Rejeita uma solicitacao de carona
	 * @param id da solicitacao
	 */
	public void rejeitarRequest(int id){
		for(int i: this.getRequests().keySet()){
			if(this.getRequests().get(i).getId() == id){
				this.getRequests().remove(i);
				return;
			}
		}
		
		throw new RuntimeException("Solicitação inexistente");	
		
	}

	/***
	 * Procura uma carona pela origem e destino especificados
	 * @param origem
	 * @param destino
	 * @return
	 */
	public List<Integer> buscaCarona(String origem, String destino){
		return Controller.getInstance().buscaCarona(origem, destino);
	}

	/**
	 * Verifica se dois usuarios sao amigos
	 * @param user
	 * @return true se sao amigos, false se nao
	 */
	public boolean isFriendOf(Usuario user){
		return friends.contains(user);
	}

	/***
	 * Adiciona um amigo
	 * @param user
	 */
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
	/**
	 * this method add a requested carona
	 * @param carona
	 */
	public void addRequest(int id,CaronaSolicitada carona){
		this.getRequests().put(id, carona);
	}

	/**
	 * Retorna a resposta ao ponto de encontro dado o id de uma sugestão
	 * @param idSugestao
	 * @return o ResponseMeetingPoint da sugestão de ponto de encontro
	 */
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

	/**
	 * Adiciona um review ao usuario
	 * @param carona
	 * @param review
	 */
	public void addReview(Carona carona, String review) {
		HistoricoCarona historico = searchHistoricoByCarona(carona);
		historico.setReview(review);
	}

	
	private HistoricoCarona searchHistoricoByCarona(Carona carona) {
		for (HistoricoCarona historico : this.historicos) {
			if(historico.getCarona().equals(carona)) return historico;
		}
		return null;
	}

	/**
	 * Retorna o numero de caronas que o usuario faltou
	 * @return número de caronas que faltou
	 */
	public int getReviewFaltou(){
		int contador = 0;
		for (HistoricoCarona historico : this.historicos) {
			if(historico.getReview().equals("faltou")) contador++;
		}
		
		return contador;
	}
	

	/**
	 * Retorna o numero de caronas que o usuario compareceu
	 * @return numero de caronas que compareceu
	 */
	public int getReviewNaoFaltou(){
		int contador = 0;
		for (HistoricoCarona historico : this.historicos) {
			if(historico.getReview().equals("não faltou")) contador++;
		}
		
		return contador;
	}
	
	/**
	 * Retorna o numero de caronas do usuario que não
	 * funcionaram
	 * @return numero de caronas que não funcionaram
	 */
	public int getReviewNaoFuncionou(){
		int contador = 0;
		for (HistoricoCarona historico : this.historicos) {
			if(historico.getReview().equals("não funcionou")) contador++;
		}
		
		return contador;
	}

	/**
	 * Retorna o numero de caronas seguras e tranquilas
	 * @return numero de caronas seguras
	 */
	public int getReviewSeguras(){
		int contador = 0;
		for (HistoricoCarona historico : this.historicos) {
			if(historico.getReview().equals("segura")) contador++;
		}
		
		return contador;
	}
	
	/**
	 * Método acessador para o numero de caronas que
	 * o usuario participou
	 * @return numero de caronas que compareceu
	 */
	public int getVagasEmCaronas() {
		return getReviewNaoFaltou()+getReviewSeguras();
	}

	/**
	 * @return the historicos
	 */
	public List<HistoricoCarona> getHistoricos() {
		return historicos;
	}
	
	
}
