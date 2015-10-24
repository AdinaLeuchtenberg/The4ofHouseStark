package Basisklassen;

import ExtraKlassen.FarbEnum;


public class Spielbrett {
	private Spielfeld[][] board = new Spielfeld[12][12];
	static String id = new String();
	
	public Spielbrett(){
		createSpielbrett();
	}
	
	/**
	 * creates a board with ids and colour
	 * @return returns the board
	 */
	public Spielfeld[][] createSpielbrett() {
	
		FarbEnum black = FarbEnum.black;
		FarbEnum white = FarbEnum.white;
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				id = String.valueOf((char)(108 - i) + String.valueOf(j + 1));
				if((i + j) % 2 != 0) {
					if(i <= 3) {
					board[i][j] = new Spielfeld(black, id, new Spielfigur());
					} else 
				} else {
					board[i][j] = new Spielfeld(white, id);
				}
			}
		}
		return board;
	}
	
	/**
	 * Method to convert from the indexes to the Id
	 * @param i row of the id
	 * @param j column of the id
	 * @return return a string of the id
	 */
	public String indexToId(int i, int j) {
		String indexToId = new String();
		indexToId = String.valueOf((char) (108 - i) + String.valueOf(j + 1));
		return indexToId;
	}
	
	/**
	 * Method to convert from the Id to indexes
	 * @param id the id which should be converted
	 * @return return an array, position 0 is the row and 1 is the column of which the id is in
	 */
	public int[] idToIndex(String id) {
		int[] idToIndex = new int[2];
		int i = 108 - id.charAt(0);
		int j = id.charAt(1) - 49;
		idToIndex[0] = i;
		idToIndex[1] = j;
		return idToIndex;
	}
	
	@Override
	public String toString() {
		String boardString = "";
		//="1 2 3 4 5 6 7 8 9 10 11 12" + "\n";
		int count = 1;
		for(int i = 0; i < this.board.length; i++) {
			boardString += (char) (108 - i) + " ";
			for(int j = 0; j < this.board[i].length; j++) {
				if(i == 0) {
					boardString += count;
					count++;
				}
				if(i == 11) {
					boardString += count;
					count++;
				}

				boardString += board[i][j].getID() + " ";
			}
			boardString += (char) (108 - i) + "\n";
		}
		//boardString += "1 2 3 4 5 6 7 8 9 10 11 12";
		return boardString;
	}
}