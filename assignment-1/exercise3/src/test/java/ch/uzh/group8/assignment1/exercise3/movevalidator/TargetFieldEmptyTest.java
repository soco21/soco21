package ch.uzh.group8.assignment1.exercise3.movevalidator;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.uzh.group8.assignment1.exercise3.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TargetFieldEmptyTest {
  public static final Piece PIECE = new Piece(Player.PLAYER_WHITE, false);

  private TargetFieldEmpty targetFieldEmpty;
  private Board board;

  @BeforeEach
  public void setup() {
    targetFieldEmpty = new TargetFieldEmpty();
    board = mock(Board.class);
  }

  @Test
  public void return_true_if_target_field_empty() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.empty());

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.A));
    assertThat(targetFieldEmpty.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_target_field_not_empty() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(PIECE));

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.A));
    assertThat(targetFieldEmpty.validate(move, board), is(false));
  }
}
