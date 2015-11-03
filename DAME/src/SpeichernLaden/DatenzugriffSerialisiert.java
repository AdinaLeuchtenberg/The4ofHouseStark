package SpeichernLaden;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ExtraKlassen.FarbEnum;
import SpielKlassen.Spiel;

public class DatenzugriffSerialisiert implements iDatenzugriff,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void speichern(Object o) {
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(new FileOutputStream("game.ser"));
			oos.writeObject(o);
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
		}
		
	}

	@Override
	public void laden() {
		// TODO Auto-generated method stub
		
	}
}
