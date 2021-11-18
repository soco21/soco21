package ch.uzh.group8.checkersv3;

import ch.uzh.group8.checkersv3.dom.Board;
import ch.uzh.group8.checkersv3.dom.JumpGambleResult;
import ch.uzh.group8.checkersv3.dom.Move;
import ch.uzh.group8.checkersv3.util.Console;
import ch.uzh.group8.checkersv3.util.Gambler;

@SuppressWarnings("ClassCanBeRecord")
public class MoveExecutor {
  private final Board board;
  private final Gambler gambler;
  private final Console console;

  public MoveExecutor(Board board, Gambler gambler, Console console) {
    this.board = board;
    this.gambler = gambler;
    this.console = console;
  }

  public Move executeMove(Move move) {
    if (!move.isJumpMove()) {
      board.executeMove(move);
      return move;
    }
    console.print(move.player() + " is making a jump move. Now " + move.player() + " may gamble.");
    console.print(move.player() + " your odds of winning are "+ 100 * gambler.calculateOdds(board, move)+"%");
    console.print("If " + move.player() + " does not gamble, the jump move is executed normally.");
    console.print("If " + move.player() + " does gamble, a coin will be tossed.");
    console.print(
        "If the gamble turns out in your favor, then the jump move will be executed and "
            + move.player()
            + " gets another turn.");
    console.print(
        "If the gamble is lost, then the jump move fails and the piece at "
            + move.start()
            + ", which would have been used for the jump move, is removed.");
    GambleChoice gambleChoice = null;
    do {
      console.print(
          move.player() + " type \"yes\" to gamble, \"no\" to execute the jump move normally");
      String userInput = console.getUserInput();
      try {
        gambleChoice = GambleChoice.valueOf(userInput.trim().toUpperCase());
      } catch (IllegalArgumentException e) {
        console.print("The input of " + move.player() + " was invalid. Input was: " + userInput);
      }
    } while (gambleChoice == null);
    if (gambleChoice == GambleChoice.NO) {
      board.executeMove(move);
      return move;
    }
    boolean tossResult = gambler.gambleExecutor(gambler.calculateOdds(board, move));
    JumpGambleResult jumpGambleResult =
        tossResult ? JumpGambleResult.WON : JumpGambleResult.LOST;
    console.print("The gamble was " + jumpGambleResult);
    Move newMove = move.withJumpGambleResult(jumpGambleResult);
    board.executeMove(newMove);
    return newMove;
  }

  private enum GambleChoice {
    @SuppressWarnings("unused")
    YES,
    NO
  }
}
