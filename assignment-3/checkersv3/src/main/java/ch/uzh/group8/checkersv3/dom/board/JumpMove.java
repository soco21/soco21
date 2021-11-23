package ch.uzh.group8.checkersv3.dom.board;

import ch.uzh.group8.checkersv3.dom.BoardCoordinates;
import ch.uzh.group8.checkersv3.dom.Piece;
import ch.uzh.group8.checkersv3.util.Tuple;

@SuppressWarnings("ClassCanBeRecord")
public class JumpMove implements Command {
  private final Store store;
  private final Tuple<BoardCoordinates, Piece> start;
  private final Tuple<BoardCoordinates, Piece> pieceBetween;
  private final Tuple<BoardCoordinates, Piece> end;

  public JumpMove(
      Store store,
      Tuple<BoardCoordinates, Piece> start,
      Tuple<BoardCoordinates, Piece> pieceBetween,
      Tuple<BoardCoordinates, Piece> end) {
    this.store = store;
    this.start = start;
    this.pieceBetween = pieceBetween;
    this.end = end;
  }

  @Override
  public void execute() {
    store.removePiece(start.getKey());
    store.removePiece(pieceBetween.getKey());
    store.addPiece(end.getKey(), end.getValue());
  }
}
