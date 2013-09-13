package model;

import java.util.Map;
import java.util.Set;

public class Perfil {
	
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
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHistoricoDeCaronas() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCaronasSegurasTranquilas() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCaronasNaoFuncionaram() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFaltasEmCaronas() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPresencaEmCaronas() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHistoricoDeVagasEmCaronas() {
		// TODO Auto-generated method stub
		return null;
	}
}
