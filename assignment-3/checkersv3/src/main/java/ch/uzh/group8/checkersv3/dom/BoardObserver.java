package ch.uzh.group8.checkersv3.dom;

import ch.uzh.group8.checkersv3.dom.board.Board;

public interface BoardObserver {
  void boardChanged(Board board);
}
