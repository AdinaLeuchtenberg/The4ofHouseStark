package Basisklassen;

import ExtraKlassen.FarbEnum;


public class Spielbrett {
	private Spielfeld[][] board = new Spielfeld[12][12];
	String id = new String();
	
	public Spielbrett(){
		createSpielbrett();
	}
	
	public Spielfeld[][] createSpielbrett() {
	
		FarbEnum black = FarbEnum.black;
		FarbEnum white = FarbEnum.white;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				id = String.valueOf((char)(108 - i) + String.valueOf(j + 1));
				if((i + j) % 2 != 0) {
					board[i][j] = new Spielfeld(black, id);
				} else {
					board[i][j] = new Spielfeld(white, id);
				}
			}
		}
		return board;
	}
	
	public static String indexToId(int i, int j) {
		String indexToId = new String();
		indexToId = String.valueOf((char) (108 - i) + String.valueOf(j + 1));
		return indexToId;
	}
	
	
	public static void main(String[] args) {
		System.out.println(indexToId(1,1));
	}
}