package SpielKlassen;

import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;
import ExtraKlassen.FarbEnum;

public class Spiel implements iBediener {

	private Spielbrett map;
	private Spielfigur token;
	private PlayerEnum player;
	private enum PlayerEnum{
		ONE,TWO;
	}
	
	public Spiel(){
		map = new Spielbrett();
		
	}
	
	public String move(boolean dame, String position, Spielbrett map){
		dame = false;
		
		if(){
			
			
		}
		return Spielfigur.setPosition(iD);
	}
	
	private void changePlayer(){
		if (player==PlayerEnum.ONE){
			player=PlayerEnum.TWO;
		}
		else if (player==PlayerEnum.TWO){
			player=PlayerEnum.ONE;
		}
		else{
			System.out.println("Error: no player selected");
		}
	}
}
