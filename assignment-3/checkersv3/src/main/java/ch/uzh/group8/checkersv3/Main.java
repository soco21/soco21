package ch.uzh.group8.checkersv3;

import ch.uzh.group8.checkersv3.dom.Board;
import ch.uzh.group8.checkersv3.movevalidator.*;
import ch.uzh.group8.checkersv3.util.BoardPrinter;
import ch.uzh.group8.checkersv3.util.CoinTosser;
import ch.uzh.group8.checkersv3.util.Console;
import ch.uzh.group8.checkersv3.util.PointsCalculator;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    GameLogic gameLogic = createGameLogic(new Console(), new CoinTosser(new PointsCalculator()));

    gameLogic.run();
  }

  public static GameLogic createGameLogic(Console console, CoinTosser coinTosser) {
    Board board = new Board();
    BoardPrinter boardPrinter = new BoardPrinter(console);
    board.registerObserver(boardPrinter::printBoard);

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
    GameLogic gameLogic =
        new GameLogic(
            console, board, moveValidators, moveExecutor, noOtherMoveToJumpPossible, winCondition);

    console.print("Welcome to checkers");
    boardPrinter.printBoard(board);
    return gameLogic;
  }
}
