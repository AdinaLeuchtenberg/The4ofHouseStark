package Basisklassen;

import ExtraKlassen.FarbEnum;

/**
	 * @author The4ofHouseStark
	 * @version 1.0
	 */
public class Spielfigur {
	
	private String position;
	protected int [] TokenArray = new int[48];
	private Spielfigur token;
	private FarbEnum farbenEnum;
	private Spielbrett map;
	
	/**
	 * constructor sets position 
	 */
	public Spielfigur(String position, FarbEnum farbenEnum){
		this.setPosition(position);
		this.setFarbenEnum(farbenEnum);
		
	}
	
	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
		* @param playerArray Amount of Tokens, i Token in Array, FarbEnum colour
		* @return return colour of token
	*/
	public FarbEnum colours( int [] TokenArray, int i, FarbEnum farbenEnum){
		
	if(TokenArray[i] > (TokenArray.length/2)){
		farbenEnum = FarbEnum.black;
	}
	else farbenEnum = FarbEnum.white;
	return farbenEnum;
	}
	
	/**
	 * @param iD position on Field
	 * @return position on Field
	 */

	public Spielfigur getToken() {
		return token;
	}

	public void setToken(Spielfigur token) {
		this.token = token;
	}

	public FarbEnum getFarbenEnum() {
		return farbenEnum;
	}

	public void setFarbenEnum(FarbEnum farbenEnum) {
		this.farbenEnum = farbenEnum;
	}
	
}

