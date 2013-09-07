package controller;

import java.util.ArrayList;
import java.util.List;

import model.RequestMeetingPoint;
import model.ResponseMeetingPoint;
import model.SolicitacaoPontoEncontro;
import model.Usuario;

public class MeetingPointController {
	private static MeetingPointController meetingPointController;
	private int contadorTalk;
	private List<SolicitacaoPontoEncontro> solicitacoes;
	
	public static MeetingPointController getInstance() {
		// controller = metodoPersistencia(); TODO implementar persistencia

		if (meetingPointController == null) {
			meetingPointController = new MeetingPointController();
		}
		return meetingPointController;
	}
	
	private MeetingPointController(){
		this.contadorTalk = 1;
		this.solicitacoes = new ArrayList<SolicitacaoPontoEncontro>();
	}
	
	/***
	 * Faz a solicitacão de ponto de encontro para
	 * uma determinada carona.
	 * @param idSessao Id da sessão do caroneiro interessado na solicitação
	 * @param idCarona Id da carona de interesse
	 * @param pontos Pontos de encontro sugeridos
	 * @param motorista Objeto Usuario do motorista dono da carona
	 * @param caroneiro Objeto Usuario do caroneiro que fez a solicitação de ponto de encontro
	 * @return  um valor inteiro que é o identificador da solicitação de ponto de encontro
	 */
	public int sugerirPontoEncontro(String idSessao, int idCarona, String pontos, Usuario motorista, Usuario caroneiro){
		RequestMeetingPoint request = new RequestMeetingPoint(idCarona, idSessao, pontos);
		ResponseMeetingPoint response = new ResponseMeetingPoint(idCarona, idSessao, pontos);

		int idTalk = contadorTalk++; 
		SolicitacaoPontoEncontro solicitacao = new SolicitacaoPontoEncontro(caroneiro, motorista, idCarona, idTalk);
		
		solicitacoes.add(solicitacao);
		request.setIdSugestao(idTalk);
		response.setIdSugestao(idTalk);
		
		
		meetingPointController.addResponsesPendentes(motorista, response);
		meetingPointController.addRequisicoesPendentes(caroneiro, request);
		
		return idTalk;
		
	}

	private void addResponsesPendentes(Usuario user,
			ResponseMeetingPoint response) {
		user.addResponse(response);
	}

	private void addRequisicoesPendentes(Usuario user, RequestMeetingPoint request){
		user.addRequest(request);
	}

	public SolicitacaoPontoEncontro getSolicitacao(int idSugestao) {
		for (SolicitacaoPontoEncontro solicitacao : this.solicitacoes) {
			if(solicitacao.getIdSugestao() == idSugestao) return solicitacao;
		}
		
		throw new RuntimeException("Não existe nenhuma solicitacao com esse id de sugestão");
	}


}
