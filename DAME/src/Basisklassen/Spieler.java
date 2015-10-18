package Basisklassen;

import java.util.ArrayList;
import ExtraKlassen.FarbEnum;

/*
 * class Spieler
 * @author The4ofHouseStark
 * @version 1.0
 *
 */

public class Spieler {
	
	private String spielerName;
	private FarbEnum farbenEnum;
	private ArrayList AnzPlayer = new ArrayList();
	//
	public String getSpielerName() {
		return spielerName;
	}

	public void setSpielerName(String n) {
		this.spielerName = n;
	}
	
	/**
	 *@param adds player,spielerName to AnzPlayer ArrayList
	 */
	
	public void addPlayer(){
		AnzPlayer.add(getSpielerName());
		AnzPlayer.add(1, getSpielerName());
	}
	
	/**
	 * Method PlayerColour() 
	 * @param Player1 gets the color white, next player gets the color black out of farbenEnum
	 */
	
  public void PlayerColour (){
  	if(AnzPlayer.contains(getSpielerName())){
  		farbenEnum = farbenEnum.white;
  				
  	}else farbenEnum = farbenEnum.black;
  
  }
}