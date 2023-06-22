package recursionLab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * A minimal collection of JUnit tests that your recursion Lab submission
 * should be able to pass.  Add additional tests as you spot weak points in
 * your own solution
 * @author gosnat
 * @version Fall 2020
 *
 */

public class TestRecursionLab {
	
	@Test
	public void testBinToDecLegal() {
		assertEquals(0, RecursionLab.binToDec("0"));
		assertEquals(1, RecursionLab.binToDec("1"));
		assertEquals(2, RecursionLab.binToDec("10"));
		assertEquals(3, RecursionLab.binToDec("11"));
		assertEquals(4, RecursionLab.binToDec("100"));
		assertEquals(42, RecursionLab.binToDec("101010"));
		assertEquals(1073741824, RecursionLab.binToDec("1000000000000000000000000000000"));
		assertEquals(2147483647, RecursionLab.binToDec("1111111111111111111111111111111"));
	}
	
	@Test
	public void testBinToDecIllegal() {
		//32 bits
		assertThrows(IllegalArgumentException.class, 
				() -> RecursionLab.binToDec("11111111111111111111111111111111"));
		//illegal character
		assertThrows(IllegalArgumentException.class, 
				() -> RecursionLab.binToDec("111111111111111111111O111111111"));
	}
	
	@Test
	public void testBinomialCoefficients() {
		assertEquals(10, RecursionLab.rec_C(5, 2));
		assertEquals(1, RecursionLab.rec_C(5, 0));
		assertEquals(1, RecursionLab.rec_C(5, 5));
		assertEquals(999, RecursionLab.rec_C(999, 1));
		assertEquals(999, RecursionLab.rec_C(999, 998));
		assertEquals(13037895, RecursionLab.rec_C(27, 11));

		//if your code completes the following test... you probably 
		//went with something other than the clean recursive solution
		//assertEquals(73006209045L, RecursionLab.rec_C(45, 32));
	}
	
	@Test
	public void testBinomialCoefficientsIllegal() {
		// k > N
		assertThrows(IllegalArgumentException.class, 
				() -> RecursionLab.rec_C(5, 6));
		
		// k < 0
		assertThrows(IllegalArgumentException.class, 
				() -> RecursionLab.rec_C(5, -1));
		
		
		
	}
	

}
