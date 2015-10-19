package JunitTests;

import static org.junit.Assert.*;
import Basisklassen.Spielfeld;
import ExtraKlassen.FarbEnum;
import org.junit.Before;
import org.junit.Test;

public class SpielfeldJunitTest {
	protected Spielfeld spielfeld;
	Spielfeld A1 = new Spielfeld(FarbEnum.black,"A1");
	
	@Test
	public void test(){
		
		assertTrue(A1.getColor().equals(FarbEnum.black));
			
		
	}
	

}
