package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.Board;
import ch.uzh.group8.checkersv2.BoardCoordinates;
import ch.uzh.group8.checkersv2.Move;

public class StartPieceValid implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var start = move.start();

    var row = BoardCoordinates.Row.values();
    var col = BoardCoordinates.Column.values();

    var startPiece = board.getPieceAt(start);

    if (startPiece.isEmpty()) {
      return false;
    }

    return startPiece.get().owner() == move.player();
  }
}
