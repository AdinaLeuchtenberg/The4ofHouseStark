package JunitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielbrett;
import Basisklassen.Spielfigur;
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
	game= new Spiel();
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
}
	@Test
	public void test(){
		boolean wow= false;
	Spielfigur testfigur = map.getField(4, 1).spielfigur;
	game.move("i1","h2");
	if(map.getField(5, 2).spielfigur == testfigur){
		wow=true;
	}
	assertTrue(wow);
	}
	@Test
	public void test1(){
	boolean hurra=false;
	game.move("i1", "h2");
	game.move("d6", "e5");
	game.move("h2", "g3");
	game.move("e5", "f4");
	game.move("g3", "e5");
	if(map.getField(7,4).getSpielfigur()==null){
		hurra=true;
	}assertTrue(hurra);
	}
}