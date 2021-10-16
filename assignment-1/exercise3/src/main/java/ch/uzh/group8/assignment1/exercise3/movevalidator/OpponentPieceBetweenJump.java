package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.*;

public class OpponentPieceBetweenJump implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var start = move.start();
    var end = move.end();

    int diffMoveColumn = end.column().ordinal() - start.column().ordinal();
    int diffMoveRow = end.row().ordinal() - start.row().ordinal();

    int rowBetween = (end.row().ordinal() + start.row().ordinal()) / 2;
    int colBetween = (end.column().ordinal() + start.column().ordinal()) / 2;

    var row = BoardCoordinates.Row.values();
    var row1 = row[rowBetween];

    var col = BoardCoordinates.Column.values();
    var col1 = col[colBetween];

    var pieceBetweenJump = board.getPieceAt(new BoardCoordinates(row1, col1));

    if (diffMoveRow == 2 && diffMoveColumn == 2) {
      if (pieceBetweenJump.isEmpty()) {
        return false;
      }
      return pieceBetweenJump.get().owner() != move.player();
    }
    return true;
  }
}
