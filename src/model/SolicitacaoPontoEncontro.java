package model;

import java.io.Serializable;

/***
 * Classe responsável por criar uma solicitaçao de ponto de encontro.
 * @author Jessika
 *
 */
public class SolicitacaoPontoEncontro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario caroneiro;
	private Usuario motorista;
	int idCarona;
	int idSugestao;
	
	/**
	 * Cria uma solicitação de ponto de encontro a ser utilizada no processo de requisição de vagas.
	 * @param caroneiro
	 * @param motorista
	 * @param idCarona
	 * @param idSugestao
	 */
	public SolicitacaoPontoEncontro(Usuario caroneiro, Usuario motorista, int idCarona, int idSugestao){
		this.caroneiro = caroneiro;
		this.motorista = motorista;
		this.idCarona = idCarona;
		this.idSugestao = idSugestao;
	}

	public Usuario getCaroneiro() {
		return caroneiro;
	}

	public Usuario getMotorista() {
		return motorista;
	}

	public int getIdCarona() {
		return idCarona;
	}

	public int getIdSugestao() {
		return idSugestao;
	}

	
	

}
