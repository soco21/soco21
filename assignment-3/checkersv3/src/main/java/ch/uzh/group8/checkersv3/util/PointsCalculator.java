package ch.uzh.group8.checkersv3.util;

import static ch.uzh.group8.checkersv3.dom.Player.PLAYER_RED;
import static ch.uzh.group8.checkersv3.dom.Player.PLAYER_WHITE;

import ch.uzh.group8.checkersv3.dom.*;
import java.util.HashMap;
import java.util.Optional;

public class PointsCalculator {

  private final Board board;

  public PointsCalculator(Board board) {
    this.board = board;
  }

  private final HashMap<Player, Float> pointsOnBoard = new HashMap<>();

  public HashMap calculatePoints(Board board) {

    float pointsPlayerWhite = 0;
    float pointsPlayerRed = 0;
    BoardCoordinates.Row[] rows = BoardCoordinates.Row.values();
    BoardCoordinates.Column[] columns = BoardCoordinates.Column.values();
    for (BoardCoordinates.Row row : rows) {
      for (BoardCoordinates.Column col : columns) {
        BoardCoordinates currentCoordinates = new BoardCoordinates(row, col);
        Optional<Piece> pieceAt = this.board.getPieceAt(currentCoordinates);
        if (pieceAt.isPresent()) {
          if (pieceAt.get().owner() == PLAYER_WHITE) {
            if (pieceAt.get().isKing()) {
              pointsPlayerWhite += 2;
            } else {
              pointsPlayerWhite += 1;
            }
          } else {
            if (pieceAt.get().isKing()) {
              pointsPlayerRed += 2;
            } else {
              pointsPlayerRed += 1;
            }
          }
        }
      }
    }
    pointsOnBoard.put(PLAYER_WHITE, pointsPlayerWhite);
    pointsOnBoard.put(PLAYER_RED, pointsPlayerRed);

    return pointsOnBoard;
  }
}
