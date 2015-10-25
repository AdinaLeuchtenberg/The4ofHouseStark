package Basisklassen;

import ExtraKlassen.FarbEnum;
/**
 * class Spielfeld
 * @author The4ofHouseStark
 * @version 1.0
 *
 */

public class Spielfeld {
public Spielfigur spielfigur;
private String ID = "";
private FarbEnum color;

/**
 * constructor sets color, ID
 * @param y color of the field, x ID of the field
 */
public Spielfeld(FarbEnum y,String x,Spielfigur figur){
	this.color = y;
	this.ID = x;
	this.spielfigur = figur;
}
public Spielfeld(FarbEnum y,String x){
	this.color = y;
	this.ID = x;
}

public String getID(){
	return this.ID;
}
public FarbEnum getColor(){
	return this.color;
}
public boolean FeldBesetzt(){
	boolean besetzt= true;
	while (this.spielfigur == null){
		besetzt= false;
	}return besetzt;
}


}
