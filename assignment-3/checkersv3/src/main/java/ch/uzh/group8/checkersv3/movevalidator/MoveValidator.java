package ch.uzh.group8.checkersv3.movevalidator;

import ch.uzh.group8.checkersv3.dom.Move;
import ch.uzh.group8.checkersv3.dom.board.Board;

public interface MoveValidator {
  boolean validate(Move move, Board board);
}
