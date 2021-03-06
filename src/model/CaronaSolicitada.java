package model;

import java.io.Serializable;


public class CaronaSolicitada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Carona carona;
	private Usuario caroneiro;
	private Usuario motorista;
	private int id;
	private String origem;
	private String destino;
	private String pontoEncontro;
	
	
	/**
	 * Cria uma solicitação de Carona com um objeto Carona e Usuario
	 * @param carona
	 * @param caroneiro
	 */
	public CaronaSolicitada(Carona carona, Usuario caroneiro){
		this.carona=carona;
		this.caroneiro=caroneiro;
		this.motorista=carona.getMotorista();
		this.origem=carona.getOrigem();
		this.destino=carona.getDestino();
		this.pontoEncontro="";
	}

	public Carona getCarona() {
		return carona;
	}
	
	public void addRequest(int id){
		this.motorista.addRequest(id,this);
	}

	public void setCarona(Carona carona) {
		this.carona = carona;
	}

	public Usuario getCaroneiro() {
		return caroneiro;
	}

	public void setCaroneiro(Usuario caroneiro) {
		this.caroneiro = caroneiro;
	}

	public Usuario getMotorista() {
		return motorista;
	}

	public void setMotorista(Usuario motorista) {
		this.motorista = motorista;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getPontoEncontro() {
		return pontoEncontro;
	}

	public void setPontoEncontro(String pontoEncontro) {
		this.pontoEncontro = pontoEncontro;
	}
		
	public void setData(String data){
		this.carona.setDate(data);
	}
	
	public String getData(){
		return this.carona.getDate();
	}
	
	public void setHora(String hora){
		this.carona.setHour(hora);
	}
	
	public String getHora(){
		return this.carona.getHour();
	}
	
	/***
	 * Metodo acessador para o nome do caroneiro
	 * @return nome do caroneiro
	 */
	public String getNomeCaroneiro(){
		return this.caroneiro.getNome();
	}
	
	/***
	 * Metodo acessador para o nome do motorista
	 * @return nome do motorista
	 */
	public String getNomeMotorista(){
		return this.motorista.getNome();
	}
}
