package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;

public record Move(Player player, BoardCoordinates start, BoardCoordinates end) {
  public static Move parse(Player player, String string) {
    // TODO: parse string
    return new Move(
        player,
        new BoardCoordinates(Row.ROW_1, Column.A),
        new BoardCoordinates(Row.ROW_1, Column.A));
  }
}
