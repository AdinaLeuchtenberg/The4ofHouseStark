package ExtraKlassen;


/**
 * class Testboard to test the perfect board
 * @author The4ofHouseStark
 * @version 1.0
 */
public class Testboard {

public void testArray() [
		String[][] board = new String[12][12];
		int number = 12;
		for(int i = 0; i < board.length; i++) {
			while(number >= 1) {	
				for(int j = 0; j < board[i].length; j++) {
					int letter = 97;
					while(letter <= 108) {
						board[i][j] = (String.valueOf((char)letter) + String.valueOf(number));
						//Tdo syso rauslöschen!
						System.out.print(board[i][j]);
						letter++;
						
					}System.out.println("\n");
					number--;
				}
			}
			
		}
	}
}

