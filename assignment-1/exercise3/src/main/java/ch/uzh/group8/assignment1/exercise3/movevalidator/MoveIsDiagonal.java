package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.Move;

public class MoveIsDiagonal implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    var start = move.start();
    var end = move.end();
    return start.row() != end.row() && start.column() != end.column();
  }
}
