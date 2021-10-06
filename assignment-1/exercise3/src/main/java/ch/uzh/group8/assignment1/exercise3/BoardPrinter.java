package ch.uzh.group8.assignment1.exercise3;

@SuppressWarnings("ClassCanBeRecord")
public class BoardPrinter {
  private final Console console;

  public BoardPrinter(Console console) {
    this.console = console;
  }

  public void printBoard(Board board) {
    console.print("printing board, is not very fancy yet");
  }
}
