package application;
/**
* The CardBlackJack program is used to test the Card Class and DeckOfCards Class.
*/
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class CardBlackjack extends Application {
    public static void main (String [] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        // Create a DiamondJack card
        Card diamondJackCard = new Card(Suit.DIAMOND,FaceName.JACK);
        //Scene scene = diamondJackCard.getDisplayCard();
        System.out.println("The value of Diamond Jack Card is: "+ diamondJackCard.getValue());
        // Create a DeckOfCards object and define the color of the back of card image
        DeckOfCards deckOfCards = new DeckOfCards(ColorBackOfCardImage.BLUE);
        Scene scene = deckOfCards.displayBackOfCardImage();
        // Create a deck of card
        deckOfCards.createDeckOfCards();
        // Shuffle a deck of card
        deckOfCards.shuffleCards();
        System.out.println(deckOfCards);
        stage.setScene(scene);
        stage.setTitle("Display Card");
        stage.show();
    }
}
