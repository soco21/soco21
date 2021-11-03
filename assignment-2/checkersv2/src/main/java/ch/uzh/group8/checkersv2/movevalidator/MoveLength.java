package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.Board;
import ch.uzh.group8.checkersv2.Move;

public class MoveLength implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var start = move.start();
    var end = move.end();
    int diffMoveColumn = start.column().diffCol(end.column());
    int diffMoveRow = start.row().diffRow(end.row());
    if (diffMoveColumn == 0 && diffMoveRow == 0) {
      return false;
    }
    return Math.abs(diffMoveRow) <= 2 && Math.abs(diffMoveColumn) <= 2;
  }
}
