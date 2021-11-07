package ch.uzh.group8.checkersv2.dom;

import static ch.uzh.group8.checkersv2.dom.BoardCoordinates.Column;
import static ch.uzh.group8.checkersv2.dom.BoardCoordinates.Row;

import java.util.*;

public class Board {
  private final Map<Row, Map<Column, Piece>> boardMatrix;
  private final List<BoardObserver> boardObservers = new ArrayList<>();

  public Board() {
    boardMatrix = new HashMap<>();
    boardMatrix.put(
        Row.ROW_1,
        Map.of(
            Column.A,
            new Piece(Player.PLAYER_WHITE, false),
            Column.C,
            new Piece(Player.PLAYER_WHITE, false),
            Column.E,
            new Piece(Player.PLAYER_WHITE, false),
            Column.G,
            new Piece(Player.PLAYER_WHITE, false)));
    boardMatrix.put(
        Row.ROW_3,
        Map.of(
            Column.A,
            new Piece(Player.PLAYER_WHITE, false),
            Column.C,
            new Piece(Player.PLAYER_WHITE, false),
            Column.E,
            new Piece(Player.PLAYER_WHITE, false),
            Column.G,
            new Piece(Player.PLAYER_WHITE, false)));
    boardMatrix.put(
        Row.ROW_2,
        Map.of(
            Column.B,
            new Piece(Player.PLAYER_WHITE, false),
            Column.D,
            new Piece(Player.PLAYER_WHITE, false),
            Column.F,
            new Piece(Player.PLAYER_WHITE, false),
            Column.H,
            new Piece(Player.PLAYER_WHITE, false)));

    boardMatrix.put(
        Row.ROW_8,
        Map.of(
            Column.B,
            new Piece(Player.PLAYER_RED, false),
            Column.D,
            new Piece(Player.PLAYER_RED, false),
            Column.F,
            new Piece(Player.PLAYER_RED, false),
            Column.H,
            new Piece(Player.PLAYER_RED, false)));
    boardMatrix.put(
        Row.ROW_6,
        Map.of(
            Column.B,
            new Piece(Player.PLAYER_RED, false),
            Column.D,
            new Piece(Player.PLAYER_RED, false),
            Column.F,
            new Piece(Player.PLAYER_RED, false),
            Column.H,
            new Piece(Player.PLAYER_RED, false)));
    boardMatrix.put(
        Row.ROW_7,
        Map.of(
            Column.A,
            new Piece(Player.PLAYER_RED, false),
            Column.C,
            new Piece(Player.PLAYER_RED, false),
            Column.E,
            new Piece(Player.PLAYER_RED, false),
            Column.G,
            new Piece(Player.PLAYER_RED, false)));
  }

  public void executeMove(Move move) {
    if (move.jumpGambleResult() == JumpGambleResult.LOST) {
      removePiece(move.start());
      boardObservers.forEach(boardObserver -> boardObserver.boardChanged(this));
      return;
    }
    Piece piece = getPieceAt(move.start()).orElseThrow();
    removePiece(move.start());
    addPiece(move.end(), piece);

    Optional<BoardCoordinates> coordinatesBetween = move.getCoordinatesBetween();
    coordinatesBetween.ifPresent(this::removePiece);

    if (move.player() == Player.PLAYER_WHITE) {
      if (move.end().row().isLastRow()) {
        addPiece(move.end(), new Piece(Player.PLAYER_WHITE, true));
      }
    } else {
      if (move.end().row().isFirstRow()) {
        addPiece(move.end(), new Piece(Player.PLAYER_RED, true));
      }
    }
    boardObservers.forEach(boardObserver -> boardObserver.boardChanged(this));
  }

  public Optional<Piece> getPieceAt(BoardCoordinates boardCoordinates) {
    Map<Column, Piece> columnOptionalMap = boardMatrix.get(boardCoordinates.row());
    if (columnOptionalMap == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(columnOptionalMap.get(boardCoordinates.column()));
  }

  public void registerObserver(BoardObserver boardObserver) {
    boardObservers.add(boardObserver);
  }

  private void removePiece(BoardCoordinates start) {
    boardMatrix.compute(
        start.row(),
        (row, columnPieceMap) -> {
          if (columnPieceMap == null) {
            return new HashMap<>();
          } else {
            HashMap<Column, Piece> columnPieceHashMap = new HashMap<>(columnPieceMap);
            columnPieceHashMap.remove(start.column());
            return columnPieceHashMap;
          }
        });
  }

  private void addPiece(BoardCoordinates boardCoordinates, Piece piece) {
    boardMatrix.compute(
        boardCoordinates.row(),
        (row, columnPieceMap) -> {
          if (columnPieceMap == null) {
            return Map.of(boardCoordinates.column(), piece);
          } else {
            HashMap<Column, Piece> columnPieceHashMap = new HashMap<>(columnPieceMap);
            columnPieceHashMap.put(boardCoordinates.column(), piece);
            return columnPieceHashMap;
          }
        });
  }
}
