import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.javafx.tk.Toolkit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage; 
import javafx.scene.control.Button;



public class LossPage2 extends Application
{
    
	@Override
    public void start(Stage primaryStage) throws Exception{
    //stage title
    primaryStage.setTitle("Loss Page");

    	
    	//Setting up "Play Again" button features
    	Button b1 = new Button("Play Again");
    	b1.setTranslateX(-520);
    	b1.setTranslateY(400);
    	b1.setPrefSize(377, 100);
    	
    	//Setting up "Exit" button features
    	Button b2 = new Button("Exit");
    	b2.setTranslateX(600);
    	b2.setTranslateY(400);
    	b2.setPrefSize(377, 100);
    	
    	//Inserting "Loss Page" image and its display 

    	ImageView img = new ImageView("LossPage.jpg");

    	StackPane root = new StackPane(img, b1, b2);
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	
    	
    	//img.setFitWidth(700);
    	img.setPreserveRatio(false);
    	img.setSmooth(true);
    	img.setCache(false);
    	
    	//imageView.fitWidthProperty().bind.(box.widthProperty());
    	
    	//VBox.setVgrow(root, Priority.ALWAYS);
    	
    	//Scale scale = new Scale();
    	//scale.setPivotX(0);
    	//scale.setPivotY(0);
    	//scene.getRoot().getTransforms().setAll(scale);
    	
    	
    	//(WILL MOVE TO SEPARATE FILE LATER) CSS for buttons with custom font
    	scene.getStylesheets().add("file:resources/stylesheet.css");

    	b1.setStyle("-fx-background-color: #5cd65c; -fx-font-size: 33; -fx-font-family: Press Start 2P;-fx-hovered-background: #39ff14;");
    	b2.setStyle("-fx-background-color: #992600; -fx-font-size: 33; -fx-font-family: Press Start 2P;");
    	//b1.setStyle("-fx-normal-background: #5cd65c; -fx-hovered-background: #39ff14; -fx-box-shadow: 0 0 50px #39ff14;");
    	//#5cd65c
        //show window
        primaryStage.show();
    }
    
    public static void main(String[] args)
    {
        // Laugh the Application.
        Application.launch(args);
    }
}
