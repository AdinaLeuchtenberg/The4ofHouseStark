package Basisklassen;

import java.util.ArrayList;

import ExtraKlassen.FarbEnum;
import SpielKlassen.KI;
import SpielKlassen.KI_Dame;
import SpielKlassen.Spiel;
import SpielKlassen.iBediener;

/**
 * class Spieler
 * @author The4ofHouseStark
 * @version 1.0
 *
 */

public class Spieler {
	
	private String spielerName;
	private FarbEnum farbEnum;
	private KI ki;
	private Spiel game;
	
	public Spieler(String spielerName, FarbEnum farbEnum) {
		this.spielerName = spielerName;
		this.farbEnum = farbEnum;
		this.ki = ki;
		this.game = game;
	}
	
	
	public String getSpielerName() {
		return spielerName;
	}

	public FarbEnum getFarbEnum() {
		return farbEnum;
	}
}