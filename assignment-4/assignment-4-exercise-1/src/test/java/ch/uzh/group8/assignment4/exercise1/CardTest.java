package ch.uzh.group8.assignment4.exercise1;

import static ch.uzh.group8.assignment4.exercise1.Card.createDeck;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CardTest {

  private static final Set<Card> SOME_DECK = createDeck();

  @Test
  public void create_deck_creates_a_deck_with_all_52_cards() {
    assertThat(createDeck(), hasSize(52));
  }

  @RepeatedTest(5)
  public void create_deck_creates_the_deck_in_the_same_order() {
    assertThat(SOME_DECK, is(createDeck()));
  }

  @Test
  public void adding_the_same_card_twice_to_a_set_is_noop() {
    HashSet<Card> set = new HashSet<>(List.of(Card.of(Suit.CLUBS, Rank.ACE)));
    set.add(Card.of(Suit.CLUBS, Rank.ACE));

    assertThat(set, hasSize(1));
  }
}
