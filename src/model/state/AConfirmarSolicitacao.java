package model.state;

import java.io.Serializable;
import java.util.List;

import model.RequestMeetingPoint;
import model.TalkAboutMeetingPoint;
import model.Util;

public class AConfirmarSolicitacao implements StateChangeRequest,  Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> pontosDoMotorista;
	
	
	/**
	 * Separa os pontos dados na solicitação de vaga na Carona para futura aceitação do motorista.
	 * @param aguardandoAceitacao
	 * @param pontos
	 */
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
