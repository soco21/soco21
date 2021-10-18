package ch.uzh.group8.assignment1.exercise3.movevalidator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.BoardCoordinates;
import ch.uzh.group8.assignment1.exercise3.Move;
import ch.uzh.group8.assignment1.exercise3.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveLengthTest {
  private MoveLength moveLength;
  private Board board;

  @BeforeEach
  public void setup() {
    moveLength = new MoveLength();
    board = new Board();
  }

  @Test
  public void return_true_if_player_moves_1_row() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_2, BoardCoordinates.Column.A));
    assertThat(moveLength.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_player_moves_2_row() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_3, BoardCoordinates.Column.A));
    assertThat(moveLength.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_player_moves_3_row() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_4, BoardCoordinates.Column.A));
    assertThat(moveLength.validate(move, board), is(false));
  }

  @Test
  public void return_true_if_player_moves_7_row() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_8, BoardCoordinates.Column.A));
    assertThat(moveLength.validate(move, board), is(false));
  }

  @Test
  public void return_true_if_player_moves_1_col() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.B));
    assertThat(moveLength.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_player_moves_2_col() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.C));
    assertThat(moveLength.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_player_moves_3_col() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.D));
    assertThat(moveLength.validate(move, board), is(false));
  }

  @Test
  public void return_true_if_player_moves_7_col() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.H));
    assertThat(moveLength.validate(move, board), is(false));
  }

  @Test
  public void return_true_if_player_moves_1_row_back() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_2, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A));
    assertThat(moveLength.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_player_moves_1_col_back() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.B),
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A));
    assertThat(moveLength.validate(move, board), is(true));
  }

  @Test
  public void return_true_if_player_moves_1_col_and_2_rows() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.B),
            new BoardCoordinates(BoardCoordinates.Row.ROW_3, BoardCoordinates.Column.A));
    assertThat(moveLength.validate(move, board), is(true));
  }

  @Test
  public void return_false_if_move_does_not_change_position() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A),
            new BoardCoordinates(BoardCoordinates.Row.ROW_1, BoardCoordinates.Column.A));
    assertThat(moveLength.validate(move, board), is(false));
  }
}
