package quoridor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The controller and view class for Quoridor
 * 
 * @author Kalin Johnson
 * @version Fall 2022
 */

public class QuoridorVC extends Application implements PropertyChangeListener {

	/**
	 * All the attributes that will be used to store the GUI elements and a version
	 * of the Quoridor Model
	 */
	private ComboBox<Integer> gamesize;
	private ComboBox<String> colorOptions1;
	private ComboBox<String> colorOptions2;
	private ComboBox<String> colorOptionsB;
	private Integer[] sizeOptions = { 5, 7, 11 };
	private String[] colorOptions = { "Red", "Blue", "Green", "Orange", "Yellow", "Purple", "Brown", "Pink" };
	private Button reset;
	private Label feedback;
	private Label sizeChange;
	private Label color1;
	private Label color2;
	private Label colorB;
	private GridPane board;
	private Button[][] gameBoard;
	private QuoridorModel myModel;

	/**
	 * This method sets up the game board, which consists of the 2D array of buttons
	 * and different values attached to those buttons such as the method called when
	 * it is clicked and its color
	 * 
	 * @return void
	 * @param nothing
	 */

	private void setUpGameBoard() {
		int s = myModel.getSize();

		// First, get the color of the barriers because that will be used in the barrier
		// for loop
		gameBoard = new Button[s + s - 1][s + s - 1];
		String colorB = myModel.getColorB();
		colorB = "-fx-background-color: " + colorB + "; -fx-border-color: black; -fx-border-width: 2";

		// This will loop through all the player locations, set them to white, and set
		// up setOnAction so that when the button is clicked, the location of the button
		// is used and .changeLocation is called
		for (int i = 0; i < gameBoard.length; i = i + 2) {
			for (int j = 0; j < gameBoard.length; j = j + 2) {
				int first = j;
				int second = i;
				Button button = new Button("");
				// button.setPrefSize(120, 120);
				button.setStyle("-fx-background-color: White");
				button.setOnAction(e -> {
					myModel.changeLocation((((first * s) / 2) + (second / 2)));
				});
				gameBoard[i][j] = button;
				board.add(button, i, j);

			}
		}

		// This will loop through all of the empty spots and set them to white. Nothing
		// will happen when they are clicked.
		for (int i = 1; i < gameBoard.length; i = i + 2) {
			for (int j = 1; j < gameBoard.length; j = j + 2) {
				Button button = new Button("");
				// button.setPrefSize(30, 30);
				button.setStyle("-fx-background-color: White");
				gameBoard[i][j] = button;
				board.add(button, i, j);

			}
		}

		// This loops through all the horizontal barriers and set them to the correct
		// color
		// It will also add the functionality of the barrier by calling .addBarrier with
		// the correct input numbers when the button is pressed
		for (int i = 0; i < gameBoard.length; i = i + 2) {
			for (int j = 1; j < gameBoard.length; j = j + 2) {
				int first = ((i) + (s * (j - 1))) / 2;
				int second = first + s;
				Button button = new Button("");
				// button.setPrefSize(120, 30);
				button.setStyle(colorB);
				button.setOnAction(e -> {
					myModel.addBarrier(first, second);
				});
				gameBoard[i][j] = button;
				board.add(button, i, j);

			}
		}

		// This loops through all the vertical barriers and set them to the correct
		// color
		// It will also add the functionality of the barrier by calling .addBarrier with
		// the correct input numbers when the button is pressed
		for (int i = 1; i < gameBoard.length; i = i + 2) {
			for (int j = 0; j < gameBoard.length; j = j + 2) {
				int first = ((i - 1) + (s * j)) / 2;
				int second = first + 1;
				Button button = new Button("");
				// button.setPrefSize(30, 120);
				button.setStyle(colorB);
				button.setOnAction(e -> {
					myModel.addBarrier(first, second);
				});
				gameBoard[i][j] = button;
				board.add(button, i, j);

			}
		}

		// Depending on the size of the board, this will loop through all the buttons
		// and set them to the prefered size
		// This makes the board look nice and have good proportions
		if (gameBoard.length == 9) {
			for (int i = 0; i < gameBoard.length; i++) {
				for (int j = 0; j < gameBoard.length; j++) {
					if (i % 2 == 1 && j % 2 == 1) {
						gameBoard[i][j].setPrefSize(30, 30);
					} else if (i % 2 == 0 && j % 2 == 1) {
						gameBoard[i][j].setPrefSize(120, 30);
					} else if (i % 2 == 1 && j % 2 == 0) {
						gameBoard[i][j].setPrefSize(30, 120);
					} else if (i % 2 == 0 && j % 2 == 0) {
						gameBoard[i][j].setPrefSize(120, 120);
					}
				}
			}
		} else if (gameBoard.length == 13) {
			for (int i = 0; i < gameBoard.length; i++) {
				for (int j = 0; j < gameBoard.length; j++) {
					if (i % 2 == 1 && j % 2 == 1) {
						gameBoard[i][j].setMaxSize(25, 25);
						gameBoard[i][j].setMinSize(25, 25);
					} else if (i % 2 == 0 && j % 2 == 1) {
						gameBoard[i][j].setMaxSize(80, 25);
						gameBoard[i][j].setMinSize(80, 25);
					} else if (i % 2 == 1 && j % 2 == 0) {
						gameBoard[i][j].setMaxSize(25, 80);
						gameBoard[i][j].setMinSize(25, 80);
					} else if (i % 2 == 0 && j % 2 == 0) {
						gameBoard[i][j].setMaxSize(80, 80);
						gameBoard[i][j].setMinSize(80, 80);
					}
				}
			}
		} else if (gameBoard.length == 21) {
			for (int i = 0; i < gameBoard.length; i++) {
				for (int j = 0; j < gameBoard.length; j++) {
					if (i % 2 == 1 && j % 2 == 1) {
						gameBoard[i][j].setMaxSize(16, 16);
						gameBoard[i][j].setMinSize(16, 16);
					} else if (i % 2 == 0 && j % 2 == 1) {
						gameBoard[i][j].setMaxSize(50, 16);
						gameBoard[i][j].setMinSize(50, 16);
					} else if (i % 2 == 1 && j % 2 == 0) {
						gameBoard[i][j].setMaxSize(16, 50);
						gameBoard[i][j].setMinSize(16, 50);
					} else if (i % 2 == 0 && j % 2 == 0) {
						gameBoard[i][j].setMaxSize(50, 50);
						gameBoard[i][j].setMinSize(50, 50);
					}
				}
			}
		}

		// Finally, this puts the players in the correct starting positions depending on
		// the size of the board
		int p1 = myModel.getLocation1();
		int p2 = myModel.getLocation2();
		String color1 = myModel.getColor1();
		String color2 = myModel.getColor2();
		color1 = "-fx-background-color: " + color1;
		color2 = "-fx-background-color: " + color2;
		gameBoard[2 * (p1 % s)][(int) (2 * Math.floor(p1 / s))].setStyle(color1);
		gameBoard[2 * (p2 % s)][(int) (2 * Math.floor(p2 / s))].setStyle(color2);
	}

