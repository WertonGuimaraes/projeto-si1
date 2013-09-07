package model.state;

import model.ResponseMeetingPoint;
import model.TalkAboutMeetingPoint;

public class RequisicaoRecebida implements StateChangeResponse {
	ResponseMeetingPoint response;
	
	public RequisicaoRecebida(ResponseMeetingPoint response){
		this.response = response;
	}
	
	@Override
	public String toString(){
		return "Solicitação recebida";
	}

	@Override
	public void aceitaSolicitacao(ResponseMeetingPoint responseMeetingPoint,
			String pontos) {
		responseMeetingPoint.setState(new SolicitacaoAceita(responseMeetingPoint, pontos));
	}

	

}
