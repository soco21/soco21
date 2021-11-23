package ch.uzh.group8.checkersv3.dom.board;

import ch.uzh.group8.checkersv3.dom.*;
import java.util.*;

public class Board {
  private final List<BoardObserver> boardObservers = new ArrayList<>();
  private final Store store = new Store();

  public void executeMove(Move move) {
    if (move.jumpGambleResult() == JumpGambleResult.LOST) {
      store.removePiece(move.start());
      boardObservers.forEach(boardObserver -> boardObserver.boardChanged(this));
      return;
    }
    Piece piece = store.getPieceAt(move.start()).orElseThrow();
    store.removePiece(move.start());
    store.addPiece(move.end(), piece);

    Optional<BoardCoordinates> coordinatesBetween = move.getCoordinatesBetween();
    coordinatesBetween.ifPresent(store::removePiece);

    if (move.player() == Player.PLAYER_WHITE) {
      if (move.end().row().isLastRow()) {
        store.addPiece(move.end(), new Piece(Player.PLAYER_WHITE, true));
      }
    } else {
      if (move.end().row().isFirstRow()) {
        store.addPiece(move.end(), new Piece(Player.PLAYER_RED, true));
      }
    }
    boardObservers.forEach(boardObserver -> boardObserver.boardChanged(this));
  }

  public Optional<Piece> getPieceAt(BoardCoordinates boardCoordinates) {
    return store.getPieceAt(boardCoordinates);
  }

  public void registerObserver(BoardObserver boardObserver) {
    boardObservers.add(boardObserver);
  }
}
