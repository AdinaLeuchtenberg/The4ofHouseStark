package ExtraKlassen;


/**
 * class Testboard to test the perfect board
 * @author The4ofHouseStark
 * @version 1.0
 */
public class Testboard {

	
	/**
	 * testArray method to test the right Ids of the board
	 * @return returns an array with the right field Ids
	 */
public String[][] testArray() {
		String[][] board = new String[12][12];
		int number = 12;
		for(int i = 0; i < board.length; i++) {
			while(number >= 1) {	
				for(int j = 0; j < board[i].length; j++) {
					int letter = 97;
					while(letter <= 108) {
						board[i][j] = (String.valueOf((char)letter) + String.valueOf(number));
						//ToDo syso rauslöschen!
						System.out.print(board[i][j]);
						letter++;
						
					}System.out.println("\n");
					number--;
				}
			}
		}
		return board;
	}

	/**
	 * colourArray Method to test the right colourspreading
	 * @return returns an array with the right field colours; 1 = black; 0 = white; 
	 */

	public int[][] colourArray() {
		int[][] colourArray = new int[12][12];
		int black = 1;
		int white = 0;
		for(int i = 0; i < colourArray.length; i++) {
			for(int j = 0; j < colourArray[i].length; j++) {
				if((i + j) % 2 != 0) {
					colourArray[i][j] = black;
				} else {
					colourArray[i][j] = white;
				}
				//ToDO syso löschen!
				System.out.print(colourArray[i][j]);
			} System.out.println("\n");
		}
	
	return colourArray;
	}
}
