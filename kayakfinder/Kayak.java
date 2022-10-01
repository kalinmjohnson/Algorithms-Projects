package kayakfinder;

/**
 * A Class that contains a method that finds the word "kayak" in a 2D array if
 * possible
 * 
 * @author Kalin Johnson
 * @version Fall 2022
 *
 */

public class Kayak {

	/**
	 * A method that finds the word "kayak"
	 * 
	 * @param a 2D array of characters
	 * @return a boolean (true or false) that states whether "kayak" was found
	 */
	public static boolean containsKayak(char[][] data) {

		/**
		 * A boolean attribute used after an if statement to keep track of whether the
		 * word "kayak" has been found.
		 */
		boolean kayakAppears = false;

		/**
		 * an integer, n, that holds the number of rows in the 2D array
		 */
		int n = data.length;

		/**
		 * an integer, m, that holds the number of columns in the 2D array
		 */
		int m = data[0].length;

		// loops through all elements in one dimension of the array
		for (int i = 0; i < n; i++) {

			// loops through all elements in the other dimension of the array
			for (int j = 0; j < m; j++) {

				// gets rid of all the cases where 'k' isn't the first letter
				if (data[i][j] == 'K') {

					// The outer if statements check whether the word "kayak" is possible.
					// It checks both directions from the current i and j values depending on which
					// direction is being checked in the inner if statement.

					// There are 8 directions that the word kayak could be in from the starting
					// letter 'k'

					// The inner if statements will check each direction for the correct letters
					// separately by changing the values of i and j

					// Checking for kayaks in the i direction

					if (n - i >= 5) {
						if (data[i + 1][j] == 'A' && data[i + 2][j] == 'Y' && data[i + 3][j] == 'A'
								&& data[i + 4][j] == 'K') {
							kayakAppears = true;
						}
					}

					if (i >= 5) {
						if (data[i - 1][j] == 'A' && data[i - 2][j] == 'Y' && data[i - 3][j] == 'A'
								&& data[i - 4][j] == 'K') {
							kayakAppears = true;
						}
					}

					// Checking for kayaks in the j direction
					if (n - j >= 5) {
						if (data[i][j + 1] == 'A' && data[i][j + 2] == 'Y' && data[i][j + 3] == 'A'
								&& data[i][j + 4] == 'K') {
							kayakAppears = true;
						}
					}

					if (j >= 5) {
						if (data[i][j - 1] == 'A' && data[i][j - 2] == 'Y' && data[i][j - 3] == 'A'
								&& data[i][j - 4] == 'K') {
							kayakAppears = true;
						}
					}

					// Checking for diagonal kayaks
					if (n - i >= 5 && n - j >= 5) {
						if (data[i + 1][j + 1] == 'A' && data[i + 2][j + 2] == 'Y' && data[i + 3][j + 3] == 'A'
								&& data[i + 4][j + 4] == 'K') {
							kayakAppears = true;
						}
					}

					if (n - i >= 5 && j >= 5) {
						if (data[i + 1][j - 1] == 'A' && data[i + 2][j - 2] == 'Y' && data[i + 3][j - 3] == 'A'
								&& data[i + 4][j - 4] == 'K') {
							kayakAppears = true;
						}
					}

					if (i >= 5 && n - j >= 5) {
						if (data[i - 1][j + 1] == 'A' && data[i - 2][j + 2] == 'Y' && data[i - 3][j + 3] == 'A'
								&& data[i - 4][j + 4] == 'K') {
							kayakAppears = true;
						}
					}

					if (i >= 5 && j >= 5) {
						if (data[i - 1][j - 1] == 'A' && data[i - 2][j - 2] == 'Y' && data[i - 3][j - 3] == 'A'
								&& data[i - 4][j - 4] == 'K') {
							kayakAppears = true;
						}
					}

				}
			}
		}

		// Return statement that uses the result of the if statements
		return kayakAppears;
	}
}
