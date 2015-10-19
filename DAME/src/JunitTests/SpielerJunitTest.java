package JunitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import ExtraKlassen.FarbEnum;

/**
 * Junit Testclass for the player
 * @author The4ofHouseStark
 * @version 1.0
 *
 */

public class SpielerJunitTest {
	
	private Spieler player;
	private ArrayList AnzPlayer = new ArrayList();
	private FarbEnum farbEnum;
	
	/**
	 * creates a new player before starting the Junit test
	 */
	
	@Before
	public void testSpieler(){
		player = new Spieler();
	}
	
	/**
	 * test to check if Player2 at index 1 is black
	 */
	
	@Test
	public boolean PlayerColour() {
		if( AnzPlayer.add(1)){
  		farbEnum = farbEnum.black;
  		return true;
		} else return false;
		
	}

}