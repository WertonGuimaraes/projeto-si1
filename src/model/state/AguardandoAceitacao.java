package model.state;

import java.io.Serializable;

import model.RequestMeetingPoint;
import model.TalkAboutMeetingPoint;

public class AguardandoAceitacao implements StateChangeRequest, Serializable {

	private static final long serialVersionUID = 1L;
	RequestMeetingPoint request;
	
	/**
	 * Estado em que a Carona aguarda aceitação.
	 * Seta o atributo request. Utilizada para armazenar uma requisição de carona.
	 * @param request
	 */
	public AguardandoAceitacao(RequestMeetingPoint request) {
		this.request = request;
	}

	@Override
	public void respondeSugestao(TalkAboutMeetingPoint talk, int idSugestao,
			String pontosDeEcontro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejeitarPonto(TalkAboutMeetingPoint talk, int idSugestao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aceitaPonto(TalkAboutMeetingPoint talk, int idSugestao,
			String ponto) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Aguardando aprovacao";
	}

	@Override
	public void motoristaAprovou(RequestMeetingPoint requestMeetingPoint,
			String pontos) {
		requestMeetingPoint.setState(new AConfirmarSolicitacao(this, pontos));
	}

}
