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
String ID = "";
FarbEnum color;

/**
 * constructor sets color, ID
 * @param y color of the field, x ID of the field
 */
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

}
