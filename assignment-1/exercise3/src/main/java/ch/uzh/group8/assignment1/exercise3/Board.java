package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;

import java.util.*;

public class Board {
  public void executeMove(Move move) {}

  public List<Piece> getAllPieces() {
    return Collections.emptyList();
  }

  public Optional<Piece> getPieceAt(BoardCoordinates boardCoordinates) {
    return Optional.empty();
  }

  /** Important that LinkedHashMap is used: this preserves the order */
  public Map<Row, Map<Column, Optional<Piece>>> getBoard() {
    return new LinkedHashMap<>();
  }
}
