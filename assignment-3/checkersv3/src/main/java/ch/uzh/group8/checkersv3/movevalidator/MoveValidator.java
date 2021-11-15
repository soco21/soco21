package ch.uzh.group8.checkersv3.movevalidator;

import ch.uzh.group8.checkersv3.dom.Board;
import ch.uzh.group8.checkersv3.dom.Move;

public interface MoveValidator {
  boolean validate(Move move, Board board);
}
