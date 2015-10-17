package Basisklassen;

import ExtraKlassen.FarbEnum;

/**
 * class Spielbrett
 * @author The4ofHouseStark
 * @version 1.0
 *
 */
public class Spielbrett {
	Spielfeld[][] fieldArray = new Spielfeld[12][12];
	Spielbrett newBoard;
	
	private Spielbrett() {
		 this.newBoard = createSpielbrett();
	}
		
	/**
	 * Method createSpielbrett() creates a gaming Board with the size 12*12. Each field on the Board has a different Id 
	 * and different colour
	 * @return returns a gaming board
	 */
	private Spielbrett createSpielbrett() {
		FarbEnum colour = FarbEnum.black;
		String id = new String();
	
		for(int i = 0; i < fieldArray.length; i++) {
			for(int j = 0; j < fieldArray[i].length; j++) {
				fieldArray[i][j] = new Spielfeld(colour, id);
				if(colour == FarbEnum.black) {
					colour = FarbEnum.white;
				} else {
					colour = FarbEnum.black;
				}
			}
			if(colour == FarbEnum.black) {
				colour = FarbEnum.white;
			} else {
				colour = FarbEnum.black;
			}
				
		}
	}
	
	public Spielbrett getSpielbrett() {
		return newBoard;
	}
	
	/**
	 * Method getFieldColour 
	 * @param colourBoard the Board of which the colour spreading will be returned in an FarbEnum Array
	 * @return returns an Array of type FarbEnum. It represents the colour spreading of the board
	 */
	public FarbEnum[][] getFieldColour(Spielbrett colourBoard) {
		colourBoard = getSpielbrett();
		FarbEnum[][] fieldColour = new FarbEnum[12][12];
		for(int i = 0; i < fieldColour.length; i++) {
			for(int j = 0; j < fieldColour[i].length; j++) {
				//ToDO Farben des Spielbretts in fieldColour schreiben
				//fieldColour[i][j] = 
			}
		}
	
	}
}


