package model;

import java.util.ArrayList;
import java.util.List;

import model.state.AguardandoAceitacao;
import model.state.StateChangeRequest;

public class RequestMeetingPoint {
	private int idSugestao;
	private List<String> pontosCaroneiro;
	private List<String> pontosMotorista;
	private int idCarona;
	private String idSessaoCaroneiro;
	private String idSessaoMotorista;
	private StateChangeRequest state;

	public RequestMeetingPoint(int idCarona, String idSessaoCaroneiro,
			String pontosDeEncontro) {
		this.idCarona = idCarona;
		this.idSessaoCaroneiro = idSessaoCaroneiro;
		this.pontosCaroneiro = extractListPoints(pontosDeEncontro);
		this.state = new AguardandoAceitacao(this);
	}
	
	private List<String> extractListPoints(String pontosDeEncontro) {
		List<String> result = new ArrayList<String>();
		
		for(String ponto: pontosDeEncontro.split(";")){
			result.add(ponto);
		}
		
		return result;
	}

	public int getIdSugestao() {
		return idSugestao;
	}

	public StateChangeRequest getState() {
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

	public void setState(StateChangeRequest state) {
		this.state = state;
	}

	public void motoristaAprovou(String pontos) {
		state.motoristaAprovou(this, pontos);
		
	}
	
	

}
