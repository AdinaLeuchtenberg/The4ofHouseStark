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
		spielstein = new Spielfigur(null, farbenEnum, false);
	}
	
	/**
	 * 
	 * @param TokenArray Array of Tokens
	 * @param x Token in Array
	 * @param farbenEnum.black 
	 */
	@Test
	public void ColourOfPlayer(){
		boolean a = true;
		if(TestTokenArray[x] < (TestTokenArray.length/2)){
			farbenEnum = FarbEnum.white;
			a = true;
		} else a =false;
		assertTrue(a);
}
}

