package JunitTests;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import org.junit.Before;
import org.junit.Test;

import Basisklassen.Spielbrett;
import Basisklassen.Spielfeld;


/**
 * Junit Testclass for the gaming board
 * @author The4ofHouseStark
 * @version 1.0
 *
 */
public class SpielbrettJunitTest {
	
	private Spielbrett board;
	private Array[][] testBoard;
	
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
		testBoard = new Array[12][12];
		//toDo richtige ids übergeben

	}

	/**
	 * Test to check if the board has valid Ids
	 */
	@Test
	public void rightFieldId() {
		Array[][] fieldId = board.getBoard();
		
		assertArrayEquals(fieldId, testBoard);
		
	}

}
