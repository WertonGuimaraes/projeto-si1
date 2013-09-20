package model.state;

import java.io.Serializable;

import model.ResponseMeetingPoint;

public class RequisicaoRecebida implements StateChangeResponse, Serializable {

	private static final long serialVersionUID = 1L;
	ResponseMeetingPoint response;
	
	/**
	 * Estado em que a Carona teve a requisição recebida.
	 * Recebe uma resposta para uma requisição de Carona.
	 * @param response resposta da carona
	 */
	public RequisicaoRecebida(ResponseMeetingPoint response){
		this.response = response;
	}
	
	@Override
	public String toString(){
		return "Solicitacao recebida";
	}

	@Override
	public void aceitaSolicitacao(ResponseMeetingPoint responseMeetingPoint,
			String pontos) {
		responseMeetingPoint.setState(new SolicitacaoAceita(responseMeetingPoint, pontos));
	}

	

}
