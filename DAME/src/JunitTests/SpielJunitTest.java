package JunitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielbrett;
import SpielKlassen.Spiel;

/**
 * Junit Testclass for Spieler
 * @author The4ofHouseStark
 * @version 1.0
 *
 */

public class SpielJunitTest {
		private Spiel game;
		private Spielbrett map;
	@Before
	public void newGame(){	
	map = new Spielbrett();
	System.out.println(map.toString());
	}
	
	@Test
	public void Test(){
	
	
		boolean x= true;
	
		System.out.println(map.toString());
		if(map.getField(9,2).getSpielfigur()==null){
				x=false;
		}
	assertTrue(x);
}}