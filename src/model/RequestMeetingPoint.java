package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import model.state.AguardandoAceitacao;
import model.state.StateChangeRequest;

/***
 * Classe responsável por criar uma requisição
 * de ponto de encontro
 * @author Jessika, Tiaraju, Rafael e Werton
 *
 */
public class RequestMeetingPoint implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idSugestao;
	private List<String> pontosCaroneiro;
	private List<String> pontosMotorista;
	private int idCarona;
	private String idSessaoCaroneiro;
	private String idSessaoMotorista;
	private StateChangeRequest state;
	
	/**
	 * Construtor de uma requisicao de ponto de encontro
	 * @param idCarona  id da carona desejada
	 * @param idSessaoCaroneiro id da sessao do caroneiro
	 * @param pontosDeEncontro pontos de encontro
	 */
	public RequestMeetingPoint(int idCarona, String idSessaoCaroneiro,
			String pontosDeEncontro) {
		this.idCarona = idCarona;
		this.idSessaoCaroneiro = idSessaoCaroneiro;
		this.pontosCaroneiro = Util.extractListPoints(pontosDeEncontro);
		this.state = new AguardandoAceitacao(this);
	}
	

	public int getIdSugestao() {
		return idSugestao;
	}
	
	/**
	 * Método acessador para o estado da sugestão
	 * @return um objeto StateChangeRequest
	 */
	public StateChangeRequest getState() {
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
	public void setState(StateChangeRequest state) {
		this.state = state;
	}

	/***
	 * Permite ao motorista aprovar o a solicitação e sugerir pontos
	 * @param pontos
	 */
	public void motoristaAprovou(String pontos) {
		state.motoristaAprovou(this, pontos);
		
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
	
	/**
	 * Método para acessar o toString do StateChangeRequest da 
	 * sugestao de ponto de encontro
	 * @return toString do estado da carona
	 */
	public String status(){
		return state.toString();
	}
}
