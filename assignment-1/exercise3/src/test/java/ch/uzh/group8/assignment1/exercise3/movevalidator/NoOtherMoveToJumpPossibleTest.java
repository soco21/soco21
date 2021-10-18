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

class NoOtherMoveToJumpPossibleTest {
  private static final Piece WHITE_PAWN = new Piece(Player.PLAYER_WHITE, false);
  private static final Piece RED_PAWN = new Piece(Player.PLAYER_RED, false);
  private static final Piece RED_KING = new Piece(Player.PLAYER_RED, true);

  private NoOtherMoveToJumpPossible noOtherMoveToJumpPossible;
  private Board board;

  @BeforeEach
  public void setup() {
    noOtherMoveToJumpPossible =
        new NoOtherMoveToJumpPossible(
            new MoveIsForwardIfNotKing(), new OpponentPieceBetweenJump(), new TargetFieldEmpty());
    board = mock(Board.class);
  }

  @Test
  public void return_true_if_no_other_pieces_exist() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.empty());

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));
    assertThat(noOtherMoveToJumpPossible.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_move_is_jump_move() {
    when(board.getPieceAt(notNull())).thenReturn(Optional.empty());

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_3, Column.C));
    assertThat(noOtherMoveToJumpPossible.validate(move, board), is(true));
  }

  @Test
  public void does_not_crash_if_piece_is_at_edge() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_1, Column.A)))
        .thenReturn(Optional.of(WHITE_PAWN));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_8, Column.A)))
        .thenReturn(Optional.of(WHITE_PAWN));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_1, Column.G)))
        .thenReturn(Optional.of(WHITE_PAWN));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_8, Column.G)))
        .thenReturn(Optional.of(WHITE_PAWN));

    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));
    assertThat(noOtherMoveToJumpPossible.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_other_jump_move_possible_for_white() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_4, Column.E)))
        .thenReturn(Optional.of(WHITE_PAWN));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_5, Column.F)))
        .thenReturn(Optional.of(RED_PAWN));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_6, Column.G))).thenReturn(Optional.empty());

    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));
    assertThat(noOtherMoveToJumpPossible.validate(move, board), is(false));
  }

  @Test
  public void return_false_if_other_jump_move_possible_for_red() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_6, Column.G)))
        .thenReturn(Optional.of(RED_PAWN));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_5, Column.F)))
        .thenReturn(Optional.of(WHITE_PAWN));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_4, Column.E))).thenReturn(Optional.empty());

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));
    assertThat(noOtherMoveToJumpPossible.validate(move, board), is(false));
  }

  @Test
  public void return_false_if_other_jump_move_with_king_possible_for_red() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_4, Column.E)))
        .thenReturn(Optional.of(RED_KING));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_5, Column.F)))
        .thenReturn(Optional.of(WHITE_PAWN));
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_6, Column.G))).thenReturn(Optional.empty());

    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));
    assertThat(noOtherMoveToJumpPossible.validate(move, board), is(false));
  }
}
