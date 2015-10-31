package SpeichernLaden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import SpielKlassen.Spiel;

public class DatenzugriffCSV implements iDatenzugriff {

private Spiel game;
private String map = game.toString();
private String name = game.getCurrentPlayer().getSpielerName();
private BufferedReader reader;

	public DatenzugriffCSV(BufferedReader reader,String name, String map) throws IOException{
		this.map = map;
		this.name = name;
		try{
			String line = reader.readLine();
			String[] fields = line.split("|");
			name = fields[0];
			map = fields[1]; 
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
