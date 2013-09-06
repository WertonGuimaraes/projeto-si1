package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class CaronaSolicitada {
	private Carona carona;
	private int idCarona;
	private List<String> pontosDeEncontro;
	private String idSessao;
	
	public CaronaSolicitada(int idCarona, String pontoDeEncontro, String idSessao){
		this.setCarona(Controller.getInstance().buscaCarona(idCarona));
		this.setIdCarona(idCarona);
		this.setIdSessao(idSessao);
		this.setPontosDeEncontro(returnPontos(pontoDeEncontro));
	}
	
	
	public List<String> returnPontos(String pontos){
		List<String> listPontos = new ArrayList<String>();
		
		for(String ponto: pontos.split(";")){
			listPontos.add(ponto);
		}
		
		return listPontos;
	}


	public Carona getCarona() {
		return carona;
	}


	public void setCarona(Carona carona) {
		this.carona = carona;
	}


	public int getIdCarona() {
		return idCarona;
	}


	public void setIdCarona(int idCarona) {
		this.idCarona = idCarona;
	}


	public List<String> getPontosDeEncontro() {
		return pontosDeEncontro;
	}


	public void setPontosDeEncontro(List<String> pontosDeEncontro) {
		this.pontosDeEncontro = pontosDeEncontro;
	}


	public String getIdSessao() {
		return idSessao;
	}


	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
}
