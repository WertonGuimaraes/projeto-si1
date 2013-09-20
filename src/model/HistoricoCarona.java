package model;

import java.io.Serializable;

public class HistoricoCarona implements Serializable {

	private static final long serialVersionUID = 1L;
	private Carona carona;
	private String review;
	private final String[] reviews= {"faltou", "não faltou", "não funcionou"};

	/**
	 * Gera o historico da carona, considerando se o caroneiro presenciou ou não.
	 * E qual a avaliação dos usuarios.
	 * @param carona
	 * @param review
	 */
	public HistoricoCarona(Carona carona, String review) {
		if(!reviewIsValid(review)) throw new RuntimeException("Opção inválida.");
		
		this.carona = carona;
		this.review = review;
	}

	public HistoricoCarona(Carona carona){
		this.carona = carona;
		this.review = "";
	}

	/**
	 * Aceita a mensagem de revisão da carona.
	 * @param review
	 * @return
	 */
	private boolean reviewIsValid(String review) {
		for (String rev : this.reviews) {
			if(rev.equals(review)) return true;
		}
		return false;
	}

	/**
	 * @return the carona
	 */
	public Carona getCarona() {
		return carona;
	}

	/**
	 * @return the review
	 */
	public String getReview() {
		return review;
	}

	/**
	 * @return the reviews
	 */
	public String[] getReviews() {
		return reviews;
	}

	public void setReview(String review) {
		if(!reviewIsValid(review)) throw new RuntimeException("Opção inválida.");

		this.review = review;
	}
	
	
}
