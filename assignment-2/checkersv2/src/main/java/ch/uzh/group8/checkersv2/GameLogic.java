package ch.uzh.group8.checkersv2;

import ch.uzh.group8.checkersv2.dom.*;
import ch.uzh.group8.checkersv2.movevalidator.MoveValidator;
import ch.uzh.group8.checkersv2.movevalidator.NoOtherMoveToJumpPossible;
import ch.uzh.group8.checkersv2.util.BoardPrinter;
import ch.uzh.group8.checkersv2.util.Console;
import java.util.List;

@SuppressWarnings("ClassCanBeRecord")
public class GameLogic {
  private final Console console;
  private final Board board;
  private final BoardPrinter boardPrinter;
  private final List<MoveValidator> moveValidators;
  private final MoveExecutor moveExecutor;
  private final NoOtherMoveToJumpPossible noOtherMoveToJumpPossible;
  private final WinCondition winCondition;

  public GameLogic(
      Console console,
      Board board,
      BoardPrinter boardPrinter,
      List<MoveValidator> moveValidators,
      MoveExecutor moveExecutor,
      NoOtherMoveToJumpPossible noOtherMoveToJumpPossible,
      WinCondition winCondition) {
    this.console = console;
    this.board = board;
    this.boardPrinter = boardPrinter;
    this.moveValidators = moveValidators;
    this.moveExecutor = moveExecutor;
    this.noOtherMoveToJumpPossible = noOtherMoveToJumpPossible;
    this.winCondition = winCondition;
  }

  public void run() {
    console.print("Welcome to checkers");
    Player currentPlayer = Player.PLAYER_RED;
    while (true) {
      boardPrinter.printBoard(board);
      console.print(currentPlayer + ", make your move");
      if (doPlayerMove(currentPlayer)) {
        return;
      }
      if (currentPlayer == Player.PLAYER_WHITE) {
        currentPlayer = Player.PLAYER_RED;
      } else {
        currentPlayer = Player.PLAYER_WHITE;
      }
    }
  }

  private boolean doPlayerMove(Player player) {
    BoardCoordinates startCoordinatesForMultipleJump = null;
    while (true) {
      var userInput = console.getUserInput();
      Move move;
      try {
        move = Move.parse(player, userInput);
      } catch (IllegalArgumentException e) {
        console.print("Invalid input, try again");
        continue;
      }
      if (startCoordinatesForMultipleJump != null
          && !startCoordinatesForMultipleJump.equals(move.start())) {
        console.print("For a multiple jump move, the same piece has to be used. Try again");
        continue;
      }
      var pieceAtStart = board.getPieceAt(move.start());
      if (!moveValidators.stream().allMatch(moveValidator -> moveValidator.validate(move, board))) {
        console.print("Invalid move, try again");
        continue;
      }

      Move executedMove = moveExecutor.executeMove(move);

      var hasPlayerWon = winCondition.hasPlayerWon(player, board);
      if (hasPlayerWon) {
        console.print("Congratulations, player " + player + " has won");
        return true;
      }

      if (executedMove.jumpGambleResult() == JumpGambleResult.WON) {
        console.print("The gamble has been won, " + player + " can play again.");
        boardPrinter.printBoard(board);
        console.print(player + ", make your move");
        continue;
      }

      var pieceAtEnd = board.getPieceAt(move.end());
      var wasKingCreated = wasKingCreated(pieceAtStart.orElse(null), pieceAtEnd.orElse(null));
      if (wasKingCreated) {
        return false;
      }
      if (!move.isJumpMove()
          || !noOtherMoveToJumpPossible.jumpMovePossibleFrom(move.end(), board)) {
        return false;
      }
      console.print("Multiple jump move for " + player + ". Enter your next jump.");
      startCoordinatesForMultipleJump = move.end();
    }
  }

  private boolean wasKingCreated(Piece pieceAtStart, Piece pieceAtEnd) {
    if (pieceAtStart == null || pieceAtEnd == null) {
      return false;
    }
    return !pieceAtStart.isKing() && pieceAtEnd.isKing();
  }
}
