package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.Column;
import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.Row;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.uzh.group8.assignment1.exercise3.movevalidator.MoveValidator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WinConditionTest {

  private static final Piece WHITE_PAWN = new Piece(Player.PLAYER_WHITE, false);
  private static final Piece RED_PAWN = new Piece(Player.PLAYER_RED, false);
  private MoveValidator moveValidator1;
  private MoveValidator moveValidator2;
  private WinCondition winCondition;
  private Board board;

  @BeforeEach
  public void setup() {
    moveValidator1 = mock(MoveValidator.class);
    moveValidator2 = mock(MoveValidator.class);
    winCondition = new WinCondition(List.of(moveValidator1, moveValidator2));

    board = mock(Board.class);
  }

  @Test
  public void player_has_won_if_other_player_has_no_pieces() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.empty());
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_4, Column.E)))
        .thenReturn(Optional.of(RED_PAWN));

    assertThat(winCondition.hasPlayerWon(Player.PLAYER_RED, board), is(true));
  }

  @Test
  public void player_has_not_won_if_other_player_can_move_a_piece() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_4, Column.E)))
        .thenReturn(Optional.of(WHITE_PAWN));
    when(moveValidator1.validate(notNull(), notNull())).thenReturn(true);
    when(moveValidator2.validate(notNull(), notNull())).thenReturn(true);

    assertThat(winCondition.hasPlayerWon(Player.PLAYER_RED, board), is(false));
  }

  @Test
  public void player_has_won_if_other_player_has_a_piece_but_cannot_move_it() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_4, Column.E)))
        .thenReturn(Optional.of(WHITE_PAWN));
    when(moveValidator1.validate(notNull(), notNull())).thenReturn(false);
    when(moveValidator2.validate(notNull(), notNull())).thenReturn(true);

    assertThat(winCondition.hasPlayerWon(Player.PLAYER_RED, board), is(true));
  }
}
