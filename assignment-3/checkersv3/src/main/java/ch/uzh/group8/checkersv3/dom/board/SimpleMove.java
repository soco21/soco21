package ch.uzh.group8.checkersv3.dom.board;

import ch.uzh.group8.checkersv3.dom.BoardCoordinates;
import ch.uzh.group8.checkersv3.dom.Piece;
import ch.uzh.group8.checkersv3.util.Tuple;

@SuppressWarnings("ClassCanBeRecord")
public class SimpleMove implements Command {
  private final Store store;
  private final Tuple<BoardCoordinates, Piece> start;
  private final Tuple<BoardCoordinates, Piece> end;

  public SimpleMove(
      Store store, Tuple<BoardCoordinates, Piece> start, Tuple<BoardCoordinates, Piece> end) {
    this.store = store;
    this.start = start;
    this.end = end;
  }

  @Override
  public void execute() {
    store.removePiece(start.getKey());
    store.addPiece(end.getKey(), end.getValue());
  }

  @Override
  public void undo() {
    store.addPiece(start.getKey(), start.getValue());
    store.removePiece(end.getKey());
  }
}
