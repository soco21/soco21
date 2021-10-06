package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.BoardCoordinates;
import ch.uzh.group8.assignment1.exercise3.Move;

public class NoOtherMoveToJumpPossible implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    return true;
  }

  public boolean jumpMovePossibleFrom(BoardCoordinates boardCoordinates, Board board) {
    return false;
  }
}
