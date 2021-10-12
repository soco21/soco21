package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.Move;
import ch.uzh.group8.assignment1.exercise3.Piece;
import java.util.Optional;

public class TargetFieldEmpty implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    Optional<Piece> pieceAt = board.getPieceAt(move.start());
    if (pieceAt.isEmpty()) {
      return true;
    }
    Piece piece = pieceAt.get();

    pieceAt = board.getPieceAt(move.end());
    return pieceAt.isEmpty();
  }
}
