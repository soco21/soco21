package ch.uzh.group8.checkersv2;

import ch.uzh.group8.checkersv2.dom.Board;
import ch.uzh.group8.checkersv2.movevalidator.*;
import ch.uzh.group8.checkersv2.util.BoardPrinter;
import ch.uzh.group8.checkersv2.util.CoinTosser;
import ch.uzh.group8.checkersv2.util.Console;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    GameLogic gameLogic = createGameLogic(new Console(), new CoinTosser());

    gameLogic.run();
  }

  public static GameLogic createGameLogic(Console console, CoinTosser coinTosser) {
    var board = new Board();
    var boardPrinter = new BoardPrinter(console);

    var moveIsDiagonal = new MoveIsDiagonal();
    var moveLength = new MoveLength();
    var moveIsForwardIfNotKing = new MoveIsForwardIfNotKing();
    var opponentPieceBetweenJump = new OpponentPieceBetweenJump();
    var targetFieldEmpty = new TargetFieldEmpty();
    var noOtherMoveToJumpPossible =
        new NoOtherMoveToJumpPossible(
            moveIsForwardIfNotKing, opponentPieceBetweenJump, targetFieldEmpty);
    var startPieceValid = new StartPieceValid();

    var moveValidators =
        List.of(
            moveIsDiagonal,
            moveIsForwardIfNotKing,
            moveLength,
            noOtherMoveToJumpPossible,
            opponentPieceBetweenJump,
            startPieceValid,
            targetFieldEmpty);

    var winCondition =
        new WinCondition(
            List.of(
                moveIsDiagonal,
                moveLength,
                moveIsForwardIfNotKing,
                opponentPieceBetweenJump,
                targetFieldEmpty,
                startPieceValid));

    MoveExecutor moveExecutor = new MoveExecutor(board, coinTosser, console);
    return new GameLogic(
        console,
        board,
        boardPrinter,
        moveValidators,
        moveExecutor,
        noOtherMoveToJumpPossible,
        winCondition);
  }
}
