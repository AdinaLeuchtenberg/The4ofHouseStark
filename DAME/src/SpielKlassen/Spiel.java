package SpielKlassen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
			player1 = new Spieler(name1, FarbEnum.white);
			
			System.out.println("Please enter the name of the second player:");
			String name2 = reader();
			if(name2 == null) {
				System.err.println("please enter a name");
				name2 = reader();
			}
			player2 = new Spieler(name2, FarbEnum.black);
			System.out.println(player1.getSpielerName() + " is player one and has colour " + player1.getFarbEnum());
			System.out.println(player2.getSpielerName() + " is player two and has colour " + player2.getFarbEnum());
			
		break;
		case "start":
			if(!started) {
				player = player1;
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
		throw new RuntimeException("Zug nicht durchführbar, muss pusten");
	}
	boolean istDame = map.getField(posNow[0], posNow[1]).getSpielfigur().getDame();
	if(zielIstGueltig(posNext)){
		if(zielIstErreichbar(posNow, posNext)){
			schlagen(posNow, posNext);
		}
		changeToDame(ziel);
	}
}

private boolean andererMussPusten(int[] tokenKoords){
	Spielfigur token = map.getField(tokenKoords[0], tokenKoords[1]).getSpielfigur();
	ArrayList<Spielfigur> bullies = map.getFigurenDieSchlagenKoennen(); //das müsste natürlich implementiert werden :D
	for(Spielfigur f : bullies){
		if(token.equals(f)){
			return false;
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
				}
			}
		}
	}
	return false;
}

	private boolean zielIstErreichbar(int[] posNow, int[] posNext){
	if(token.getDame()){
		if(posNext[0]==posNow[0]+1 || posNext[0]==posNow[0]-1){	// nächste Felder sind erreichbar
			if(posNext[1]==posNow[1]+1 || posNext[1]== posNow[1]-1){
					return true;
				}  
		}
			// geht nach oben rechts als Dame wenn schlagbarer Stein im Weg ist:
			for(int h=1; h<10;h++){		
			if(posNext[0]==posNow[0]+h){		// buchstabe +1
						if(posNext[1] == posNow[1]+h){	// zahl+1
							if(map.getField(posNow[0]+h, posNow[1]+h).fieldBesetzt()){	// ist bestetzt?
								if(map.getField(posNow[0]+h, posNow[1]+h).getSpielfigur().getFarbenEnum() == FarbEnum.black){ // Gegner ?
									if(!map.getField(posNow[0]+h+1, posNow[1]+h+1).fieldBesetzt()){
									if(posNext[0] == posNext[0]+h+1 && posNext[1]==posNext[1]+h+1){	// test rechts eins weiter nach oben 
								return true;
							} 
						}
					}
				}
			}
		}
			}				
							
						 for(int i=1; i<10;i++){
								if(posNext[0]==posNow[0]+i){		// buchstabe +1
								if(posNext[1] == posNow[1]-i){	// zahl-1
								if(map.getField(posNow[0]+i, posNow[1]-i).fieldBesetzt()){	// ist bestetzt?
								if(map.getField(posNow[0]+i, posNow[1]-i).getSpielfigur().getFarbenEnum() == FarbEnum.black){ // Gegner ?
									if(!map.getField(posNow[0]+i+1, posNow[1]+i-1).fieldBesetzt()){
									if(posNext[0] == posNext[0]+i && posNext[1]==posNext[1]-i){ // test links eins weiter nach oben
								return true;
							}
						}
					}
				}
			}
		}
	}
							
				// geht nach unten als Dame wenn Stein schlagbar ist:
				for(int j=1; j<10;j++){
				if(posNext[0]==posNow[0]-j){ // buchstabe -1
						if(posNext[1] == posNow[1]+j){	// zahl+1
							if(map.getField(posNow[0]-j, posNow[1]+j).fieldBesetzt()){	// ist bestetzt?
								if(map.getField(posNow[0]-j, posNow[1]+j).getSpielfigur().getFarbenEnum() == FarbEnum.black){ // Gegner ?
									if(!map.getField(posNow[0]+j-1, posNow[1]+j+1).fieldBesetzt()){
									if(posNext[0] == posNext[0]-j && posNext[1]==posNext[1]+j){	// test rechts eins weiter nach unten 
								return true;
									} 
								}
							}
						}
					}
				}
			}
						for(int k=1; k<10;k++){
							if(posNext[0]==posNow[0]-k){ // buchstabe -1
								if(posNext[1] == posNow[1]-k){	// zahl-1
									if(map.getField(posNow[0]-k, posNow[1]-k).fieldBesetzt()){	// ist bestetzt?
										if(map.getField(posNow[0]-1, posNow[1]-k).getSpielfigur().getFarbenEnum() == FarbEnum.black){ // Gegner ?
											if(!map.getField(posNow[0]+k-1, posNow[1]+k-1).fieldBesetzt()){
												if(posNext[0] == posNext[0]-k && posNext[1]==posNext[1]-k){	// test links eins weiter nach unten
													return true;
												}
											}
										}
									}
								}	
							}
						}
					}
		if(posNext[0]== posNow[0]+1){ // ein feld weiter (nach oben) // buchstaben nach oben 
			if(posNext[1]==posNow[1]+1 || posNext[1]== posNow[1]-1){ // Feld nach rechts oder links 
				return true;
			}
		}
	
		// geht nach oben als Stein wenn Stein schlagbar ist:
		if(posNext[0]==posNow[0]+1){		// buchstabe +1
			if(posNext[1] == posNow[1]+1){	// zahl+1
				if(map.getField(posNow[0]+1, posNow[1]+1).fieldBesetzt()){	// ist bestetzt?
					if(map.getField(posNow[0]+1, posNow[1]+1).getSpielfigur().getFarbenEnum() == FarbEnum.black){ // Gegner ?
						if(posNext[0] == posNext[0]+1 && posNext[1]==posNext[1]+1){	// test rechts eins weiter nach oben 
					return true;
				} 
			}
		}
	}
}
				else{ if(map.getField(posNow[0]+1, posNow[1]-1).fieldBesetzt()){	// ist bestetzt?
					if(map.getField(posNow[0]+1, posNow[1]-1).getSpielfigur().getFarbenEnum() == FarbEnum.black){ // Gegner ?
					if(posNext[0] == posNext[0]+1 && posNext[1]==posNext[1]-1){ // test links eins weiter nach oben
					return true;
				}
					}
				}
				}
		return false;
	}
	

			
