package ch.uzh.group8.checkersv2;

import static org.mockito.Mockito.*;

import ch.uzh.group8.checkersv2.dom.Player;
import ch.uzh.group8.checkersv2.util.CoinTosser;
import ch.uzh.group8.checkersv2.util.CoinTosser.Result;
import ch.uzh.group8.checkersv2.util.Console;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

class GameLogicTest {
  @Test
  public void end_game_with_winner() {
    var console = mock(Console.class);
    doCallRealMethod().when(console).print(notNull());
    CoinTosser coinTosser = mock(CoinTosser.class);
    var gameLogic = Main.createGameLogic(console, coinTosser);
    // numbers were taken from: http://www.quadibloc.com/other/bo1211.htm
    when(console.getUserInput())
        // RED
        .thenReturn(fromNumbers(11, 15))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(23, 19))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(8, 11))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(22, 17))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(11, 16))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(24, 20))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(16, 23))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(27, 18))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(18, 11))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(7, 16))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(20, 11))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(3, 7))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(28, 24))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(7, 16))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(24, 20))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(16, 19))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(25, 22))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(4, 8))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(29, 25))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(9, 14))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(22, 18))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(14, 23))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(17, 14))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(10, 17))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(21, 14))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(2, 7))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(31, 27))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(6, 10))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(27, 18))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(10, 17))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(25, 21))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(1, 6))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(21, 14))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(6, 10))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(30, 25))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(10, 17))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(25, 21))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(19, 23))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(26, 19))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(17, 22))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(19, 15))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(22, 26))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(18, 14))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(26, 31))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(15, 10))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(5, 9))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(10, 3))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(9, 18))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(21, 17))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(18, 22))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(17, 14))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(22, 26))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(20, 16))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(12, 19))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(3, 12))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(26, 30))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(12, 16))
        .thenReturn("no")

        // Added moves that white wins
        // RED
        .thenReturn(fromNumbers(30, 26))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(16, 23))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(23, 30))
        .thenReturn("no")
        // RED
        .thenReturn(fromNumbers(31, 27))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(32, 23))
        .thenReturn("no");

    gameLogic.run();

    verify(console).print("Congratulations, player " + Player.PLAYER_WHITE + " has won");
  }

  @Test
  public void only_allow_multiple_jump_with_same_piece() {
    var console = mock(Console.class);
    doCallRealMethod().when(console).print(notNull());
    CoinTosser coinTosser = mock(CoinTosser.class);
    var gameLogic = Main.createGameLogic(console, coinTosser);
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
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(27, 18))
        .thenReturn("no")
        // WHITE
        .thenReturn(fromNumbers(26, 11))
        .thenThrow(RuntimeException.class);

    Assertions.assertThrows(RuntimeException.class, gameLogic::run);

    verify(console).print("For a multiple jump move, the same piece has to be used. Try again");
  }

  @Test
  public void let_same_player_play_again_if_jumpgamble_is_won() {
    var console = mock(Console.class);
    doCallRealMethod().when(console).print(notNull());
    CoinTosser coinTosser = mock(CoinTosser.class);
    when(coinTosser.toss()).thenReturn(Result.HEADS);
    var gameLogic = Main.createGameLogic(console, coinTosser);
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
        .thenReturn("yes")
        // RED
        .thenThrow(RuntimeException.class);

    Assertions.assertThrows(RuntimeException.class, gameLogic::run);

    verify(console).print("The gamble has been won, PLAYER_RED can play again.");
  }

  @Test
  public void switch_player_if_jumpgamble_is_lost() {
    var console = mock(Console.class);
    doCallRealMethod().when(console).print(notNull());
    CoinTosser coinTosser = mock(CoinTosser.class);
    when(coinTosser.toss()).thenReturn(Result.TAILS);
    var gameLogic = Main.createGameLogic(console, coinTosser);
    InOrder inOrder = inOrder(console);
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
        .thenReturn("yes")
        // RED
        .thenThrow(RuntimeException.class);

    Assertions.assertThrows(RuntimeException.class, gameLogic::run);

    inOrder.verify(console).print("Coin toss resulted in TAILS, the gamble was: LOST");
    inOrder.verify(console).print("5   | [   ] [   ] [   ] [   ] [R_P] [   ] [   ] [   ] |   5");
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
