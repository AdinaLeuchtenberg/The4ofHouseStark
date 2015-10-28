package JunitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import SpielKlassen.Spiel;

/**
 * Junit Testclass for Spieler
 * @author The4ofHouseStark
 * @version 1.0
 *
 */

public class SpielJunitTest {
	
	private Spiel game;
	
	@Before
	public void newSpiel(){
	game = new Spiel();
	}
	
	@Test
	public void moveTest(String posJetzt, String posDann){
		if(posJetzt !=null && posDann != null){
			assertTrue(game.move(posJetzt, posDann));
		}
		
	}
	
	
}
