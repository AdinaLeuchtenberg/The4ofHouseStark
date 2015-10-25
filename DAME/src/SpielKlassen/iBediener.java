package SpielKlassen;

import Basisklassen.Spielbrett;
import SpielKlassen.Spiel;
public interface iBediener {
	
	public String move(boolean dame, String position, Spielbrett map);
	public void changePlayer();
	public void Reader();
	public void currentBoardstate();

	
	/*
	//Spieler oder KI?
	//methode um Zug zu beenden
	//Methoden zum Speichern,Laden
	*/
	
}

