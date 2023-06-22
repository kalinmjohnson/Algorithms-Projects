package examdynamic;

public class ExamDynamic {
	
	public static long nttpot(int n) {
		if(n == 0) return 1;
		long answer = 1;
		for(int i = 0; i < n; i++) {
			answer += nttpot(i);
		}
		return answer;
	}
	
	public static long nttpotDyn1 (int n) {
		int[] arr = new int[n + 1];
		arr[0] = 1;
		for (int j = 1; j <= n; j++ ) {
			arr[j] = 1;
			for (int i = 0; i < j; i++) {
				arr[j] += arr[i];
			}
		}
		return arr[n];
	}
	
	public static long nttpotDyn2 (int n) {
		int[] arr = new int[n + 1];
		arr[0] = 1;
		for (int j = 1; j <= n; j++ ) {
			arr[j] = 2* arr[j-1];
		}
		return arr[n];
	}

}
