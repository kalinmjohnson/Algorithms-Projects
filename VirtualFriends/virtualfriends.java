package virtualfriends;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class virtualfriends {

	public class VirtualFriends {

		HashMap<String, String> network;

		VirtualFriends() {
			network = new HashMap<String, String>(100000);
		}
		
		public boolean clear() {
			network.clear();
			return network.isEmpty();
		}

		public String find(String item) {
			if (network.containsKey(item)) {
				char c = item.charAt(0);
				if (Character.isDigit(c)) {
					return item;
				} else {
					return find(network.get(item));
				}
			} else {
				network.put(item, "1");
				return item;
			}
		}

		public String union(String set1, String set2) {

			String root1 = find(set1);
			String root2 = find(set2);
			String total = String.valueOf(Integer.parseInt(network.get(root1)) + Integer.parseInt(network.get(root2)));

			if (root1 != root2) {
				if (Integer.parseInt(network.get(root1)) < Integer.parseInt(network.get(root2))) {
					network.replace(root2, total);
					network.replace(root1, root2);
				} else {
					network.replace(root1, total);
					network.replace(root2, root1);
				}
			}
			return total;
		}

	}

	public static void main(String[] args) {
		/**
		 * Attributes that will be used later on
		 */
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		virtualfriends vf = new virtualfriends();
		virtualfriends.VirtualFriends VF = vf.new VirtualFriends();

		String[] connections;
		int testCases;
		int friendships;
		String total;
		try {
			testCases = Integer.parseInt(br.readLine());

			for (int i = 0; i < testCases; i++) {
				if (VF.clear()) {
					friendships = Integer.parseInt(br.readLine());
					for (int j = 0; j < friendships; j++) {
						connections = (br.readLine()).split("");
						total = VF.union(connections[0], connections[1]);
						sb.append(total + "\n");
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(sb);
	}

}
