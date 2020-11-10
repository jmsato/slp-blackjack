import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage; 
import javafx.scene.control.Button;


public class LossPage extends Application
{
    

    @Override
    public void start(Stage primaryStage) throws Exception{
    // Set the stage title
    primaryStage.setTitle("Loss Page");
    	
    	Pane pane = new HBox(15);
    	// Image Constructor is set to 700 by 700 with the jpg pic insert for the Hbox 
    	Image img = new Image("LossPage.jpg",700,700,true,false);
    	pane.getChildren().add(new ImageView(img));
    	Scene scene = new Scene(pane);
    	primaryStage.setScene(scene);
        
    	// setting up both buttons
    	//root.getChildren().add(new Button("Play Again"));
    	//Button myButton = new Button("Play Again");
    	//HBox row1 = new HBox(button1,button2)
        

        //show window
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        // Laugh the Application.
        Application.launch(args);

    }
}