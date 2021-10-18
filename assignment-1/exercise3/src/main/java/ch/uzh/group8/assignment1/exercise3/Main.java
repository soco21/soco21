package ch.uzh.group8.assignment1.exercise3;

import ch.uzh.group8.assignment1.exercise3.movevalidator.*;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    GameLogic gameLogic = createGameLogic(new Console());

    gameLogic.run();
  }

  public static GameLogic createGameLogic(Console console) {
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

    return new GameLogic(
        console, board, boardPrinter, moveValidators, noOtherMoveToJumpPossible, winCondition);
  }
}
