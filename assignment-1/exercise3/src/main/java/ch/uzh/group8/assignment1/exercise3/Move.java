package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;

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
}
