package ch.uzh.group8.assignment1.exercise3;

import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class GameLogicTest {
  @Test
  public void end_game_with_winner() {
    var console = mock(Console.class);
    doCallRealMethod().when(console).print(notNull());
    var gameLogic = Main.createGameLogic(console);
    // numbers were taken from: http://www.quadibloc.com/other/bo1211.htm
    when(console.getUserInput())
        // RED
        .thenReturn(fromNumbers(11, 15))
        // WHITE
        .thenReturn(fromNumbers(23, 19))
        // RED
        .thenReturn(fromNumbers(8, 11))
        // WHITE
        .thenReturn(fromNumbers(22, 17))
        // RED
        .thenReturn(fromNumbers(11, 16))
        // WHITE
        .thenReturn(fromNumbers(24, 20))
        // RED
        .thenReturn(fromNumbers(16, 23))
        // WHITE
        .thenReturn(fromNumbers(27, 18))
        // WHITE
        .thenReturn(fromNumbers(18, 11))
        // RED
        .thenReturn(fromNumbers(7, 16))
        // WHITE
        .thenReturn(fromNumbers(20, 11))
        // RED
        .thenReturn(fromNumbers(3, 7))
        // WHITE
        .thenReturn(fromNumbers(28, 24))
        // RED
        .thenReturn(fromNumbers(7, 16))
        // WHITE
        .thenReturn(fromNumbers(24, 20))
        // RED
        .thenReturn(fromNumbers(16, 19))
        // WHITE
        .thenReturn(fromNumbers(25, 22))
        // RED
        .thenReturn(fromNumbers(4, 8))
        // WHITE
        .thenReturn(fromNumbers(29, 25))
        // RED
        .thenReturn(fromNumbers(9, 14))
        // WHITE
        .thenReturn(fromNumbers(22, 18))
        // RED
        .thenReturn(fromNumbers(14, 23))
        // WHITE
        .thenReturn(fromNumbers(17, 14))
        // RED
        .thenReturn(fromNumbers(10, 17))
        // WHITE
        .thenReturn(fromNumbers(21, 14))
        // RED
        .thenReturn(fromNumbers(2, 7))
        // WHITE
        .thenReturn(fromNumbers(31, 27))
        // RED
        .thenReturn(fromNumbers(6, 10))
        // WHITE
        .thenReturn(fromNumbers(27, 18))
        // RED
        .thenReturn(fromNumbers(10, 17))
        // WHITE
        .thenReturn(fromNumbers(25, 21))
        // RED
        .thenReturn(fromNumbers(1, 6))
        // WHITE
        .thenReturn(fromNumbers(21, 14))
        // RED
        .thenReturn(fromNumbers(6, 10))
        // WHITE
        .thenReturn(fromNumbers(30, 25))
        // RED
        .thenReturn(fromNumbers(10, 17))
        // WHITE
        .thenReturn(fromNumbers(25, 21))
        // RED
        .thenReturn(fromNumbers(19, 23))
        // WHITE
        .thenReturn(fromNumbers(26, 19))
        // RED
        .thenReturn(fromNumbers(17, 22))
        // WHITE
        .thenReturn(fromNumbers(19, 15))
        // RED
        .thenReturn(fromNumbers(22, 26))
        // WHITE
        .thenReturn(fromNumbers(18, 14))
        // RED
        .thenReturn(fromNumbers(26, 31))
        // WHITE
        .thenReturn(fromNumbers(15, 10))
        // RED
        .thenReturn(fromNumbers(5, 9))
        // WHITE
        .thenReturn(fromNumbers(10, 3))
        // RED
        .thenReturn(fromNumbers(9, 18))
        // WHITE
        .thenReturn(fromNumbers(21, 17))
        // RED
        .thenReturn(fromNumbers(18, 22))
        // WHITE
        .thenReturn(fromNumbers(17, 14))
        // RED
        .thenReturn(fromNumbers(22, 26))
        // WHITE
        .thenReturn(fromNumbers(20, 16))
        // RED
        .thenReturn(fromNumbers(12, 19))
        // WHITE
        .thenReturn(fromNumbers(3, 12))
        // RED
        .thenReturn(fromNumbers(26, 30))
        // WHITE
        .thenReturn(fromNumbers(12, 16))

        // Added moves that white wins
        // RED
        .thenReturn(fromNumbers(30, 26))
        // WHITE
        .thenReturn(fromNumbers(16, 23))
        // WHITE
        .thenReturn(fromNumbers(23, 30))
        // RED
        .thenReturn(fromNumbers(31, 27))
        // WHITE
        .thenReturn(fromNumbers(32, 23));

    gameLogic.run();

    verify(console).print("Congratulations, player " + Player.PLAYER_WHITE + " has won");
  }

  private static String fromNumbers(int start, int end) {
    var numberCoordinatesMap =
        List.of(
            "B8", "D8", "F8", "H8", "A7", "C7", "E7", "G7", "B6", "D6", "F6", "H6", "A5", "C5",
            "E5", "G5", "B4", "D4", "F4", "H4", "A3", "C3", "E3", "G3", "B2", "D2", "F2", "H2",
            "A1", "C1", "E1", "G1");
    return String.format(
        "%sx%s", numberCoordinatesMap.get(start - 1), numberCoordinatesMap.get(end - 1));
  }
}
