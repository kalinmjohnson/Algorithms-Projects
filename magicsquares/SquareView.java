package magicsquares;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * SquareView A class shows the data from the model and passes on user input
 * square
 * 
 * @author Kalin Johnson
 * @version Fall 2022
 *
 */

public class SquareView extends Application implements PropertyChangeListener {

	/**
	 * attributes used in the GUI and to access the model
	 */
	TicTacToeModel myModel;
	Integer[] sizeOptions = {3, 4, 5, 6, 7, 8, 9, 10 };
	TextField[][] GUIsquare;
	private Label title;
	private Label feedback;
	private Button clear;
	private ComboBox<Integer> squareSize;
	private GridPane square;

	
	/**
	 * Run when something in the model changes
	 * 
	 * @param a property change event
	 * @return no return value
	 */
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		title.setText(myModel.getTitle());
		feedback.setText(myModel.getFeedback());
		
		if (myModel.getSize() != square.getColumnCount()) {
			square.getChildren().clear();
			GUIsquare = new TextField[myModel.getSize()][myModel.getSize()];
			for (int i = 0; i < myModel.getSize(); i++) {
				for (int j = 0; j < myModel.getSize(); j++) {
					TextField squareElement = new TextField();
					GUIsquare[i][j] = squareElement;

					int row = i;
					int column = j;

					squareElement.textProperty().addListener((a, b, c) -> {
						myModel.setValueAt(squareElement.getText(), row, column);
					});

					square.add(squareElement, i, j);
				}
			}
		} else {
			for (int i = 0; i < myModel.getSize(); i++) {
				for (int j = 0; j < myModel.getSize(); j++) {
						GUIsquare[i][j].setText(myModel.getValueAt(i, j));
				}
			}
		}

	}
	
	/**
	 * Run at launch
	 * 
	 * @param a stage
	 * @return no return value
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {

		myModel = new TicTacToeModel(3);
		
		myModel.addPropertyChangeListener(this);
		
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 600, 600);

			title = new Label(myModel.getTitle());
			feedback = new Label(myModel.getFeedback());
			
			clear = new Button("Clear");
			clear.setOnAction(e -> {myModel.clear();});

			squareSize = new ComboBox<Integer>();
			squareSize.getItems().addAll(sizeOptions);
			
			squareSize.valueProperty().addListener((e -> {
				myModel.setSize(squareSize.getValue());
			}));

			square = new GridPane();
			GUIsquare = new TextField[myModel.getSize()][myModel.getSize()];
			for (int i = 0; i < myModel.getSize(); i++) {
				for (int j = 0; j < myModel.getSize(); j++) {
					TextField squareElement = new TextField();
					GUIsquare[i][j] = squareElement;
					
					int row = i;
					int column = j;
					
					squareElement.textProperty().addListener((a, b, c) -> {
						myModel.setValueAt(squareElement.getText(), row, column);
					});

					square.add(squareElement, i, j);
				}
			}
			
			title.setAlignment(Pos.CENTER);
			clear.setAlignment(Pos.BOTTOM_LEFT);

			root.add(title, 0, 1);
			root.add(feedback, 0, 2);
			root.add(squareSize, 0, 3);
			root.add(clear, 0, 5);
			root.add(square, 1, 4);
					    
		    ColumnConstraints column1 = new ColumnConstraints();
		    column1.setPercentWidth(30);
		    ColumnConstraints column2 = new ColumnConstraints();
		    column2.setPercentWidth(70);
		    root.getColumnConstraints().addAll(column1, column2); // each get 50% of width
		    
		   
			
			primaryStage.setScene(scene);
			primaryStage.setTitle(myModel.getTitle());
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * main method
	 * 
	 * @param array of Strings
	 * @return no return value
	 */

	public static void main(String[] args) {
		launch(args);
	}

}
