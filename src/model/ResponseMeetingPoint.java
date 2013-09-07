package model;

import java.util.ArrayList;
import java.util.List;

import model.state.AguardandoAceitacao;
import model.state.RequisicaoRecebida;
import model.state.StateChangeRequest;
import model.state.StateChangeResponse;

public class ResponseMeetingPoint{
	private int idSugestao;
	private List<String> pontosCaroneiro;
	private List<String> pontosMotorista;
	private int idCarona;
	private String idSessaoCaroneiro;
	private String idSessaoMotorista;
	private StateChangeResponse state;
	
	public ResponseMeetingPoint(int idCarona, String idSessaoCaroneiro,
			String pontosDeEncontro) {
		this.idCarona = idCarona;
		this.idSessaoCaroneiro = idSessaoCaroneiro;
		this.pontosCaroneiro = Util.extractListPoints(pontosDeEncontro);
		this.state = new RequisicaoRecebida(this);
	}
	
	
	/**
	 * Método acessador para o idSugestao
	 * @return int
	 */
	public int getIdSugestao() {
		return idSugestao;
	}
	
	/**
	 * Retorna o estado em que se encontra a resposta
	 * da solicitacao de ponto de encotro feita por algum
	 * caroneiro
	 * @return StateChange
	 */
	public StateChangeResponse getState() {
		return state;
	}

	public List<String> getPontosCaroneiro() {
		return pontosCaroneiro;
	}

	public List<String> getPontosMotorista() {
		return pontosMotorista;
	}

	public int getIdCarona() {
		return idCarona;
	}

	public String getIdSessaoCaroneiro() {
		return idSessaoCaroneiro;
	}

	public String getIdSessaoMotorista() {
		return idSessaoMotorista;
	}

	public void setIdSugestao(int idSugestao) {
		this.idSugestao = idSugestao;
	}

	public void setState(StateChangeResponse state) {
		this.state = state;
	}

	public void aceitaSolicitacao(String pontos) {
		this.state.aceitaSolicitacao(this, pontos);
	}
	
	

}
