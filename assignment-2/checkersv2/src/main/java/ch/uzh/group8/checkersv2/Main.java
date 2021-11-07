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
    Board board = new Board();
    BoardPrinter boardPrinter = new BoardPrinter(console);

    MoveIsDiagonal moveIsDiagonal = new MoveIsDiagonal();
    MoveLength moveLength = new MoveLength();
    MoveIsForwardIfNotKing moveIsForwardIfNotKing = new MoveIsForwardIfNotKing();
    OpponentPieceBetweenJump opponentPieceBetweenJump = new OpponentPieceBetweenJump();
    TargetFieldEmpty targetFieldEmpty = new TargetFieldEmpty();
    NoOtherMoveToJumpPossible noOtherMoveToJumpPossible =
        new NoOtherMoveToJumpPossible(
            moveIsForwardIfNotKing, opponentPieceBetweenJump, targetFieldEmpty);
    StartPieceValid startPieceValid = new StartPieceValid();

    List<MoveValidator> moveValidators =
        List.of(
            moveIsDiagonal,
            moveIsForwardIfNotKing,
            moveLength,
            noOtherMoveToJumpPossible,
            opponentPieceBetweenJump,
            startPieceValid,
            targetFieldEmpty);

    WinCondition winCondition =
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
