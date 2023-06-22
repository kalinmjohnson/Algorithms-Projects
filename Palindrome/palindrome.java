package palindrome;


/**The class contains to methods that both find palindromes in different ways
 * 
 * @author kalinjohnson
 * @version Fall 2022
 */
public class palindrome {
	
	/** Finds the palindrome recursively
	 * 
	 * @param s
	 * @return the largest palindrome
	 */
	public static String findPalindromeRec(String s) {
		if (s.length() == 0 || s.length() == 1) {
			return s;
		}
		
		if (s.charAt(0) == s.charAt(s.length()-1)) {
			String result = findPalindromeRec(s.subSequence(1, (s.length()-1)).toString());
			String fl = Character.toString(s.charAt(0));
			result = fl + result + fl;
			return result;
		} else {
			String result1 = findPalindromeRec(s.subSequence(0, (s.length()-1)).toString());
			String result2 = findPalindromeRec(s.subSequence(1, (s.length())).toString());
			
			if (result1.length() > result2.length()) {
				return result1;
			} else {
				return result2;
			}
		}
	}
	
	/** Finds the palindrome dynamically
	 * 
	 * @param s
	 * @return the largest palindrome
	 */
	
	public static String findPalindromeDyn(String s) {
		
		String[][] table = new String[s.length()][s.length()];
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				table[i][j] = "";
			}
		}
		
		
		for (int i = 0; i < s.length(); i++) {
			table[i][i] = Character.toString(s.charAt(i)); // maybe i+1
		}
		
		
		
		for (int i = 1; i < s.length(); i++) {
			for (int j = s.length(); j >= 0; j--) {
				if (j < i) {
					
					if (s.charAt(i) == s.charAt(j)) {
						String fl = Character.toString(s.charAt(i));
						table[i][j] = (fl + table[i-1][j+1] + fl);
					} else {
						if (table[i-1][j].length() > table[i][j+1].length()) {
							table[i][j] = table[i-1][j];
						} else {
							table[i][j] = table[i][j+1];
						}
					}
					
				}
			}
		}
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				System.out.print(table[i][j]);
			}
			System.out.println("");
		}
		
		return "here";//table[s.length()-1][0];
	}
}
