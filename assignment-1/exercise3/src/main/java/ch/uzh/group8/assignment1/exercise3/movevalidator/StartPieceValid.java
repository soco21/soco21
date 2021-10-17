package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.*;
import ch.uzh.group8.assignment1.exercise3.Move;

public class StartPieceValid implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var start = move.start();

    var row = BoardCoordinates.Row.values();
    var col = BoardCoordinates.Column.values();

    var startPiece =
        board.getPieceAt(
            new BoardCoordinates(row[start.row().ordinal()], col[start.column().ordinal()]));

    if (startPiece.isEmpty()) {
      return false;
    }

    return startPiece.get().owner() == move.player();
  }
}
