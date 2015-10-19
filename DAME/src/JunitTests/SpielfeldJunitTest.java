package JunitTests;

import static org.junit.Assert.*;
/**
 * Junit Testclass for the player
 * @author The4ofHouseStark
 * @version 1.0
 *
 */

import Basisklassen.Spielfeld;
import ExtraKlassen.FarbEnum;
import org.junit.Before;
import org.junit.Test;

public class SpielfeldJunitTest {
	public Spielfeld spielfeld;
	Spielfeld A1;
	/**
	 * creates a new Spielfeld before starting the Junit test
	 */
	
	@Before
	public void Spielfelderzeugen(){;
	Spielfeld A2 = new Spielfeld(FarbEnum.black,"A2");
	this.A1=A2;
	}
	/**
	 * test to check if constructor works right for the color
	 */
	@Test
	public void test(){
		
		assertTrue(A1.getColor().equals(FarbEnum.black));
			
		
	}
	

}
