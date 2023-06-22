package unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/** Union find main static class
 * 
 * @author Kalin Johnson
 * @version Fall 2022
 */

public class unionfind {
	
	/** Holds the array and the methods to modify or find items in the array
	 * 
	 * @author kalinjohnson
	 * @version Fall 2022
	 */

	public class UnionFind {
		
		/**
		 * Attribute: array that contains all the connections
		 */
		int[] arr;
		
		/**
		 * Constructor for Union Find
		 * @param i
		 */

		UnionFind(int i) {
			arr = new int[i];
			Arrays.fill(arr, -1);
		}
		
		/**
		 * Finds the root of the tree item is in
		 * @param item
		 * @return int
		 */

		public int find(int item) {
			if (arr[item] < 0) {
				return item;
			}
			arr[item] = find(arr[item]);
			return arr[item];
		}
		
		/**Unions 2 sets if they are not already together
		 * 
		 * @param set1
		 * @param set2
		 */

		public void union(int set1, int set2) {
			int root1 = find(set1);
			int root2 = find(set2);
			if (root1 != root2) {
				if (arr[root1] > arr[root2]) {
					arr[root2] = arr[root2] + arr[root1];
					arr[root1] = root2;
				} else if (arr[root1] < arr[root2]) {
					arr[root1] = arr[root2] + arr[root1];
					arr[root2] = root1;
				} else {
					if (root1 < root2) {
						arr[root1] = arr[root2] + arr[root1];
						arr[root2] = root1;
					} else {
						arr[root2] = arr[root2] + arr[root1];
						arr[root1] = root2;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		/**
		 * Attributes that will be used later on
		 */
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] current;
		int operations;
		
		/**
		 * Reading in input lines and do things with them depending on whether the first character is ? or =
		 */

		try {

			String start[] = (br.readLine()).split(" ");
			int size = Integer.parseInt(start[0]);

			unionfind uf = new unionfind();
			unionfind.UnionFind UF = uf.new UnionFind(size);

			operations = Integer.parseInt(start[1]);
			for (int i = 0; i < operations; i++) {
				current = (br.readLine()).split(" ");
				if (current[0].equals("?")) {
					if (UF.find(Integer.parseInt(current[1])) == UF.find(Integer.parseInt(current[2]))) {
						sb.append("yes" + "\n");
					} else {
						sb.append("no" + "\n");
					}
				} else if (current[0].equals("=")) {
					UF.union(Integer.parseInt(current[1]), Integer.parseInt(current[2]));
				}

			}
		} catch (IOException e) {
		}

		System.out.println(sb);
	}
}
