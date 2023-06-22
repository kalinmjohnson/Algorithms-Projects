package recursionLab;

/**
 * Template for recursion Lab Remember: NO LOOPING ALLOWED FOR THIS ASSIGNMENT
 * 
 * Calculate Binomial Coefficients
 * 
 * @author Nathan Gossett
 * @author Kalin Johnson
 * @version Spring 2022
 *
 */
public class RecursionLab {
	
	
	/**
	 * Implement this method for part 1 of the assignment 
	 * Plain recursive calculation of binomial coefficients
	 * 
	 * @param N
	 * @param k
	 * @return The number of ways to choose k items from a set of N items
	 * @throws IllegalArgumentException if k > N or k is negative
	 */
	public static long rec_C(int N, int k) throws IllegalArgumentException {
		//TODO delete the return statement below and implement this method
		if (k > N || k < 0) {
			throw new IllegalArgumentException("ERROR: k can't be great than N or less than 0.");
		}
		
		if (k == N || k == 0) { return 1; }
		
		return rec_C(N - 1, k - 1) + rec_C(N-1, k);
	}

	/**
	 * Implement this method for part 2 of the assignment 
	 * Recursive method to find decimal equivalent of binary number
	 * 
	 * @param binNumber An String of 0s and 1s representing a binary number
	 * @return The decimal equivalent of input string
	 * @throws IllegalArgumentException if input string is more than 31 bits or if
	 *                                  any 'bit' is something other than a '0' or
	 *                                  '1'
	 */
	public static int binToDec(String binNumber) throws IllegalArgumentException {
		//TODO delete the return statement below and implement this method
		if (binNumber.length() == 0) {
			return 0;
		} //base case
		
		int x = (int) binNumber.charAt(binNumber.length() - 1) - 48;
		
		if (binNumber.length() > 31) {
			throw new IllegalArgumentException("There can't be more than 31 bits.");
		}
		
		if (x == 0 || x == 1) {
			return x + (2 * binToDec(binNumber.substring(0, binNumber.length() - 1)));
		} else {
			throw new IllegalArgumentException("The bits must be either 0 or 1.");
		}
	}



}
