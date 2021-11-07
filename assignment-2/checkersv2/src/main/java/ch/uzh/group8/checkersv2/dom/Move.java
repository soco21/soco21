package ch.uzh.group8.checkersv2.dom;

import static ch.uzh.group8.checkersv2.dom.BoardCoordinates.Column;
import static ch.uzh.group8.checkersv2.dom.BoardCoordinates.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Move(
    Player player,
    BoardCoordinates start,
    BoardCoordinates end,
    JumpGambleResult jumpGambleResult) {
  public static Move parse(Player player, String string) {
    string = string.replace("X", "x").replace("[", "").replace("]", "");
    String[] startAndEnd = string.split("x");
    if (startAndEnd.length < 2) {
      throw new IllegalArgumentException("invalid input");
    }
    BoardCoordinates start = parseBoardCoordinates(startAndEnd[0]);
    BoardCoordinates end = parseBoardCoordinates(startAndEnd[1]);

    return Move.of(player, start, end);
  }

  public static Move of(Player player, BoardCoordinates start, BoardCoordinates end) {
    return new Move(player, start, end, JumpGambleResult.NO_GAMBLE);
  }

  public static List<Move> generatePossibleMoves(
      BoardCoordinates boardCoordinates, Player pieceOwner, List<Integer> distances) {
    int rowIndex = boardCoordinates.row().ordinal();
    int colIndex = boardCoordinates.column().ordinal();
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
    Row[] rows = Row.values();
    Column[] columns = Column.values();

    if (rowIndex >= 0 && rowIndex < rows.length && colIndex >= 0 && colIndex < columns.length) {
      return Optional.of(
          Move.of(player, start, new BoardCoordinates(rows[rowIndex], columns[colIndex])));
    }
    return Optional.empty();
  }

  private static BoardCoordinates parseBoardCoordinates(String s) {
    String[] columnAndRow = s.split("");
    if (columnAndRow.length != 2) {
      throw new IllegalArgumentException("invalid input");
    }
    String columnString = columnAndRow[0];
    Column column = Column.valueOf(columnString.toUpperCase());

    String rowString = columnAndRow[1];
    for (Row row : Row.values()) {
      int enumValue = row.ordinal() + 1;
      if (rowString.equals(String.valueOf(enumValue))) {
        return new BoardCoordinates(row, column);
      }
    }
    throw new IllegalArgumentException("invalid input");
  }

  public Optional<BoardCoordinates> getCoordinatesBetween() {
    int rowDiff = start.row().diffRow(end.row());
    int colDiff = start.column().diffCol(end.column());

    if (Math.abs(rowDiff) != 2 || Math.abs(colDiff) != 2) {
      return Optional.empty();
    }

    Row[] rows = Row.values();
    int rowIndexBetween = (end.row().ordinal() + start.row().ordinal()) / 2;
    Row rowBetween = rows[rowIndexBetween];

    Column[] columns = Column.values();
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

  public Move withJumpGambleResult(JumpGambleResult jumpGambleResult) {
    if (jumpGambleResult != JumpGambleResult.NO_GAMBLE && !isJumpMove()) {
      throw new IllegalArgumentException(
          "cannot create move with JumpGambleResult"
              + jumpGambleResult
              + ", when the move is no jump move");
    }
    return new Move(player, start, end, jumpGambleResult);
  }
}
