package ch.uzh.group8.assignment4.exercise1;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import ch.uzh.group8.assignment4.exercise1.domain.Card;
import ch.uzh.group8.assignment4.exercise1.domain.Rank;
import ch.uzh.group8.assignment4.exercise1.domain.Suit;
import ch.uzh.group8.assignment4.exercise1.state.Pack;
import ch.uzh.group8.assignment4.exercise1.state.Player;
import ch.uzh.group8.assignment4.exercise1.util.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayTest {

  private static final int SOME_SCORE = 1;
  private static final Card SOME_CARD = Card.of(Suit.CLUBS, Rank.ACE);

  private Pack pack;
  private Play play;
  private Player player;

  @BeforeEach
  public void setup() {
    pack = mock(Pack.class);
    Console console = mock(Console.class);
    play = new Play(pack, console);
    player = mock(Player.class);

    when(pack.drawCard()).thenReturn(SOME_CARD);
  }

  @Test
  public void stop_if_player_wants_to_stay() {
    when(player.hitOrStay()).thenReturn(PlayAction.STAY);
    when(player.getScore()).thenReturn(SOME_SCORE);

    assertThat(play.doPlayFor(player), is(of(SOME_SCORE)));
    verify(pack, never()).drawCard();
    verify(player, never()).addCard(notNull());
  }

  @Test
  public void continue_as_long_as_player_hits() {
    when(player.hitOrStay())
        .thenReturn(PlayAction.HIT, PlayAction.HIT, PlayAction.HIT, PlayAction.STAY);
    when(player.getScore()).thenReturn(SOME_SCORE);

    assertThat(play.doPlayFor(player), is(of(SOME_SCORE)));
    verify(pack, times(3)).drawCard();
    verify(player, times(3)).addCard(notNull());
  }

  @Test
  public void stop_if_player_gets_busted() {
    when(player.hitOrStay())
        .thenReturn(PlayAction.HIT, PlayAction.HIT, PlayAction.HIT, PlayAction.STAY);
    when(player.getScore()).thenReturn(SOME_SCORE, SOME_SCORE, 22);

    assertThat(play.doPlayFor(player), is(empty()));
    verify(pack, times(2)).drawCard();
    verify(player, times(2)).addCard(notNull());
  }
}
