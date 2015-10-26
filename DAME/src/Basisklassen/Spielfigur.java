package Basisklassen;

import ExtraKlassen.FarbEnum;

/**
	 * @author The4ofHouseStark
	 * @version 1.0
	 */
public class Spielfigur {
	
	private String position;
	private FarbEnum farbenEnum;
	private boolean dame;
	
	/**
	 * constructor sets position 
	 */
	public Spielfigur(String position, FarbEnum farbenEnum, boolean dame){
		this.setPosition(position);
		this.setFarbenEnum(farbenEnum);
		this.setDame(dame);
		
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

	public boolean getDame() {
		return dame;
	}

	public void setDame(boolean dame) {
		this.dame = dame;
	}
	
}

