package ch.uzh.group8.assignment4.exercise1;

import static ch.uzh.group8.assignment4.exercise1.Rank.*;
import static ch.uzh.group8.assignment4.exercise1.Suit.CLUBS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import ch.uzh.group8.assignment4.exercise1.util.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;

class HumanPlayerTest {

  private Console console;
  private HumanPlayer humanPlayer;

  @BeforeEach
  public void setup() {
    console = mock(Console.class);
    humanPlayer = new HumanPlayer(console);
  }

  @Test
  public void has_empty_hand_when_initialized() {
    humanPlayer.printAllCards();

    verify(console).print("");
  }

  @Test
  public void clears_hand_on_startRound() {
    InOrder inOrder = inOrder(console);
    humanPlayer.addCard(Card.of(CLUBS, ACE));
    humanPlayer.printAllCards();

    humanPlayer.startRound();
    humanPlayer.printAllCards();

    inOrder.verify(console).print(ArgumentMatchers.argThat(s -> s.length() > 0));
    inOrder.verify(console).print("");
  }

  @Test
  public void print_whole_hand_on_printInitialHand() {
    InOrder inOrder = inOrder(console);
    humanPlayer.addCard(Card.of(CLUBS, ACE));
    humanPlayer.addCard(Card.of(CLUBS, TEN));

    humanPlayer.printInitialHand();

    inOrder
        .verify(console)
        .print(
            """
          Card 1: Card[suit=CLUBS, rank=ACE]
          Card 2: Card[suit=CLUBS, rank=TEN]""");
  }

  @Test
  public void calculates_score_correctly() {
    humanPlayer.addCard(Card.of(CLUBS, ACE));
    humanPlayer.addCard(Card.of(CLUBS, TEN));

    assertThat(humanPlayer.getScore(), is(21));
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          hit,HIT
          stay,STAY
          HIT,HIT
          STAY,STAY
          '   HIT   ',HIT
          'STAY ',STAY
          """)
  public void get_hit_or_stay(String input, PlayAction playAction) {
    when(console.getUserInput()).thenReturn(input);

    assertThat(humanPlayer.hitOrStay(), is(playAction));
  }

  @Test
  public void repeat_getting_user_input_for_hit_or_stay_if_invalid() {
    when(console.getUserInput()).thenReturn("", "blabla", "s tay", "stay");

    assertThat(humanPlayer.hitOrStay(), is(PlayAction.STAY));
  }

  @Test
  public void start_with_a_balance_of_100() {
    humanPlayer.printBalance();

    verify(console).print("Your current balance is: 100");
  }

  @Test
  public void increase_balance_by_bet_if_won() {
    InOrder inOrder = inOrder(console);
    when(console.getUserInput()).thenReturn("50");

    humanPlayer.placeBet();
    humanPlayer.applyWon();
    humanPlayer.printBalance();

    inOrder.verify(console).print("Your current balance is: 150");
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void decrease_balance_by_bet_if_lost() {
    InOrder inOrder = inOrder(console);
    when(console.getUserInput()).thenReturn("50");

    humanPlayer.placeBet();
    humanPlayer.applyLost();
    humanPlayer.printBalance();

    inOrder.verify(console).print("Your current balance is: 50");
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void reset_current_bet_on_startRound() {
    InOrder inOrder = inOrder(console);
    when(console.getUserInput()).thenReturn("50");

    humanPlayer.placeBet();
    humanPlayer.startRound();
    humanPlayer.applyLost();
    humanPlayer.printBalance();

    inOrder.verify(console).print("Your current balance is: 100");
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void repeat_getting_bet_as_long_as_the_user_inputs_invalid_bets() {
    InOrder inOrder = inOrder(console);
    when(console.getUserInput()).thenReturn("", "-1", "1.0", "101", " 50 ");

    humanPlayer.placeBet();
    humanPlayer.applyLost();
    humanPlayer.printBalance();

    inOrder.verify(console).print("Your current balance is: 50");
    inOrder.verifyNoMoreInteractions();
  }
}
