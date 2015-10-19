package JunitTests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielbrett;
import Basisklassen.Spielfigur;
import ExtraKlassen.FarbEnum;
import SpielKlassen.Spielstein;
/**
 * @author The4ofHouseStark
 * @version 1.0
 */

public class SpielfigurJUnitTest {
	
	private Spielfigur spielstein;
	private FarbEnum farbenEnum;
	
	@Before
	public void newSpielfigur(){
		spielstein = new Spielstein();
	}
	
	/**
	 * 
	 * @param TokenArray Array of Tokens
	 * @param x Token in Array
	 * @return Test passed if colour white, Test failed ("false") if colour black
	 */
	@Test
	public void ColourOfPlayer(int[] TokenArray, int x) {
		if (TokenArray[x]< (TokenArray.length/2)){
			assertTrue(FarbEnum.white);
		}
	}

}
