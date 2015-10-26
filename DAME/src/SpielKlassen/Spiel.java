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