	/**
	 * The method that is called when something has been changed in the model and
	 * the GUI needs to be updated
	 * 
	 * @param the property change event
	 * @return void
	 */

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		// First update the Feedback Label to match the model and get the values of the
		// two variables
		feedback.setText(myModel.getFeedback());
		int s = myModel.getSize();
		String pro = evt.getPropertyName();

		// Then, check whether this method was called because the size was changed or
		// the board was reset
		if (pro.equals("size")) {
			board.getChildren().clear();
			// Set up a new, fresh board
			setUpGameBoard();

			// Next, check whether this method was called because one of the player's
			// locations was changed
		} else if (pro.equals("location")) {
			// Set all the locations to white
			for (int i = 0; i < gameBoard.length; i = i + 2) {
				for (int j = 0; j < gameBoard.length; j = j + 2) {
					gameBoard[i][j].setStyle("-fx-background-color: White");
				}
			}

			// Then set both the player locations to their respective colors
			int p1 = myModel.getLocation1();
			int p2 = myModel.getLocation2();
			String color1 = myModel.getColor1();
			String color2 = myModel.getColor2();
			color1 = "-fx-background-color: " + color1;
			color2 = "-fx-background-color: " + color2;
			gameBoard[2 * (p1 % s)][(int) (2 * Math.floor(p1 / s))].setStyle(color1);
			gameBoard[2 * (p2 % s)][(int) (2 * Math.floor(p2 / s))].setStyle(color2);

			// Finally, check whether this method was called because a barrier was added
		} else if (pro.equals("barrier")) {
			String colorB = myModel.getColorB();
			colorB = "-fx-background-color: " + colorB + "; -fx-border-color: black; -fx-border-width: 2";

			// Loop through all the barrier locations and check whether a barrier exists in
			// that location
			// First loop through horizontal barriers
			for (int i = 0; i < gameBoard.length; i = i + 2) {
				for (int j = 1; j < gameBoard.length; j = j + 2) {
					int first = ((i) + (s * (j - 1))) / 2;
					int second = first + s;
					if (myModel.isBarrier(first, second)) {
						gameBoard[i][j]
								.setStyle("-fx-background-color: Black; -fx-border-color: black; -fx-border-width: 2");
					} else {
						gameBoard[i][j].setStyle(colorB);
					}

				}
			}

			// Then loop through vertical barriers
			for (int i = 1; i < gameBoard.length; i = i + 2) {
				for (int j = 0; j < gameBoard.length; j = j + 2) {
					int first = ((i - 1) + (s * j)) / 2;
					int second = first + 1;
					if (myModel.isBarrier(first, second)) {
						gameBoard[i][j]
								.setStyle("-fx-background-color: Black; -fx-border-color: black; -fx-border-width: 2");
					} else {
						gameBoard[i][j].setStyle(colorB);
					}

				}
			}

		}

	}

	/**
	 * This is the method that is run at launch
	 * 
	 * @param primary Stage
	 * @return void
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {
		// First make an instance of QuoridorModel to keep track of the backend
		myModel = new QuoridorModel(5);
		myModel.addPropertyChangeListener(this);

		try {
			// The main Gridpane is root and it holds everything in the GUI
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 900, 800);

			// Make the reset button
			reset = new Button("Reset");
			reset.setOnAction(e -> {
				myModel.clear();
			});

			feedback = new Label(myModel.getFeedback());

			sizeChange = new Label("Change Size");
			color1 = new Label("Player 1 Color");
			color2 = new Label("Player 2 Color");
			colorB = new Label("Barrier Color");

			// Add all 4 of the Combo Boxes. The options are listed up in the attributes
			gamesize = new ComboBox<Integer>();
			gamesize.getItems().addAll(sizeOptions);
			gamesize.valueProperty().addListener((e -> {
				myModel.setSize(gamesize.getValue());
			}));

			colorOptions1 = new ComboBox<String>();
			colorOptions1.getItems().addAll(colorOptions);
			colorOptions1.valueProperty().addListener((e -> {
				myModel.setColor1(colorOptions1.getValue());
			}));

			colorOptions2 = new ComboBox<String>();
			colorOptions2.getItems().addAll(colorOptions);
			colorOptions2.valueProperty().addListener((e -> {
				myModel.setColor2(colorOptions2.getValue());
			}));

			colorOptionsB = new ComboBox<String>();
			colorOptionsB.getItems().addAll(colorOptions);
			colorOptionsB.valueProperty().addListener((e -> {
				myModel.setColorB(colorOptionsB.getValue());
			}));

			// Set up the gameboard by calling the method
			board = new GridPane();
			setUpGameBoard();
			board.setStyle("-fx-background-color: Gray; -fx-border-color: black; -fx-border-width: 2");

			// Add everything to the root so it will all have a spot in the GUI
			root.add(board, 0, 0, 10, 10);
			root.add(feedback, 0, 10);
			root.add(reset, 10, 0);
			root.add(sizeChange, 10, 1);
			root.add(gamesize, 10, 2);
			root.add(color1, 10, 3);
			root.add(colorOptions1, 10, 4);
			root.add(color2, 10, 5);
			root.add(colorOptions2, 10, 6);
			root.add(colorB, 10, 7);
			root.add(colorOptionsB, 10, 8);

			// Set Column Constraints so different elements of the root gridpane are in teh
			// correct spot
			ColumnConstraints column1 = new ColumnConstraints();
			column1.setPercentWidth(30);
			ColumnConstraints column2 = new ColumnConstraints();
			column2.setPercentWidth(5);
			ColumnConstraints column3 = new ColumnConstraints();
			column3.setPercentWidth(5);
			ColumnConstraints column4 = new ColumnConstraints();
			column4.setPercentWidth(5);
			ColumnConstraints column5 = new ColumnConstraints();
			column5.setPercentWidth(5);
			ColumnConstraints column6 = new ColumnConstraints();
			column6.setPercentWidth(5);
			ColumnConstraints column7 = new ColumnConstraints();
			column7.setPercentWidth(5);
			ColumnConstraints column8 = new ColumnConstraints();
			column8.setPercentWidth(5);
			ColumnConstraints column9 = new ColumnConstraints();
			column9.setPercentWidth(5);
			ColumnConstraints column10 = new ColumnConstraints();
			column10.setPercentWidth(10);
			root.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8,
					column9, column10);

			root.setHgap(10); // horizontal gap in pixels
			root.setVgap(10); // vertical gap in pixels
			root.setPadding(new Insets(10, 10, 10, 10));

			// Final setup steps
			primaryStage.setScene(scene);
			primaryStage.setTitle("Quoridor");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method
	 * 
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
