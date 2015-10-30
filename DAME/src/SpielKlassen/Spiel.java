package SpielKlassen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;
import ExtraKlassen.FarbEnum;

public class Spiel implements iBediener {


	private Spielbrett map;
	private Spielfigur token;
	private Spieler player1;
	private Spieler player2;
	private Spieler player;
	private FarbEnum [] TokenArray = new FarbEnum[48];

	
	public Spiel(){
		map = new Spielbrett();
		gameLoop();
		
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
	
/**
 * method which manages the game from beginning to end
 */
private void gameLoop() {
	boolean started = false;
	getFarbTokenArray();
	this.gameInstruction();
	String readIn = "";
	do{
		readIn = reader();
		switch(readIn) {
		case "add":
			System.out.println("Please enter the name of the first player:");
			String name1 = reader();
			if(name1 == null) {
				System.err.println("please enter a name");
				name1 = reader();
			}

			System.out.println("Please choose your colour");
			String color= reader();
			System.out.println("");
			switch(color){
			case"black":
				player1 = new Spieler(name1, FarbEnum.black);
			break;
			
			case"white":
				player1 = new Spieler(name1, FarbEnum.white);
			break;

			default:
				System.out.println("Because your input was invalid you get the colour white");
				player1 = new Spieler(name1, FarbEnum.white);
			}
			
			System.out.println("Please enter the name of the second player:");
			String name2 = reader();
			if(name2 == null) {
				System.err.println("please enter a name");
				name2 = reader();
			}
			if(player1.getFarbEnum()== FarbEnum.black){
				player2 = new Spieler(name2, FarbEnum.white);
			}else player2 = new Spieler(name2, FarbEnum.black);


			System.out.println(player1.getSpielerName() + " is player one and has colour " + player1.getFarbEnum());
			System.out.println(player2.getSpielerName() + " is player two and has colour " + player2.getFarbEnum());
			
		break;
		case "start":
			if(!started) {
				if(player1.getFarbEnum()== FarbEnum.black){
				player = player1;
				}
				else player = player2;
				started = true;
				System.out.println(map.toString());
			} else {
				System.out.println("game has already started");
			}
		break;
		
		case "startturn":
			System.out.println("enter the coordinate of the token you want to move + coordinate of the field you want to move to; e.g.: b3 c4");
			String coordinates = reader();
			String nowCoordinate = coordinates.substring(0, 2);
			String nextCoordinate = coordinates.substring(3);
			move(nowCoordinate, nextCoordinate);
			boolean wins = wins();
			if(wins) {
				started = false;
				System.out.println("if you want to exit the game enter 'exit' or follow the insturctions");
				gameLoop();
				
			}
		break;
			
		case "endturn":
			System.out.println(map.toString());
			changePlayer();
			if(player == player1) {
				System.out.println(player1.getSpielerName() + "'s turn");
			}
			else {
				System.out.println(player2.getSpielerName() + "'s turn");
			}
		break;
			
		case "exit":
			System.out.println("Thank you for playing");
			System.exit(0);
		break;
		
		default:
			System.err.println("invalid input");
			gameInstruction();
		 
		
	}
	}while (!readIn.equals( "exit"));
	
}
private void gameInstruction() {
	String instructions = "Instructions: \n";
	instructions += "You can enter the following commands; \n to execute press 'Enter' \n";
	instructions += "add - adds a new Player \n";
	instructions += "start - starts the game \n";
	instructions += "startTurn - to start you turn \n";
	instructions += "endTurn - ends the current turn so that the other players turn begins \n";
	instructions += "exit - ends the game";
	System.out.println(instructions);
	
	}

/**
 * method move
 */

public void move(String start, String ziel){
	int[] posNow = map.idToIndex(start);
	int[] posNext = map.idToIndex(ziel);

if(zielIstGueltig(posNext)&& andererMussPusten(posNow)){
		System.err.println("Zug nicht durchführbar, muss pusten " + "Lösche eine der angegeben Figuren! " );
	}
	boolean istDame = true;
	istDame = map.getField(posNow[0], posNow[1]).getSpielfigur().getDame();
	if(zielIstGueltig(posNext)){
		if(zielIstErreichbar(posNow, posNext)){
			schlagen(posNow, posNext);
		}
		changeToDame(ziel);
	}
}

private boolean andererMussPusten(int[] tokenKoords){
	Spielfigur token = map.getField(tokenKoords[0], tokenKoords[1]).getSpielfigur();
	ArrayList<Spielfigur> bullies = map.getFigurenDieSchlagenKoennen(); 
	for(Spielfigur f : bullies){
		if(token.equals(f)){
			return false;
		} else {
				for(Spielfigur g: bullies){	// Löschen fürs pusten
					if(token.getFarbenEnum() == FarbEnum.black){
						System.out.println(token);
						map.getField(tokenKoords[0], tokenKoords[1]).setSpielfigur(null);
					} else System.out.println(token);
						map.getField(tokenKoords[0], tokenKoords[1]).setSpielfigur(null);
				}
		}
	}
	return true;
}

private boolean zielIstGueltig(int[] posNext){
	if(map.getField(posNext[0], posNext[1]).getColor() == FarbEnum.black){ 	// farb test 
		if(posNext[0]<= 11 && posNext[0]>=0){ // im Feld (buchstaben)
			if(posNext[1]<= 11 && posNext[1]>=0){ // im feld (zahlen)
				if(!map.getField(posNext[0], posNext[1]).fieldBesetzt()){
					return true;
				} else System.out.println("Dieses Feld ist besetzt! gebe 'startTurn' erneut ein ");
					return false;
			}
		}
	} else System.out.println("Ungültiges Feld! Dieses Feld ist WEIß! gebe 'startTurn' erneut ein ");
	return false;
}
public int differenz(int[] posNow,int[]posNext){
	int i= 0;
	if(posNow[0]>posNext[0]){
		i=posNow[0]-posNext[0]-1;
			}
	else{
	i=	posNext[0]-posNow[0]-1;
	}return i;
}
	private boolean zielIstErreichbar(int[] posNow, int[] posNext){
	if(map.getField(posNow[0], posNow[1]).getSpielfigur().getDame()){
	// Dame kann ganz ohne schlagen ganz hoch nach rechts laufen:
				if(!map.getField(posNext[0], posNext[1]).fieldBesetzt()){//feld auf das ich ziehe besetzt?
					for(int l=0; l<=differenz(posNow,posNext); l++){ //zählt die differenz hoch
						if(!map.getField(posNow[0]+l, posNow[1]+l).fieldBesetzt()){//felder dazwischen leer?
						if(l==differenz(posNow,posNext)){//nach letzter prüfung wird zug ausgeführt
						map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
						map.getField(posNow[0], posNow[1]).setSpielfigur(null);
							return true;
						}
					}
				}							
						
				}/*
		// geht nach oben rechts als Dame wenn schlagbarer Stein im Weg ist:
		if(!map.getField(posNext[0], posNext[1]).fieldBesetzt()){//feld auf das ich ziehe besetzt?
			for(int h=0; h<10;h++){		
				if(map.getField(posNow[0]+h, posNow[1]+h).getSpielfigur().getFarbenEnum()==map.getField(posNow[0], posNow[1]).getSpielfigur().getFarbenEnum()){
					if(h==differenz(posNow,posNext)){
						for(int q=0;q<=h;q++){
							
						}
					}
					if(map.getField(posNow[0]+h, posNow[1]+h).getSpielfigur().getFarbenEnum() != map.getField(posNow[0], posNow[1]).getSpielfigur().getFarbenEnum()){ // Gegner ?
						//for(int x=1; x<11-h; x++){
						//if(!map.getField(posNow[0]+h+x, posNow[1]+h+x).fieldBesetzt()){
							//return true;
						}
						if(map.getField(posNow[0]+h+x, posNow[1]+h+x).fieldBesetzt()){
							if(map.getField(posNow[0]+h+x, posNow[1]+h+x).getSpielfigur().getFarbenEnum()==map.getField(posNow[0], posNow[1]).getSpielfigur().getFarbenEnum()){
									return false;				}
								if(x==differenz(posNow,posNext)){
									return true;
								}
							}
							
							}
						map.getField(posNow[0]+h, posNow[1]+h).setSpielfigur(null);
						map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
						map.getField(posNow[0], posNow[1]).setSpielfigur(null);
							return true;
			if(posNext[0]==posNow[0]+h+1){		// buchstabe +1
						if(posNext[1] == posNow[1]+h+1){	// zahl+1
							if(map.getField(posNow[0]+h, posNow[1]+h).fieldBesetzt()){	// ist bestetzt?
								
								} else {
									for(int x=1; x<11-h; x++){
										if(map.getField(posNow[0]+h+x, posNow[1]+h+x).fieldBesetzt()){
											if(!map.getField(posNow[0]+h+x+1, posNow[1]+h+x+1).fieldBesetzt()){		
												return true; // test rechts weiter nach oben 
											}
										}
									}
								}
							} 
						}
					}
				}
*/
	
			// Dame kann ganz ohne schlagen ganz hoch nach links laufen:
			if(!map.getField(posNext[0], posNext[1]).fieldBesetzt()){//feld auf das ich ziehe besetzt?
			for(int g=0;g<=differenz(posNow,posNext); g++){
				if(!map.getField(posNow[0]+g, posNow[1]-g).fieldBesetzt()){//felder dazwischen leer?
					if(g==differenz(posNow,posNext)){//nach letzter prüfung wird zug ausgeführt
						map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
						map.getField(posNow[0], posNow[1]).setSpielfigur(null);
							return true;
						
					}
				}
			}
			}
		// geht nach oben links als Dame wenn schlagbarer Stein im Weg ist:		
						 for(int i=0; i<11;i++){
								if(posNext[0]==posNow[0]+i){		// buchstabe +1
								if(posNext[1] == posNow[1]-i){	// zahl-1
								if(map.getField(posNow[0]+i, posNow[1]-i).fieldBesetzt()){	// ist bestetzt?
								if(map.getField(posNow[0]+i, posNow[1]-i).getSpielfigur().getFarbenEnum()!= player2.getFarbEnum()){// Gegner ?
								}
								}
							}
						}	
									else {
										for(int x=1; x<11-i; x++){
											if(map.getField(posNow[0]+i+x, posNow[1]-i-x).fieldBesetzt()){
												if(!map.getField(posNow[0]+i+x+1, posNow[1]-i-x-1).fieldBesetzt()){
													return true; // test links weiter nach oben 
												}
											}
										}
									}
								}
			// Dame kann ganz ohne schlagen ganz runter nach rechts laufen:
						if(!map.getField(posNext[0], posNext[1]).fieldBesetzt()){
						for(int m=0; m<=differenz(posNow,posNext); m++){
							if(!map.getField(posNow[0]-m, posNow[1]+m).fieldBesetzt()){//felder dazwischen leer?
								if(m==differenz(posNow,posNext)){//nach letzter prüfung wird zug ausgeführt
									map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
										map.getField(posNow[0], posNow[1]).setSpielfigur(null);
											return true;
								}
							}
						}
					}
				// geht nach unten rechts als Dame wenn Stein schlagbar ist:
				for(int j=0; j<11;j++){
				if(posNext[0]==posNow[0]-j){ // buchstabe -1
						if(posNext[1] == posNow[1]+j){	// zahl+1
							if(map.getField(posNow[0]-j, posNow[1]+j).fieldBesetzt()){	// ist bestetzt?
								if(map.getField(posNow[0]-j, posNow[1]+j).getSpielfigur().getFarbenEnum() != player2.getFarbEnum()){ // Gegner ?
								}
							}
						}
				}
								else {
									for(int x=1; x<11-j; x++){
										if(map.getField(posNow[0]-j-x, posNow[1]+j+x).fieldBesetzt()){
											if(!map.getField(posNow[0]-j-x-1, posNow[1]+j+x+1).fieldBesetzt()){
												return true; // test rechts weiter nach unten
											}
										}
									}
								}
							}

				// Dame kann ganz ohne schlagen ganz unten nach links laufen:
				if(!map.getField(posNext[0], posNext[1]).fieldBesetzt()){
				for(int n=0; n<=differenz(posNow,posNext); n++){
					if(!map.getField(posNow[0]-n, posNow[1]-n).fieldBesetzt()){//felder dazwischen leer?
						if(n==differenz(posNow,posNext)){//nach letzter prüfung wird zug ausgeführt
							map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
								map.getField(posNow[0], posNow[1]).setSpielfigur(null);
									return true;
						}
					}
				}
				}	
		
			// geht nach unten links als Dame wenn Stein schlagbar ist:
						for(int k=0; k<11;k++){
							if(posNext[0]==posNow[0]-k){ // buchstabe -1
								if(posNext[1] == posNow[1]-k){	// zahl-1
									if(map.getField(posNow[0]-k, posNow[1]-k).fieldBesetzt()){	// ist bestetzt?
										if(map.getField(posNow[0]-k, posNow[1]-k).getSpielfigur().getFarbenEnum() != player2.getFarbEnum()){ // Gegner ?
										}
									}
								}
							}
										else {
											for(int x=1; x<11-k; x++){
												if(map.getField(posNow[0]-k-x, posNow[1]-k-x).fieldBesetzt()){
													if(!map.getField(posNow[0]-k-x-1, posNow[1]-k-x-1).fieldBesetzt()){
														return true; // test links weiter nach unten 
													}
												}
											}
										}
										}
				}

	// Stein zieht ein Feld hoch rechts oder links:
	else { 
			if(posNext[0]== posNow[0]+1){ // ein feld weiter (nach oben) // buchstaben nach oben 
			if(posNext[1]==posNow[1]+1 || posNext[1]== posNow[1]-1){ // Feld nach rechts oder links 
				if (!map.getField(posNext[0], posNext[1]).fieldBesetzt()){	
					map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
					map.getField(posNow[0], posNow[1]).setSpielfigur(null);
					return true;
			}
		}
	}
		// geht nach oben rechts als Stein wenn Stein schlagbar ist:
		if(posNext[0]==posNow[0]+2){		// buchstabe +2, da man ja eins überspringt
			if(posNext[1] == posNow[1]+2){	// zahl+2, da man ja eins überspringt
				if(map.getField(posNow[0]+1, posNow[1]+1).fieldBesetzt()){	// ist bestetzt?
					if(map.getField(posNow[0]+1, posNow[1]+1).getSpielfigur().getFarbenEnum() != player2.getFarbEnum()){ // Gegner ?
						if(!map.getField(posNext[0]+1, posNext[1]+1).fieldBesetzt()){	// test ist rechts drüber frei?
									map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
											map.getField(posNow[0], posNow[1]).setSpielfigur(null);		
					return true;
				} 
			}
		}
	}
		}
	// geht nach oben links als Stein wenn Stein schlagbar ist:
				else{ 
					if(map.getField(posNow[0]+1, posNow[1]-1).fieldBesetzt()){	// ist bestetzt?
					if(map.getField(posNow[0]+1, posNow[1]-1).getSpielfigur().getFarbenEnum()!= player2.getFarbEnum()){ // Gegner ?
					if(posNext[0] == posNext[0]+1 && posNext[1]==posNext[1]-1){ // test links eins weiter nach oben
						//	map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
						//	map.getField(posNow[0], posNow[1]).setSpielfigur(null);	 drüber noch auf besetzt testen
					return true;
				}
					}
				}
				}
		// geht nach unten(anderer PLAYER) rechts als Stein wenn Stein schlagbar ist:
		if(posNext[0]==posNow[0]-1){		// buchstabe +1
			if(posNext[1] == posNow[1]+1){	// zahl+1
				if(map.getField(posNow[0]-1, posNow[1]+1).fieldBesetzt()){	// ist bestetzt?
					if(map.getField(posNow[0]-1, posNow[1]+1).getSpielfigur().getFarbenEnum() != player2.getFarbEnum()){ // Gegner ?
						if(posNext[0] == posNext[0]-1 && posNext[1]==posNext[1]+1){	// test rechts eins weiter nach oben 
							//	map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
							//	map.getField(posNow[0], posNow[1]).setSpielfigur(null);	 drüber noch auf besetzt testen
					return true;
				} 
			}
		}
	}
		}
	// geht nach unten(anderer PLAYER) links als Stein wenn Stein schlagbar ist:
				else{ 
					if(map.getField(posNow[0]-1, posNow[1]-1).fieldBesetzt()){	// ist bestetzt?
					if(map.getField(posNow[0]-1, posNow[1]-1).getSpielfigur().getFarbenEnum()!= player2.getFarbEnum()){ // Gegner ?
					if(posNext[0] == posNext[0]-1 && posNext[1]==posNext[1]-1){ // test links eins weiter nach oben
						//	map.getField(posNext[0], posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).spielfigur);
						//	map.getField(posNow[0], posNow[1]).setSpielfigur(null);	 drüber noch auf besetzt testen
					return true;
				}
					}
				}
				}
				}
		return false;
	}
	

			
private void schlagen(int[] posNow, int[] posNext){
	if(map.getField(posNow[0], posNow[1]).getSpielfigur().getDame()){
		if (zielIstErreichbar(posNow,posNext)){
			for(int x=0; x< 11; x++){
			if(!map.getField(posNext[0]+x,posNext[1]+x).fieldBesetzt()){	
						map.getField(posNext[0]+x-1,posNext[1]+x-1).setSpielfigur(null);
						int j =24;
						while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
							j++;
						} TokenArray[j] =null;
					}
					map.getField(posNext[0]+x,posNext[1]+x).setSpielfigur(map.getField(posNow[0], posNow[1]).getSpielfigur()); // setzten spielfigur neu, auf neues feld nach schlagen
					map.getField(posNow[0],posNow[1]).setSpielfigur(null);
			}
		}
		if (zielIstErreichbar(posNow,posNext)){
			for(int t=0; t< 11; t++){
			if(!map.getField(posNext[0]+t,posNext[1]-t).fieldBesetzt()){	
				map.getField(posNext[0]+t-1,posNext[1]-t+1).setSpielfigur(null);
				int j =24;
				while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
					j++;
				} TokenArray[j] =null;
			}
			map.getField(posNext[0]+t,posNext[1]-t).setSpielfigur(map.getField(posNow[0], posNow[1]).getSpielfigur()); // setzten spielfigur neu, auf neues feld nach schlagen
			map.getField(posNow[0],posNow[1]).setSpielfigur(null);
	}
		}
		
		if (zielIstErreichbar(posNow,posNext)){
			for(int o=0; o<11;o++){
			if(!map.getField(posNext[0]-o,posNext[1]+o).fieldBesetzt()){	
				map.getField(posNext[0]-o+1,posNext[1]+o-1).setSpielfigur(null);
				int j =24;
				while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
					j++;
				} TokenArray[j] =null;
			}
			map.getField(posNext[0]-o,posNext[1]+o).setSpielfigur(map.getField(posNow[0], posNow[1]).getSpielfigur()); // setzten spielfigur neu, auf neues feld nach schlagen
			map.getField(posNow[0],posNow[1]).setSpielfigur(null);
	}
		}
		if (zielIstErreichbar(posNow,posNext)){
			for(int z=0; z<11;z++){
			if(!map.getField(posNext[0],posNext[1]).fieldBesetzt()){	
				map.getField(posNow[0]-z,posNow[1]-z).setSpielfigur(null);
				int j =24;
				while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
					j++;
				} TokenArray[j] =null;
			}
			map.getField(posNext[0]-z,posNext[1]-z).setSpielfigur(map.getField(posNow[0], posNow[1]).getSpielfigur()); // setzten spielfigur neu, auf neues feld nach schlagen
			map.getField(posNow[0],posNow[1]).setSpielfigur(null);
	}
	}
	} else {
		if(zielIstErreichbar(posNow,posNext)){
			if(!map.getField(posNext[0],posNext[1]).fieldBesetzt()){	
				map.getField(posNow[0]+1,posNow[1]+1).setSpielfigur(null);
				int j =24;
				while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
					j++;
				} TokenArray[j] =null;
			}
			map.getField(posNext[0],posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).getSpielfigur()); // setzten spielfigur neu, auf neues feld nach schlagen
			map.getField(posNow[0],posNow[1]).setSpielfigur(null);
	}
		if(!map.getField(posNext[0],posNext[1]).fieldBesetzt()){	
			map.getField(posNow[0]+1,posNow[1]-1).setSpielfigur(null);
			int j =24;
			while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
				j++;
			} TokenArray[j] =null;
		}
		map.getField(posNext[0],posNext[1]).setSpielfigur(map.getField(posNow[0], posNow[1]).getSpielfigur()); // setzten spielfigur neu, auf neues feld nach schlagen
		map.getField(posNow[0],posNow[1]).setSpielfigur(null);
}
	}

