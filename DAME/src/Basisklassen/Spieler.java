package Basisklassen;

import java.util.ArrayList;

import ExtraKlassen.FarbEnum;
import SpielKlassen.iBediener;

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
	
	public Spieler(String spielerName, FarbEnum farbEnum) {
		this.spielerName = spielerName;
		this.farbEnum = farbEnum;
	}
	
	
	public String getSpielerName() {
		return spielerName;
	}

	public FarbEnum getFarbEnum() {
		return farbEnum;
	}
	
}