package ch.uzh.group8.checkersv3.movevalidator;

import ch.uzh.group8.checkersv3.dom.BoardCoordinates;
import ch.uzh.group8.checkersv3.dom.Move;
import ch.uzh.group8.checkersv3.dom.board.Board;

public class MoveLength implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    BoardCoordinates start = move.start();
    BoardCoordinates end = move.end();
    int diffMoveColumn = start.column().diffCol(end.column());
    int diffMoveRow = start.row().diffRow(end.row());
    if (diffMoveColumn == 0 && diffMoveRow == 0) {
      return false;
    }
    return Math.abs(diffMoveRow) <= 2 && Math.abs(diffMoveColumn) <= 2;
  }
}
