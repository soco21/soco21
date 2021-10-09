package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;

import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings("ClassCanBeRecord")
public class BoardPrinter {
  private final Console console;

  public BoardPrinter(Console console) {
    this.console = console;
  }

  public void printBoard(Board board) {
    StringBuilder header = new StringBuilder();
    header.append("\t\t");
    for (Column col : Column.values()) {
      header.append(col.name()).append("\t\t");
    }
    console.print(header.toString());
    console.print("\t+------------------------------------------------------------------------+");

    var rows = Arrays.asList(Row.values());
    Collections.reverse(rows);
    for (Row row : rows) {
      StringBuilder rowString = new StringBuilder(String.valueOf(row.ordinal() + 1) + "\t");
      for (Column col : Column.values()) {
        var pieceAt = board.getPieceAt(new BoardCoordinates(row, col));
        if (pieceAt.isEmpty()) {
          rowString.append("[\t\t]");
        } else {
          var piece = pieceAt.get();
          rowString.append("[");
          if (piece.owner() == Player.PLAYER_WHITE) {
            rowString.append("W");
          } else {
            rowString.append("R");
          }
          rowString.append("_");
          if (piece.isKing()) {
            rowString.append("K");
          } else {
            rowString.append("P");
          }
          rowString.append("\t]");
        }
      }
      console.print(rowString.toString());
    }
  }
}
