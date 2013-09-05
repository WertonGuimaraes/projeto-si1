package model;

import controller.Controller;

public class CaronaSolicitada {
	private Carona carona;
	private int idCarona;
	private String pontoDeEncontro;
	//private CaronaState state;
	
	public CaronaSolicitada(int idCarona, String pontoDeEncontro){
		this.carona = Controller.getInstance().buscaCarona(idCarona);
		this.idCarona = idCarona;
		this.pontoDeEncontro = pontoDeEncontro;
		//this.state = 
	}

}
