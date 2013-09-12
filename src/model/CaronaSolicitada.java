package model;


public class CaronaSolicitada {
	
	private Carona carona;
	private Usuario caroneiro;
	private Usuario motorista;
	private int id;
	private String origem;
	private String destino;
	private String pontoEncontro;
	
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
	
	
	
}
