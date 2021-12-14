package ch.uzh.group8.assignment4.exercise1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ch.uzh.group8.assignment4.exercise1.util.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HandTest {
  private Console console;
  private Hand hand;

  @BeforeEach
  public void setup() {
    console = mock(Console.class);
    hand = new Hand(console);
  }

  @RepeatedTest(5)
  public void store_and_print_cards_added() {
    Card.createDeck().forEach(hand::addCard);

    hand.printAllCards();

    verify(console)
        .print(
            """
              Card 1: Card[suit=CLUBS, rank=ACE]
              Card 2: Card[suit=CLUBS, rank=TWO]
              Card 3: Card[suit=CLUBS, rank=THREE]
              Card 4: Card[suit=CLUBS, rank=FOUR]
              Card 5: Card[suit=CLUBS, rank=FIVE]
              Card 6: Card[suit=CLUBS, rank=SIX]
              Card 7: Card[suit=CLUBS, rank=SEVEN]
              Card 8: Card[suit=CLUBS, rank=EIGHT]
              Card 9: Card[suit=CLUBS, rank=NINE]
              Card 10: Card[suit=CLUBS, rank=TEN]
              Card 11: Card[suit=CLUBS, rank=JACK]
              Card 12: Card[suit=CLUBS, rank=QUEEN]
              Card 13: Card[suit=CLUBS, rank=KING]
              Card 14: Card[suit=DIAMONDS, rank=ACE]
              Card 15: Card[suit=DIAMONDS, rank=TWO]
              Card 16: Card[suit=DIAMONDS, rank=THREE]
              Card 17: Card[suit=DIAMONDS, rank=FOUR]
              Card 18: Card[suit=DIAMONDS, rank=FIVE]
              Card 19: Card[suit=DIAMONDS, rank=SIX]
              Card 20: Card[suit=DIAMONDS, rank=SEVEN]
              Card 21: Card[suit=DIAMONDS, rank=EIGHT]
              Card 22: Card[suit=DIAMONDS, rank=NINE]
              Card 23: Card[suit=DIAMONDS, rank=TEN]
              Card 24: Card[suit=DIAMONDS, rank=JACK]
              Card 25: Card[suit=DIAMONDS, rank=QUEEN]
              Card 26: Card[suit=DIAMONDS, rank=KING]
              Card 27: Card[suit=HEARTS, rank=ACE]
              Card 28: Card[suit=HEARTS, rank=TWO]
              Card 29: Card[suit=HEARTS, rank=THREE]
              Card 30: Card[suit=HEARTS, rank=FOUR]
              Card 31: Card[suit=HEARTS, rank=FIVE]
              Card 32: Card[suit=HEARTS, rank=SIX]
              Card 33: Card[suit=HEARTS, rank=SEVEN]
              Card 34: Card[suit=HEARTS, rank=EIGHT]
              Card 35: Card[suit=HEARTS, rank=NINE]
              Card 36: Card[suit=HEARTS, rank=TEN]
              Card 37: Card[suit=HEARTS, rank=JACK]
              Card 38: Card[suit=HEARTS, rank=QUEEN]
              Card 39: Card[suit=HEARTS, rank=KING]
              Card 40: Card[suit=SPADES, rank=ACE]
              Card 41: Card[suit=SPADES, rank=TWO]
              Card 42: Card[suit=SPADES, rank=THREE]
              Card 43: Card[suit=SPADES, rank=FOUR]
              Card 44: Card[suit=SPADES, rank=FIVE]
              Card 45: Card[suit=SPADES, rank=SIX]
              Card 46: Card[suit=SPADES, rank=SEVEN]
              Card 47: Card[suit=SPADES, rank=EIGHT]
              Card 48: Card[suit=SPADES, rank=NINE]
              Card 49: Card[suit=SPADES, rank=TEN]
              Card 50: Card[suit=SPADES, rank=JACK]
              Card 51: Card[suit=SPADES, rank=QUEEN]
              Card 52: Card[suit=SPADES, rank=KING]
              """);
  }

  @Test
  public void print_first_card_for_printFirstCard() {
    Card.createDeck().forEach(hand::addCard);

    hand.printFirstCard();

    verify(console).print("Card 1: Card[suit=CLUBS, rank=ACE]\n");
  }

  @Test
  public void throw_exception_when_printFirstCard_on_empty_hand() {
    assertThrows(IllegalStateException.class, hand::printFirstCard);
  }

  @Test
  public void print_empty_line_for_empty_hand() {
    hand.printAllCards();

    verify(console).print("");
  }

  @ParameterizedTest
  @CsvSource({
    "ACE,11",
    "TWO,2",
    "THREE,3",
    "FOUR,4",
    "FIVE,5",
    "SIX,6",
    "SEVEN,7",
    "EIGHT,8",
    "NINE,9",
    "TEN,10",
    "JACK,10",
    "QUEEN,10",
    "KING,10",
  })
  public void calculate_points_of_single_card(Rank rank, int points) {
    hand.addCard(Card.of(Suit.CLUBS, rank));

    assertThat(hand.getScore(), is(points));
  }

  @Test
  public void add_up_points_of_hand() {
    hand.addCard(Card.of(Suit.CLUBS, Rank.SEVEN));
    hand.addCard(Card.of(Suit.CLUBS, Rank.TWO));
    hand.addCard(Card.of(Suit.CLUBS, Rank.ACE));

    assertThat(hand.getScore(), is(20));
  }

  @Test
  public void set_value_of_two_ace_to_1_when_score_would_be_greater_than_21() {
    hand.addCard(Card.of(Suit.CLUBS, Rank.EIGHT));
    hand.addCard(Card.of(Suit.CLUBS, Rank.ACE));
    hand.addCard(Card.of(Suit.CLUBS, Rank.ACE));
    hand.addCard(Card.of(Suit.CLUBS, Rank.ACE));

    assertThat(hand.getScore(), is(21));
  }

  @Test
  public void return_value_greater_21_when_sum_greater_21() {
    hand.addCard(Card.of(Suit.CLUBS, Rank.NINE));
    hand.addCard(Card.of(Suit.CLUBS, Rank.TEN));
    hand.addCard(Card.of(Suit.CLUBS, Rank.ACE));
    hand.addCard(Card.of(Suit.CLUBS, Rank.ACE));
    hand.addCard(Card.of(Suit.CLUBS, Rank.ACE));

    assertThat(hand.getScore(), is(22));
  }
}
