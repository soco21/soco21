package ch.uzh.group8.checkersv3.movevalidator;

import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Column;
import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Row;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ch.uzh.group8.checkersv3.dom.Board;
import ch.uzh.group8.checkersv3.dom.BoardCoordinates;
import ch.uzh.group8.checkersv3.dom.Move;
import ch.uzh.group8.checkersv3.dom.Player;
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
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.B));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_move_up_left() {
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.B),
            new BoardCoordinates(Row.ROW_2, Column.A));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_move_down_right() {
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_2, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.B));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_move_down_left() {
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_2, Column.B),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_jump_move_up_right() {
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_3, Column.C));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_true_for_diagonal_jump_move_down_left() {
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_3, Column.C),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsDiagonal.validate(move, board), is(true));
  }

  @Test
  public void return_false_for_move_to_the_right() {
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.B));

    assertThat(moveIsDiagonal.validate(move, board), is(false));
  }

  @Test
  public void return_false_for_move_up() {
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_1, Column.A));

    assertThat(moveIsDiagonal.validate(move, board), is(false));
  }

  @Test
  public void return_false_for_move_one_up_and_two_right() {
    Move move =
        Move.of(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_1, Column.A),
            new BoardCoordinates(Row.ROW_2, Column.C));

    assertThat(moveIsDiagonal.validate(move, board), is(false));
  }
}
