package Basisklassen;

import java.util.ArrayList;

import ExtraKlassen.FarbEnum;

/**
 * class Spieler
 * @author The4ofHouseStark
 * @version 1.0
 *
 */

//?????????????????????????????????
//Public class Spieler extends KI {
//  
//   super();
//   this.ki = ki;
//}

public class Spieler {
	
	private String spielerName;
	private FarbEnum farbEnum;
	private ArrayList <String> AnzPlayer = new ArrayList<String>();
	
	public String getSpielerName() {
		return spielerName;
	}

	public void setSpielerName(String n) {
		this.spielerName = n;
	}
	
	/**
	 *adds player,spielerName to AnzPlayer ArrayList
	 */
	
	public void addPlayer(){
		AnzPlayer.add(getSpielerName());
		AnzPlayer.add(1, getSpielerName());
	}
	
	/**
	 * Method PlayerColour() 
	 * Player1 gets the color white, next player gets the color black out of farbenEnum
	 */
	
  public void PlayerColour (){
  	if(AnzPlayer.contains(getSpielerName())){
  		farbEnum = farbEnum.white;
  				
  	}else farbEnum = farbEnum.black;
  
  }
}