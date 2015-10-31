package SpeichernLaden;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import SpielKlassen.Spiel;

public class SpielTestLaden {

	public static void main(String[] args) throws ClassNotFoundException {
		ObjectInputStream ois = null;
		try{
			ois = new ObjectInputStream(new FileInputStream("game.ser"));
			Spiel game = (Spiel) ois.readObject();
			System.out.println(game);
		}
		catch(FileNotFoundException e){
			System.err.println("'game.ser' couldn't be generated");
		}
		catch(IOException e){
			System.err.println("Error during imput-/output: "+e);
		}
		finally{
			try{
				ois.close();
			}
			catch (Exception e){
				System.err.println("Error occured when trying to close the file ");
			}
	}
// CSV:
		BufferedReader reader =null;
		try{
			reader = new BufferedReader(new FileReader("dame.txt"));
			DatenzugriffCSV dzCSV = new DatenzugriffCSV (reader);
			System.out.println(dzCSV);
		}
		catch(FileNotFoundException e){
			System.err.println("'game.ser' couldn't be generated");
		}
		catch(IOException e){
			System.err.println("Error during imput-/output: "+e);
		}
		finally{
			try{
				reader.close();
			}
			catch(Exception e){
				System.err.println("Error occured when trying to close the file ");
			}
		}
	}

}
