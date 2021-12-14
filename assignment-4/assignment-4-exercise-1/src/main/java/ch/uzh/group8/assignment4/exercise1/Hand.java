package ch.uzh.group8.assignment4.exercise1;

import java.util.ArrayList;

public class Hand {

  private ArrayList<Card> hand;

  public Hand() {
    this.hand = new ArrayList<>();
  }

  public void draw(Deck deck) {
    this.hand.add(deck.getCard(0));
    deck.removeCard(0);
  }

  public String toString() {
    StringBuilder cardListOutput = new StringBuilder();
    for (Card aCard : this.hand) {
      cardListOutput.append("\n" + "-").append(aCard.toString());
    }
    return cardListOutput.toString();
  }

  public int handValue() {
    return 0;
  }
}
