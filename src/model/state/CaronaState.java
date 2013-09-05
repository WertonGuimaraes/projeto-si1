package model.state;

import model.CaronaSolicitada;

public interface CaronaState {
	
	
	
	public void solicitar(CaronaSolicitada caronaSolicitada);
	
	public String sugerirPontoDeEncontro(String pontoDeEncontro);
	
	public boolean aprovar(CaronaSolicitada caronaSolicitada);
	
	public boolean rejeitar(CaronaSolicitada caronaSolicitada);

}
