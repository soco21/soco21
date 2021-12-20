package ch.uzh.group8.assignment4.exercise1;

import ch.uzh.group8.assignment4.exercise1.domain.Card;
import ch.uzh.group8.assignment4.exercise1.domain.Rank;
import ch.uzh.group8.assignment4.exercise1.util.Console;
import java.util.ArrayList;
import java.util.List;

public class Hand {

  private final Console console;
  private final List<Card> hand = new ArrayList<>();

  public Hand(Console console) {
    this.console = console;
  }

  public void addCard(Card card) {
    hand.add(card);
  }

  public void printFirstCard() {
    if (hand.size() == 0) {
      throw new IllegalStateException("printFirstCard called when there were no cards");
    }
    printCardsUpTo(1);
  }

  public void printAllCards() {
    printCardsUpTo(hand.size());
  }

  private void printCardsUpTo(int to) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < to; i++) {
      Card currentCard = hand.get(i);
      stringBuilder.append("Card ").append(i + 1).append(": ").append(currentCard);
      if (i != to - 1) {
        stringBuilder.append("\n");
      }
    }
    console.print(stringBuilder.toString());
  }

  public int getScore() {
    int sum = hand.stream().map(Card::rank).mapToInt(Hand::getScoreOfRank).sum();
    long numberOfAces = hand.stream().map(Card::rank).filter(Rank.ACE::equals).count();
    while (sum > 21 && numberOfAces > 0) {
      sum -= 10;
      numberOfAces--;
    }
    return sum;
  }

  private static int getScoreOfRank(Rank rank) {
    switch (rank) {
      case ACE -> {
        return 11;
      }
      case TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE -> {
        return rank.ordinal() + 1;
      }
      case TEN, JACK, QUEEN, KING -> {
        return 10;
      }
      default -> throw new RuntimeException("Unknown " + Rank.class + ", was: " + rank);
    }
  }
}
