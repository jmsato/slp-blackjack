public class Dealer{
  private int dealerTotal; //total card amount that dealer has
  private int playerTotal;//total card amount that player has
  private boolean outcome;
  private ArrayList<Card> dealerCards;//list of cards
  private int count;//tracks next card that needs to be revealed

//passes in a array of cards for the dealer
public Dealer(ArrayList<Card> cardsForDealer){
  dealerTotal = 0;
  playerTotal = player.getTotal();//should be from the players class
  outcome = false;
  dealerCards = cardsForDealer;
  count = 0;
}
//calculate the total of the cards using the getValue() method from the Card class
public void calulateTotal(){
  dealerTotal = 0;
  for(int i =0; i<dealerCards.length(); i++){
    dealerTotal += dealerCards.get(i).getValue();
  }
}
//Adds card to deck
public void addDeck(Card card){
  dealerCards.add(card);
  dealerTotal += card.getValue;
}
//returns the dealer total 
public int getDealerTotal(){
  return dealerTotal;
}
//checks to see if dealer should draw another card
public boolean draw(Card card){
if(dealerTotal<17){
  return true;
}
return false;
}
//calculates the outcome
public boolean outcome(){
  if(dealerTotal == 21 || dealerTotal > playerTotal){
    return true;
  }
  return false;
}
//prints the outcome
public String printOutcome(){
  if(outcome){
    return "Dealer Win!";
  }
  else{
    return "Dealer Lose";
  }
  }
  //dealer actions
  //shows the dealers card
  public method revealHandDealer(){
    return dealerCards.get(count);
    count++;
  }
}