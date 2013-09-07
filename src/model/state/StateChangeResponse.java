package model.state;

import model.ResponseMeetingPoint;

public interface StateChangeResponse {

	void aceitaSolicitacao(ResponseMeetingPoint responseMeetingPoint,
			String pontos);

}