private void schlagen(int[] posNow, int[] posNext){
	if(token.getDame()){
		if (zielIstErreichbar(posNext,posNext)){
			if(!map.getField(posNext[0]+1,posNext[1]+1).fieldBesetzt()){	
						map.getField(posNext[0],posNext[1]).setSpielfigur(null);
						int j =24;
						while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
							j++;
						} TokenArray[j] =null;
					}
					map.getField(posNext[0]+1,posNext[1]+1).setSpielfigur(token); // setzten spielfigur neu, auf neues feld nach schlagen
					map.getField(posNow[0],posNow[1]).setSpielfigur(null);
			}
		
		if (zielIstErreichbar(posNext,posNext)){
			if(!map.getField(posNext[0]+1,posNext[1]-1).fieldBesetzt()){	
				map.getField(posNext[0],posNext[1]).setSpielfigur(null);
				int j =24;
				while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
					j++;
				} TokenArray[j] =null;
			}
			map.getField(posNext[0]+1,posNext[1]-1).setSpielfigur(token); // setzten spielfigur neu, auf neues feld nach schlagen
			map.getField(posNow[0],posNow[1]).setSpielfigur(null);
	}
	if(token.getDame()){
		if (zielIstErreichbar(posNext,posNext)){
			if(!map.getField(posNext[0]-1,posNext[1]+1).fieldBesetzt()){	
				map.getField(posNext[0],posNext[1]).setSpielfigur(null);
				int j =24;
				while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
					j++;
				} TokenArray[j] =null;
			}
			map.getField(posNext[0]-1,posNext[1]+1).setSpielfigur(token); // setzten spielfigur neu, auf neues feld nach schlagen
			map.getField(posNow[0],posNow[1]).setSpielfigur(null);
	}
		if (zielIstErreichbar(posNext,posNext)){
			if(!map.getField(posNext[0]-1,posNext[1]-1).fieldBesetzt()){	
				map.getField(posNext[0],posNext[1]).setSpielfigur(null);
				int j =24;
				while(TokenArray[j]== null){		//löscht den Token aus den FarbTokenArray
					j++;
				} TokenArray[j] =null;
			}
			map.getField(posNext[0]-1,posNext[1]-1).setSpielfigur(token); // setzten spielfigur neu, auf neues feld nach schlagen
			map.getField(posNow[0],posNow[1]).setSpielfigur(null);
	}
	}
	}		
	//überprüfe, ob zwischen den positionen gegner steht
	//entferne Gegner von Feld
	//reduziere Anzahl Token von Gegner
	//wenn kein Gegner da war, tue einfach nichts

private void changeToDame(String ziel){
	int [] posZiel = map.idToIndex(ziel);
		if(token.getFarbenEnum() == FarbEnum.black){
			if (posZiel[0]==0){
				token.setDame(true);
			}
		}else {
			if(token.getFarbenEnum() == FarbEnum.white){
				if (posZiel[0]==11){
					token.setDame(true);
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
			System.out.println(player2.getSpielerName() + "has won!");
			return true;
		}
		else if (spieler2==24){
			System.out.println(player1.getSpielerName() + "has won!");
			return true;
		}
		else return false;
	}
	
	private FarbEnum chosenPlayerColor(FarbEnum color, Spieler player){
		if (color == color.black){
			 player = new Spieler(player.getSpielerName(), FarbEnum.black);
		} 
		if(color == color.white){
			player = new Spieler(player.getSpielerName(), FarbEnum.white);
		}
		return color;
		
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
		
}
