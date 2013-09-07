package model.state;

import model.RequestMeetingPoint;
import model.TalkAboutMeetingPoint;

public class AguardandoAceitacao implements StateChangeRequest {
	RequestMeetingPoint request;
	
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
		return "Aguardando aprovação";
	}

}
