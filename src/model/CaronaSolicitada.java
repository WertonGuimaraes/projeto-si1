package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class CaronaSolicitada {
	private Carona carona;
	private int idCarona;
	private List<String> pontosDeEncontro;
	//private CaronaState state;
	
	public CaronaSolicitada(int idCarona, String pontoDeEncontro){
		this.carona = Controller.getInstance().buscaCarona(idCarona);
		this.idCarona = idCarona;
		this.pontosDeEncontro = returnPontos(pontoDeEncontro);
		this.state = new SugerirPontosDeEncontro(this, pontosDeEncontro); 
	}
	
	
	public List<String> returnPontos(String pontos){
		List<String> listPontos = new ArrayList<String>();
		
		for(String ponto: pontos.split(";")){
			listPontos.add(ponto);
		}
		
		return listPontos;
	}
	
	public void responderSugestaoPontoDeEncontro(List<String> pontosConvenientes){
		state = new RespostaAosPontosSugeridos(this, pontosConvenientes);
		
	}

}
