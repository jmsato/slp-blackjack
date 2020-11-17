package application;

/**
 * The Card class is used to create card objects.
 */
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Card {
    private Suit suit;
    private FaceName faceName;
    private int value;
    private Image imageOfCard;
    private Scene displayCard;
    /**
     * A constructor that will initialize a value of card to play blackjack, initialize a file name of card image, and initialize the scene to put the card image to display.
     * @param  suit  enum types of a card suit, i.e., CLUBS, SPADES, HEARTS, DIAMOND
     * @param  faceName enum types of a card's face name, i.e, ACE, TWO, THREE, ..., JACK, QUEEN, KING
     */
    public Card(Suit suit, FaceName faceName){
        this.suit = suit;
        this.faceName = faceName;
        this.value = getValue();
        String fileName = suit + " " + getValueToDisplayCard() + ".png";
        imageOfCard = new Image("file:resources/images/cards/"+ fileName);
        displayCard = new Scene(new HBox(new ImageView(imageOfCard)));
    }
    public Suit getSuit() {
        return suit;
    }
    public FaceName getFaceName(){
        return faceName;
    }
    public int getValue() {
            switch (faceName) {
                case ACE:
                    return value = getValueOfAce(true); // supposed to be linked with other class
                case TWO: return value = 2;
                case THREE: return value = 3;
                case FOUR: return value = 4;
                case FIVE: return value = 5;
                case SIX: return value = 6;
                case SEVEN: return value = 7;
                case EIGHT: return value = 8;
                case NINE: return value = 9;
                case TEN: case JACK: case QUEEN: case KING: return value = 10;
                default: return 0;
        }
    }
    /**
     * A method that returns a value of Ace which could be either 1 or 11.
     * @param  playerChoice returns true if a play wants a value of Ace to be 11, and return false if a player wants a value of Ace to be 1.
     */
    public int getValueOfAce(boolean playerChoice){
        // playerChoice = true, when Ace = 11
        int value = 0;
        if(faceName == FaceName.ACE && playerChoice == true){
            value = 11;
        }
        if(faceName == FaceName.ACE && playerChoice == false){
            value = 1;
        }
        return value;
    }
    private int getValueToDisplayCard(){
        switch (faceName) {
            case ACE: return value = 1;
            case TWO: return value = 2;
            case THREE: return value = 3;
            case FOUR: return value = 4;
            case FIVE: return value = 5;
            case SIX: return value = 6;
            case SEVEN: return value = 7;
            case EIGHT: return value = 8;
            case NINE: return value = 9;
            case TEN: return value = 10;
            case JACK: return value = 10;
            case QUEEN: return value = 10;
            case KING: return value = 10;
            default: return 0;
        }
    }
    public Image getImageOfCard() {
        return imageOfCard;
    }
    public Scene getDisplayCard(){
        return displayCard;
    }
    public String toString(){
        return suit.toString() + "-" + faceName.toString() + "  (Value to play Blackjack: " + getValue() + ")";
    }
}
