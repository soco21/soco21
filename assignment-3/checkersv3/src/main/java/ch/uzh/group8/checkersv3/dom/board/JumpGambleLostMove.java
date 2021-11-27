package ch.uzh.group8.checkersv3.dom.board;

import ch.uzh.group8.checkersv3.dom.BoardCoordinates;
import ch.uzh.group8.checkersv3.dom.Piece;
import ch.uzh.group8.checkersv3.util.Tuple;

@SuppressWarnings("ClassCanBeRecord")
class JumpGambleLostMove implements Command {
  private final Store store;
  private final Tuple<BoardCoordinates, Piece> start;

  JumpGambleLostMove(Store store, Tuple<BoardCoordinates, Piece> start) {
    this.store = store;
    this.start = start;
  }

  @Override
  public void execute() {
    store.removePiece(start.getKey());
  }

  @Override
  public void undo() {
    store.addPiece(start.getKey(), start.getValue());
  }
}
