package ch.uzh.group8.checkersv3.movevalidator;

import ch.uzh.group8.checkersv3.dom.Move;
import ch.uzh.group8.checkersv3.dom.Piece;
import ch.uzh.group8.checkersv3.dom.board.Board;
import java.util.Optional;

public class TargetFieldEmpty implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    Optional<Piece> pieceAt = board.getPieceAt(move.end());
    return pieceAt.isEmpty();
  }
}
