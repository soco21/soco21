package ch.uzh.group8.assignment4.exercise1;

import ch.uzh.group8.assignment4.exercise1.util.Console;

public class Dealer implements Player {
  private final Console console;
  private Hand hand;

  public Dealer(Console console) {
    this.console = console;
    hand = new Hand(console);
  }

  public void startRound() {
    hand = new Hand(console);
  }

  public void printInitialHand() {
    hand.printFirstCard();
    console.print("Card 2: Hidden");
  }

  @Override
  public void addCard(Card card) {
    hand.addCard(card);
  }

  @Override
  public void printAllCards() {
    hand.printAllCards();
  }

  @Override
  public int getScore() {
    return hand.getScore();
  }

  @Override
  public PlayAction hitOrStay() {
    return hand.getScore() < 17 ? PlayAction.HIT : PlayAction.STAY;
  }
}
