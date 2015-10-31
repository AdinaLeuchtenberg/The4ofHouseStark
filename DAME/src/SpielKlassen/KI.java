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
	public boolean selbstNichtGeschlagenWerden(int[] posNext) {
			// test auf Dame rechts hoch 
		for(int i=2;i<12;i++){//von 2 über dem feld bis zum max. letzten feld auf dame testen
			if(board.getField(posNext[0]+i, posNext[1]+i).fieldBesetzt()){
				if(board.getField(posNext[0]+i, posNext[1]+i).getSpielfigur().getDame()){
					if(board.getField(posNext[0]-1, posNext[1]-1).fieldBesetzt()){
				return false;
				}
				}
			}
		}
		// test auf Dame links hoch
		for(int i=2;i<12;i++){//von 2 über dem feld bis zum max. letzten feld auf dame testen
			if(board.getField(posNext[0]+i, posNext[1]-i).fieldBesetzt()){
				if(board.getField(posNext[0]+i, posNext[1]-i).getSpielfigur().getDame()){
					if(board.getField(posNext[0]-1, posNext[1]+1).fieldBesetzt()){
				return false;
				}
			}
		}
		}
		// test auf Dame rechts runter
		for(int i=2;i<12;i++){//von 2 über dem feld bis zum max. letzten feld auf dame testen
			if(board.getField(posNext[0]-i, posNext[1]+i).fieldBesetzt()){
				if(board.getField(posNext[0]-i, posNext[1]+i).getSpielfigur().getDame()){
					if(board.getField(posNext[0]+1, posNext[1]-1).fieldBesetzt()){
				return false;
				}
			}
		}
	}
		// test auf Dame links runter
		for(int i=2;i<12;i++){//von 2 über dem feld bis zum max. letzten feld auf dame testen
		if(board.getField(posNext[0]-i, posNext[1]-i).fieldBesetzt()){
			if(board.getField(posNext[0]-i, posNext[1]-i).getSpielfigur().getDame()){
				if(board.getField(posNext[0]+1, posNext[1]+1).fieldBesetzt()){
			return false;
			}
		}
	}
}
		if(spieler.getFarbEnum()==FarbEnum.white){//für weißen spieler
			//rechts oben über zielfeld steht gegner, ist feld unten links von zielfeld frei
			if(board.getField(posNext[0]+1,posNext[1]+1).fieldBesetzt()){
				if(board.getField(posNext[0]+1,posNext[1]+1).getSpielfigur().getFarbenEnum() == FarbEnum.black){
					if(!board.getField(posNext[0]-1,posNext[1]-1).fieldBesetzt()){
						return false;
					}
				}
			}
		//links oben über zielfeld steht gegner, ist feld unten rechts von zielfeld frei
				if(board.getField(posNext[0]+1,posNext[1]-1).fieldBesetzt()){
					if(board.getField(posNext[0]+1,posNext[1]-1).getSpielfigur().getFarbenEnum() == FarbEnum.black){
						if(!board.getField(posNext[0]-1,posNext[1]+1).fieldBesetzt()){
							return false;
						}
					}
				}return true;
		}
	if(spieler.getFarbEnum()== FarbEnum.black){
	//für schwarzen spieler
//rechts unten unter zielfeld steht gegner, ist feld oben	links von zielfeld frei
	if(board.getField(posNext[0]-1,posNext[1]+1).fieldBesetzt()){
		if(board.getField(posNext[0]-1,posNext[1]+1).getSpielfigur().getFarbenEnum() == FarbEnum.black){
			if(!board.getField(posNext[0]+1,posNext[1]-1).fieldBesetzt()){
				return false;
			}
		}
	}
	//für schwarzen spieler
	//links unten unter zielfeld steht gegner, ist feld oben	rechts von zielfeld frei
		if(board.getField(posNext[0]-1,posNext[1]-1).fieldBesetzt()){
			if(board.getField(posNext[0]-1,posNext[1]-1).getSpielfigur().getFarbenEnum() == FarbEnum.black){
				if(!board.getField(posNext[0]+1,posNext[1]+1).fieldBesetzt()){
					return false;
				}
			}
		}
	}
	return true;
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
				if(positionFigurDieSchlagenKann[0] < 4) {
					 return true;
				}
			} else if(figurDieSchlagenKann.getFarbenEnum() == FarbEnum.white) {
					if(positionFigurDieSchlagenKann[0] > 7) {
						return true;
					}
				}
			
			}	return false;
		}
}