private void changeToDame(String ziel){
	int [] posZiel = map.idToIndex(ziel);
		if(map.getField(posZiel[0], posZiel[1]).getSpielfigur().getFarbenEnum() == FarbEnum.black){
			if (posZiel[0]==0){
				map.getField(posZiel[0], posZiel[1]).getSpielfigur().setDame(true);
			}
		}else {
			if(map.getField(posZiel[0], posZiel[1]).getSpielfigur().getFarbenEnum() == FarbEnum.white){
				if (posZiel[0]==11){
					map.getField(posZiel[0], posZiel[1]).getSpielfigur().setDame(true);
		}
	}
		}
}

	private void changePlayer() {
		if (player == player1) {
			player = player2;
		}
		else if (player == player2) {
			player = player1;
		}

	}
	
	/**
	 * method to test who is winner
	 */
	
	private boolean wins(){
		int spieler1=0;
		int spieler2=0;
	 
		for(int i=0; i<TokenArray.length/2; i++){
			if(TokenArray[i]==null){
				spieler1++;	
			}
		}
		for(int j=TokenArray.length/2; j<48; j++){
			if(TokenArray[j]==null){
				spieler2++;
			}
		}
		if(spieler1==24){
			System.out.println(player2.getSpielerName() + " has won!");
			return true;
		}
		else if (spieler2==24){
			System.out.println(player1.getSpielerName() + " has won!");
			return true;
		}
		else return false;
	}
	
	@Override
	public String reader() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = in.readLine().toLowerCase();
		} catch (IOException e) {
			System.out.println("Eingabe konnte nicht gelesen werde");
		}
		return input;
	}
	
	/**
	 * getter for map to use in KI
	 * @return return the map
	 */
	public Spielbrett getMap() {
		return map;
	}
	
	/**
	 * getter for the currentPlayer to use in KI
	 * @return returns the current player
	 */
	public Spieler getCurrentPlayer() {
		return player;
	}
		
}
