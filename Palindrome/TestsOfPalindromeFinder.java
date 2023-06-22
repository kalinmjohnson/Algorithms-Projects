package palindrome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Some basic sanity checks of the Palindrome problem
 * @author Nathan Gossett
 * @version Spring 2019
 *
 */
class TestsOfPalindromeFinder {
	
	/**
	 * Check to see if the input is a palindrome.  
	 * Does not ignore spaces or punctuation
	 * @param s the string to check
	 * @return true if s is a palindrome, false if not
	 */
	public static boolean isPalindrome(String s){
		if(s.length() == 0) return true;
		s = s.toLowerCase();
		for(int i = 0; i <= s.length()/2; i++){
			if(s.charAt(i) != s.charAt(s.length()-i-1)){
				return false;
			}
		}
		return true;
	}

	@Test
	void testLegalInputsRec() {
		String answer = palindrome.findPalindromeRec("programming");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 4);
		
		answer = palindrome.findPalindromeRec("expialidociouse");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 5);
		
		//will take a few seconds
		answer = palindrome.findPalindromeRec("supercalifragilisticexpialidocious");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 15);
	}
	
	@Test //uncomment @Test if you want to run this one.  Should take too long for recursive
	public void testLongRec(){
		
		//This one should take several seconds (up to 25 seconds before you kill it)
		String answer = palindrome.findPalindromeRec("loremipsumdolorsitametconsectetu");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 13);
	}
	
	@Test
	public void testLegalInputsDyn() {
		String answer = palindrome.findPalindromeDyn("rare");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 3);
		
		answer = palindrome.findPalindromeDyn("supercalifragilisticexpialidocious");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 15);
		
		answer = palindrome.findPalindromeDyn("supercalifragilistsupercalifragilisticexpialidociousicexpialidocious");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 29);
		
	}
	
	@Test
	public void testLongDyn(){
		
		//This one should go almost instantly and still get the right answer
		String answer = palindrome.findPalindromeDyn("loremipsumdolorsitametconsecteturadipisicingelitseddoeiusmodtemporincididuntutlaboreetdoloremagnaaliquautenimadminimveniamquisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsequatduisauteiruredolorinreprehenderitinvoluptatevelitessecillumdoloreeufugiatnullapariaturexcepteursintoccaecatcupidatatnonproidentsuntinculpaquiofficiadeseruntmollitanimidestlaborum");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 154);
	}

}
