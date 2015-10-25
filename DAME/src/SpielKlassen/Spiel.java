package SpielKlassen;

import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;
import ExtraKlassen.FarbEnum;

public class Spiel implements iBediener {


	private Spielbrett map;
	private Spielfigur token;
	private PlayerEnum player;
	private FarbEnum [] TokenArray = new FarbEnum[48];
	private enum PlayerEnum{
		ONE,TWO;
	}
	
	public Spiel(){
		map = new Spielbrett();
		
	}
	/**
	* 
	* @return return colours of tokens
*/
public FarbEnum[] getFarbTokenArray(){
	
	for(int i=0; i<(TokenArray.length/2); i++){
		TokenArray[i] = FarbEnum.white;
	} 
	for(int j=24;j<TokenArray.length; j++){
		TokenArray[j]= FarbEnum.black;
	}
	return TokenArray;
}
	
	public String move(boolean dame, String position, String iDFeld){
		dame = false;
		int [] posNow = map.idToIndex(position);
		int [] posNext = map.idToIndex(iDFeld);
		
		// für weißer spieler
		if(map.getField([posNext[0]][posNext[1]]).getColor() == FarbEnum.black){ 	// farb test 
			if(posNext[0]<= 11 && posNext[0]>=0){ // im Feld (buchstaben)
				if(posNext[1]<= 11 && posNext[1]>=0){ // im feld (zahlen)
					if(posNext[0]== posNow[0]+1){ // ein feld weiter (nach oben)
						if(posNext[1]==posNow[1]+1 || posNext[1]== posNow[1]-1){ // Feld nach rechts oder links 
							if(map.getField([posNext[0]][posNext[1]]).fieldBesetzt()){ //Feld besetzt?
								if(map.getField([posNext[0]][posNext[1]]).getSpielfigur.getFarbenEnum()== FarbEnum.black ){	// Ist Spielfigur schwarz somit Gegnerfarbe
									if(posNext[0]-1 <= 11 && posNext[0]-1 >=0){	// Ist neues Spielfeld im Feld, nächste reihe 
										if((posNext[1]+1 <= 11 && posNext[1]+ 1 >=0)||(posNext[1]-1 <= 11 && posNext[1]- 1 >=0)){ // NeuesSpielfeld ist im Brett um schlagen möglich zu machen 
											if((map.getField([posNext[0]+1][posNext[1]+1]).fieldBesetzt()) || (map.getField([posNext[0]+1][posNext[1]-1]).fieldBesetzt())){
												map.getField([posNext[0]][posNext[1]]).setSpielfigur(null);	// setzt das Feld null auf dem die Spielfigur jetzt saß
													int j=24;
													while(TokenArray[j]= null){		//löscht den Token aus den FarbTokenArray
														j++;
													} TokenArray[j] =null;
													 //... setzt die Spielfigur aufs neue Feld + muss schlagen wenn möglich!!
													
												}
												
											}
										}
									}
								}
							}
							
						}
					}
				}
				
			}
			
			
		}
		return ;
	}
	
	private void changePlayer(){
		if (player==PlayerEnum.ONE){
			player=PlayerEnum.TWO;
		}
		else if (player==PlayerEnum.TWO){
			player=PlayerEnum.ONE;
		}
		else{
			System.out.println("Error: no player selected");
		}
	}
	@Override
	public void reader() {
		// TODO Auto-generated method stub
		
	}
}
