package quoridor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class QuoridorModel {

	/**
	 * Attributes the model is storing to keep track of different parts of the game
	 */
	private int size;
	private LinkedList<Integer>[] list;
	private int player1Location;
	private int player2Location;
	private String player1Color;
	private String player2Color;
	private String barrierColor;
	private boolean turn;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	// Making Input class to tie two values together in the Priority queue
	class Input implements Comparable<Input> {

		private Integer value;
		private Integer priority;

		// The constructor
		public Input(int v, int p) {
			value = v;
			priority = p;
		}

		// These two methods are used to override two methods for the priority queue to
		// work correctly

		// Compare based on priority instead of something else
		@Override
		public int compareTo(Input other) {
			return this.priority.compareTo(other.priority);
		}

		// These are equal if the value attribute is the same
		@Override
		public boolean equals(Object o) {
			Input other = (Input) o;
			if (this.value == other.value) {
				return true;
			} else {
				return false;
			}

		}
	}

	/**
	 * The Constructor for the model class
	 * 
	 * @param s
	 */

	public QuoridorModel(int s) {
		setSize(s);
		player1Color = "Red";
		player2Color = "Blue";
		barrierColor = "Green";
	}

	/**
	 * Sets the color of player 1 to what was in the combo box Also makes sure the
	 * two players and the barriers are all different colors
	 * 
	 * @param string
	 */

	public void setColor1(String string) {
		if (!string.equals(player2Color) && !string.equals(barrierColor)) {
			player1Color = string;
			this.pcs.firePropertyChange("location", null, player1Color);
		}
	}

	/**
	 * Sets the color of player 2 to what was in the combo box Also makes sure the
	 * two players and the barriers are all different colors
	 * 
	 * @param string
	 */

	public void setColor2(String string) {
		if (!string.equals(player1Color) && !string.equals(barrierColor)) {
			player2Color = string;
			this.pcs.firePropertyChange("location", null, player2Color);
		}
	}

	/**
	 * Sets the color of the barriers to what was in the combo box Also makes sure
	 * the two players and the barriers are all different colors
	 * 
	 * @param string
	 */

	public void setColorB(String string) {
		if (!string.equals(player1Color) && !string.equals(player2Color)) {
			barrierColor = string;
			this.pcs.firePropertyChange("barrier", null, barrierColor);
		}
	}

	/**
	 * Return the color of player 1
	 * 
	 * @return player1Color
	 */

	public String getColor1() {
		return player1Color;
	}

	/**
	 * Return the color of player 2
	 * 
	 * @return player2Color
	 */

	public String getColor2() {
		return player2Color;
	}

	/**
	 * Return the color of the barriers
	 * 
	 * @return barrierColor
	 */

	public String getColorB() {
		return barrierColor;
	}

	/**
	 * Sets up a new game board using input size
	 * 
	 * @param s (size)
	 */

	public void setSize(int s) {

		// Set players back to starting locations and set other attributes to starting
		// positions
		player1Location = (int) Math.floor(s / 2);
		player2Location = s * s - ((s / 2) + 1);
		turn = true;
		size = s;
		list = (LinkedList<Integer>[]) (new LinkedList[s * s]);

		// Add all the linked lists to the array
		for (int i = 0; i < (s * s); i++) {
			list[i] = new LinkedList<>();
		}

		// Loop through all the nodes and form all the edges between them
		for (Integer i = 0; i < s * s; i++) {
			// This if statements makes sure it doesn't loop to the other side of the board
			if (i % s != s - 1) {
				Integer a = i + 1;
				// This doesn't add an edge if it is out of bounds
				if (a >= 0 && a < s * s) {
					// They are only added in one direction because they will be added in the other
					// direction when i is the value of a
					list[i].add(a);
				}
			}

			// This if statements makes sure it doesn't loop to the other side of the board
			if (i % s != 0) {
				Integer b = i - 1;
				// This doesn't add an edge if it is out of bounds
				if (b >= 0 && b < s * s) {
					list[i].add(b);
				}
			}
			Integer c = i + s;
			Integer d = i - s;

			// This doesn't add an edge if it is out of bounds
			if (c >= 0 && c < s * s) {
				list[i].add(c);
			}

			// This doesn't add an edge if it is out of bounds
			if (d >= 0 && d < s * s) {
				list[i].add(d);
			}
		}

		// Finally, let the controller know something has chagned to do with the size
		this.pcs.firePropertyChange("size", null, size);

	}

	/**
	 * Change the turn attribute to the other player depending on the current value
	 */

	public void changeTurn() {
		if (turn == true) {
			turn = false;
		} else {
			turn = true;
		}
	}

	/**
	 * Check if a player has won the game yet
	 * 
	 * @return boolean on whether the game has been won
	 */
	public boolean isGameWon() {
		// The if statements check whether either location is within the requirements to
		// win
		if (player1Location >= (size * size) - size && player1Location <= (size * size) - 1) {
			return true;
		} else if (player2Location >= 0 && player2Location <= (size - 1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Called when you want to reset the board using the current size instead of a
	 * new size
	 */
	public void clear() {
		setSize(size);
	}

	/**
	 * A method that finds out the current states of the game and puts it into
	 * string form
	 * 
	 * @return A string that states something about current state of the game
	 */

	public String getFeedback() {
		if (isGameWon()) {
			if (turn == true) {
				return ("Player 2 has won the game!");
			} else {
				return ("Player 1 has won the game!");
			}
		} else {
			if (turn == true) {
				return ("It is Player 1's turn");
			} else {
				return ("It is Player 2's turn");
			}
		}
	}

	/**
	 * Returns the size
	 * 
	 * @return the size of the game
	 */

	public int getSize() {
		return size;
	}

	/**
	 * Returns the location of player 1
	 * 
	 * @return player1Location
	 */

	public int getLocation1() {
		return player1Location;
	}

	/**
	 * Returns the location of player 2
	 * 
	 * @return player2Location
	 */

	public int getLocation2() {
		return player2Location;
	}

	/**
	 * Checks whether a barrier exists in a specific spot
	 * 
	 * @param f
	 * @param l
	 * @return whether a barrier exists between the two nodes
	 */

	public boolean isBarrier(Integer f, Integer l) {
		if (list[f].contains(l)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * The public method that is used to change the location of whichever player's
	 * turn it is
	 * 
	 * @param location
	 */

	public void changeLocation(int location) {
		// First check whether the change should be made
		if (isLegalMove(location) && !isGameWon()) {
			// Then make the change depending on whose turn it is
			if (turn == true) {
				player1Location = location;
			} else {
				player2Location = location;
			}

			changeTurn();
			this.pcs.firePropertyChange("location", null, location);
		}
	}

	/**
	 * Private method used to determine whether a location is legal to move to
	 * 
	 * @param location
	 * @return boolean whether the move is legal or not
	 */
	private boolean isLegalMove(Integer location) {
		Integer current;
		Integer other;
		// Set current and other up according to the value of turn
		if (turn == true) {
			current = player1Location;
			other = player2Location;
		} else {
			current = player2Location;
			other = player1Location;
		}

		// Check whether the two spots are adjacent and the other player isn't in that
		// spot
		if (list[current].contains(location) && location != other) {
			return true;
			// Then check whether you can go through the other players location to get to
			// the desired location
		} else if (list[current].contains(other) && list[other].contains(location)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The public method used to add a Barrier to the game
	 * 
	 * @param first node
	 * @param last  node
	 */
	public void addBarrier(Integer f, Integer l) {

		// Check whether the barrier should be added
		if (isLegalBarrier(f, l) && !isGameWon()) {

			// Add it by removing the edge from both directions
			list[f].remove(l);
			list[l].remove(f);
			changeTurn();
			this.pcs.firePropertyChange("barrier", null, f);
		}
	}

	/**
	 * A private method used to determine whether a barrier location is legal
	 * 
	 * @param first node
	 * @param last  node
	 * @return boolean whether the barrier is legal or not
	 */

	private boolean isLegalBarrier(Integer f, Integer l) {
		// First check whether a barrier already exists in that spot
		if (!list[f].contains(l)) {
			return false;
		} else {
			// Set up variables for a modified version of Djikstra's algorithm
			// There are two values because the algorithm needs to be run twice (everything
			// is repeated twice)
			// It needs to be run twice to separately check whether both players can make it
			// to their respective sides of the board
			int startingVertex1;
			int startingVertex2;
			int[] result1;
			int[] result2;
			Input first;
			boolean finalResult1 = false;
			boolean finalResult2 = false;

			// Add the barrier to check whether it will work

			list[f].remove(l);
			list[l].remove(f);

			// Add first elements and -1 to results table and priority queue
			// Both results tables are 1D arrays because I don't care about the cost, so I'm
			// not calculating it
			// I only care about whether it gets to a specific set of nodes

			PriorityQueue<Input> pq = new PriorityQueue<Input>();
			result1 = new int[size * size];
			result2 = new int[size * size];

			startingVertex1 = player1Location;
			startingVertex2 = player2Location;

			// Set up pq for player 1
			first = new Input(startingVertex1, 1);
			pq.add(first);
			result1[startingVertex1] = 1;
			djikstras(result1, pq);

			// Set up pq for player 2
			first = new Input(startingVertex2, 1);
			pq.add(first);
			result2[startingVertex2] = 1;
			djikstras(result2, pq);

			// Remove the barrier again after testing
			list[f].add(l);
			list[l].add(f);

			// Check whether player 2 can still win
			for (int i = 0; i < size; i++) {
				if (result2[i] != 0) {
					finalResult2 = true;
				}
			}

			// Check whether player 1 can still win
			for (int i = (result1.length - size); i < size * size; i++) {
				if (result1[i] != 0) {
					finalResult1 = true;
				}
			}

			// If both players can still win, return true, other return false and let the
			// user know by printing out a string
			if (finalResult1 == true && finalResult2 == true) {
				return true;
			} else {
				System.out.println("Invalid Barrier Placement");
				return false;
			}
		}
	}

	/**
	 * A method that runs a modified version of Djikstra's algorithm
	 * 
	 * @param result1
	 * @param pq
	 */

	private void djikstras(int[] result1, PriorityQueue<Input> pq) {
		while (pq.size() > 0) {
			Input current = pq.poll();
			int s = list[current.value].size();
			for (int j = 0; j < s; j++) {
				int k = list[current.value].get(j);
				if (result1[k] == 0) {
					result1[k] = current.value;
					pq.add(new Input(k, 1));
				}
			}
		}
	}

	/**
	 * Don't forget to create a way for Observers to subscribe to this
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	/**
	 * And Observers probably want to be able to unsubscribe as well
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

}
