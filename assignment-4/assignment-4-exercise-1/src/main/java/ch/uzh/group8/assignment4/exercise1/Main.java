package ch.uzh.group8.assignment4.exercise1;

public class Main {
  public static void main(String[] args) {

    Deck deck = new Deck();
    deck.createDeck();

    Hand playerHand = new Hand();
    playerHand.draw(deck);
    playerHand.draw(deck);

    Hand dealerHand = new Hand();
    dealerHand.draw(deck);
    dealerHand.draw(deck);

    System.out.println(deck);
    System.out.println(playerHand);
    System.out.println(dealerHand);
  }
}
