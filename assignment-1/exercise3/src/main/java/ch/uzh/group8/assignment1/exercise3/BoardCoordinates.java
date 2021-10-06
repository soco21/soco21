package ch.uzh.group8.assignment1.exercise3;

public record BoardCoordinates(Row row, Column column) {

  public enum Row {
    ROW_1,
    ROW_2,
    ROW_3,
    ROW_4,
    ROW_5,
    ROW_6,
    ROW_7,
    ROW_8;
  }

  public enum Column {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H
  }
}
