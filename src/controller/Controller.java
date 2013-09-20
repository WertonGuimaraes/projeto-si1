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
	 * Retorna a unica instancia do Controlador (Singleton)
	 * @return singleton controller
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

	/**
	 * Persiste os dados do sistema
	 */
	private static void gravaDados() {
		// TODO usar THREAD aqui
		try {
			getWriter().write(controller);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * Inicia o Controlador, setando todas as variáveis e escrevendo o objeto da classe em um arquivo.
	 */
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

	/**
	 * Cria conta de usuario utilizando login,senha,nome,email e endereco
	 * @param login
	 * @param senha
	 * @param nome
	 * @param email
	 * @param endereco
	 */
	public void criaConta(String login, String senha, String nome, String email, String endereco){
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
	 * Procura usuario utilizando a String do login
	 * @param login
	 * @return objeto Usuario
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
	 * Procura usuario utilizando a String de login
	 * @param login
	 * @return objeto Usuario
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
	 * Procura um Perfil utilizando o int Id do Perfil
	 * @param id
	 * @return objeto Perfil
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
	 * Procura um Perfil utilizando um objeto Usuario
	 * @param user
	 * @return Objeto Perfil
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
	 * Procura um perfil a partir de um objeto Usuario.
	 * @param user
	 * @return id do Perfil criado
	 */
	public int visualizaPerfil(Usuario user) {
		if (user == null)
			throw new RuntimeException("Login inválido");
		Perfil perfil = new Perfil(user);
		int id = newPerfilVisualizadoID();
		this.perfisLocalizados.put(id, perfil);
		return id;
	}

	
	/**
	 * Zera as variáveis do sistema.
	 */
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
	 * Busca uma carona a partir da Origem e Destino passados como parâmetros.
	 * Caso um dos parâmetros não seja passado, qualquer valor é considerado nesse parâmetro
	 * no momento da busca. A busca não é case-sensitive. 
	 * @param origem
	 * @param destino
	 * @return retorna uma lista de Id de caronas que se enquadram nessa origem/destino.
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
					condicao = ( caronaExistente.getOrigem().toLowerCase() ).equals(origem.toLowerCase());
				else if (Util.isEmpty(origem))
					condicao = ( caronaExistente.getDestino().toLowerCase() ) .equals(destino.toLowerCase());
				else
					condicao = ( caronaExistente.getDestino().toLowerCase() ).equals(destino.toLowerCase())
					&& ( caronaExistente.getOrigem().toLowerCase() ).equals(origem.toLowerCase());
				if (condicao)
					caronasEncontradas.add(chave);
			}
		}
		return caronasEncontradas;
	}

	/**
	 * Busca carona a partir de um id
	 * @param idCarona
	 * @return objeto Carona
	 */
	public Carona buscaCarona(int idCarona) {
		for (Usuario usr : usuarios) {
			for (int idCaronaExistente : usr.getCaronas().keySet()) {
				if (idCarona == idCaronaExistente)
					return usr.getCaronas().get(idCarona);
			}
		}
		throw new RuntimeException("Item inexistente");
	}

	/**Incrementa o contador de Id de caronas e retorna o mesmo.
	 * É usado durante a criação de caronas do sistema 
	 * @return contadorCaronas será utilizado como Id
	 */
	public int newCaronaId() {
		return ++contadorCaronas;
	}

	/**Incrementa o contador de Requisições e retorna o mesmo.
	 * É utilizado quando uma requisição é criada.
	 * @return contadorRequisicao
	 */
	public int newRequestID() {
		return ++contadorRequisicao;
	}

	/** Incrementa o contador de Perfis do Sistema e retorna o mesmo.
	 *  É utilizado quando um novo Perfil é criado no sistema.
	 * @return contadorDePerfisVisualizados
	 */
	public int newPerfilVisualizadoID() {
		return ++contadorDePefisVisualizados;
	}

	/**
	 * Adiciona uma requisição de carona
	 * @param carona
	 * @param id da carona
	 * @return id da requisição
	 */
	public int adicionaRequest(CaronaSolicitada carona, int id) {
		carona.addRequest(id);
		return id;
	}

	/**
	 * Busca uma carona através do seu Id
	 * @param idCaronaSolicitada
	 * @return Carona buscada
	 */
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
	 * @param idCarona identificador único da carona
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
	 * @param idSessao Id da sessão do caroneiro interessado na solicitação
	 * @param idCarona Id da carona de interesse
	 * @param pontos Pontos de encontro sugeridos
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
	 * @param idSessao id da sessão do motorista
	 * @param idCarona id da carona a qual o motorista vai 
	 * responder a sugestão de ponto de encontro
	 * @param idSugestao identificador da sugestão de ponto de encontro 
	 * ao qual o motorista quer responder
	 * @param pontos pontos que o motorista pode sugerir para ser o de encontro
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
	 * O Usuario utiliza este método para desistir de uma Carona
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 */
	public void desitirCarona(int idSessao, int idCarona, int idSugestao) {
		System.out.println("to aqui");
		
		Usuario caroneiro = Controller.getInstance().getSessoes()
				.searchSessionById(String.valueOf(idSessao));
		System.out.println(caroneiro.getLogin());
		System.out.println(caroneiro.getRequests().size());
		for (int i : caroneiro.getRequests().keySet()) {
			if (caroneiro.getRequests().get(i).getId() == idSugestao) {
				Usuario motorista = caroneiro.getRequests().get(i)
						.getMotorista();
				caroneiro.getRequests().remove(i);
				caroneiro.removeSolicitacaoCaronaFeita(idSugestao);
				motorista.getRequests().remove(i);
				return;
			}
		}

	}
	
	/***
	 * 
	 * @param idSessao
	 * @param idSugestao
	 */
	public void desitirCarona(int idSessao, int idCaronaSolicitada) {
		System.out.println("to aqui");
		
		Usuario caroneiro = Controller.getInstance().getSessoes()
				.searchSessionById(String.valueOf(idSessao));
		
		for(CaronaSolicitada carona: caroneiro.getCaronasSolicitadas()){
			if(carona.getId() == idCaronaSolicitada){
				Usuario motorista =  carona.getMotorista();
			}
		}
		
		for (int i : caroneiro.getRequests().keySet()) {
			if (caroneiro.getRequests().get(i).getId() == idCaronaSolicitada) {
				Usuario motorista = caroneiro.getRequests().get(i)
						.getMotorista();
				caroneiro.getRequests().remove(i);
				caroneiro.removeSolicitacaoCaronaFeita(idCaronaSolicitada);
				motorista.getRequests().remove(i);
				return;
			}
		}

	}

	/**
	 * Permite a um motorista fazer um review da carona informando o
	 * comportamento do caroneiro: faltou, não faltou, não funcionou.
	 * @param idSessao id do motorista
	 * @param idCarona id da carona
	 * @param login login do caroneiro
	 * @param string comentário sobre a carona
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

	/**
	 * Reinicia o sistema, zerando todas as sessões
	 */
	public void reiniciar() {
		Reader reader = new Reader(NOME_DO_ARQUIVO);
		SessionController.getInstance().zeraSessoes();
		try {
			this.controller = reader.read(this.controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Encerra o Sistema
	 */
	public void encerrarSistema() {
		gravaDados();
		//zerarSistema();

	}

}
