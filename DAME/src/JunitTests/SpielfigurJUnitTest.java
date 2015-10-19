package JunitTests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielfigur;
import ExtraKlassen.FarbEnum;
/**
 * @author The4ofHouseStark
 * @version 1.0
 */

public class SpielfigurJUnitTest {
	
	private Spielfigur spielfigur;
	
	/**
	 * creates new spielfigur
	 * @param farbenEnum 
	 */
	
	@Before
	public void Spielfigur(){
		spielfigur = new Spielfigur();
	}
	
	/**
	 * 
	 * @param TokenArray Array of Tokens
	 * @param x Token in Array
	 * @param farbenEnum colour of Tokens
	 * @return Test passed if colour white, Test failed ("false") if colour black
	 */
	@Test
	public boolean ColourOfPlayer(int[] TokenArray, int x, FarbEnum farbenEnum) {
		if (TokenArray[x]< (TokenArray.length/2)){
			farbenEnum = FarbEnum.white;
			return true;
			} else return false;
	}

}
