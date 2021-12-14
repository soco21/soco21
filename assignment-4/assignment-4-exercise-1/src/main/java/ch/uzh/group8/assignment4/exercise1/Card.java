package ch.uzh.group8.assignment4.exercise1;

public class Card {
  private Suit suit;
  private Rank value;

  public Card(Suit suit, Rank value) {
    this.value = value;
    this.suit = suit;
  }

  public Rank getValue() {
    return this.value;
  }

  public String toString() {
    return this.value.toString() + "-" + this.suit.toString();
  }
}
