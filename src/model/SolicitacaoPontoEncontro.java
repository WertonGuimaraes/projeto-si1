package model;

public class SolicitacaoPontoEncontro {
	private Usuario caroneiro;
	private Usuario motorista;
	int idCarona;
	int idSugestao;
	
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
