package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.dom.Board;
import ch.uzh.group8.checkersv2.dom.BoardCoordinates;
import ch.uzh.group8.checkersv2.dom.Move;
import ch.uzh.group8.checkersv2.dom.Piece;
import java.util.Optional;

public class OpponentPieceBetweenJump implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    Optional<BoardCoordinates> coordinatesBetween = move.getCoordinatesBetween();
    if (coordinatesBetween.isEmpty()) {
      return true;
    }
    Optional<Piece> pieceBetweenJump = board.getPieceAt(coordinatesBetween.get());
    if (pieceBetweenJump.isEmpty()) {
      return false;
    }
    return pieceBetweenJump.get().owner() != move.player();
  }
}
