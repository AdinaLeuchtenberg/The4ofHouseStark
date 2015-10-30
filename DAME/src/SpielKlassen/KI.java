package SpielKlassen;

import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;
import ExtraKlassen.FarbEnum;

public abstract class KI {

	private Spieler spieler;
	private Spiel spiel = new Spiel();
	private Spielbrett board = spiel.getMap();
	
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
	 * @return returns true if KI can take a token of the other player to prevent that he gets a "Dame" 
	 * return false if not
	 */
	
	public boolean keineDameDesGegnersZulassen() {
		for(int i = 0; i < 12; i++) {
			Spielfigur figurDieSchlagenKann = board.getFigurenDieSchlagenKoennen().get(i);
			int[] positionFigurDieSchlagenKann = board.idToIndex(figurDieSchlagenKann.getPosition());
			if(figurDieSchlagenKann.getFarbenEnum() == FarbEnum.black) {
				if(positionFigurDieSchlagenKann[0] < 3) {
					 return true;
				}
			} else if(figurDieSchlagenKann.getFarbenEnum() == FarbEnum.white) {
					if(positionFigurDieSchlagenKann[0] > 8) {
						return true;
					}
				}
			
			}	return false;
		}
}
