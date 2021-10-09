package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;

import java.util.*;

public class Board {
  private final Map<Row, Map<Column, Piece>> boardMatrix;

  public Board() {
    boardMatrix = new HashMap<>();
    boardMatrix.put(Row.ROW_1, Map.of(Column.A, new Piece(Player.PLAYER_WHITE, false)));
    boardMatrix.put(Row.ROW_3, Map.of(Column.A, new Piece(Player.PLAYER_WHITE, false)));
    boardMatrix.put(Row.ROW_2, Map.of(Column.B, new Piece(Player.PLAYER_WHITE, false)));
    boardMatrix.put(Row.ROW_8, Map.of(Column.A, new Piece(Player.PLAYER_RED, false)));
  }

  public void executeMove(Move move) {}

  public List<Piece> getAllPieces() {
    return Collections.emptyList();
  }

  public Optional<Piece> getPieceAt(BoardCoordinates boardCoordinates) {
    var columnOptionalMap = boardMatrix.get(boardCoordinates.row());
    if (columnOptionalMap == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(columnOptionalMap.get(boardCoordinates.column()));
  }

  /** Important that LinkedHashMap is used: this preserves the order */
  public Map<Row, Map<Column, Piece>> getBoard() {
    return boardMatrix;
  }
}
