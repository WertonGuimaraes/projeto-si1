package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.ws.Response;

import model.Carona;
import model.CaronaSolicitada;
import model.LoginInvalidoException;
import model.RequestMeetingPoint;
import model.ResponseMeetingPoint;
import model.SolicitacaoPontoEncontro;
import model.TalkAboutMeetingPoint;
import model.Usuario;
import model.Util;

public class Controller {

	int contadorCaronas;
	int contadorRequisicao;
	int contadorTalks;
	private Set<Usuario> usuarios;
	private SessionController controladorDeSessoes;
	private static Controller controller; 			//singleton
	private Map<Integer, TalkAboutMeetingPoint> controlMeetingPoints;
	
	
	public static Controller getInstance() {
		// controller = metodoPersistencia(); TODO implementar persistencia

		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private Controller() {
		this.contadorCaronas = 0;
		this.contadorRequisicao = 0;
		this.contadorTalks = 0;
		this.usuarios = new HashSet<Usuario>();
		this.controladorDeSessoes = SessionController.getInstance();
		this.controlMeetingPoints = new HashMap<Integer, TalkAboutMeetingPoint>();
	}

	public void criaConta(String login, String senha, String nome, String email, String endereco) throws Exception{
		for (Usuario usuarioExistente : usuarios) {
			if (usuarioExistente.getEmail().equals(email))
				throw new LoginInvalidoException("Já existe um usuário com este email");
			else if (usuarioExistente.getLogin().equals(login))
				throw new LoginInvalidoException("Já existe um usuário com este login");
		}
		Usuario usuario = new Usuario(login, senha, nome, email, endereco);
		usuarios.add(usuario);
	}

	public Usuario searchUsuariobyLogin(String login){
		if(login == null || Util.isEmpty(login)) throw new RuntimeException("Login inválido");
		for (Usuario usr : usuarios) {
			if (usr.getLogin().equals(login)) {
				return usr;
			}
		}
		throw new RuntimeException("Usuário inexistente");
	}



	public void zerarSistema(){
		usuarios = new HashSet<Usuario>();
		controladorDeSessoes.zeraSessoes();
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public SessionController getSessoes() {
		return controladorDeSessoes;
	}

	public List<Integer> buscaCarona(String origem, String destino) {
		boolean condicao;

		if(Util.containsInvalidChar(origem)) throw new RuntimeException("Origem inválida");
		if(Util.containsInvalidChar(destino)) throw new RuntimeException("Destino inválido");

		List<Integer> caronasEncontradas = new LinkedList<Integer>();
		for (Usuario usr : usuarios) {
			for (int chave: usr.getCaronas().keySet() ){
				Carona caronaExistente = usr.getCaronas().get(chave);	

				if (Util.isEmpty(origem) && Util.isEmpty(destino))
					condicao = true;
				else if(Util.isEmpty(destino))
					condicao = caronaExistente.getOrigem().equals(origem);
				else if (Util.isEmpty(origem))
					condicao = caronaExistente.getDestino().equals(destino);
				else
					condicao = caronaExistente.getDestino().equals(destino)
					&& caronaExistente.getOrigem().equals(origem);
				if(condicao)
					caronasEncontradas.add(chave);
			}
		}
		return caronasEncontradas;
	}


	public Carona buscaCarona(int idCarona) {
		for (Usuario usr : usuarios) {
			for (int idCaronaExistente : usr.getCaronas().keySet()) {
				if (idCarona == idCaronaExistente) return usr.getCaronas().get(idCarona);
			}
		}
		throw new RuntimeException("Item inexistente");
	}

	public int newCaronaId() {
		return ++contadorCaronas;
	}

	public int newRequestID(){
		return ++contadorRequisicao;
	}

	public int adicionaRequest(CaronaSolicitada carona, int id) {
		carona.getMotorista().getRequests().put(id,carona);
		return id;
		//TODO ajeitar isso
	}
<<<<<<< HEAD
	
	
	public Usuario searchMotorista(int idCarona){
		for (Usuario usr : usuarios) {
			for (int idCaronaExistente : usr.getCaronas().keySet()) {
				if (idCarona == idCaronaExistente) return usr;
			}
		}
		return null;
	}
	/***
	 * Preciso pensar se esse metodo esta pronto, e o colocar
	 * a solicitacao em uma lista
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return
	 */
	public int sugerirPontoEncontro(String idSessao, int idCarona, String pontos){
		RequestMeetingPoint request = new RequestMeetingPoint(idCarona, idSessao, pontos);
		ResponseMeetingPoint response = new ResponseMeetingPoint(idCarona, idSessao, pontos);
=======

	public CaronaSolicitada buscaCaronaSolicitada(int idCaronaSolicitada){
		for (Usuario usr : usuarios) {
			for (int idCaronaExistente : usr.getRequests().keySet()) {
				if (idCaronaSolicitada == idCaronaExistente) return usr.getRequests().get(idCaronaExistente);
			}
		}
		throw new RuntimeException("Item inexistente");
	}



>>>>>>> 4ecef37d497dbb1c0223cd167605e8ee1e6b1258

		int idTalk = contadorTalks++;
		Usuario motorista = searchMotorista(idCarona);
		Usuario caroneiro = controller.getSessoes().searchSessionById(idSessao);
		SolicitacaoPontoEncontro solicitacao = new SolicitacaoPontoEncontro(caroneiro, motorista, idCarona, idTalk);
		
		controller.addResponsesPendentes(motorista, response);
		controller.addRequisicoesPendentes(caroneiro, request);
		
		return idTalk;
		
	}

	private void addResponsesPendentes(Usuario user,
			ResponseMeetingPoint response) {
		user.addResponse(response);
	}

	private void addRequisicoesPendentes(Usuario user, RequestMeetingPoint request){
		user.addRequest(request);
	}

}



