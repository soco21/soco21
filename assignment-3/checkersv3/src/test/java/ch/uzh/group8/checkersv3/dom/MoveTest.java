package ch.uzh.group8.checkersv3.dom;

import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Column.*;
import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Row.*;
import static ch.uzh.group8.checkersv3.dom.JumpGambleResult.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoveTest {
  @Test
  public void throw_for_empty_string() {
    assertThrows(IllegalArgumentException.class, () -> Move.parse(Player.PLAYER_WHITE, ""));
  }

  @Test
  public void throw_if_string_contains_only_delimiter() {
    assertThrows(IllegalArgumentException.class, () -> Move.parse(Player.PLAYER_WHITE, "x"));
  }

  @Test
  public void throw_if_string_contains_only_move_start() {
    assertThrows(IllegalArgumentException.class, () -> Move.parse(Player.PLAYER_WHITE, "a3x"));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "a9xb3", "i9xb3", "y9xb3", "a9x33", "a9xä3",
      })
  public void throw_if_string_contains_invalid_column(String input) {
    assertThrows(IllegalArgumentException.class, () -> Move.parse(Player.PLAYER_WHITE, input));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "a9xb3", "a0xb3", "a-1xb3", "a0.5xb3", "a xb3",
      })
  public void throw_if_string_contains_invalid_row(String input) {
    assertThrows(IllegalArgumentException.class, () -> Move.parse(Player.PLAYER_WHITE, input));
  }

  @Test
  public void parse_correct_move() {
    Move expectedMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_3, A), new BoardCoordinates(ROW_4, B));
    assertThat(Move.parse(Player.PLAYER_WHITE, "a3xb4"), is(expectedMove));
    assertThat(Move.parse(Player.PLAYER_WHITE, "A3xB4"), is(expectedMove));
    assertThat(Move.parse(Player.PLAYER_WHITE, "a3Xb4"), is(expectedMove));
    assertThat(Move.parse(Player.PLAYER_WHITE, "[a3]Xb4"), is(expectedMove));
    assertThat(Move.parse(Player.PLAYER_WHITE, "[a3Xb4"), is(expectedMove));
    assertThat(Move.parse(Player.PLAYER_WHITE, "[a3]X[b4]"), is(expectedMove));
  }

  @Test
  public void generate_possible_moves() {
    assertThat(
        Move.generatePossibleMoves(
            new BoardCoordinates(ROW_4, D), Player.PLAYER_WHITE, List.of(1, 2)),
        hasSize(8));
    assertThat(
        Move.generatePossibleMoves(
            new BoardCoordinates(ROW_1, A), Player.PLAYER_WHITE, List.of(1, 2)),
        hasSize(2));
    assertThat(
        Move.generatePossibleMoves(
            new BoardCoordinates(ROW_8, A), Player.PLAYER_WHITE, List.of(1, 2)),
        hasSize(2));
    assertThat(
        Move.generatePossibleMoves(
            new BoardCoordinates(ROW_8, H), Player.PLAYER_WHITE, List.of(1, 2)),
        hasSize(2));
    assertThat(
        Move.generatePossibleMoves(
            new BoardCoordinates(ROW_1, H), Player.PLAYER_WHITE, List.of(1, 2)),
        hasSize(2));
    assertThat(
        Move.generatePossibleMoves(
            new BoardCoordinates(ROW_1, G), Player.PLAYER_WHITE, List.of(1, 2)),
        hasSize(3));
    assertThat(
        Move.generatePossibleMoves(
            new BoardCoordinates(ROW_1, F), Player.PLAYER_WHITE, List.of(1, 2)),
        hasSize(4));
  }

  @Test
  public void return_empty_for_getCoordinatesBetween_for_simple_move() {
    Move simpleMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_2, B));
    assertThat(simpleMove.getCoordinatesBetween(), is(Optional.empty()));
  }

  @Test
  public void calculate_correct_coordinates_between_for_jump_move() {
    Move jumpMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_3, C));
    assertThat(jumpMove.getCoordinatesBetween(), is(Optional.of(new BoardCoordinates(ROW_2, B))));
  }

  @Test
  public void return_true_for_isJumpMove_on_jump_move() {
    Move jumpMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_3, C));
    assertThat(jumpMove.isJumpMove(), is(true));
  }

  @Test
  public void return_false_for_isJumpMove_on_move_from_same_position_to_same_position() {
    Move jumpMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_1, A));
    assertThat(jumpMove.isJumpMove(), is(false));
  }

  @Test
  public void return_false_for_isJumpMove_on_simple_move() {
    Move simpleMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_2, B));
    assertThat(simpleMove.isJumpMove(), is(false));
  }

  @Test
  public void throw_if_withJumpGambleResult_called_with_WON_on_simple_move() {
    Move simpleMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_2, B));
    assertThrows(IllegalArgumentException.class, () -> simpleMove.withJumpGambleResult(WON));
  }

  @Test
  public void throw_if_withJumpGambleResult_called_with_LOST_on_simple_move() {
    Move simpleMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_2, B));
    assertThrows(
        IllegalArgumentException.class,
        () -> simpleMove.withJumpGambleResult(JumpGambleResult.LOST));
  }

  @Test
  public void not_change_anything_if_withJumpGambleResult_called_with_NO_GAMBLE_on_simple_move() {
    Move simpleMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_2, B));
    assertThat(simpleMove.withJumpGambleResult(NO_GAMBLE), is(simpleMove));
  }

  @Test
  public void change_to_jumpGambleResult_given_with_withJumpGambleResult() {
    Move jumpMove =
        Move.of(
            Player.PLAYER_WHITE, new BoardCoordinates(ROW_1, A), new BoardCoordinates(ROW_3, C));
    assertThat(jumpMove.withJumpGambleResult(WON).jumpGambleResult(), is(WON));
    assertThat(jumpMove.withJumpGambleResult(NO_GAMBLE).jumpGambleResult(), is(NO_GAMBLE));
    assertThat(jumpMove.withJumpGambleResult(LOST).jumpGambleResult(), is(LOST));
  }
}
