package ch.uzh.group8.assignment4.exercise1;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record Card(Suit suit, Rank rank) {
  public static Card of(Suit suit, Rank rank) {
    return new Card(suit, rank);
  }

  public static Set<Card> createDeck() {
    return Arrays.stream(Suit.values())
        .flatMap(suit -> Arrays.stream(Rank.values()).map(rank -> Card.of(suit, rank)))
        // use linked hash set that the order stays the same
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }
}
