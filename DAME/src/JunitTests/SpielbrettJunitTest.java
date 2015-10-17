package JunitTests;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielbrett;
import ExtraKlassen.FarbEnum;
import ExtraKlassen.Testboard;



/**
 * Junit Testclass for the gaming board
 * @author The4ofHouseStark
 * @version 1.0
 *
 */
public class SpielbrettJunitTest {
	
	private Spielbrett board;
	private Testboard testBoard;
	
	/**
	 * creates a new board object before starting the Junit test
	 */
	
	@Before
	public void newBoard() {
		board = new Spielbrett();
	}
	
	/**
	 * creates a board how it should be during the game
	 */
	@Before
	public void testBoard() {
		testBoard = new Testboard();
	}

	/**
	 * Test to check if the board has valid Ids
	 */
	@Test
	public void rightFieldId() {
		Array[][] fieldId = board.getBoard();
		assertArrayEquals(fieldId, testBoard.testArray());
	}
	
	/**
	 * Test to check if the board has the right colours
	 */
	@Test
	public void rightFieldColour() {
		FarbEnum[][] fieldColour = board.getFieldColour();
		assertArrayEquals(fieldColour, testBoard.colourArray());
	}
	

}
