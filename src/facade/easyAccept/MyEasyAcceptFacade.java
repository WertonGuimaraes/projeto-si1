package facade.easyAccept;

import java.util.List;

import model.Carona;
import model.CaronaSolicitada;
import model.Perfil;
import model.Usuario;
import model.Util;
import controller.Controller;

public class MyEasyAcceptFacade {

	private Controller controller;

	public MyEasyAcceptFacade() {
		this.controller = Controller.getInstance();
	}

	public void zerarSistema() {
		controller.zerarSistema();
	}

	public int abrirSessao(String login, String senha) {
		return controller.getSessoes().abrirSessao(login, senha);
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		controller.criaConta(login, senha, nome, email, endereco);
	}

	public String getAtributoUsuario(String login, String atributo) {
		if (atributo == null || Util.isEmpty(atributo))
			throw new RuntimeException("Atributo inválido");

		else if (atributo.equals("nome"))
			return controller.searchUsuariobyLogin(login).getNome();
		else if (atributo.equals("endereco"))
			return controller.searchUsuariobyLogin(login).getEndereco();

		throw new RuntimeException("Atributo inexistente");
	}

	public String localizarCarona(String idSessao, String origem, String destino) {
		Usuario usr = controller.getSessoes().searchSessionById(idSessao);
		List<Integer> caronas = usr.buscaCarona(origem, destino);

		return Util.easyAccListParser(caronas);
	}

	public int cadastrarCarona(String idSessao, String origem, String destino,
			String data, String hora, String vagas) throws Exception {
		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Sessão inválida");
		if (hora == null || Util.isEmpty(hora))
			throw new RuntimeException("Hora inválida");
		Usuario usr = Controller.getInstance().getSessoes()
				.searchSessionById(idSessao);
		int idCarona = usr.adicionaCarona(origem, destino, data, hora, vagas);

		return idCarona;
	}

	public String getAtributoCarona(String idCarona, String atributo) {
		if (idCarona == null || Util.isEmpty(idCarona))
			throw new RuntimeException("Identificador do carona é inválido");
		if (atributo == null || Util.isEmpty(atributo))
			throw new RuntimeException("Atributo inválido");

		int id;
		try {
			id = Integer.parseInt(idCarona);
		} catch (Exception e) {
			throw new RuntimeException("Item inexistente");
		}
		if (atributo.equals("origem"))
			return Controller.getInstance().buscaCarona(id).getOrigem();
		if (atributo.equals("destino"))
			return Controller.getInstance().buscaCarona(id).getDestino();
		if (atributo.equals("data"))
			return Controller.getInstance().buscaCarona(id).getDate();
		if (atributo.equals("vagas"))
			return "" + Controller.getInstance().buscaCarona(id).getVagasLivres();

		throw new RuntimeException("Atributo inexistente");
	}

	public String getTrajeto(String idCarona) {
		if (idCarona == null)
			throw new RuntimeException("Trajeto Inválida");
		else if (Util.isEmpty(idCarona))
			throw new RuntimeException("Trajeto Inexistente");
		int id;
		try {
			id = Integer.parseInt(idCarona);
		} catch (Exception e) {
			throw new RuntimeException("Trajeto Inexistente");
		}
		return Controller.getInstance().buscaCarona(id).getTrajeto();
	}

	public String getCarona(String idCarona) {
		if (idCarona == null)
			throw new RuntimeException("Carona Inválida");

		int id;
		try {
			id = Integer.parseInt(idCarona);
		} catch (Exception e) {
			throw new RuntimeException("Carona Inexistente");
		}
		return Controller.getInstance().buscaCarona(id).toString();
	}

	public void encerrarSistema() {
		controller.zerarSistema();
	}

	public void encerrarSessao(String login) {
		int key = Controller.getInstance().getSessoes()
				.searchSessionByLogin(login);
		Controller.getInstance().getSessoes().desconectarSessao(key);
	}

	// US4

	public int sugerirPontoEncontro(String idSessao, String idCarona, String pontos){
		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Solicitação inexistente");
		Usuario usr = Controller.getInstance().getSessoes()
				.searchSessionById(idSessao);
		return Controller.getInstance().sugerirPontoEncontro(idSessao, Integer.parseInt(idCarona), pontos);

	}

