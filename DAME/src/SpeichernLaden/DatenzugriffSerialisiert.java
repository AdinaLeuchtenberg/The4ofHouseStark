package SpeichernLaden;

import java.io.Serializable;

import ExtraKlassen.FarbEnum;
import SpielKlassen.Spiel;

public class DatenzugriffSerialisiert implements iDatenzugriff,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Spiel game;
	private String map = game.getMap().toString();
	private FarbEnum color = game.getCurrentPlayer().getFarbEnum();
	private String name = game.getCurrentPlayer().getSpielerName();
	
	public DatenzugriffSerialisiert(String name, String map, FarbEnum color){
		this.map = map;
		this.name = name;
		this.color=color;
	}
}
