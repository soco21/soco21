package ch.uzh.group8.checkersv2.movevalidator;

import ch.uzh.group8.checkersv2.dom.Board;
import ch.uzh.group8.checkersv2.dom.Move;

public interface MoveValidator {
  boolean validate(Move move, Board board);
}
