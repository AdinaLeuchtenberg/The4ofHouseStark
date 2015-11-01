package SpeichernLaden;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import SpielKlassen.Spiel;

public class SpielTestSpeichern {

	public static void main(String[] args) {
		/*ObjectOutputStream oos = null;
		try{
			DatenzugriffSerialisiert dzs = new DatenzugriffSerialisiert(null, null);
			oos = new ObjectOutputStream(new FileOutputStream("game.ser"));
			oos.writeObject(dzs);
			System.out.println(dzs);
		}
		catch(FileNotFoundException e){
			System.err.println("'game.ser' couldn't be generated");
		}
		catch(IOException e){
			System.err.println("Error during imput-/output: "+e);
		}
		finally{
			try{
				oos.close();
			}
			catch (Exception e){
				System.err.println("Error occured when trying to close the file ");
			}
		}*/
//CSV:
		PrintWriter pw = null;
		try{
			// Spiel game = new Spiel();
			DatenzugriffCSV dzCSV = new DatenzugriffCSV(null, null, null);
			dzCSV.writeToStream(new PrintWriter(System.out));
			pw = new PrintWriter(new FileWriter("dame.txt"));
			dzCSV.writeToStream(pw);
			//System.out.println(dzCSV.toString());

		}
		catch(FileNotFoundException e){
			System.err.println("'game.ser' couldn't be generated");
		}
		catch(IOException e){
			System.err.println("Error during imput-/output: "+e);
		}
		finally{
			if(pw!= null) pw.close();
		}
	}
}
