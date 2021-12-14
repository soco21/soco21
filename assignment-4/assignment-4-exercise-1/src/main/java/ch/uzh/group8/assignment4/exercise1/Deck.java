package ch.uzh.group8.assignment4.exercise1;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
  private ArrayList<Card> deck;

  public Deck() {
    this.deck = new ArrayList<>();
  }

  public void createDeck() {
    for (Suit aSuit : Suit.values()) {
      for (Rank aValue : Rank.values()) {
        this.deck.add(new Card(aSuit, aValue));
      }
    }
    Collections.shuffle(deck);
  }

  public String toString() {
    StringBuilder cardListOutput = new StringBuilder();
    for (Card aCard : this.deck) {
      cardListOutput.append("\n" + "-").append(aCard.toString());
    }
    return cardListOutput.toString();
  }

  public void removeCard(int i) {
    this.deck.remove(i);
  }

  public Card getCard(int i) {
    return this.deck.get(i);
  }
}
