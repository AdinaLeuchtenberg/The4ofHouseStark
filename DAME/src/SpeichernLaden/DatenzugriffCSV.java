package SpeichernLaden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import SpielKlassen.Spiel;

public class DatenzugriffCSV implements iDatenzugriff {

private Spiel game;
private BufferedReader reader;
	
	public DatenzugriffCSV(){
		game.getMap();
		game.getCurrentPlayer();
	}
	public DatenzugriffCSV(BufferedReader reader) throws IOException{
		try{
			String line = reader.readLine();
			String[] fields = line.split(";");
			// map = fields[0]; nameCurrentPlayer [1];
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
		pw.println(game.getCurrentPlayer().getSpielerName()+ ";" + game.getMap());
		pw.flush();
	}
	@Override 
	public String toString(){
		return game.getCurrentPlayer().getSpielerName() + " "+ game.getMap();
	}
}
