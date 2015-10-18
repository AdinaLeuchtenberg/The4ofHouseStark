package JunitTests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielfigur;
import ExtraKlassen.FarbEnum;


public class SpielfigurJUnitTest {
	
	private Spielfigur spielfigur;
	
	@Before
	public void Spielfigur(){
		spielfigur = new Spielfigur();
	}
	/**
	 * creates new spielfigur
	 * @param farbenEnum 
	 */
	
	@Test
	public boolean ColourOfPlayer(int[] playerArray, int x, FarbEnum farbenEnum) {
		if (playerArray[x]< (playerArray.length/2)){
			farbenEnum = FarbEnum.white;
			return true;
			} else return false;
	}

}
