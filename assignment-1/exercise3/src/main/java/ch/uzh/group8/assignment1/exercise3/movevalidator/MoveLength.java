package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.Move;

public class MoveLength implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var start = move.start();
    var end = move.end();
    int diffMoveColumn = end.column().ordinal() - start.column().ordinal();
    int diffMoveRow = end.row().ordinal() - start.row().ordinal();
    if (diffMoveColumn == 0 && diffMoveRow == 0) {
      return false;
    }
    return Math.abs(diffMoveRow) <= 2 && Math.abs(diffMoveColumn) <= 2;
  }
}
