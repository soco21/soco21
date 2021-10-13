package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.Move;
import ch.uzh.group8.assignment1.exercise3.Piece;
import ch.uzh.group8.assignment1.exercise3.Player;
import java.util.Optional;

public class MoveIsForwardIfNotKing implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    Optional<Piece> pieceAt = board.getPieceAt(move.start());
    if (pieceAt.isEmpty()) {
      return true;
    }
    Piece piece = pieceAt.get();
    if (piece.isKing()) {
      return true;
    }
    if (piece.owner().equals(Player.PLAYER_WHITE)) {
      return move.start().row().ordinal() < move.end().row().ordinal();

    } else if (piece.owner().equals(Player.PLAYER_RED)) {
      return move.start().row().ordinal() > move.end().row().ordinal();
    }
    return false;
  }
}
