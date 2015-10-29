package Basisklassen;

import java.util.ArrayList;

import ExtraKlassen.FarbEnum;

public class Spielbrett {
	private Spielfeld[][] board = new Spielfeld[12][12];
	String id = new String();

	public Spielbrett() {
		createSpielbrett();
	}

	/**
	 * creates a board with ids, colour and the starting condition of the figures
	 * 
	 * @return returns the board
	 */
	public Spielfeld[][] createSpielbrett() {

		FarbEnum black = FarbEnum.black;
		FarbEnum white = FarbEnum.white;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				id = String.valueOf((char) (108 - i) + String.valueOf(j + 1));
				if ((i + j) % 2 != 0) {
					if (i > 3 & i < 8) {
						board[i][j] = new Spielfeld(black, id, null);
					} else if (i <= 3) {
						board[i][j] = new Spielfeld(black, id, new Spielfigur(id, black, false));
					} else if (i >= 8) {
						board[i][j] = new Spielfeld(black, id, new Spielfigur(id, white, false));
					}
				} else {
					board[i][j] = new Spielfeld(white, id, null);
				}

			}
		}
		return board;
	}

	/**
	 * Method to convert from the indexes to the Id
	 * 
	 * @param i
	 *          row of the id
	 * @param j
	 *          column of the id
	 * @return return a string of the id
	 */
	public String indexToId(int i, int j) {
		String indexToId = new String();
		indexToId = String.valueOf((char) (108 - i) + String.valueOf(j + 1));
		return indexToId;
	}

	/**
	 * Method to convert from the Id to indexes
	 * 
	 * @param id
	 *          the id which should be converted
	 * @return return an array, position 0 is the row and 1 is the column of which
	 *         the id is in
	 */
	public int[] idToIndex(String id) {
		int[] idToIndex = new int[2];
		int i = 108 - id.charAt(0);
		int j = id.charAt(1) - 49;
		idToIndex[0] = i;
		idToIndex[1] = j;
		return idToIndex;
	}

	public Spielfeld getField(int i, int j) {
		return this.board[i][j];
	}

	/**
	 * method to save the tokens which have to move to beat the other player
	 * 
	 * @return array of token which have to move to beat the other player
	 */
	public ArrayList<Spielfigur> getFigurenDieSchlagenKoennen() {
		ArrayList<Spielfigur> bullies = new ArrayList<Spielfigur>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// alle Felder die besetzt sind
				if (board[i][j].fieldBesetzt()) {
					// ist figur dame?
					if (!board[i][j].getSpielfigur().getDame()) {
						// ist stein schwarz?
						if (board[i][j].getSpielfigur().getFarbenEnum() == FarbEnum.black) {
							// untergrenze board
							if (i + 2 < board.length) {
								// rechte Grenze Board?
								if (j + 2 < board[i].length) {
									// alle rechtenfelder die besetzt sind
									if (board[i + 1][j + 1].fieldBesetzt()) {
										// hat Spielfigur auf dem nächsten Feld andere farbe als auf
										// jetzigem feld?
										if (board[i][j].getSpielfigur().getFarbenEnum() != board[i + 1][j + 1].getSpielfigur().getFarbenEnum()) {
											// feld zweil felder weiter besetz?
											if (!board[i + 2][j + 2].fieldBesetzt()) {
												bullies.add(board[i][j].getSpielfigur());
											}
										}
									}
								}
								// linke grenze vom board?
								else if (j - 2 < 0) {
									// alle linken felder die besetzt sind
									if (board[i + 1][j - 1].fieldBesetzt()) {
										// hat Spielfigur auf dem nächsten Feld andere farbe als auf
										// jetzigem feld?
										if (board[i][j].getSpielfigur().getFarbenEnum() != board[i + 1][j - 1].getSpielfigur().getFarbenEnum()) {
											// feld zwei felder weiter links besetz?
											if (!board[i + 2][j - 2].fieldBesetzt()) {
												bullies.add(board[i][j].getSpielfigur());
											}
										}
									}
								}
							}
						} else
						// ist stein weiß?
						if (board[i][j].getSpielfigur().getFarbenEnum() == FarbEnum.white) {
							// ober grenze vom board?
							if (i - 2 < 0) {
								// rechte grenze?
								if (j + 2 < board[i].length) {
									// alle rechtenfelder die besetzt sind
									if (board[i - 1][j + 1].fieldBesetzt()) {
										// hat Spielfigur auf dem nächsten Feld andere farbe als auf
										// jetzigem feld?
										if (board[i][j].getSpielfigur().getFarbenEnum() != board[i - 1][j + 1].getSpielfigur().getFarbenEnum()) {
											// feld zweil felder weiter besetz?
											if (!board[i - 2][j + 2].fieldBesetzt()) {
												bullies.add(board[i][j].getSpielfigur());
											}
										}
									}
								}
								// linke grenze vom board
								else if (j - 2 < 0) {
									// alle linken felder die besetzt sind
									if (board[i - 1][j - 1].fieldBesetzt()) {
										// hat Spielfigur auf dem nächsten Feld andere farbe als auf
										// jetzigem feld?
										if (board[i][j].getSpielfigur().getFarbenEnum() != board[i - 1][j - 1].getSpielfigur().getFarbenEnum()) {
											// feld zwei felder weiter links besetz?
											if (!board[i - 2][j - 2].fieldBesetzt()) {
												bullies.add(board[i][j].getSpielfigur());
											}
										}
									}
								}
							}
						}
					}
					// spielfigur ist dame
					else {
						// untergrenze board
						if (i + 2 < board.length) {
							// rechte Grenze Board?
							if (j + 2 < board[i].length) {
								// alle rechten felder oben die besetzt sind
								if (board[i + 1][j + 1].fieldBesetzt()) {
									// hat Spielfigur auf dem nächsten Feld andere farbe als auf
									// jetzigem feld?
									if (board[i][j].getSpielfigur().getFarbenEnum() != board[i + 1][j + 1].getSpielfigur().getFarbenEnum()) {
										// feld zweil felder weiter besetz?
										if (!board[i + 2][j + 2].fieldBesetzt()) {
											bullies.add(board[i][j].getSpielfigur());
										}
									}
								}
							}
							// linke grenze vom board?
							else if (j - 2 < 0) {
								// alle linken felder oben die besetzt sind
								if (board[i + 1][j - 1].fieldBesetzt()) {
									// hat Spielfigur auf dem nächsten Feld andere farbe als auf
									// jetzigem feld?
									if (board[i][j].getSpielfigur().getFarbenEnum() != board[i + 1][j - 1].getSpielfigur().getFarbenEnum()) {
										// feld zwei felder weiter links besetz?
										if (!board[i + 2][j - 2].fieldBesetzt()) {
											bullies.add(board[i][j].getSpielfigur());
										}
									}
								}
							}
							// ober grenze vom board?
						} else if (i - 2 < 0) {
							// rechte grenze?
							if (j + 2 < board[i].length) {
								// alle rechtenfelder unten die besetzt sind
								if (board[i - 1][j + 1].fieldBesetzt()) {
									// hat Spielfigur auf dem nächsten Feld andere farbe als auf
									// jetzigem feld?
									if (board[i][j].getSpielfigur().getFarbenEnum() != board[i - 1][j + 1].getSpielfigur().getFarbenEnum()) {
										// feld zweil felder weiter besetz?
										if (!board[i - 2][j + 2].fieldBesetzt()) {
											bullies.add(board[i][j].getSpielfigur());
										}
									}
								}
							}

							// rechte grenze?
							else if (j - 2 < 0) {
								// alle linken felder unten die besetzt sind
								if (board[i - 1][j - 1].fieldBesetzt()) {
									// hat Spielfigur auf dem nächsten Feld andere farbe als auf
									// jetzigem feld?
									if (board[i][j].getSpielfigur().getFarbenEnum() != board[i - 1][j - 1].getSpielfigur().getFarbenEnum()) {
										// feld zwei felder weiter links besetz?
										if (!board[i - 2][j - 2].fieldBesetzt()) {
											bullies.add(board[i][j].getSpielfigur());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return bullies;
	}

	@Override
	public String toString() {
		String boardString = "  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 |  11 |  12 | \n";

		for (int i = 0; i < this.board.length; i++) {

			boardString += (char) (108 - i) + " " + "|";
			for (int j = 0; j < this.board[i].length; j++) {

				try {
					boardString += board[i][j].getSpielfigur().getFarbenEnum();
				} catch (NullPointerException e) {
					boardString += "     ";
				}
				boardString += "|";

			}
			boardString += (char) (108 - i) + "\n";
		}
		boardString += "  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 |  11 |  12 |";
		return boardString;
	}
}