	public void responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos){
		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Solicitação inexistente");
		Usuario usr = Controller.getInstance().getSessoes()
				.searchSessionById(idSessao);
		Controller.getInstance().respondeSolicitacaoMeetingPoint(Integer.parseInt(idSessao), Integer.parseInt(idCarona), Integer.parseInt(idSugestao), pontos);

	}

	public int solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto){
		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Solicitação inexistente");
		Usuario usr = Controller.getInstance().getSessoes()
				.searchSessionById(idSessao);
		int idCar = Integer.parseInt(idCarona);
		Carona c = Controller.getInstance().buscaCarona(idCar);
		int idSolicitacao = usr.solicitaVaga(c, ponto);
		return idSolicitacao;
	}


	public void desistirRequisicao(String idSessao, String idCarona, String idSolicitacao){
		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Solicitação inexistente");
		if (idSolicitacao == null || Util.isEmpty(idSolicitacao))
			throw new RuntimeException("Solicitação inexistente");

		Controller.getInstance().desitirCarona(Integer.parseInt(idSessao), Integer.parseInt(idCarona), 
				Integer.parseInt(idSolicitacao));

	}


	// US5

	public int solicitarVaga(String idSessao, String idCarona) {
		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Solicitação inexistente");
		Usuario usr = Controller.getInstance().getSessoes()
				.searchSessionById(idSessao);
		int idCar = Integer.parseInt(idCarona);
		Carona c = Controller.getInstance().buscaCarona(idCar);
		int idSolicitacao = usr.solicitaVaga(c);
		return idSolicitacao;

	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		if (idSolicitacao == null || Util.isEmpty(idSolicitacao))
			throw new RuntimeException("Solicitação inexistente");
		int id = Integer.parseInt(idSolicitacao);
		CaronaSolicitada carona = Controller.getInstance()
				.buscaCaronaSolicitada(id);

		if (atributo.equals("origem"))
			return carona.getOrigem();
		if (atributo.equals("destino"))
			return carona.getDestino();
		if (atributo.equals("Dono da carona"))
			return carona.getMotorista().getNome();
		if (atributo.equals("Dono da solicitacao"))
			return carona.getCaroneiro().getNome();
		if(atributo.equals("Ponto de Encontro")){
			return carona.getPontoEncontro();
			}

		throw new RuntimeException("Atributo inexistente");
	}

	public void aceitarSolicitacao(String idSessao, String idSolicitacao){
		if (idSolicitacao == null || Util.isEmpty(idSolicitacao))
			throw new RuntimeException("Solicitação inexistente");

		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Solicitação inexistente");

		Usuario usr = Controller.getInstance().getSessoes()
				.searchSessionById(idSessao);

		int id = Integer.parseInt(idSolicitacao);
		usr.aceitaRequest(id);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao){
		if (idSolicitacao == null || Util.isEmpty(idSolicitacao))
			throw new RuntimeException("Solicitação inexistente");

		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Solicitação inexistente");

		Usuario usr = Controller.getInstance().getSessoes()
				.searchSessionById(idSessao);

		int id = Integer.parseInt(idSolicitacao);
		usr.aceitaRequest(id);
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao){
		if (idSolicitacao == null || Util.isEmpty(idSolicitacao))
			throw new RuntimeException("Solicitação inexistente");

		if (idSessao == null || Util.isEmpty(idSessao))
			throw new RuntimeException("Solicitação inexistente");

		Usuario usr = Controller.getInstance().getSessoes()
				.searchSessionById(idSessao);

		int id = Integer.parseInt(idSolicitacao);
		usr.rejeitarRequest(id);
	}

	//US6

	public int visualizarPerfil(int idSessao,String login){
		Usuario usr = Controller.getInstance().searchPerfilUsuariobyLogin(login);
		int id = Controller.getInstance().visualizaPerfil(usr);
		return id;
	}

	public String getAtributoPerfil(String login,String atributo){
		Usuario usr = Controller.getInstance().searchUsuariobyLogin(login);
		Perfil perfil = Controller.getInstance().searchPerfilByUser(usr);

		if(perfil != null){
			if(atributo.equals("nome")) return perfil.getNome();
			else if(atributo.equals("endereco")) return perfil.getEndereco();
			else if(atributo.equals("email")) return perfil.getEmail();
			else if(atributo.equals("historico de caronas")) return perfil.getHistoricoDeCaronas();
			else if(atributo.equals("historico de vagas em caronas")) return perfil.getHistoricoDeVagasEmCaronas();
			else if(atributo.equals("caronas seguras e tranquilas")) return String.valueOf(perfil.getCaronasSegurasTranquilas());
			else if(atributo.equals("caronas que não funcionaram")) return String.valueOf(perfil.getCaronasNaoFuncionaram());
			else if(atributo.equals("faltas em vagas de caronas")) return String.valueOf(perfil.getFaltasEmCaronas());
			else if(atributo.equals("presenças em vagas de caronas")) return String.valueOf(perfil.getPresencaEmCaronas());


		}

		throw new RuntimeException("login invalido");
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona, String loginCaroneiro, String review){
		Controller.getInstance().reviewEmCarona(Integer.parseInt(idSessao), Integer.parseInt(idCarona), loginCaroneiro, review);
	}
	
	
	//US 07
	public void reiniciarSistema(){
		Controller.getInstance().reiniciar();
	}
	
	

}