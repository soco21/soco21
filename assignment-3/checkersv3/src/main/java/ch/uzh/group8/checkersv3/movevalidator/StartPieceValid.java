package ch.uzh.group8.checkersv3.movevalidator;

import ch.uzh.group8.checkersv3.dom.BoardCoordinates;
import ch.uzh.group8.checkersv3.dom.Move;
import ch.uzh.group8.checkersv3.dom.Piece;
import ch.uzh.group8.checkersv3.dom.board.Board;
import java.util.Optional;

public class StartPieceValid implements MoveValidator {
  @Override
  public boolean validate(Move move, Board board) {
    BoardCoordinates start = move.start();

    BoardCoordinates.Row[] row = BoardCoordinates.Row.values();
    BoardCoordinates.Column[] col = BoardCoordinates.Column.values();

    Optional<Piece> startPiece = board.getPieceAt(start);

    if (startPiece.isEmpty()) {
      return false;
    }

    return startPiece.get().owner() == move.player();
  }
}
