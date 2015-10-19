package Basisklassen;

import ExtraKlassen.FarbEnum;

public class Spielfeld {
public Spielfigur spielfigur;
String ID = "";
FarbEnum color;

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
