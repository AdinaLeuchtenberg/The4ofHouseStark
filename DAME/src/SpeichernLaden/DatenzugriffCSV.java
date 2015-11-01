package SpeichernLaden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import ExtraKlassen.FarbEnum;
import SpielKlassen.Spiel;

public class DatenzugriffCSV implements iDatenzugriff {

private Spiel game;
private String map = game.getMap().toString();
private String name = game.getCurrentPlayer().getSpielerName();
private FarbEnum color = game.getCurrentPlayer().getFarbEnum();
private BufferedReader reader;

	public DatenzugriffCSV(BufferedReader reader,String name, String map) throws IOException{
		this.map = map;
		this.name = name;
		try{
			//String line = reader.readLine();	// list ganze reihe bis n/
			String[] elements = new String [3];
			elements = map.split("|"); // splitted die werte an der stelle "|"
			name = elements[0];
			if(color == FarbEnum.black){
				String black = elements[1];
			} else {
				String white = elements[1];
			}
			map = elements[2]; 
		}
		catch (NullPointerException e){
			throw new IOException("Unexpected end of file");
		}
		catch(NumberFormatException e){
			throw new IOException("False element format");
		}
		catch(IndexOutOfBoundsException e){
			throw new IOException("To few file elements");
		}
	}
	public void writeToStream(PrintWriter pw){
		pw.println(name+ ";" + map);
		pw.flush();
	}
}
