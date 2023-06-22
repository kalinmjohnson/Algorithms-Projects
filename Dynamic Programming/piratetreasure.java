package piratetreasure;

public class piratetreasure {
	
	private long[][] memo;
	
	public long Locks(int N, int G) {
		if (N > 60) {
			return 0;
		} else {
			return LocksRecursive(N, G);
		}
	}

	public long LocksRecursive(int N, int G) {
		if (G == 1) {
			return 1;
		} else if (G == N) {
			return N;
		} else {
			memo = new long[N + 1][G + 1];
			return LocksMemo(N-1, G-1) + LocksMemo(N-1, G);
		}
	}
	
	public long LocksMemo(int N, int G) {
		if(memo[N][G] == 0){
			if(G == 1) {
				memo[N][G] = 1;
				return 1;
			} else if (G == N) {
				memo[N][G] = N;
				return N;
			} else {
				memo[N][G] = LocksMemo(N-1, G-1) + LocksMemo(N-1, G);
			}
		}
		return memo[N][G];
	}
}
