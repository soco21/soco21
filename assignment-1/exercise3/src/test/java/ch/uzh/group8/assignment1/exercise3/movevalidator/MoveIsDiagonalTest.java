package ch.uzh.group8.assignment1.exercise3.movevalidator;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.Column;
import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.Row;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.BoardCoordinates;
import ch.uzh.group8.assignment1.exercise3.Move;
import ch.uzh.group8.assignment1.exercise3.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveIsDiagonalTest {

  private MoveIsDiagonal moveIsDiagonal;
  private Board board;

  @BeforeEach
  public void setup() {
    moveIsDiagonal = new MoveIsDiagonal();
    board = new Board();
  }

  @Test
  public void return_true_for_diagonal_move_up_right() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_move_up_left() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.B),
            new BoardCoordinates(Row.ROW_2, Column.A));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_move_down_right() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_2, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.B));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_move_down_left() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_2, Column.B),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_jump_move_up_right() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_3, Column.C));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_jump_move_down_left() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_3, Column.C),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_false_for_move_to_the_right() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.B));

    assertThat(moveIsDiagonal.validate(move, board), is(false));
  }

  @Test
  public void return_false_for_move_up() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsDiagonal.validate(move, board), is(false));
  }

  @Test
  public void return_false_for_move_one_up_and_two_right() {
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.C));

    assertThat(moveIsDiagonal.validate(move, board), is(false));
  }
}
