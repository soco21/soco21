package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.dom.Board;
import ch.uzh.group8.checkersv2.dom.Move;

public class OpponentPieceBetweenJump implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var coordinatesBetween = move.getCoordinatesBetween();
    if (coordinatesBetween.isEmpty()) {
      return true;
    }
    var pieceBetweenJump = board.getPieceAt(coordinatesBetween.get());
    if (pieceBetweenJump.isEmpty()) {
      return false;
    }
    return pieceBetweenJump.get().owner() != move.player();
  }
}
