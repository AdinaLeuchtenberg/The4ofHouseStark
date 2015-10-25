package Basisklassen;

import ExtraKlassen.FarbEnum;

/**
	 * @author The4ofHouseStark
	 * @version 1.0
	 */
public class Spielfigur {
	
	private String position;
	private FarbEnum farbenEnum;
	
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
	
	public FarbEnum getFarbenEnum() {
		return farbenEnum;
	}

	public void setFarbenEnum(FarbEnum farbenEnum) {
		this.farbenEnum = farbenEnum;
	}
	
}

