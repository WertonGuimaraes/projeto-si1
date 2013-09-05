package model.state;

import model.CaronaSolicitada;

public class CaronaNegada implements CaronaState {

	@Override
	public void solicitar(CaronaSolicitada caronaSolicitada) {
		// TODO Auto-generated method stub

	}

	@Override
	public String sugerirPontoDeEncontro(String pontoDeEncontro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean aprovar(CaronaSolicitada caronaSolicitada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejeitar(CaronaSolicitada caronaSolicitada) {
		// TODO Auto-generated method stub
		return false;
	}

}
