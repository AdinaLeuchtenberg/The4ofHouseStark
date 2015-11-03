package SpeichernLaden;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DatenzugriffCSV implements iDatenzugriff {


	@Override
	public void speichern(Object o) {
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(new FileWriter("dame.csv"));
			pw.write((String)o);

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
		pw.flush();
	}

	@Override
	public void laden() {
		// TODO Auto-generated method stub
		
	}

	
}
