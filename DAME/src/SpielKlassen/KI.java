package SpielKlassen;

import java.util.Random;

import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;
import ExtraKlassen.FarbEnum;

public abstract class KI {

	private Spieler spieler;
	private Spiel spiel = new Spiel();
	private Spielbrett board = spiel.getMap();
	private Spielfigur token;

	public KI(Spieler spieler) {
		this.spieler = spieler;
	}

	/**
	 * Goal of the KI to reach the other end of the board
	 *
	 * @return returns true if there is no token to beat returns false if there is
	 *         one (schlagen?)
	 */
	public boolean zurDameWerden() {
		// TODO Tamara
		if (board.getFigurenDieSchlagenKoennen() == null) {
			String positionSpielfigur = token.getPosition();
			if(spiel.getCurrentPlayer().getFarbEnum()== FarbEnum.black){
			if (token.getFarbenEnum() == FarbEnum.black) {
				// choose a token which is the closest to index 11
				positionSpielfigur.lastIndexOf();
				return true;
			}
			if(spiel.getCurrentPlayer().getFarbEnum()== FarbEnum.white){
				if(token.getFarbenEnum() == FarbEnum.white) {
					//choose a token which have the highest position to index 0
					positionSpielfigur.lastIndexOf();
					return true;
			}

		}else return false;
	}

	/**
	 * goal of the KI to take away the tokens of the other player(random)
	 * 
	 * @return returns true if the KI can take one or more tokens of the other
	 *         player return false if there is no token to beat
	 */
			public void schlagen() {
				//TODO Tamara
				for(int i = 0; i < 12; i++) {
					if(board.getFigurenDieSchlagenKoennen()!=null){
					Spielfigur figurDieSchlagenKann = board.getFigurenDieSchlagenKoennen().get(i);
						if(figurDieSchlagenKann.getFarbenEnum() == FarbEnum.black) {
							if(){
								Random rand = new Random();
								this.board = rand.nextInt();
							 }
							}						
					} else if(figurDieSchlagenKann.getFarbenEnum() == FarbEnum.white) {
							Random rand = new Random();
							this.token = rand.nextInt();
							//int figurDieSchlagenKann(new Random()).nextInt(figurDieSchlagenKann.length)];
							}
					}
			}

	/**
	 * goal of the KI to keep tokens
	 */
	public boolean selbstNichtGeschlagenWerden(int[] posNext) {
		
		// test auf Dame rechts hoch
		for (int i = 2; i < 12; i++) {// von 2 über dem feld bis zum max. letzten
																	// feld auf dame testen
			if (board.getField(posNext[0] + i, posNext[1] + i).fieldBesetzt()) {
				if (board.getField(posNext[0] + i, posNext[1] + i).getSpielfigur().getDame()) {
					if (board.getField(posNext[0] - 1, posNext[1] - 1).fieldBesetzt()) {
						return false;
					}
				}
			}
		}
		// test auf Dame links hoch
		for (int i = 2; i < 12; i++) {// von 2 über dem feld bis zum max. letzten
																	// feld auf dame testen
			if (board.getField(posNext[0] + i, posNext[1] - i).fieldBesetzt()) {
				if (board.getField(posNext[0] + i, posNext[1] - i).getSpielfigur().getDame()) {
					if (board.getField(posNext[0] - 1, posNext[1] + 1).fieldBesetzt()) {
						return false;
					}
				}
			}
		}
		// test auf Dame rechts runter
		for (int i = 2; i < 12; i++) {// von 2 über dem feld bis zum max. letzten
																	// feld auf dame testen
			if (board.getField(posNext[0] - i, posNext[1] + i).fieldBesetzt()) {
				if (board.getField(posNext[0] - i, posNext[1] + i).getSpielfigur().getDame()) {
					if (board.getField(posNext[0] + 1, posNext[1] - 1).fieldBesetzt()) {
						return false;
					}
				}
			}
		}
		// test auf Dame links runter
		for (int i = 2; i < 12; i++) {// von 2 über dem feld bis zum max. letzten
																	// feld auf dame testen
			if (board.getField(posNext[0] - i, posNext[1] - i).fieldBesetzt()) {
				if (board.getField(posNext[0] - i, posNext[1] - i).getSpielfigur().getDame()) {
					if (board.getField(posNext[0] + 1, posNext[1] + 1).fieldBesetzt()) {
						return false;
					}
				}
			}
		}
		if (spieler.getFarbEnum() == FarbEnum.white) {// für weißen spieler
			// rechts oben über zielfeld steht gegner, ist feld unten links von
			// zielfeld frei
			if (board.getField(posNext[0] + 1, posNext[1] + 1).fieldBesetzt()) {
				if (board.getField(posNext[0] + 1, posNext[1] + 1).getSpielfigur().getFarbenEnum() == FarbEnum.black) {
					if (!board.getField(posNext[0] - 1, posNext[1] - 1).fieldBesetzt()) {
						return false;
					}
				}
			}
			// links oben über zielfeld steht gegner, ist feld unten rechts von
			// zielfeld frei
			if (board.getField(posNext[0] + 1, posNext[1] - 1).fieldBesetzt()) {
				if (board.getField(posNext[0] + 1, posNext[1] - 1).getSpielfigur().getFarbenEnum() == FarbEnum.black) {
					if (!board.getField(posNext[0] - 1, posNext[1] + 1).fieldBesetzt()) {
						return false;
					}
				}
			}
			return true;
		}
		if (spieler.getFarbEnum() == FarbEnum.black) {
			// für schwarzen spieler
			// rechts unten unter zielfeld steht gegner, ist feld oben links von
			// zielfeld frei
			if (board.getField(posNext[0] - 1, posNext[1] + 1).fieldBesetzt()) {
				if (board.getField(posNext[0] - 1, posNext[1] + 1).getSpielfigur().getFarbenEnum() == FarbEnum.black) {
					if (!board.getField(posNext[0] + 1, posNext[1] - 1).fieldBesetzt()) {
						return false;
					}
				}
			}
			// für schwarzen spieler
			// links unten unter zielfeld steht gegner, ist feld oben rechts von
			// zielfeld frei
			if (board.getField(posNext[0] - 1, posNext[1] - 1).fieldBesetzt()) {
				if (board.getField(posNext[0] - 1, posNext[1] - 1).getSpielfigur().getFarbenEnum() == FarbEnum.black) {
					if (!board.getField(posNext[0] + 1, posNext[1] + 1).fieldBesetzt()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * goal of the KI to prevent that the other player gets a "Dame"
	 * 
	 * @return returns true if KI can take a token of the other player to prevent
	 *         that he gets a "Dame" return false if not
	 */

	public boolean keineDameDesGegnersZulassen() {
		for (int i = 0; i < 12; i++) {
			Spielfigur figurDieSchlagenKann = board.getFigurenDieSchlagenKoennen().get(i);
			int[] positionFigurDieSchlagenKann = board.idToIndex(figurDieSchlagenKann.getPosition());
			if (spiel.getCurrentPlayer().getFarbEnum() == FarbEnum.black) {
				if (figurDieSchlagenKann.getFarbenEnum() == FarbEnum.black) {
					if (positionFigurDieSchlagenKann[0] < 4) {
						return true;
					}
				}
			} else {
				if (figurDieSchlagenKann.getFarbenEnum() == FarbEnum.white) {
					if (positionFigurDieSchlagenKann[0] > 7) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
