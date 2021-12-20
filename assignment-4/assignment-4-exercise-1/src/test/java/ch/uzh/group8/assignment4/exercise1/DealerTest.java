package ch.uzh.group8.assignment4.exercise1;

import static ch.uzh.group8.assignment4.exercise1.domain.Rank.*;
import static ch.uzh.group8.assignment4.exercise1.domain.Suit.CLUBS;
import static ch.uzh.group8.assignment4.exercise1.domain.Suit.HEARTS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import ch.uzh.group8.assignment4.exercise1.domain.Card;
import ch.uzh.group8.assignment4.exercise1.util.Console;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;

class DealerTest {

  private Console console;
  private Dealer dealer;

  @BeforeEach
  public void setup() {
    console = mock(Console.class);
    dealer = new Dealer(console);
  }

  @Test
  public void has_empty_hand_when_initialized() {
    dealer.printAllCards();

    verify(console).print("");
  }

  @Test
  public void clears_hand_on_startRound() {
    InOrder inOrder = inOrder(console);
    dealer.addCard(Card.of(CLUBS, ACE));
    dealer.printAllCards();

    dealer.startRound();
    dealer.printAllCards();

    inOrder.verify(console).print(ArgumentMatchers.argThat(s -> s.length() > 0));
    inOrder.verify(console).print("");
  }

  @Test
  public void print_only_first_card_on_printInitialHand() {
    InOrder inOrder = inOrder(console);
    dealer.addCard(Card.of(CLUBS, ACE));
    dealer.addCard(Card.of(CLUBS, TEN));

    dealer.printInitialHand();

    inOrder.verify(console).print("Card 1: Card[suit=CLUBS, rank=ACE]");
    inOrder.verify(console).print("Card 2: Hidden");
  }

  @Test
  public void calculates_score_correctly() {
    dealer.addCard(Card.of(CLUBS, ACE));
    dealer.addCard(Card.of(CLUBS, TEN));

    assertThat(dealer.getScore(), is(21));
  }

  @ParameterizedTest
  @MethodSource("getStayHands")
  public void stays_when_score_is_17_or_higher(List<Card> currentHand) {
    currentHand.forEach(dealer::addCard);

    assertThat(dealer.hitOrStay(), is(PlayAction.STAY));
  }

  private static Stream<Arguments> getStayHands() {
    return Stream.of(
        Arguments.of(List.of(Card.of(CLUBS, ACE), Card.of(CLUBS, TEN))),
        Arguments.of(List.of(Card.of(CLUBS, ACE), Card.of(CLUBS, NINE))),
        Arguments.of(List.of(Card.of(CLUBS, ACE), Card.of(CLUBS, EIGHT))),
        Arguments.of(List.of(Card.of(CLUBS, ACE), Card.of(CLUBS, SIX))));
  }

  @ParameterizedTest
  @MethodSource("getHitHands")
  public void hits_when_score_lower_than_17(List<Card> currentHand) {
    currentHand.forEach(dealer::addCard);

    assertThat(dealer.hitOrStay(), is(PlayAction.HIT));
  }

  private static Stream<Arguments> getHitHands() {
    return Stream.of(
        Arguments.of(List.of(Card.of(CLUBS, TWO), Card.of(CLUBS, TEN))),
        Arguments.of(List.of(Card.of(CLUBS, EIGHT), Card.of(HEARTS, EIGHT))),
        Arguments.of(List.of(Card.of(CLUBS, FIVE), Card.of(CLUBS, EIGHT))),
        Arguments.of(List.of(Card.of(CLUBS, TEN), Card.of(CLUBS, SIX))));
  }
}
