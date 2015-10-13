package Basisklassen;

import java.util.ArrayList;

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
	
	public String getSpielerName() {
		return spielerName;
	}

	public void setSpielerName(String n) {
		this.spielerName = n;
	}

}