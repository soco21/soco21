package ch.uzh.group8.assignment4.exercise1;

import ch.uzh.group8.assignment4.exercise1.domain.Card;
import ch.uzh.group8.assignment4.exercise1.util.Console;

public class HumanPlayer implements Player {
  private final Console console;

  private Hand hand;
  private int balance = 100;
  private int currentBet;

  public HumanPlayer(Console console) {
    this.console = console;
    hand = new Hand(console);
  }

  boolean wantsToContinue() {
    while (true) {
      console.print("Do you want to play? Type 'yes' or 'no'");
      String userInput = console.getUserInput().strip();
      if ("yes".equalsIgnoreCase(userInput)) {
        return true;
      } else if ("no".equalsIgnoreCase(userInput)) {
        return false;
      }
    }
  }

  public void startRound() {
    hand = new Hand(console);
    currentBet = 0;
  }

  public void printInitialHand() {
    hand.printAllCards();
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
    console.print("Do you want to hit or stay?");
    while (true) {
      try {
        return PlayAction.valueOf(console.getUserInput().strip().toUpperCase());
      } catch (IllegalArgumentException e) {
        console.print("Invalid input, please type 'hit' or 'stay'");
      }
    }
  }

  public void printBalance() {
    console.print("Your current balance is: " + balance);
  }

  public void placeBet() {
    while (currentBet <= 0 || currentBet > balance) {
      console.print("Your current balance is " + balance + ", how much do you want to bet?");
      try {
        currentBet = Integer.parseInt(console.getUserInput().strip());
        if (currentBet <= 0 || currentBet > balance) {
          console.print("Your bet must be greater than 0 and smaller or equal your balance.");
        }
      } catch (NumberFormatException e) {
        console.print("Invalid input, please type a natural number.");
      }
    }
  }

  public void applyWon() {
    balance += currentBet;
  }

  public void applyLost() {
    balance -= currentBet;
  }

  public boolean isBroke() {
    return balance <= 0;
  }
}
