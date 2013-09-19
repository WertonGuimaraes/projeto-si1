package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario user;

	public Perfil(Usuario user) {
		this.user=user;
	}

	public Usuario getUser(){
		return this.user;
	}
	
	public String getNome(){
		return getUser().getNome();
	}
	
	public Set<Usuario> getFriends() {
		return getUser().getFriends();
	}

	public Map<Integer, Carona> getCaronasOfericidas() {
		return getUser().getCaronas();
	}

	public Map<Integer, CaronaSolicitada> getCaronasSolicitadas() {
		return getUser().getRequests();
	}

	public String getEndereco() {
		return getUser().getEndereco();
	}

	public String getEmail() {
		return getUser().getEmail();
	}

	public String getHistoricoDeCaronas() {
		List list = new ArrayList<Carona>();
		for (int i : getUser().getCaronas().keySet()) {
			list.add(getUser().getCaronas().get(i));
		}
		return list.toString();
	}

	public int getCaronasSegurasTranquilas() {
		return this.user.getReviewSeguras();
	}

	public int getCaronasNaoFuncionaram() {
		return this.user.getReviewNaoFuncionou();
	}

	public int getFaltasEmCaronas() {
		return this.user.getReviewFaltou();
	}

	public int getPresencaEmCaronas() {
		return this.user.getReviewNaoFaltou();
	}

	public String getHistoricoDeVagasEmCaronas() {		
		int size = this.user.getHistoricos().size();
		String resultado = "[";
		for(int i=0; i < size; i++){
			if (i == size - 1) resultado += this.user.getHistoricos().get(i).getCarona().getId();
			else{
				resultado += this.user.getHistoricos().get(i).getCarona().getId() + ",";
			}
		}
		
		resultado += "]";
		return resultado;
	}
}
