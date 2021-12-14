package ch.uzh.group8.assignment4.exercise1;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class PackTest {

  private static final int CARDS_UNTIL_RESHUFFLE =
      Pack.NUMBER_OF_DECKS * 52 - Pack.RESHUFFLE_THRESHOLD;
  private Pack pack;

  @BeforeEach
  public void setup() {
    pack = new Pack();
  }

  @Test
  public void return_card_for_drawCard() {
    assertThat(pack.drawCard(), notNullValue());
  }

  @Test
  public void repeat_a_card_a_maximum_of_number_of_decks_times_before_reshuffle_threshold() {
    List<Card> cards = drawCards(CARDS_UNTIL_RESHUFFLE);

    assertTrue(
        createCardOccurrenceMap(cards).values().stream()
            .allMatch(cardOccurrence -> cardOccurrence <= Pack.NUMBER_OF_DECKS));
  }

  @Test
  public void repeat_a_card_maximal_number_of_decks_times_the_reshuffles() {
    int numberOfReShuffles = 5;
    int numberOfCardsToDraw = CARDS_UNTIL_RESHUFFLE * numberOfReShuffles;

    List<Card> cards = drawCards(numberOfCardsToDraw);

    assertTrue(
        createCardOccurrenceMap(cards).values().stream()
            .allMatch(
                cardOccurrence -> cardOccurrence <= Pack.NUMBER_OF_DECKS * numberOfReShuffles));
  }

  @RepeatedTest(5)
  public void not_use_the_same_sequence_twice() {
    List<Card> firstSequence = drawCards(CARDS_UNTIL_RESHUFFLE);

    Pack secondPack = new Pack();
    List<Card> secondSequence =
        IntStream.range(0, CARDS_UNTIL_RESHUFFLE).mapToObj(__ -> secondPack.drawCard()).toList();

    assertThat(firstSequence, is(not(secondSequence)));
  }

  private List<Card> drawCards(int numberOfCards) {
    return IntStream.range(0, numberOfCards).mapToObj(__ -> pack.drawCard()).toList();
  }

  private static Map<Card, Integer> createCardOccurrenceMap(List<Card> cards) {
    return cards.stream().collect(Collectors.toMap(Function.identity(), __ -> 1, Integer::sum));
  }
}
