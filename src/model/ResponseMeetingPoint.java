package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;

import model.state.AguardandoAceitacao;
import model.state.RequisicaoRecebida;
import model.state.StateChangeRequest;
import model.state.StateChangeResponse;

/**
 * Classe responsavel pela resposta a uma sugestão de ponto 
 * de encontro
 * @author Jessika, Tiaraju, Rafael e Werton
 *
 */
public class ResponseMeetingPoint implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idSugestao;
	private List<String> pontosCaroneiro;
	private List<String> pontosMotorista;
	private int idCarona;
	private String idSessaoCaroneiro;
	private String idSessaoMotorista;
	private StateChangeResponse state;
	
	/**
	 * Construtor da resposta à sugestão de ponto de encontro
	 * @param idCarona 
	 * @param idSessaoCaroneiro 
	 * @param pontosDeEncontro
	 */
	public ResponseMeetingPoint(int idCarona, String idSessaoCaroneiro,
			String pontosDeEncontro) {
		this.idCarona = idCarona;
		this.idSessaoCaroneiro = idSessaoCaroneiro;
		this.pontosCaroneiro = Util.extractListPoints(pontosDeEncontro);
		this.state = new RequisicaoRecebida(this);
	}
	
	
	/**
	 * Metodo acessador para o idSugestao
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

	/***
	 * Método acessador dos pontos do caroneiro
	 * @return lista dos pontos do caroneiro
	 */
	public List<String> getPontosCaroneiro() {
		return pontosCaroneiro;
	}

	
	/***
	 * Método acessador dos pontos do motorista
	 * @return lista dos pontos do motorista
	 */
	public List<String> getPontosMotorista() {
		return pontosMotorista;
	}
	
	/**
	 * Método acessador para o id da carona
	 * @return id da carona
	 */
	public int getIdCarona() {
		return idCarona;
	}

	/***
	 * Método acessador para o id da sessao do caroneiro
	 * @return id da sessao do caroneiro
	 */ 
	public String getIdSessaoCaroneiro() {
		return idSessaoCaroneiro;
	}

	/***
	 * Método acessador para o id da sessao do motorista
	 * @return id da sessao do motorista
	 */
	public String getIdSessaoMotorista() {
		return idSessaoMotorista;
	}

	/***
	 * Método modificador para o id da sugestão
	 * @param idSugestao
	 */
	public void setIdSugestao(int idSugestao) {
		this.idSugestao = idSugestao;
	}

	/**
	 * Modifica o estado da carona
	 * @param state
	 */
	public void setState(StateChangeResponse state) {
		this.state = state;
	}

	/***
	 * Permite ao motorista aprovar o a solicitação e sugerir pontos
	 * @param pontos
	 */
	public void aceitaSolicitacao(String pontos) {
		this.state.aceitaSolicitacao(this, pontos);
	}
	
	/**
	 * Retorna o toString do estado atual
	 * @return estado atual
	 */
	public String getStatus(){
		return state.toString();
	}
	
	/***
	 * Retorna os pontos sugeridos pelo motorista
	 * @return pontos do motorista
	 */
	public String getPointMotorista(){
		if (this.pontosMotorista == null) return "não cadastrado";
		String result = "";
		
		for (String ponts : this.pontosMotorista) {
			result += ponts + " ";
		}
		
		return result;
	}
	
	/**
	 * Retorna os pontos sugeridos pelo caroneiro
	 * @return pontos do caroneiro
	 */
	public String getPointCaroneiro(){
		if (this.pontosCaroneiro == null) return "não cadastrado";
		String result = "";
		
		for (String ponts : this.pontosCaroneiro) {
			result += ponts + " ";
		}
		
		return result;
	}
	
	/**
	 * Retorna o caminho, origem a destino da carona
	 * @return trajeto
	 */
	public String getCaminho(){
		Carona carona = Controller.getInstance().buscaCarona(idCarona);
		return carona.getTrajeto();
	}
	

}
