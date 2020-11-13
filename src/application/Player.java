package application;

import java.util.ArrayList;

public class Player{
	private int playerTotal;
	private int dealerTotal;
	private boolean outcome;
	private ArrayList<Card> playerCards;
	private int count;

	public Player(ArrayList<Card> cardsForPlayer){
		playerTotal = 0;
		dealerTotal = dealer.getTotal(); //change to dealer's method
		outcome = false;
		playerCards = cardsForPlayer;
		count = 0;
	}
	//calculate the total points for player
	public void calulateTotal(){
		playerTotal = 0;
		for(int i =0; i<playerCards.size(); i++){
			playerTotal += playerCards.get(i).getValue();
		}
	}
	//gives player a card and add the value of the card 
	// onto the player's total points 
	public void addDeck(Card card){
		playerCards.add(card);
		playerTotal += card.getValue();
	}
	//returns the total points the player has
	public int getPlayerTotal(){
		return playerTotal;
	}
	//checks if the player player is able to draw another card
	public boolean draw(Card card){
		if(playerTotal<21){
			return true;
		}
		return false;
	}
	//returns whether or not the player has more points that 
	// the dealer or has a total of 21 points
	public boolean outcome(){
		if(playerTotal == 21 || playerTotal > dealerTotal){
			return true;
		}
		return false;
	}
	//prints the result of the game for player
	public String printOutcome(){
		if(outcome){
			return "Player Wins!";
		}
		else{
			return "Player Lose";
		}
	}
	//returns the player's card 
	public Card revealHandPlayer(){
		count++;
		return playerCards.get(count-1);
	}
}
