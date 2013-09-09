package model.state;

import java.util.List;

import model.RequestMeetingPoint;
import model.TalkAboutMeetingPoint;
import model.Util;

public class AConfirmarSolicitacao implements StateChangeRequest {
	private List<String> pontosDoMotorista;
	
	public AConfirmarSolicitacao(AguardandoAceitacao aguardandoAceitacao,
			String pontos) {
		this.pontosDoMotorista = Util.extractListPoints(pontos);
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

	@Override
	public void motoristaAprovou(RequestMeetingPoint requestMeetingPoint,
			String pontos) {
		throw new OperacaoInvalidaParaOEstadoException("Operacao nao permitida para esse estado");
	}
	
	@Override
	public String toString() {
		return "Motorista aprovou";
	}

}
