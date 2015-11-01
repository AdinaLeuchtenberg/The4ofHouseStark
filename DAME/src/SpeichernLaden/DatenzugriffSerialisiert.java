package SpeichernLaden;

import java.io.Serializable;

import SpielKlassen.Spiel;

public class DatenzugriffSerialisiert implements iDatenzugriff,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Spiel game;
	private String map = game.getMap().toString();
	private String name = game.getCurrentPlayer().getSpielerName();
	
	public DatenzugriffSerialisiert(String name, String map){
		this.map = map;
		this.name = name;
	}
	@Override 
	public String toString(){
		return name + map;
	}
	
}
