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
			System.out.println(player1.getSpielerName() + "is player one" + "and has colour" + player1.getFarbEnum());
			System.out.println(player2.getSpielerName() + "is player two" + "and has colour" + player2.getFarbEnum());
			
		break;
		case "start":
			if(!started) {
				player = player1;
				started = true;
			} else {
				System.out.println("game has already started");
			}
		break;
		
		case "startTurn":
			System.out.println("enter the coordinate of the token you want to move + coordinate of the field you want to move to; e.g.: b3 c4");
			String coordinates = reader();
			String nowCoordinate = coordinates.substring(0, 2);
			String nextCoordinate = coordinates.substring(3);
			move(nowCoordinate, nextCoordinate);
			boolean wins = wins();
			if(wins) {
				started = false;
				System.out.println("Enter 'start' if you want to play another game or 'exit' if you want to end");
				readIn = reader();
				if(readIn == "exit") {
					System.out.println("Thank you for playing");
					System.exit(0);
				} else if(readIn == "start") {
						gameLoop();
				} else {
					System.err.println("invalid input");
				}
			} else {
				System.out.println(map.toString());
			}
		break;
			
		case "endturn":
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
	instructions += "startTurn - to start you turn \n ";
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

	if(zielIstGueltig(posNow) && andererMussPusten(posNow)){
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
			// geht nach oben als Dame wenn Stein schlagbar ist:
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
				// geht nach unten als Dame wenn Stein schlagbar ist:
					if(posNext[0]==posNow[0]-1){ // buchstabe -1
						if(posNext[1] == posNow[1]+1){	// zahl+1
							if(map.getField(posNow[0]-1, posNow[1]+1).fieldBesetzt()){	// ist bestetzt?
								if(map.getField(posNow[0]-1, posNow[1]+1).getSpielfigur().getFarbenEnum() == FarbEnum.black){ // Gegner ?
							if(posNext[0] == posNext[0]-1 && posNext[1]==posNext[1]+1){	// test rechts eins weiter nach unten 
								return true;
							} 
								}
							}
							else{ if(map.getField(posNow[0]-1, posNow[1]-1).fieldBesetzt()){	// ist bestetzt?
								if(map.getField(posNow[0]-1, posNow[1]-1).getSpielfigur().getFarbenEnum() == FarbEnum.black){ // Gegner ?
								if(posNext[0] == posNext[0]-1 && posNext[1]==posNext[1]-1){	// test links eins weiter nach unten
								return true;
								}
							}
							}
							}
						}
				}
	}else{
		if(posNext[0]== posNow[0]+1){ // ein feld weiter (nach oben) // buchstaben nach oben 
			if(posNext[1]==posNow[1]+1 || posNext[1]== posNow[1]-1){ // Feld nach rechts oder links 
				return true;
			}
		}
	}
		// geht nach oben als Stein wenn Stein schlagbar ist:
			if(posNext[0]==posNow[0]+1){		// buchstabe +1
				if(posNext[1] == posNow[1]+1){	// zahl+1
					if(posNext[0] == posNext[0]+1 && posNext[1]==posNext[1]+1){	// test rechts eins weiter nach oben 
						return true;
					} 
				}
			} else{ if(posNext[0] == posNext[0]+1 && posNext[1]==posNext[1]-1){ // test links eins weiter nach oben
						return true;
					}
		}
		return false;
	}

			
private void schlagen(int[] posNow, int[] posNext){
	//if(token.getDame()){
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
			if (posZiel[0]==11){
			token.setDame(true);
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
			if(TokenArray[i]!=null){
				spieler1++;	
			}
		}
		for(int j=TokenArray.length/2; j<48; j++){
			if(TokenArray[j]!=null){
				spieler2++;
			}
		}
		if(spieler1==0){
			System.out.println(player2.getSpielerName() + "has won!");
			return true;
		}
		else if (spieler2==0){
			System.out.println(player1.getSpielerName() + "has won!");
			return true;
		}
		else return false;
	}
	
	/**
	 * method to save the tokens which have to move to beat the other player
	 * @return array of token which have to move to beat the other player
	 */
	private ArrayList<Spielfigur> getFigurenDieSchlagenKoennen() {
		ArrayList<Spielfigur> bullies = new ArrayList<Spielfigur>();
		
		//ist feld besetzt?
		if(map.getField(posNext[0], posNext[1]).istBesetzt()) {
			//besetztes feld farbe des gegners?
			if(map.getField(posNext[0], posNext[1]).getSpielfigur.getColor() != player.getFarbEnum()) {
				bullies.add(map.getField(posNow[0], posNow[1]).getSpielfigur);
				//TODO 
			}
		}
		
		return bullies;
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
