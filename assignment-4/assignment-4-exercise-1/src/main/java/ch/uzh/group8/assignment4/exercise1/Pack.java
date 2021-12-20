package ch.uzh.group8.assignment4.exercise1;

import ch.uzh.group8.assignment4.exercise1.domain.Card;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pack {
  static final int RESHUFFLE_THRESHOLD = 100;
  static final int NUMBER_OF_DECKS = 6;

  private List<Card> cards;

  public Pack() {
    cards = initializeDeck();
  }

  public Card drawCard() {
    Card cardToReturn = cards.remove(0);
    if (cards.size() < RESHUFFLE_THRESHOLD) {
      cards = initializeDeck();
    }
    return cardToReturn;
  }

  private static List<Card> initializeDeck() {
    List<Card> cards =
        IntStream.range(0, NUMBER_OF_DECKS)
            .mapToObj(__ -> Card.createDeck())
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    Collections.shuffle(cards);
    return cards;
  }
}
