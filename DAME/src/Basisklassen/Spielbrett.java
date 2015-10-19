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
	Spielfeld[][] newBoard;
	private FarbEnum[][] newColour;
	private String[][] newId;
	
	public Spielbrett() {
		 newBoard = createSpielbrett();
	}
		
	/**
	 * Method createSpielbrett() creates a gaming Board with the size 12*12. Each field on the Board has a different Id 
	 * and different colour
	 * Array newColour to save the colour of the field
	 * Array newId to save the Ids of the field
	 * @return returns a gaming board
	 */
	private Spielfeld[][] createSpielbrett() {
		FarbEnum colour = FarbEnum.black;
		newColour = new FarbEnum[12][12];
		newId = new String[12][12];
		String id = new String();
		int number = 12;
		
		for(int i = 0; i < fieldArray.length; i++) {
			while(number >= 1) {
			for(int j = 0; j < fieldArray[i].length; j++) {
				int letter = 97;
				while(letter <= 108) {
					id = (String.valueOf((char)letter) + String.valueOf(number));
					// schreibt ins Spielbrett colour und id
						fieldArray[i][j] = new Spielfeld(colour, id);
						// schreibt in newColour nur die Farbe der Felder
						newColour[i][j] = colour;
						// schreibt in newId nur die ids der Felder
						newId[i][j] = id;
						letter++;
						
						if(colour == FarbEnum.black) {
							colour = FarbEnum.white;
						} else {
							colour = FarbEnum.black;
						}
			} number--;
			}
			if(colour == FarbEnum.black) {
				colour = FarbEnum.white;
				} else {
					colour = FarbEnum.black;
				}	
		}
		}
		return fieldArray;
	}
	
	/**
	 * 
	 * @return returns the gaming board
	 */
	public Spielfeld[][] getSpielbrett() {
		return newBoard;
	}
	
	/**
	 * 
	 * @return returns the colour Array
	 */
	public FarbEnum[][] getFarbEnum() {
		return newColour;
	}
	
	/**
	 * 
	 * @return returns the id Array
	 */
	public String[][] getNewId() {
		return newId;
	}
	
}


