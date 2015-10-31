package SpeichernLaden;

import java.io.Serializable;

import SpielKlassen.Spiel;

public class DatenzugriffSerialisiert implements iDatenzugriff,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Spiel game;
	
	public DatenzugriffSerialisiert(){
		game.getMap();
		game.getCurrentPlayer().getSpielerName();
	}
	/*@Override 
	public String toString(){
		return game;
	}
	*/
}
