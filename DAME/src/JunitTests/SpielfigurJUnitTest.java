package JunitTests;
import static org.junit.Assert.*;

import org.junit.Assert;
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
	private int [] TestTokenArray;
	private int x;
	
	@Before
	public void newSpielfigur(){
		spielstein = new Spielstein();
	}
	
	/**
	 * 
	 * @param TokenArray Array of Tokens
	 * @param x Token in Array
	 * @param farbenEnum.black 
	 * @return Test passed if colour white, Test failed ("false") if colour black
	 */
	@Test
	public boolean ColourOfPlayer(){
		/*if(TokenArray[i] < (TokenArray.length/2)){
			farbenEnum = FarbEnum.white;
			return true;
		} else return false;
		 */	
		assertEquals(spielstein.colours(TestTokenArray, x, farbenEnum.black));
}
}

