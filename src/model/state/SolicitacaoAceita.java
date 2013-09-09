package model.state;

import java.util.ArrayList;
import java.util.List;

import model.ResponseMeetingPoint;
import model.Util;

public class SolicitacaoAceita implements StateChangeResponse {
	private List<String> pontosMotorista;
	
	public SolicitacaoAceita(ResponseMeetingPoint responseMeetingPoint,
			String pontos) {
		this.pontosMotorista = Util.extractListPoints(pontos);
	}

	@Override
	public void aceitaSolicitacao(ResponseMeetingPoint responseMeetingPoint,
			String pontos) {
		throw new OperacaoInvalidaParaOEstadoException("Operacao nao permitida para esse estado");
	}
	
	@Override
	public String toString(
			) {
		return "Solicitacao aprovada";
	}
	
	
	
}
