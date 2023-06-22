package palindrome;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class palindromeTests {
	
	@Test
	void Test1 () {	
		assertEquals(palindrome.findPalindromeRec("s"), "s");
	}
	
	@Test
	void Test2 () {	
		palindrome.findPalindromeRec("programming");
		//assertEquals(palindrome.findPalindromeRec("programming"), "gmmg");
	}
	
	@Test
	void Test3 () {	
		assertEquals(palindrome.findPalindromeRec("rare"), "rar");
	}
	
	@Test
	void Test4 () {	
		assertEquals(palindrome.findPalindromeRec("supercalifragilisticexpialidocious"), "suciaiitiiaicus");
	}
}
