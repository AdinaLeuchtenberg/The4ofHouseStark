package SpielKlassen;

import Basisklassen.Spieler;

public abstract class KI {

	private Spieler spieler;
	private Spiel spiel = new Spiel();
	
	public KI(Spieler spieler) {
		this.spieler = spieler;	
	}
	
	/**
	 * Goal of the KI to reach the other end of the board
	 */
	public void zurDameWerden() {
		//TODO Tamara
	}
	
	/**
	 * goal of the KI to take away the tokens of the other player
	 */
	public void schlagen() {
		//TODO Tamara
	}
	
	/**
	 * goal of the KI to keep tokens
	 */
	public void selbstNichtGeschlagenWerden() {
		//TODO Manu
	}
	
	/**
	 * goal of the KI to prevent that the other player gets a "Dame"
	 */
	
	public void keineDameDesGegnersZulassen() {
		//TODO Adina
		
	}

}
