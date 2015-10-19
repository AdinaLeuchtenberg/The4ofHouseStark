package Basisklassen;

import ExtraKlassen.FarbEnum;

/**
	 * @author The4ofHouseStark
	 * @version 1.0
	 */
public abstract class Spielfigur {
	
	private String position;
	protected int [] TokenArray = new int[24];
	private FarbEnum farbenEnum;
	private Spielbrett map;
	
	/**
	 * constructor sets position 
	 */
	public Spielfigur(){
		this.position(position);
		
	}
	
	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
		* @param playerArray Amount of Tokens, i Token in Array
	*/
	public void colours( int [] TokenArray, int i, FarbEnum farbenEnum){
		
	if(TokenArray[i] > (TokenArray.length/2)){
		farbenEnum = FarbEnum.black;
	}
	else farbenEnum = FarbEnum.white;
	}
	
	/**
	 * @param iD position on Field
	 * @return position on Field
	 */
	public String position(String iD){
			
		return;
		}
	
}

