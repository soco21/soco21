package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.Board;
import ch.uzh.group8.checkersv2.Move;
import ch.uzh.group8.checkersv2.Piece;
import java.util.Optional;

public class TargetFieldEmpty implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    Optional<Piece> pieceAt = board.getPieceAt(move.end());
    return pieceAt.isEmpty();
  }
}
