package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.Column;
import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.Row;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

class BoardPrinterTest {

  private Board board;
  private Console console;
  private BoardPrinter boardPrinter;
  private InOrder inOrder;

  @BeforeEach
  public void setup() {
    board = spy(new Board());
    console = mock(Console.class);
    boardPrinter = new BoardPrinter(console);
    inOrder = inOrder(console);
  }

  @Test
  public void prints_initial_board_state() {
    boardPrinter.printBoard(board);

    inOrder.verify(console).print("        a     b     c     d     e     f     g     h     ");
    inOrder.verify(console).print("    +-------------------------------------------------+");
    inOrder.verify(console).print("8   | [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] |   8");
    inOrder.verify(console).print("7   | [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] |   7");
    inOrder.verify(console).print("6   | [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] |   6");
    inOrder.verify(console).print("5   | [   ] [   ] [   ] [   ] [   ] [   ] [   ] [   ] |   5");
    inOrder.verify(console).print("4   | [   ] [   ] [   ] [   ] [   ] [   ] [   ] [   ] |   4");
    inOrder.verify(console).print("3   | [W_P] [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] |   3");
    inOrder.verify(console).print("2   | [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] [W_P] |   2");
    inOrder.verify(console).print("1   | [W_P] [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] |   1");
    inOrder.verify(console).print("    +-------------------------------------------------+");
    inOrder.verify(console).print("        a     b     c     d     e     f     g     h     ");
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  public void print_king() {
    when(board.getPieceAt(new BoardCoordinates(Row.ROW_1, Column.A)))
        .thenReturn(Optional.of(new Piece(Player.PLAYER_WHITE, true)));

    boardPrinter.printBoard(board);

    inOrder.verify(console).print("        a     b     c     d     e     f     g     h     ");
    inOrder.verify(console).print("    +-------------------------------------------------+");
    inOrder.verify(console).print("8   | [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] |   8");
    inOrder.verify(console).print("7   | [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] |   7");
    inOrder.verify(console).print("6   | [   ] [R_P] [   ] [R_P] [   ] [R_P] [   ] [R_P] |   6");
    inOrder.verify(console).print("5   | [   ] [   ] [   ] [   ] [   ] [   ] [   ] [   ] |   5");
    inOrder.verify(console).print("4   | [   ] [   ] [   ] [   ] [   ] [   ] [   ] [   ] |   4");
    inOrder.verify(console).print("3   | [W_P] [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] |   3");
    inOrder.verify(console).print("2   | [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] [W_P] |   2");
    inOrder.verify(console).print("1   | [W_K] [   ] [W_P] [   ] [W_P] [   ] [W_P] [   ] |   1");
    inOrder.verify(console).print("    +-------------------------------------------------+");
    inOrder.verify(console).print("        a     b     c     d     e     f     g     h     ");
    inOrder.verifyNoMoreInteractions();
  }
}
