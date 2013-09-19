package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
/**
 * 
 * @author tiaraju
 *
 */
public class Carona implements Serializable {

	private static final long serialVersionUID = 1L;

	private String origem;
	private String destino;
	private DateTime dateTime;
	private int vagas, year, month, day, hour, minute, vagasOcupadas;
	private Usuario motorista;
	private Set<Usuario> caroneiros;
	private Set<String> pontosDeEncontro;
	private Set<Request> requisicoes;
	private int id;

	public Carona(String origem, String destino, String data, String horaSaida,
			int vagas) throws Exception {
		if (origem == null || Util.isEmpty(origem))
			throw new RuntimeException("Origem inválida");
		if (Util.containsInvalidChar(origem))
			throw new RuntimeException("Origem inválida");
		if (destino == null || Util.isEmpty(destino))
			throw new RuntimeException("Destino inválido");
		if (data == null || Util.isEmpty(data))
			throw new RuntimeException("Data inválida");
		if (horaSaida == null || Util.isEmpty(horaSaida))
			throw new RuntimeException("Hora inválida");

		year = Integer.parseInt(data.split("/")[2]);
		month = Integer.parseInt(data.split("/")[1]);
		day = Integer.parseInt(data.split("/")[0]);

		try {
			hour = Integer.parseInt(horaSaida.split(":")[0]);
			minute = Integer.parseInt(horaSaida.split(":")[1]);
		} catch (RuntimeException e) {
			throw new RuntimeException("Hora inválida");
		}

		try {
			this.dateTime = new DateTime(year, month, day, hour, minute);
		} catch (RuntimeException e) {
			throw new RuntimeException("Data inválida");
		}
		if (dateTime.compareTo(new DateTime()) < 0) {
			throw new RuntimeException("Data inválida");
		}
		this.origem = origem;
		this.vagas = vagas;
		this.destino = destino;
		this.caroneiros = new HashSet<Usuario>();
		this.vagasOcupadas = caroneiros.size();
		this.pontosDeEncontro = new HashSet<String>();
		this.requisicoes = new HashSet<Request>();
	}
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Carona)) {
			return false;
		}
		Carona carona = (Carona) obj;
		return (carona.getOrigem().equals(this.getOrigem())
				&& carona.getDestino().equals(this.getDestino())
				&& carona.getDate().equals(this.getDate())
				&& carona.getHour().equals(this.getHour()) && carona.getVagas() == this
				.getVagas());
	}
	/**
	 * 
	 * @param user
	 */
	public void addCaroneiro(Usuario user) {
		if (vagasOcupadas < vagas) {
			caroneiros.add(user);
			vagasOcupadas = caroneiros.size();
		}

	}
	/**
	 * 
	 * @return
	 */
	public String getTrajeto() {
		return origem + " - " + destino;
	}
	/**
	 * 
	 * @return
	 */
	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}
	/**
	 * 
	 * @return
	 */
	public String getDestino() {
		return destino;
	}
	/**
	 * 
	 * @param destino
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}
	/**
	 * 
	 * @return
	 */
	public String getDate() {
		return dateTime.toString("dd/MM/yyyy");
	}

	/***
	 * Método acessador para a hora
	 * 
	 * @return hora
	 */
	public String getHour() {
		return dateTime.toString("HH:mm");
	}

	/**
	 * Método modificador da data
	 * 
	 * @param data
	 */
	public void setDate(String data) {
		this.year = Integer.parseInt(data.split("/")[2]);
		this.month = Integer.parseInt(data.split("/")[1]);
		this.day = Integer.parseInt(data.split("/")[0]);
		this.dateTime = new DateTime(year, month, day, hour, minute);
	}
	/**
	 * 
	 * @param hora
	 */
	public void setHour(String hora) {
		this.hour = Integer.parseInt(hora.split(":")[0]);
		this.minute = Integer.parseInt(hora.split(":")[1]);
		this.dateTime = new DateTime(year, month, day, hour, minute);
	}
	
	public int getVagas() {
		return vagas;
	}

	public int getVagasLivres() {
		return vagas - getVagasOcupadas();
	}

	private int getVagasOcupadas() {
		return vagasOcupadas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	@Override
	public String toString() {
		return origem + " para " + destino + ", no dia " + getDate() + ", as "
				+ getHour();

	}

	public Usuario getMotorista() {
		return motorista;
	}

	public void setMotorista(Usuario motorista) {
		this.motorista = motorista;
	}

	/***
	 * Metodo acessaro para os pontos de encontro da carona
	 * 
	 * @return set de pontos de encontro
	 */
	public Set<String> getPontosDeEncontro() {
		return pontosDeEncontro;
	}

	/**
	 * Método modificador para os pontos de encontro da carona
	 * 
	 * @param pontosDeEncontro
	 */
	public void setPontosDeEncontro(Set<String> pontosDeEncontro) {
		this.pontosDeEncontro = pontosDeEncontro;
	}

	/***
	 * Método acessaro para as requisicoes da carona.
	 * 
	 * @return set de requisições para essa carona
	 */
	public Set<Request> getRequisicoes() {
		return requisicoes;
	}

	/***
	 * Metodo modificador para as requisições da carona.
	 * 
	 * @param requisicoes
	 */
	public void setRequisicoes(Set<Request> requisicoes) {
		this.requisicoes = requisicoes;
	}

	/***
	 * Metodo modificador para o id da carona
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Método acessador para o id da carona
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param caroneiro
	 * @return
	 */
	public boolean hasCaroneiro(Usuario caroneiro) {
		for (Usuario usuario : this.caroneiros) {
			if (usuario.equals(caroneiro))
				return true;
		}

		return false;
	}

}
