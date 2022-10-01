package magicsquares;

public class TicTacToeModel extends SquareModel {

	/**
	 * Attributes square is a 2D array that stores the provided square for future
	 * use
	 */
	
	String[][] board;
	String previousMove = "o";
	
	/**
	 * The constructor for the TisTacToe Model Class
	 * 
	 * @param integer for the size
	 * @return there is no return
	 */

	public TicTacToeModel(int i) {
		setSize(i);
	}

	public TicTacToeModel() {
		setSize(0);
	}
	
	/**
	 * Gives the title of the class
	 * 
	 * @param no parameters
	 * @return a string that contains the title
	 */

	@Override
	public String getTitle() {
		return "TicTacToe";
	}

	/**
	 * Gives feedback on the type of square that is currently in the class
	 * 
	 * @param no parameters
	 * @return a String containing a description
	 */
	
	@Override
	public String getFeedback() {

		if (isGameWon().equals("x")) {
			return "x won the game!";
		} else if (isGameWon().equals("o")) {
			return "o won the game!";
		} else if (isGameWon().equals("t")) {
			return "It is a tie";
		} else if (isGameWon().equals("") && previousMove.equals("x")) {
			return ("It is o's turn");
		} else if (isGameWon().equals("") && previousMove.equals("o")) {
			return ("It is x's turn");
		} else {
			return ("No info is known about the game");
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
		board = new String[i][i];
		clear();
		for (int j = 0; j < board.length; j++) {
			for (int k = 0; k < board.length; k++) {
				board[j][k] = "";
			}
		}
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
		return board.length;
	}
	
	/**
	 * Clears out the current values in the square and sets all the spaces equal to
	 * ""
	 * 
	 * @param no parameters
	 * @return there is no return
	 */

	@Override
	public void clear() {
		String oldVal = null;
		previousMove = "o";
		if (board.length != 0) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] != null) {
						oldVal = board[i][j];
					}
					board[i][j] = "";
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
		return board[row][col];
	}
	
	/**
	 * Sets the value of a specific spot in the array
	 * 
	 * @param the row, column, and data of the new insert
	 * @return there is no return
	 */

	@Override
	public void setValueAt(String data, int row, int col) {
		String oldValue = board[row][col];

		try {
			System.out.println(isGameWon());
			if (data != null) {
				if ((data.equals("x") || data.equals("o")) && (isGameWon().equals(""))) {
					if (data.equals(previousMove)) {
						throw new NumberFormatException();
					} else {
						board[row][col] = data;
						previousMove = data;
					}
				}
			}
		} catch (NumberFormatException e) {
			this.pcs.firePropertyChange("change input data", oldValue, data);
		} catch (NullPointerException e) {
			this.pcs.firePropertyChange("change input data", oldValue, data);
		}
		this.pcs.firePropertyChange("change input data", oldValue, data);

	}
	
	/**
	 * Determines whether the game has been won yet
	 * 
	 * @param the row, column, and data of the new insert
	 * @return there is no return
	 */

	public String isGameWon() {

		boolean isfull = true;
		boolean iswon = true;
		String winningChar = "";
		String tempVal = "";
		int i = 0;
		int j = 0;
		int sameChar = 0;

		// Check the rows

		for (i = 0; i < board.length; i++) {
			tempVal = board[i][0];
			sameChar = 0;
			for (j = 1; j < board.length; j++) {
				if (board[i][j].equals(tempVal) && !board[i][j].equals("") && !tempVal.equals("")) {
					sameChar++;
				} 
			}
			if (sameChar == board.length-1) {
				winningChar = tempVal;
			}
		}

		// Check the columns

		if (winningChar.equals("")) {
			for (i = 0; i < board.length; i++) {
				tempVal = board[0][i];
				sameChar = 0;
				for (j = 1; j < board.length; j++) {
					if (board[j][i].equals(tempVal) && !board[j][i].equals("")  && !tempVal.equals("")) {
						sameChar++;
					} 
				}
				if (sameChar == board.length-1) {
					winningChar = tempVal;
				}
			}
		}

		// Check the positive slop diagonal
		if (winningChar.equals("")) {
			tempVal = board[0][0];
			sameChar = 0;
			for (j = 1, i = 1; j < board.length; j++, i++) {
				if (board[i][j].equals(tempVal) && !board[i][j].equals("") && !tempVal.equals("")) {
					sameChar++;
				}
			}
			if (sameChar == board.length-1) {
				winningChar = tempVal;
			}

		}

		// Check the negative slop diagonal
		if (winningChar.equals("")) {
			tempVal = board[0][board.length - 1];
			for (j = board.length - 1, i = 1; j < board.length - 1; j--, i++) {
				if (board[i][j].equals(tempVal) && !board[i][j].equals("") && !tempVal.equals("")) {
					sameChar++;				}
			}
			if (sameChar == board.length-1) {
				winningChar = tempVal;
			}

		}

		// Check for a tie because the board is full
		for (i = 0; i < board.length; i++) {
			for (j = 0; j < board.length; j++) {
				if (board[i][j] == "") {
					isfull = false;
				}
			}
		}

		if (isfull && (winningChar == "")) {
			winningChar = "t";
		}

		return winningChar;
	}

}
