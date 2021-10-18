package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;
import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class BoardTest {
  @Test
  public void move_a_piece_from_a_to_b() {
    var board = new Board();
    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_3, Column.A),
            new BoardCoordinates(Row.ROW_4, Column.B));

    board.executeMove(move);

    assertThat(board.getPieceAt(move.start()), is(empty()));
    assertThat(
        board.getPieceAt(move.end()), is(Optional.of(new Piece(Player.PLAYER_WHITE, false))));
  }

  @Test
  public void remove_piece_between_jump_move() {
    var board = new Board();
    var move1 =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_3, Column.A),
            new BoardCoordinates(Row.ROW_4, Column.B));
    var move2 =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_6, Column.B),
            new BoardCoordinates(Row.ROW_5, Column.C));
    var move3 =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_3, Column.C),
            new BoardCoordinates(Row.ROW_4, Column.D));
    var jumpMove =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_5, Column.C),
            new BoardCoordinates(Row.ROW_3, Column.A));

    List.of(move1, move2, move3, jumpMove).forEach(board::executeMove);

    assertThat(
        board.getPieceAt(move1.start()), is(Optional.of(new Piece(Player.PLAYER_RED, false))));
    assertThat(board.getPieceAt(move1.end()), is(empty()));
  }

  @Test
  public void convert_piece_to_king_if_at_end_for_white() {
    var board = new Board();
    // this move is not valid, but the board doesn't know that
    var move =
        new Move(
            Player.PLAYER_WHITE,
            new BoardCoordinates(Row.ROW_3, Column.A),
            new BoardCoordinates(Row.ROW_8, Column.B));

    board.executeMove(move);

    assertThat(board.getPieceAt(move.start()), is(empty()));
    assertThat(board.getPieceAt(move.end()), is(Optional.of(new Piece(Player.PLAYER_WHITE, true))));
  }

  @Test
  public void convert_piece_to_king_if_at_end_for_red() {
    var board = new Board();
    // this move is not valid, but the board doesn't know that
    var move =
        new Move(
            Player.PLAYER_RED,
            new BoardCoordinates(Row.ROW_6, Column.B),
            new BoardCoordinates(Row.ROW_1, Column.B));

    board.executeMove(move);

    assertThat(board.getPieceAt(move.start()), is(empty()));
    assertThat(board.getPieceAt(move.end()), is(Optional.of(new Piece(Player.PLAYER_RED, true))));
  }
}
