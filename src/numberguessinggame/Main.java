package numberguessinggame;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
	private Label header = new Label();
	private Label label = new Label(); 
	private final TextField txtGuess = new TextField();
	private Button btn1, btn2;
	private Image congrat = new Image(getClass().getResourceAsStream("congrat.png"));
	private Image notOnTarget = new Image(getClass().getResourceAsStream("wrong.png"));
	private Image warning = new Image(getClass().getResourceAsStream("warning.png"));
	private Label imageResult = new Label();
	private int target, counter;

	@Override
	public void start(Stage primaryStage){
			primaryStage.setTitle("Number Guessing Game");
			VBox pane = new VBox();
			Scene scene = new Scene(pane,300,250);
			primaryStage.setScene(scene);
			header.setText("Welcome to Number Guessing Game");
			header.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
			txtGuess.setPromptText("Enter your guess (1-100)");
			txtGuess.setFocusTraversable(false);
			btn1 = new Button("Try");
			btn2 = new Button("New Game");
			pane.setAlignment(Pos.CENTER);
			pane.setPadding(new Insets(25,25,25,25));
			pane.getChildren().addAll(header,txtGuess,label,btn1, btn2,imageResult);
			newGame(pane);
			btn1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						check(pane);
						}catch(NumberFormatException e) {
							pane.getChildren().remove(imageResult);
							label.setText("Invalid input. Must be a number from 1 to 100.");
							imageResult = new Label(" ", new ImageView(warning));
							pane.getChildren().add(imageResult);
						}
				}
			});
			btn2.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						newGame(pane);
						check(pane);
					}catch(NumberFormatException e) {
						pane.getChildren().remove(imageResult);
						label.setText("Invalid input. Must be a number from 1 to 100.");
						imageResult = new Label(" ", new ImageView(warning));
						pane.getChildren().add(imageResult);
					}
				}
			});
			/*btn.setOnMouseClicked(action -> {
				checkGuess();
			});*/
			primaryStage.sizeToScene();
			primaryStage.show();
	}
	public void newGame(VBox pane) {
		pane.getChildren().remove(imageResult);
		counter = 0;
		Random r = new Random();
		target = r.nextInt(99) + 1;
		System.out.println("Target: "+target);
		
	}
	public void check(VBox pane) {
		int guess = Integer.parseInt(txtGuess.getText());
		String message = "";
		pane.getChildren().remove(imageResult);
		if (guess == target) {
			counter++;
			message = guess + " was right on target! You won after "+counter+" attempts.";
			label.setText(message);
			imageResult = new Label(" ", new ImageView(congrat));
		}
		else if (guess > target && guess < 101) {
			counter++;
			message = guess + " was too high. "+counter+" attempts so far. Guess again:";
			label.setText(message);
			imageResult = new Label(" ", new ImageView(notOnTarget));
		}
		else if (guess < target && guess > 0) {
			counter++;
			message = guess + " was too low. "+counter+" attempts so far. Guess again:";
			label.setText(message);
			imageResult = new Label(" ", new ImageView(notOnTarget));
		}
		else{
			counter++;
			message = guess + " is out of range. "+counter+" attempts so far. Guess again:";
			label.setText(message);
			imageResult = new Label(" ", new ImageView(warning));
		}
		pane.getChildren().add(imageResult);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}

