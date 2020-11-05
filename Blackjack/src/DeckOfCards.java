/**
 * The DeckOfCards class is used to create a deck of card objects and display the back of card image.
 */
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {
    private ArrayList<Card> deck;
    private Image backOfCardImage;
    private Scene displayBackOfCardImage;
    private ColorBackOfCardImage colorBackOfCardImage;
    /**
     * A constructor that initializes ArrayList of a deck of cards, initializes a file name of a back of card image, and initialize the scene to put the back of card image to display.
     * @param  colorBackOfCardImage  enum types of a choice of colors for the back of card image, i.e., BLUE, GREY, RED
     */
    public DeckOfCards(ColorBackOfCardImage colorBackOfCardImage){
        this.deck = new ArrayList<Card>();
        this.colorBackOfCardImage = colorBackOfCardImage;
        String fileName = "Back "+ colorBackOfCardImage +" 1.png";
        backOfCardImage = new Image("file:C:/Users/acer/Blackjack/CardImage/"+ fileName);
        displayBackOfCardImage = new Scene(new HBox(new ImageView(backOfCardImage)));
    }
    public ArrayList<Card> getDeck(){
        return deck;
    }
    public Image getBackOfCardImage() {
        return backOfCardImage;
    }
    public Scene displayBackOfCardImage(){
        return displayBackOfCardImage;
    }
    public ColorBackOfCardImage getColorBackOfCardImage(){
        return colorBackOfCardImage;
    }
    /**
     * A method that create a deck of cards.
     */
    public void createDeckOfCards(){
        for(Suit suitOfCards: Suit.values()){
            for(FaceName faceNameOfCards: FaceName.values()){
                this.deck.add(new Card(suitOfCards,faceNameOfCards));
            }
        }
    }
    public void shuffleCards(){
        Collections.shuffle(deck);
    }
    public String toString (){
        String printList = "";
        int i = 1;
        for(Card card: this.deck){
            printList += "\n" + i + "-" + card.toString();
            i++;
        }
        return printList;
    }
}
