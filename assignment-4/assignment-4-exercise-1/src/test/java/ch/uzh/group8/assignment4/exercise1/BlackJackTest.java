package ch.uzh.group8.assignment4.exercise1;

import static org.mockito.Mockito.*;

import ch.uzh.group8.assignment4.exercise1.state.Dealer;
import ch.uzh.group8.assignment4.exercise1.state.HumanPlayer;
import ch.uzh.group8.assignment4.exercise1.state.Pack;
import ch.uzh.group8.assignment4.exercise1.util.Console;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackJackTest {

  private static final int LOW_SCORE = 4;
  private static final int SCORE = 5;
  private static final int HIGH_SCORE = 6;
  private Console console;
  private HumanPlayer humanPlayer;
  private Dealer dealer;
  private Play play;
  private BlackJack blackJack;

  @BeforeEach
  public void setup() {
    console = mock(Console.class);
    humanPlayer = mock(HumanPlayer.class);
    dealer = mock(Dealer.class);
    Pack pack = mock(Pack.class);
    play = mock(Play.class);
    blackJack = new BlackJack(console, humanPlayer, dealer, pack, play);
  }

  @Test
  public void stop_if_player_does_not_want_to_play_at_start() {
    when(humanPlayer.wantsToContinue()).thenReturn(false);

    blackJack.run();

    verify(humanPlayer, never()).startRound();
    verify(dealer, never()).startRound();
  }

  @Test
  public void not_execute_dealer_play_if_player_was_busted() {
    when(humanPlayer.wantsToContinue()).thenReturn(true, false);
    when(play.doPlayFor(humanPlayer)).thenReturn(Optional.empty());

    blackJack.run();

    verify(play, never()).doPlayFor(dealer);
  }

  @Test
  public void player_looses_if_he_is_busted() {
    when(humanPlayer.wantsToContinue()).thenReturn(true, false);
    when(play.doPlayFor(humanPlayer)).thenReturn(Optional.empty());

    blackJack.run();

    verify(humanPlayer).applyLost();
  }

  @Test
  public void player_looses_if_he_has_lower_score_than_dealer() {
    when(humanPlayer.wantsToContinue()).thenReturn(true, false);
    when(play.doPlayFor(humanPlayer)).thenReturn(Optional.of(LOW_SCORE));
    when(play.doPlayFor(dealer)).thenReturn(Optional.of(HIGH_SCORE));

    blackJack.run();

    verify(humanPlayer).applyLost();
  }

  @Test
  public void player_wins_if_dealer_is_busted() {
    when(humanPlayer.wantsToContinue()).thenReturn(true, false);
    when(play.doPlayFor(humanPlayer)).thenReturn(Optional.of(SCORE));
    when(play.doPlayFor(dealer)).thenReturn(Optional.empty());

    blackJack.run();

    verify(humanPlayer).applyWon();
  }

  @Test
  public void player_wins_if_he_has_higher_score_than_dealer() {
    when(humanPlayer.wantsToContinue()).thenReturn(true, false);
    when(play.doPlayFor(humanPlayer)).thenReturn(Optional.of(HIGH_SCORE));
    when(play.doPlayFor(dealer)).thenReturn(Optional.of(LOW_SCORE));

    blackJack.run();

    verify(humanPlayer).applyWon();
  }

  @Test
  public void player_does_not_loose_or_win_when_its_a_draw() {
    when(humanPlayer.wantsToContinue()).thenReturn(true, false);
    when(play.doPlayFor(humanPlayer)).thenReturn(Optional.of(SCORE));
    when(play.doPlayFor(dealer)).thenReturn(Optional.of(SCORE));

    blackJack.run();

    verify(humanPlayer, never()).applyWon();
    verify(humanPlayer, never()).applyLost();
  }

  @Test
  public void its_possible_to_play_multiple_rounds() {
    when(humanPlayer.wantsToContinue()).thenReturn(true, true, true, true, false);
    when(play.doPlayFor(humanPlayer)).thenReturn(Optional.of(HIGH_SCORE));
    when(play.doPlayFor(dealer)).thenReturn(Optional.of(LOW_SCORE));

    blackJack.run();

    verify(humanPlayer, times(4)).applyWon();
  }

  @Test
  public void game_ends_if_player_is_broke() {
    when(humanPlayer.wantsToContinue()).thenReturn(true);
    when(humanPlayer.isBroke()).thenReturn(true);
    when(play.doPlayFor(humanPlayer)).thenReturn(Optional.empty());

    blackJack.run();

    verify(humanPlayer).applyLost();
    verify(console).print("You lost all your money, see you next time");
  }
}
