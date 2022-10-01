package magicsquares;

import java.util.Arrays;
import java.util.Objects;

/**
 * MagicSquareModel A class that stores and relays information about a provided
 * square
 * 
 * @author Kalin Johnson
 * @version Fall 2022
 *
 */

public class MagicSquareModel extends SquareModel {

	/**
	 * Attributes square is a 2D array that stores the provided square for future
	 * use
	 */
	Integer[][] square;

	/**
	 * The constructor for the Magic Square Model Class
	 * 
	 * @param integer for the size
	 * @return there is no return
	 */

	public MagicSquareModel(int i) {
		setSize(i);
	}

	public MagicSquareModel() {
		setSize(0);
	}

	/**
	 * Gives feedback on the type of square that is currently in the class
	 * 
	 * @param no parameters
	 * @return a String containing a description
	 */

	@Override
	public String getFeedback() {
		if (isNormalMagic()) {
			return "It is a normal magic square.";
		} else if (isMagic()) {
			return "It is a magic square.";
		} else {
			return "It is not a magic square.";
		}
	}

	/**
	 * Used to set the size of the square, and it is used in the constructor
	 * 
	 * @param integer for the size
	 * @return there is no return
	 */

	@Override
	public void setSize(int i) {
		square = new Integer[i][i];
		clear();
		this.pcs.firePropertyChange("size difference", null, i);
	}

	/**
	 * Gives the current size of the square
	 * 
	 * @param no parameters
	 * @return an integer that is the size
	 */

	@Override
	public int getSize() {
		return square.length;
	}

	/**
	 * Gives the title of the class
	 * 
	 * @param no parameters
	 * @return a string that contains the title
	 */

	@Override
	public String getTitle() {
		return "Magic Squares";
	}

	/**
	 * Clears out the current values in the square and sets all the spaces equal to
	 * null
	 * 
	 * @param no parameters
	 * @return there is no return
	 */

	@Override
	public void clear() {
		Integer oldVal = null;
		if (square.length != 0) {
			for (int i = 0; i < square.length; i++) {
				for (int j = 0; j < square.length; j++) {
					if (square[i][j] != null) {
						oldVal = square[i][j];
					}
					square[i][j] = null;
				}
			}
		}
		this.pcs.firePropertyChange("change input data", oldVal, null);

	}

	/**
	 * Retrieves that value at the specific point in the square
	 * 
	 * @param the row and column of requested retrieval
	 * @return a string of the information at that spot
	 */

	@Override
	public String getValueAt(int row, int col) {
		if (square[row][col] == null) {
			return "";
		} else {
			String outputValue = Integer.toString(square[row][col]);
			return outputValue;
		}
	}

	/**
	 * Sets the value of a specific spot in the array
	 * 
	 * @param the row, column, and data of the new insert
	 * @return there is no return
	 */

	@Override
	public void setValueAt(String data, int row, int col) {
		Integer oldValue = square[row][col];
		Integer inputNum;

		try {
			if (data.equals("")) {
				square[row][col] = null;
			} else {
				inputNum = Integer.parseInt(data);
				if (inputNum >= 0) {
					square[row][col] = inputNum;
				}
			}
		} catch (NumberFormatException e) {
			this.pcs.firePropertyChange("change input data", oldValue, data);
		}

		this.pcs.firePropertyChange("change input data", oldValue, data);
	}

	/**
	 * A method that determines whether the current square is magic
	 * 
	 * @param no parameters
	 * @return a boolean that tells whether or not it is magic
	 */

	public boolean isMagic() {
		// Attributes required for future evaluations. ismagic is returned at the end
		boolean ismagic = true;
		boolean isfull = true;
		int tempsum = 0;
		int sum = 0;
		int i = 0;
		int j = 0;

		// check so make sure all elements are full
		for (i = 0; i < square.length; i++) {
			for (j = 0; j < square.length; j++) {
				if (square[i][j] == null) {
					isfull = false;
				}
			}
		}

		// find the initial sum to compare future sums against
		if (isfull == true) {
			for (j = 0; j < square.length; j++) {
				sum = sum + square[j][0];
			}
			
			// check the sums of all the different rows
			while (i < square.length) {
				for (j = 0; j < square.length; j++) {
					tempsum = tempsum + square[i][j];
				}
				if (tempsum != sum) {
					ismagic = false;
					break;
				}
				tempsum = 0;
				i++;
			}

			// check the sums of all the different columns
			if (ismagic != false) {
				tempsum = 0;
				i = 0;
				j = 0;
				while (i < square.length) {
					for (j = 0; j < square.length; j++) {
						tempsum = tempsum + square[j][i];
					}
					if (tempsum != sum) {
						ismagic = false;
						break;
					}
					tempsum = 0;
					i++;
				}
			}

			// check the sums of all the negative slop diagonal
			if (ismagic != false) {
				tempsum = 0;
				j = 0;
				for (i = 0; i < square.length; i++, j++) {
					tempsum = tempsum + square[i][j];
				}

				if (tempsum != sum) {
					ismagic = false;
				}
			}
			
			// check the sums of all the positive slop diagonal
			if (ismagic != false) {
				tempsum = 0;
				j = square.length - 1;
				for (i = 0; i < square.length; i++, j--) {
					tempsum = tempsum + square[i][j];
				}

				if (tempsum != sum) {
					ismagic = false;
				}
			
			}

		} else {
			ismagic = false;
		}

		// return true unless found to be false
		return ismagic;
	}

	/**
	 * A method that determines whether the current square is normal magic
	 * 
	 * @param no parameters
	 * @return a boolean that tells whether or not it is normal magic
	 */

	public boolean isNormalMagic() {
		// Attribute that will be returned at the end
		boolean isNormal = true;

		// checks whether the square is magic
		if (isMagic()) {

			// Setting up attributes necessary for later
			Integer[] flatSquare = new Integer[square.length * square.length];
			int h = 0;

			// flattens the 2d array into a 1D array called flatSquare
			for (int i = 0; i < square.length; i++) {
				for (int j = 0; j < square.length; j++) {
					flatSquare[h] = square[i][j];
					h++;
				}
			}

			// sort the flat array into ascending order
			Arrays.sort(flatSquare);

			// checks whether 1 through flatSquare.length squared are present
			for (int i = 0; i < flatSquare.length; i++) {
				if (i + 1 != flatSquare[i]) {
					isNormal = false;
				}
			}
		} else {
			isNormal = false;
		}
		return isNormal;
	}

}
