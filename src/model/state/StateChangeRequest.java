package model.state;

import model.RequestMeetingPoint;
import model.TalkAboutMeetingPoint;

public interface StateChangeRequest {
	public void respondeSugestao(TalkAboutMeetingPoint talk, int idSugestao, String pontosDeEcontro);
	
	public void rejeitarPonto(TalkAboutMeetingPoint talk, int idSugestao);
		
	public void aceitaPonto(TalkAboutMeetingPoint talk, int idSugestao, String ponto);

	public void motoristaAprovou(RequestMeetingPoint requestMeetingPoint,
			String pontos);

}
