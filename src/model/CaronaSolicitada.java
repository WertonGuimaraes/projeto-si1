package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class CaronaSolicitada {
	private Carona carona;
	private int idCarona;
	private List<String> pontosDeEncontro;
	private CaronaState state;
	private String idSessao;
	
	public CaronaSolicitada(int idCarona, String pontoDeEncontro, String idSessao){
		this.carona = Controller.getInstance().buscaCarona(idCarona);
		this.idCarona = idCarona;
		this.idSessao = idSessao;
		this.pontosDeEncontro = returnPontos(pontoDeEncontro);
		this.state = new SugerirPontosDeEncontro(this, pontosDeEncontro, idCarona, idSessao); 
	}
	
	
	
	
	public void responderSugestaoPontoDeEncontro(List<String> pontosConvenientes, String idSessao, String idSugestao, int idCarona){
		state.responderSugestaoPontoDeEncontro(this, pontosConvenientes, idSessao, idSugestao, idCarona);

	}
	
	public void requisitarVagaNaCarona(int idCarona, String idSessao, String ponto){
		state.requisitarVagaNaCarona(this, idCarona, idSessao, ponto);
	}

	
	public List<String> returnPontos(String pontos){
		List<String> listPontos = new ArrayList<String>();
		
		for(String ponto: pontos.split(";")){
			listPontos.add(ponto);
		}
		
		return listPontos;
	}
}
