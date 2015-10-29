package SpielKlassen;

import Basisklassen.Spieler;

public abstract class KI {

	private Spieler spieler;
	
	public KI(Spieler spieler) {
		this.spieler = spieler;	
	}
	
	/**
	 * Goal of the KI to reach the other end of the board
	 */
	public void zurDameWerden() {
		
	}
	
	/**
	 * goal of the KI to take away the tokens of the other player
	 */
	public void schlagen() {
		
	}
	
	/**
	 * goal of the KI to keep tokens
	 */
	public void selbstNichtGeschlagenWerden() {
		
	}
	
	/**
	 * goal of the KI to prevent that the other player gets a "Dame"
	 */
	
	public void keineDameDesGegnersZulassen() {
		
	}

}
