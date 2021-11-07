package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.dom.Board;
import ch.uzh.group8.checkersv2.dom.BoardCoordinates;
import ch.uzh.group8.checkersv2.dom.Move;
import ch.uzh.group8.checkersv2.dom.Piece;
import java.util.Optional;

public class StartPieceValid implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    BoardCoordinates start = move.start();

    BoardCoordinates.Row[] row = BoardCoordinates.Row.values();
    BoardCoordinates.Column[] col = BoardCoordinates.Column.values();

    Optional<Piece> startPiece = board.getPieceAt(start);

    if (startPiece.isEmpty()) {
      return false;
    }

    return startPiece.get().owner() == move.player();
  }
}
