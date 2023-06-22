package piratetreasure;

/**
 * Solves pirate treasure problem but with dynamic programming
 * @author kalinjohnson
 * @version Fall 2022
 */

public class piratetreasuredynamic {
	
	/**
	 * Only attribute is a table to store values that are already found
	 */
	private long[][] table;
	
	/**
	 * The method that is called by people wanting to use the class
	 * @param N
	 * @param G
	 * @return a long which corresponds to the inputs
	 */
	public long Locks(int N, int G) {
		if (N > 60) {
			return 0;
		} else {
			return locksDyn(N, G);
		}
	}
	
	/**
	 * The dynamic method called by the Locks method
	 * @param N
	 * @param G
	 * @return a long that tells you the final calculated number
	 */
	
	private long locksDyn(int N, int G) {
		if (G == 1) {
			return 1;
		} else if (G == N) {
			return N;
		} else {
			table = new long[N][G];
			for (int i = 0; i < N; i++) {
                table[i][0] = 1;
            }
			for (int i = 1; i < Math.min(N, G); i++) {
                table[i][i] = i + 1;
            }
			for (int i = 1; i < N; i++) {
                for (int j = 1; j < G; j++) {
                    if (j < i) {
                        table[i][j] = table[i - 1][j - 1] + table[i - 1][j];
                    }
                }
            }

			return (table[N-2][G-1] + table[N-2][G-2]);
		}
	}
}
