package model.state;

import model.RequestMeetingPoint;
import model.TalkAboutMeetingPoint;

public class AguardandoAceitacao implements StateChange {

	public AguardandoAceitacao(RequestMeetingPoint requestMeetingPoint) {
		// TODO Auto-generated constructor stub
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

}
