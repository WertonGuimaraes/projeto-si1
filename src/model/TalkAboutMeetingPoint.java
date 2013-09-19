package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.state.AguardandoAceitacao;
import model.state.StateChangeRequest;

public class TalkAboutMeetingPoint implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idSugestao;
	private List<String> pontosCaroneiro;
	private List<String> pontosMotorista;
	private int idCarona;
	private String idSessaoCaroneiro;
	private String idSessaoMotorista;
	private StateChangeRequest state;

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
