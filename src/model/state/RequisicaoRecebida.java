package model.state;

import java.io.Serializable;

import model.ResponseMeetingPoint;
import model.TalkAboutMeetingPoint;

public class RequisicaoRecebida implements StateChangeResponse, Serializable {

	private static final long serialVersionUID = 1L;
	ResponseMeetingPoint response;
	
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
