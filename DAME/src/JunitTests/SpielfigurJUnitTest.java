package JunitTests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielfigur;


public class SpielfigurJUnitTest {
	
	private Spielfigur spielfigur;
	
	@Before
	public void Spielfigur(){
		spielfigur = new Spielfigur();
	}
	/**
	 * creates new spielfigur
	 */
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
