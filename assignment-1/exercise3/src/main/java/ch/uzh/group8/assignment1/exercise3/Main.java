package ch.uzh.group8.assignment1.exercise3;

import ch.uzh.group8.assignment1.exercise3.movevalidator.*;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    var board = new Board();
    var console = new Console();
    var boardPrinter = new BoardPrinter(console);

    var moveIsDiagonal = new MoveIsDiagonal();
    var moveIsForwardIfNotKing = new MoveIsForwardIfNotKing();
    var moveLength = new MoveLength();
    var noOtherMoveToJumpPossible = new NoOtherMoveToJumpPossible();
    var opponentPieceBetweenJump = new OpponentPieceBetweenJump();
    var startPieceValid = new StartPieceValid();
    var targetFieldEmpty = new TargetFieldEmpty();

    var moveValidators =
        List.of(
            moveIsDiagonal,
            moveIsForwardIfNotKing,
            moveLength,
            noOtherMoveToJumpPossible,
            opponentPieceBetweenJump,
            startPieceValid,
            targetFieldEmpty);

    var winCondition = new WinCondition(moveValidators);

    var gameLogic =
        new GameLogic(
            console, board, boardPrinter, moveValidators, noOtherMoveToJumpPossible, winCondition);

    gameLogic.run();
  }
}
