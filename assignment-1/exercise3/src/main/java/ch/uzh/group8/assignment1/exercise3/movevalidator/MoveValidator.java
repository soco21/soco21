package ch.uzh.group8.assignment1.exercise3.movevalidator;

import ch.uzh.group8.assignment1.exercise3.Board;
import ch.uzh.group8.assignment1.exercise3.Move;

public interface MoveValidator {
  boolean validate(Move move, Board board);
}
