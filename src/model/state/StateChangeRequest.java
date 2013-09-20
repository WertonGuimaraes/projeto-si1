package model.state;

import model.RequestMeetingPoint;
import model.TalkAboutMeetingPoint;

public interface StateChangeRequest {
	/**
	 * Aceita à solicitação de ponto de encontro de 
	 * um Usuario, enviando outra sugestao de ponto de encontro
	 * @param talk
	 * @param idSugestao
	 * @param pontosDeEcontro
	 */
	public void respondeSugestao(TalkAboutMeetingPoint talk, int idSugestao, String pontosDeEcontro);
	
	/**
	 * Rejeita a solicitação de ponto de encontro de um Usuario.
	 * @param talk
	 * @param idSugestao
	 */
	public void rejeitarPonto(TalkAboutMeetingPoint talk, int idSugestao);
		
	/**
	 * Aceita o ponto de encontro sugerido por outro Usuario
	 * @param talk
	 * @param idSugestao
	 * @param ponto
	 */
	public void aceitaPonto(TalkAboutMeetingPoint talk, int idSugestao, String ponto);

	
	/**
	 * Confirma a carona no ponto de encontro sugerido pelo Usuario.
	 * @param requestMeetingPoint
	 * @param pontos
	 */
	public void motoristaAprovou(RequestMeetingPoint requestMeetingPoint,
			String pontos);

}
