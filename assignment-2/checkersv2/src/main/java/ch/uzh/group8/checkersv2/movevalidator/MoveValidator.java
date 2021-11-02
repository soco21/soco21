package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.Board;
import ch.uzh.group8.checkersv2.Move;

public interface MoveValidator {
  boolean validate(Move move, Board board);
}
