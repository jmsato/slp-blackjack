package application;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
	private DeckOfCards deck;
	private Dealer dealer;
	private Player player;
	private Button submitBet = new Button("Submit");
	private HBox dealerBox = new HBox(10);
	private HBox playerBox = new HBox(10);
	private double playerAmount = 100;
	private double parsedBet = 0;

	@Override
	public void start(Stage primaryStage) {
		startNewGame();

		//Left side with the bets
		//Bet area
		Label betAmount = new Label("Bet Amount:");
		TextField betField = new TextField("$ Enter your bet");

		HBox betRow = new HBox(betField, submitBet);
		VBox betBox = new VBox(betAmount, betRow);

		//Buttons
		Button hit = new Button("HIT");
		Button stand = new Button("STAND");
		hit.setDisable(true);
		stand.setDisable(true);
		VBox buttons = new VBox(hit, stand);

		//Bank area
		Label bank = new Label("Bank:");
		Label playerTotal = new Label(String.format("$%.2f", playerAmount));
		VBox bankBox = new VBox(bank, playerTotal);

		VBox leftSide = new VBox(betBox, buttons, bankBox);

		//Right side with the cards
		//Deck area
		ImageView backOfDeck = new ImageView(deck.getBackOfCardImage());
		Label pot = new Label("Pot: ");
		Label potAmount = new Label("$");
		VBox potBox = new VBox(pot, potAmount);
		HBox deckArea = new HBox(backOfDeck, potBox);

		VBox rightSide = new VBox(dealerBox, deckArea, playerBox);

		//Add functionality to all buttons here
		//Submit bet button
		submitBet.setOnAction(e -> {
			parsedBet = Double.parseDouble(betField.getText());
			//Creates an error pop up if the bet amount is not valid
			if (parsedBet > playerAmount || parsedBet == 0) {
				Stage dialog = new Stage();
				dialog.initOwner(primaryStage);
				Label error = new Label(String.format("Your bet needs to be at least $1.00-$%.2f", parsedBet));
				VBox dialogVbox = new VBox(error);
				Scene dialogScene = new Scene(dialogVbox, 300, 150);
				dialog.setScene(dialogScene);
				dialog.show();
			} else {
				potAmount.setText(String.format("$%.2f", parsedBet));
				//Enable appropriate buttons to allow user input
				submitBet.setDisable(true);
				hit.setDisable(false);
				stand.setDisable(false);
				//Show the appropriate cards
				playerBox.getChildren().remove(0, 2);
				for(Card c : player.getPlayerHand()) {
					playerBox.getChildren().add(new ImageView(c.getImageOfCard()));
				}
				dealerBox.getChildren().remove(0);
				dealerBox.getChildren().add(0, new ImageView(dealer.getDealerHand().get(0).getImageOfCard()));
				//Check to see if the player wins by getting 21
				if(player.getPlayerTotal() == 21) {
					hit.setDisable(true);
					stand.setDisable(true);
					Stage popup = new Stage();
					popup.initOwner(primaryStage);
					PauseTransition delay = new PauseTransition(Duration.seconds(5));
					delay.setOnFinished(event -> {
						try {
							startWinPage(popup);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
					delay.play();
					playerAmount += (2 * parsedBet);
					playerTotal.setText(String.format("$%.2f", playerAmount));
				}
			}
		});
		//Hit button
		hit.setOnAction(e -> {
			Card drawn = deck.drawCard();
			player.addDeck(drawn);
			playerBox.getChildren().add(new ImageView(drawn.getImageOfCard()));
			//Check if the player busts (goes over 21)
			if(!player.draw()) {
				hit.setDisable(true);
				stand.setDisable(true);
				Stage popup = new Stage();
				popup.initOwner(primaryStage);
				PauseTransition delay = new PauseTransition(Duration.seconds(5));
				delay.setOnFinished(event -> {
					try {
						startLossPage(popup);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				delay.play();
				playerAmount -= parsedBet;
				playerTotal.setText(String.format("$%.2f", playerAmount));
			}
			//Player automatically wins when they hit 21
			else if(player.getPlayerTotal() == 21) {
				hit.setDisable(true);
				stand.setDisable(true);
				Stage popup = new Stage();
				popup.initOwner(primaryStage);
				PauseTransition delay = new PauseTransition(Duration.seconds(5));
				delay.setOnFinished(event -> {
					try {
						startWinPage(popup);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				delay.play();
				playerAmount += (2 * parsedBet);
				playerTotal.setText(String.format("$%.2f", playerAmount));
			}
		});
		//Stand button
		stand.setOnAction(e -> {
			hit.setDisable(true);
			stand.setDisable(true);
			//Removes the back of the second card
			dealerBox.getChildren().remove(1);
			//Reveals the other card in dealer's hand
			dealerBox.getChildren().add(new ImageView(dealer.getDealerHand().get(1).getImageOfCard()));
			//Take the dealer's turn
			while(dealer.draw()) {
				Card drawn = deck.drawCard();
				dealer.addDeck(drawn);
				dealerBox.getChildren().add(new ImageView(drawn.getImageOfCard()));
			}
			//Game is over, determine who won
			dealer.setPlayerTotal(player.getPlayerTotal());
			player.setDealerTotal(dealer.getDealerTotal());
			//Dealer won
			if(dealer.outcome()) {
				Stage popup = new Stage();
				popup.initOwner(primaryStage);
				PauseTransition delay = new PauseTransition(Duration.seconds(5));
				delay.setOnFinished(event -> {
					try {
						startLossPage(popup);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				delay.play();
				playerAmount -= parsedBet;
				playerTotal.setText(String.format("$%.2f", playerAmount));
			}
			//Player won
			else {
				Stage popup = new Stage();
				popup.initOwner(primaryStage);
				PauseTransition delay = new PauseTransition(Duration.seconds(5));
				delay.setOnFinished(event -> {
					try {
						startWinPage(popup);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				delay.play();
				playerAmount += (2 * parsedBet);
				playerTotal.setText(String.format("$%.2f", playerAmount));
			}
		});

		//Set scene and stage
		HBox root = new HBox(leftSide, rightSide);
		Scene scene = new Scene(root, 1000, 600);
		scene.getStylesheets().add("file:resources/css/application.css");
		primaryStage.setTitle("Blackjack");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Call this to start a new game of blackjack.
	 * Resets the cards and deals 2 cards to each player.
	 */
	private void startNewGame() {
		//Create a new deck and shuffle the cards
		deck = new DeckOfCards(ColorBackOfCardImage.BLUE);
		deck.createDeckOfCards();
		deck.shuffleCards();

		//Deal the cards out to the player and dealer
		ArrayList<Card> dealerCards = new ArrayList<Card>();
		ArrayList<Card> playerCards = new ArrayList<Card>();

		for(int i = 0; i < 4; i++) {
			if(i % 2 == 0) {
				playerCards.add(deck.drawCard());
			}
			else {
				dealerCards.add(deck.drawCard());
			}
		}

		//Dealer area
		dealerBox.getChildren().remove(0, dealerBox.getChildren().size());
		ArrayList<ImageView> dealerHand = new ArrayList<ImageView>();
		for(Card c : dealerCards) {
			dealerHand.add(new ImageView(deck.getBackOfCardImage()));
		}
		dealerBox.getChildren().addAll(dealerHand);

		//Player area
		playerBox.getChildren().remove(0, playerBox.getChildren().size());
		ArrayList<ImageView> playerHand = new ArrayList<ImageView>();
		for(Card c : playerCards) {
			playerHand.add(new ImageView(deck.getBackOfCardImage()));
		}
		playerBox.getChildren().addAll(playerHand);

		//Create the player and dealer
		player = new Player(playerCards);
		player.calulateTotal();
		dealer = new Dealer(dealerCards);
		dealer.calulateTotal();

		//Ensure buttons are enabled
		submitBet.setDisable(false);
	}

	private void startWinPage(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Win Page");
		primaryStage.setWidth(600);
		primaryStage.setHeight(400);

		Label hello = new Label("Winner!"); // this is just an example.. always write Object name = new Object() when creating new object
		// need a semicolon at end of line for almost every line, unless there's a if statement or {}

		Button PlayAgain = new Button ("Play Again");
		PlayAgain.setOnAction(e -> {
			startNewGame();
			primaryStage.close();
		});
		Button Exit = new Button ("Exit");
		Exit.setOnAction(e -> { 
			primaryStage.getOwner().hide();
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

	private void startLossPage(Stage primaryStage) throws Exception{
		//stage title
		primaryStage.setTitle("Loss Page");

		//Setting up "Play Again" button features
		Button b1 = new Button("Play Again");
		b1.setTranslateX(-520);
		b1.setTranslateY(400);
		b1.setPrefSize(377, 100);
		b1.setOnAction(e -> {
			startNewGame();
			primaryStage.close();
		});

		//Setting up "Exit" button features
		Button b2 = new Button("Exit");
		b2.setTranslateX(600);
		b2.setTranslateY(400);
		b2.setPrefSize(377, 100);
		b2.setOnAction(e -> {
			primaryStage.getOwner().hide();
			primaryStage.close();
		});

		//Inserting "Loss Page" image and its display 

		ImageView img = new ImageView("file:resources/images/LossPage.jpg");

		StackPane root = new StackPane(img, b1, b2);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		img.setPreserveRatio(false);
		img.setSmooth(true);
		img.setCache(false);

		//(WILL MOVE TO SEPARATE FILE LATER) CSS for buttons with custom font
		scene.getStylesheets().add("file:resources/css/lossPageStylesheet.css");

		b1.setStyle("-fx-background-color: #5cd65c; -fx-font-size: 33; -fx-font-family: Press Start 2P;-fx-hovered-background: #39ff14;");
		b2.setStyle("-fx-background-color: #992600; -fx-font-size: 33; -fx-font-family: Press Start 2P;");

		//show window
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
