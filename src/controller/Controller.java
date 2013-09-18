package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Carona;
import model.CaronaSolicitada;
import model.LoginInvalidoException;
import model.Perfil;
import model.SolicitacaoPontoEncontro;
import model.TalkAboutMeetingPoint;
import model.Usuario;
import model.Util;
import persistencia.Reader;
import persistencia.Writer;
/**
 * 
 * @author tiaraju
 *
 */
public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	int contadorCaronas;
	int contadorRequisicao;
	int contadorTalks;
	int contadorDePefisVisualizados;
	private Set<Usuario> usuarios;
	private SessionController controladorDeSessoes;
	private MeetingPointController controladorPontosEncontro;
	private static Controller controller; // singleton
	private Map<Integer, TalkAboutMeetingPoint> controlMeetingPoints;
	private Map<Integer, Perfil> perfisLocalizados;
	private static Reader reader;
	private static Writer writer;
	private static final String NOME_DO_ARQUIVO="SYSTEMA.txt";


	/**
	 * 
	 * @return
	 */
	public static Controller getInstance() {
		if (controller == null) {
			try {
				controller = (Controller) getReader().read(controller);
			} catch (Exception e) {
				controller = new Controller();
				gravaDados();
				// controller.add500Users();
			}
		}
		return controller;
	}

	private static void gravaDados() {
		// TODO usar THREAD aqui
		try {
			getWriter().write(controller);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	private Controller() {
		this.contadorCaronas = 0;
		this.contadorRequisicao = 0;
		this.contadorTalks = 0;
		this.contadorDePefisVisualizados = 0;
		this.usuarios = new HashSet<Usuario>();
		this.controladorDeSessoes = SessionController.getInstance();
		controladorPontosEncontro = MeetingPointController.getInstance();
		this.controlMeetingPoints = new HashMap<Integer, TalkAboutMeetingPoint>();
		this.perfisLocalizados = new HashMap<Integer, Perfil>();
		this.reader=new Reader(NOME_DO_ARQUIVO);
		this.writer=new Writer(NOME_DO_ARQUIVO);
	}

	private void add500Users() {
		String c;
		for (int i = 0; i < 500; i++) {
			c = String.valueOf(i);
			try {
				Usuario user = new Usuario("user" + c, "senha" + c, "User" + c,
						"user" + c + "@carona.com", "Rua das flores");
				controller.usuarios.add(user);
			} catch (Exception e) {
			}

		}
	}
	/**
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param email
	 * @param endereco
	 * @throws Exception
	 */
	public void criaConta(String login, String senha, String nome,
			String email, String endereco) throws Exception {
		for (Usuario usuarioExistente : usuarios) {
			if (usuarioExistente.getEmail().equals(email))
				throw new LoginInvalidoException(
						"Já existe um usuário com este email");
			else if (usuarioExistente.getLogin().equals(login))
				throw new LoginInvalidoException(
						"Já existe um usuário com este login");
		}
		Usuario usuario = new Usuario(login, senha, nome, email, endereco);
		usuarios.add(usuario);
	}
	/**
	 * 
	 * @param login
	 * @return
	 */
	public Usuario searchUsuariobyLogin(String login) {
		if (login == null || Util.isEmpty(login))
			throw new RuntimeException("Login inválido");
		for (Usuario usr : usuarios) {
			if (usr.getLogin().equals(login)) {
				return usr;
			}
		}
		throw new RuntimeException("Usuário inexistente");
	}
	/**
	 * 
	 * @param login
	 * @return
	 */
	public Usuario searchPerfilUsuariobyLogin(String login) {
		if (login == null || Util.isEmpty(login))
			throw new RuntimeException("Login inválido");
		for (Usuario usr : usuarios) {
			if (usr.getLogin().equals(login)) {
				return usr;
			}
		}
		throw new RuntimeException("Login inválido");
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Perfil searchPerfilById(int id) {
		for (int key : perfisLocalizados.keySet()) {
			if (key == id) {
				return perfisLocalizados.get(id);
			}
		}
		throw new RuntimeException("Perfil não encontrado");
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Perfil searchPerfilByUser(Usuario user) {
		for (int key : perfisLocalizados.keySet()) {
			if (perfisLocalizados.get(key).getUser().equals(user)) {
				return perfisLocalizados.get(key);
			}
		}
		throw new RuntimeException("Perfil não encontrado");
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int visualizaPerfil(Usuario user) {
		if (user == null)
			throw new RuntimeException("Login inválido");
		Perfil perfil = new Perfil(user);
		int id = newPerfilVisualizadoID();
		this.perfisLocalizados.put(id, perfil);
		return id;
	}

	public void zerarSistema() {
		usuarios = new HashSet<Usuario>();
		controladorDeSessoes.zeraSessoes();
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public static Writer getWriter(){
		return writer;
	}
	
	public static Reader getReader(){
		return reader;
	}
	
	public SessionController getSessoes() {
		return controladorDeSessoes;
	}
	/**
	 * 
	 * @param origem
	 * @param destino
	 * @return
	 */
	public List<Integer> buscaCarona(String origem, String destino) {
		boolean condicao;

		if (Util.containsInvalidChar(origem))
			throw new RuntimeException("Origem inválida");
		if (Util.containsInvalidChar(destino))
			throw new RuntimeException("Destino inválido");

		List<Integer> caronasEncontradas = new LinkedList<Integer>();
		for (Usuario usr : usuarios) {
			for (int chave : usr.getCaronas().keySet()) {
				Carona caronaExistente = usr.getCaronas().get(chave);

				if (Util.isEmpty(origem) && Util.isEmpty(destino))
					condicao = true;
				else if (Util.isEmpty(destino))
					condicao = caronaExistente.getOrigem().equals(origem);
				else if (Util.isEmpty(origem))
					condicao = caronaExistente.getDestino().equals(destino);
				else
					condicao = caronaExistente.getDestino().equals(destino)
					&& caronaExistente.getOrigem().equals(origem);
				if (condicao)
					caronasEncontradas.add(chave);
			}
		}
		return caronasEncontradas;
	}

	public Carona buscaCarona(int idCarona) {
		for (Usuario usr : usuarios) {
			for (int idCaronaExistente : usr.getCaronas().keySet()) {
				if (idCarona == idCaronaExistente)
					return usr.getCaronas().get(idCarona);
			}
		}
		throw new RuntimeException("Item inexistente");
	}

	public int newCaronaId() {
		return ++contadorCaronas;
	}

	public int newRequestID() {
		return ++contadorRequisicao;
	}

	public int newPerfilVisualizadoID() {
		return ++contadorDePefisVisualizados;
	}

	public int adicionaRequest(CaronaSolicitada carona, int id) {
		carona.addRequest(id);
		return id;
	}

	public CaronaSolicitada buscaCaronaSolicitada(int idCaronaSolicitada) {
		for (Usuario usr : usuarios) {
			for (int idCaronaExistente : usr.getRequests().keySet()) {
				if (idCaronaSolicitada == idCaronaExistente)
					return usr.getRequests().get(idCaronaExistente);
			}
		}
		throw new RuntimeException("Item inexistente");
	}

	/**
	 * Método que retorna o motorista dono da carona
	 * 
	 * @param idCarona
	 *            identificador único da carona
	 * @return Usuario motorista
	 */
	public Usuario searchMotorista(int idCarona) {
		for (Usuario usr : usuarios) {
			for (int idCaronaExistente : usr.getCaronas().keySet()) {
				if (idCarona == idCaronaExistente)
					return usr;
			}
		}
		return null;
	}

	/***
	 * Faz a solicitação de ponto de encontro para uma determinada carona.
	 * 
	 * @param idSessao
	 *            Id da sessão do caroneiro interessado na solicitação
	 * @param idCarona
	 *            Id da carona de interesse
	 * @param pontos
	 *            Pontos de encontro sugeridos
	 * @return um valor inteiro que é o identificador da solicitação de ponto de
	 *         encontro
	 */
	public int sugerirPontoEncontro(String idSessao, int idCarona, String pontos) {
		Usuario motorista = searchMotorista(idCarona);
		Usuario caroneiro = controller.getSessoes().searchSessionById(idSessao);

		return controladorPontosEncontro.sugerirPontoEncontro(idSessao,
				idCarona, pontos, motorista, caroneiro);

	}

	/***
	 * Permite a um motorista resposder a uma sugestão de ponto de encontro
	 * feita a uma carona oferecida por ele.
	 * 
	 * @param idSessao
	 *            id da sessão do motorista
	 * @param idCarona
	 *            id da carona a qual o motorista vai responder a sugestão de
	 *            ponto de encontro
	 * @param idSugestao
	 *            identificador da sugestão de ponto de encontro ao qual o
	 *            motorista quer responder
	 * @param pontos
	 *            pontos que o motorista pode sugerir para ser o de encontro
	 */
	public void respondeSolicitacaoMeetingPoint(int idSessao, int idCarona,
			int idSugestao, String pontos) {

		SolicitacaoPontoEncontro solicitacao = controladorPontosEncontro
				.getSolicitacao(idSugestao);
		Usuario motorista = solicitacao.getMotorista();
		Usuario caroneiro = solicitacao.getCaroneiro();

		controladorPontosEncontro.respondeSolicitacaoMeetingPoint(idSessao,
				idCarona, idSugestao, pontos);

	}
	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 */
	public void desitirCarona(int idSessao, int idCarona, int idSugestao) {
		Usuario caroneiro = Controller.getInstance().getSessoes()
				.searchSessionById(String.valueOf(idSessao));
		for (int i : caroneiro.getRequests().keySet()) {
			if (caroneiro.getRequests().get(i).getId() == idSugestao) {
				Usuario motorista = caroneiro.getRequests().get(i)
						.getMotorista();
				caroneiro.getRequests().remove(i);
				motorista.getRequests().remove(i);
				return;
			}
		}

	}

	/**
	 * Permite a um motorista fazer um review da carona informando o
	 * comportamento do caroneiro: faltou, não faltou, não funcionou.
	 * 
	 * @param idSessao
	 *            id do motorista
	 * @param idCarona
	 *            id da carona
	 * @param login
	 *            login do caroneiro
	 * @param string
	 *            comentário sobre a carona
	 */
	public void reviewEmCarona(int idSessao, int idCarona, String login,
			String review) {
		Usuario motorista = controller.getSessoes().searchSessionById(
				String.valueOf(idSessao));
		Usuario caroneiro = controller.searchUsuariobyLogin(login);
		Carona carona = controller.buscaCarona(idCarona);

		if (carona.hasCaroneiro(caroneiro)) {
			// carona.addReview(caroneiro, review);
			caroneiro.addReview(carona, review);
		} else
			throw new RuntimeException("Usuário não possui vaga na carona.");

	}

	public void reiniciar() {


	}

	public void encerrarSistema() {
		gravaDados();
		//zerarSistema();

	}

}
