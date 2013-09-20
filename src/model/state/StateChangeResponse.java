package model.state;

import model.ResponseMeetingPoint;

public interface StateChangeResponse {

	/**
	 * Aceita a solicitação de carona de um Usuario, num determinado ponto de encontro.
	 * @param responseMeetingPoint
	 * @param pontos
	 */
	void aceitaSolicitacao(ResponseMeetingPoint responseMeetingPoint,
			String pontos);

}
