package ch.uzh.group8.checkersv2;

import static ch.uzh.group8.checkersv2.BoardCoordinates.Column;
import static ch.uzh.group8.checkersv2.BoardCoordinates.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Move(Player player, BoardCoordinates start, BoardCoordinates end) {
  public static Move parse(Player player, String string) {
    //    string = "b3xa5";
    //    var test = new String[]{"b3", "a5"};
    var startAndEnd = string.split("x");
    if (startAndEnd.length < 2) {
      throw new IllegalArgumentException("invalid input");
    }
    var start = parseBoardCoordinates(startAndEnd[0]);
    var end = parseBoardCoordinates(startAndEnd[1]);

    return new Move(player, start, end);
  }

  public static List<Move> generatePossibleMoves(
      BoardCoordinates boardCoordinates, Player pieceOwner, List<Integer> distances) {
    var rowIndex = boardCoordinates.row().ordinal();
    var colIndex = boardCoordinates.column().ordinal();
    List<Move> possibleJumpMoves = new ArrayList<>();

    for (Integer distance : distances) {
      of(pieceOwner, boardCoordinates, rowIndex + distance, colIndex + distance)
          .ifPresent(possibleJumpMoves::add);
      of(pieceOwner, boardCoordinates, rowIndex + distance, colIndex - distance)
          .ifPresent(possibleJumpMoves::add);
      of(pieceOwner, boardCoordinates, rowIndex - distance, colIndex + distance)
          .ifPresent(possibleJumpMoves::add);
      of(pieceOwner, boardCoordinates, rowIndex - distance, colIndex - distance)
          .ifPresent(possibleJumpMoves::add);
    }
    return possibleJumpMoves;
  }

  private static Optional<Move> of(
      Player player, BoardCoordinates start, int rowIndex, int colIndex) {
    var rows = Row.values();
    var columns = Column.values();

    if (rowIndex >= 0 && rowIndex < rows.length && colIndex >= 0 && colIndex < columns.length) {
      return Optional.of(
          new Move(player, start, new BoardCoordinates(rows[rowIndex], columns[colIndex])));
    }
    return Optional.empty();
  }

  private static BoardCoordinates parseBoardCoordinates(String s) {
    var columnAndRow = s.split("");
    if (columnAndRow.length != 2) {
      throw new IllegalArgumentException("invalid input");
    }
    var columnString = columnAndRow[0];
    var column = Column.valueOf(columnString.toUpperCase());

    var rowString = columnAndRow[1];
    for (Row row : Row.values()) {
      var enumValue = row.ordinal() + 1;
      if (rowString.equals(String.valueOf(enumValue))) {
        return new BoardCoordinates(row, column);
      }
    }
    throw new IllegalArgumentException("invalid input");
  }

  public Optional<BoardCoordinates> getCoordinatesBetween() {
    var rowDiff = start.row().diffRow(end.row());
    var colDiff = start.column().diffCol(end.column());

    if (Math.abs(rowDiff) != 2 || Math.abs(colDiff) != 2) {
      return Optional.empty();
    }

    var rows = Row.values();
    int rowIndexBetween = (end.row().ordinal() + start.row().ordinal()) / 2;
    Row rowBetween = rows[rowIndexBetween];

    var columns = Column.values();
    int colIndexBetween = (end.column().ordinal() + start.column().ordinal()) / 2;
    Column colBetween = columns[colIndexBetween];

    return Optional.of(new BoardCoordinates(rowBetween, colBetween));
  }

  public boolean isJumpMove() {
    int diffMoveColumn = start.column().diffCol(end.column());
    int diffMoveRow = start.row().diffRow(end.row());
    if (diffMoveColumn == 0 && diffMoveRow == 0) {
      return false;
    }
    return Math.abs(diffMoveRow) == 2 && Math.abs(diffMoveColumn) == 2;
  }
}
