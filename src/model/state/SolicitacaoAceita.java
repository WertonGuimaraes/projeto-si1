package model.state;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.ResponseMeetingPoint;
import model.Util;

public class SolicitacaoAceita implements StateChangeResponse, Serializable {

	private static final long serialVersionUID = 1L;
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
