package ch.uzh.group8.checkersv3.util;

import static ch.uzh.group8.checkersv3.dom.Player.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import ch.uzh.group8.checkersv3.dom.Board;
import ch.uzh.group8.checkersv3.dom.Piece;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointsCalculatorTest {
  private PointsCalculator pointsCalculator;
  private Board board;

  @BeforeEach
  public void setup() {
    pointsCalculator = new PointsCalculator();
    board = spy(new Board());
  }

  @Test
  public void calculate_points_correctly_at_start() {
    assertThat(
        pointsCalculator.calculatePoints(board), is(Map.of(PLAYER_WHITE, 12, PLAYER_RED, 12)));
  }

  @Test
  public void calculate_points_if_no_pieces_are_on_board() {
    doReturn(Optional.empty()).when(board).getPieceAt(notNull());
    assertThat(pointsCalculator.calculatePoints(board), is(Map.of(PLAYER_WHITE, 0, PLAYER_RED, 0)));
  }

  @Test
  public void use_2_as_value_for_kings() {
    doReturn(Optional.of(new Piece(PLAYER_WHITE, true)), Optional.of(new Piece(PLAYER_RED, true)))
        .when(board)
        .getPieceAt(notNull());
    assertThat(
        pointsCalculator.calculatePoints(board), is(Map.of(PLAYER_WHITE, 2, PLAYER_RED, 126)));
  }
}
