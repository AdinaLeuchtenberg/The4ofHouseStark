package Basisklassen;

import ExtraKlassen.FarbEnum;

/**
	 * @author The4ofHouseStark
	 * @version 1.0
	 */
public class Spielfigur {
	
	private double position;
	private int [] TokenArray = new int[24];
	private FarbEnum farbenEnum;
	private Spielbrett map;
	
	/**
	 * constructor sets position 
	 */
	public Spielfigur(){
		this.position(position);
		
	}
	
	public double getPosition() {
		return position;
	}


	public void setPosition(double position) {
		this.position = position;
	}
	
	/**
		* @param playerArray Amount of Tokens, i Token in Array
	*/
	public void colours( int [] TokenArray, int i){
		
	if(TokenArray[i] > (TokenArray.length/2)){
		farbenEnum = FarbEnum.black;
	}
	else farbenEnum = FarbEnum.white;
	}
	
	/**
	 * @param iD position on Field
	 * @return position on Field
	 */
	public double position(double iD){
			
		return;
		}
	
}

