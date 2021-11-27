package ch.uzh.group8.checkersv3;

import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Column.*;
import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Row.*;
import static ch.uzh.group8.checkersv3.util.CoinTosser.Result;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

import ch.uzh.group8.checkersv3.dom.BoardCoordinates;
import ch.uzh.group8.checkersv3.dom.JumpGambleResult;
import ch.uzh.group8.checkersv3.dom.Move;
import ch.uzh.group8.checkersv3.dom.Player;
import ch.uzh.group8.checkersv3.dom.board.Board;
import ch.uzh.group8.checkersv3.util.CoinTosser;
import ch.uzh.group8.checkersv3.util.Console;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveExecutorTest {

  private static final Move NORMAL_MOVE =
      Move.of(Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_2, B));
  private static final Move JUMP_MOVE =
      Move.of(Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_3, C));
  private static final Move JUMP_WITH_WON_GAMBLE_RESULT =
      JUMP_MOVE.withJumpGambleResult(JumpGambleResult.WON);
  private static final Move JUMP_WITH_LOST_GAMBLE_RESULT =
      JUMP_MOVE.withJumpGambleResult(JumpGambleResult.LOST);
  private CoinTosser coinTosser;
  private Board board;
  private MoveExecutor moveExecutor;
  private Console console;

  @BeforeEach
  public void setup() {
    coinTosser = mock(CoinTosser.class);
    board = mock(Board.class);
    console = mock(Console.class);
    moveExecutor = new MoveExecutor(board, coinTosser, console);
  }

  @Test
  public void execute_move_normally_if_no_jump_move() {
    Move move = NORMAL_MOVE;

    Move executedMove = moveExecutor.executeMove(move);

    verify(board).executeMove(same(move));
    assertThat(executedMove, sameInstance(move));
  }

  @Test
  public void ask_player_if_he_wants_to_gamble_for_jump_move() {
    when(console.getUserInput()).thenThrow(RuntimeException.class);

    Assertions.assertThrows(RuntimeException.class, () -> moveExecutor.executeMove(JUMP_MOVE));

    verify(console).getUserInput();
  }

  @Test
  public void ask_player_if_he_wants_to_gamble_until_he_types_yes() {
    when(console.getUserInput()).thenReturn("blabla").thenReturn("invalid").thenReturn("yes");

    moveExecutor.executeMove(JUMP_MOVE);

    verify(console, times(3)).getUserInput();
  }

  @Test
  public void ask_player_if_he_wants_to_gamble_until_he_types_no() {
    when(console.getUserInput()).thenReturn("blabla").thenReturn("invalid").thenReturn("no");

    moveExecutor.executeMove(JUMP_MOVE);

    verify(console, times(3)).getUserInput();
  }

  @Test
  public void execute_move_normally_if_player_types_no() {
    when(console.getUserInput()).thenReturn("no");

    Move executedMove = moveExecutor.executeMove(JUMP_MOVE);

    verify(board).executeMove(same(JUMP_MOVE));
    assertThat(executedMove, sameInstance(JUMP_MOVE));
  }

  @Test
  public void execute_gamble_win_move_if_player_types_yes_and_wins() {
    when(console.getUserInput()).thenReturn("yes");
    when(coinTosser.toss(notNull(), notNull())).thenReturn(Result.HEADS);

    Move executedMove = moveExecutor.executeMove(JUMP_MOVE);

    verify(board).executeMove(eq(JUMP_WITH_WON_GAMBLE_RESULT));
    assertThat(executedMove, Matchers.is(JUMP_WITH_WON_GAMBLE_RESULT));
  }

  @Test
  public void execute_gamble_lost_move_if_player_types_yes_and_loses() {
    when(console.getUserInput()).thenReturn("yes");
    when(coinTosser.toss(notNull(), notNull())).thenReturn(Result.TAILS);

    Move executedMove = moveExecutor.executeMove(JUMP_MOVE);

    verify(board).executeMove(eq(JUMP_WITH_LOST_GAMBLE_RESULT));
    assertThat(executedMove, Matchers.is(JUMP_WITH_LOST_GAMBLE_RESULT));
  }
}
