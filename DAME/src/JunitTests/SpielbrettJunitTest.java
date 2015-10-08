package JunitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielbrett;


/**
 * Junit Testclass for the gaming board
 * @author AdinaLeuchtenberg
 * @version 1.0
 *
 */
public class SpielbrettJunitTest {
	
	private Spielbrett board;
	
	/**
	 * create a new board object before starting the Junit test
	 */
	
	@Before
	public void newBoard() {
		board = new Spielbrett();
	}

	@Test
	public void test() {
		
		fail("Not yet implemented");
	}

}
