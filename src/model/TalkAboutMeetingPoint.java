package model;

import java.util.ArrayList;
import java.util.List;

import model.state.AguardandoAceitacao;
import model.state.StateChange;

public class TalkAboutMeetingPoint {
	private int idSugestao;
	private List<String> pontosCaroneiro;
	private List<String> pontosMotorista;
	private int idCarona;
	private String idSessaoCaroneiro;
	private String idSessaoMotorista;
	private StateChange state;

	public TalkAboutMeetingPoint(int idCarona, String idSessaoCaroneiro, 
			String pontosDeEncontro){
		
		this.idCarona = idCarona;
		this.idSessaoCaroneiro = idSessaoCaroneiro;
		this.pontosCaroneiro = extractListPoints(pontosDeEncontro);
		//this.state = new AguardandoAceitacao(this);
	}
	
	

	private List<String> extractListPoints(String pontosDeEncontro) {
		List<String> result = new ArrayList<String>();
		
		for(String ponto: pontosDeEncontro.split(";")){
			result.add(ponto);
		}
		
		return result;
	}

}
