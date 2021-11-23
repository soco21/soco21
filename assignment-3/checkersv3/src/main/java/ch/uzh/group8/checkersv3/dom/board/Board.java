package ch.uzh.group8.checkersv3.dom.board;

import ch.uzh.group8.checkersv3.dom.*;
import ch.uzh.group8.checkersv3.util.Tuple;
import java.util.*;

public class Board {
  private final List<BoardObserver> boardObservers = new ArrayList<>();
  private final Store store = new Store();

  public void executeMove(Move move) {
    Command command = createCommand(move);
    command.execute();
    boardObservers.forEach(boardObserver -> boardObserver.boardChanged(this));
  }

  public Optional<Piece> getPieceAt(BoardCoordinates boardCoordinates) {
    return store.getPieceAt(boardCoordinates);
  }

  public void registerObserver(BoardObserver boardObserver) {
    boardObservers.add(boardObserver);
  }

  private Command createCommand(Move move) {
    Piece pieceAtStart = store.getPieceAt(move.start()).orElseThrow();
    Tuple<BoardCoordinates, Piece> start = Tuple.of(move.start(), pieceAtStart);
    if (move.jumpGambleResult() == JumpGambleResult.LOST) {
      return new JumpGambleLostMove(store, start);
    }

    boolean convertToKing = isConvertToKing(move);
    Piece pieceAtEnd = new Piece(move.player(), pieceAtStart.isKing() || convertToKing);
    Tuple<BoardCoordinates, Piece> end = Tuple.of(move.end(), pieceAtEnd);
    if (move.isJumpMove()) {
      Piece pieceBetween = move.getCoordinatesBetween().flatMap(store::getPieceAt).orElseThrow();
      return new JumpMove(
          store, start, Tuple.of(move.getCoordinatesBetween().orElseThrow(), pieceBetween), end);
    } else {
      return new SimpleMove(store, start, end);
    }
  }

  private boolean isConvertToKing(Move move) {
    if (move.player() == Player.PLAYER_WHITE) {
      return move.end().row().isLastRow();
    }
    return move.end().row().isFirstRow();
  }
}
