package Basisklassen;

import ExtraKlassen.FarbEnum;

public class Spielfigur {
	/**
	 * @author The4ofHouseStark
	 * @version 1.0
	 * 
	 */
	
	private double position;
	private int [] playerArray = new int[24];
	private FarbEnum farbenEnum;
	private Spielbrett map;
	
	public Spielfigur(){
		this.position(position);
		
	}
	
	public double getPosition() {
		return position;
	}


	public void setPosition(double position) {
		this.position = position;
	}

	public void colours( int [] playerArray, int i){
		/**
		 * @param playerArray Anzahl der Player
		 */
	if(playerArray[i] > (playerArray.length/2)){
		farbenEnum = FarbEnum.black;
	}
	else farbenEnum = FarbEnum.white;
	}
	
	public double position(double iD){
			
		return;
		}
	
}

