package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WinPage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Win Page");
		primaryStage.setWidth(600);
		primaryStage.setHeight(400);

		Label hello = new Label("Winner!"); // this is just an example.. always write Object name = new Object() when creating new object
		// need a semicolon at end of line for almost every line, unless there's a if statement or {}

		Button PlayAgain = new Button ("Play Again");
		Button Exit = new Button ("Exit");
		Exit.setOnAction(e -> { 
			primaryStage.close();
		});
		HBox row1 = new HBox (PlayAgain,Exit );
		row1.setSpacing(190);
		row1.setAlignment(Pos.CENTER);
		Image background = new Image ("file:resources/images/Win Page-2.jpg",600,400,true, true);
		ImageView backgroundView = new ImageView (background);
		//Loading a font from local file system
		Font font = Font.loadFont("file:resources/fonts/Goldman-Bold.ttf", 50);

		Label Winner = new Label("Winner!");
		Winner.setTextFill(Color.GOLD);
		Winner.setFont(font);//setting the font
		VBox VBoxrow1 = new VBox (Winner,row1 );
		VBoxrow1.setSpacing(210);
		VBoxrow1.setAlignment (Pos.CENTER);
		Image confettibackground = new Image ("file:resources/images/confetti.giphy.gif", 600,400,true, true);
		ImageView confettibackground2 = new ImageView (confettibackground);
		StackPane Stackpane = new StackPane (backgroundView,VBoxrow1, confettibackground2 );
		Scene scene = new Scene(Stackpane);
		scene.getStylesheets ().add("file:resources/css/Winsheet.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}