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

class OpponentPieceBetweenJumpTest {
  private static final Piece WHITE_PAWN = new Piece(Player.PLAYER_WHITE, false);
  private static final Piece RED_PAWN = new Piece(Player.PLAYER_RED, false);

  private OpponentPieceBetweenJump opponentPieceBetweenJump;
  private Board board;

  @BeforeEach
  public void setup() {
    opponentPieceBetweenJump = new OpponentPieceBetweenJump();
    board = mock(Board.class);
  }

  @Test
  public void return_true_if_no_jump() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));
    assertThat(opponentPieceBetweenJump.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_no_piece_between_jump() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_2, Column.B))).thenReturn(Optional.empty());

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_3, Column.C));
    assertThat(opponentPieceBetweenJump.validate(move, board), is(false));
  }

  @Test
  public void return_true_if_piece_between_belongs_to_opponent_white() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_2, Column.B)))
        .thenReturn(Optional.of(RED_PAWN));

    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_3, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.C));
    assertThat(opponentPieceBetweenJump.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_piece_between_belongs_to_opponent_red() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_2, Column.B)))
        .thenReturn(Optional.of(WHITE_PAWN));

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.C),
            new BoardCoordinates(Row.ROW_3, Column.A));
    assertThat(opponentPieceBetweenJump.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_piece_between_belongs_to_same_player_white() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_2, Column.B)))
        .thenReturn(Optional.of(WHITE_PAWN));

    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_3, Column.C),
            new BoardCoordinates(Row.ROW_1, Column.A));
    assertThat(opponentPieceBetweenJump.validate(move, board), is(false));
  }

  @Test
  public void return_false_if_piece_between_belongs_to_same_player_red() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.of(RED_PAWN));

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_3, Column.C));
    assertThat(opponentPieceBetweenJump.validate(move, board), is(false));
  }
}
