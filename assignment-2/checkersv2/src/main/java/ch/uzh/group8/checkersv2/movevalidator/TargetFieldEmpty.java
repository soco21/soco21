package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.dom.Board;
import ch.uzh.group8.checkersv2.dom.Move;
import ch.uzh.group8.checkersv2.dom.Piece;
import java.util.Optional;

public class TargetFieldEmpty implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    Optional<Piece> pieceAt = board.getPieceAt(move.end());
    return pieceAt.isEmpty();
  }
}
