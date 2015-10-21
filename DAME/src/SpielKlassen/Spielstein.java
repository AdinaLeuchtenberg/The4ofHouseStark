package SpielKlassen;

import Basisklassen.Spielfeld;
import Basisklassen.Spielfigur;
import Basisklassen.Spielbrett;

public class Spielstein extends Spielfigur {
	
	private Spielstein token;
	private Spielfeld[][] map;
	
	
	public Spielstein(){
		token = new Spielstein();
	}

	public Spielstein getToken(){
		return this.token;
	}

	public String moveUp(){
		if(){
			
		}
	}

	@Override
	public String position(String iD) {
		return null;
	}

}
