package ch.uzh.group8.checkersv3;

import ch.uzh.group8.checkersv3.dom.*;
import ch.uzh.group8.checkersv3.dom.board.Board;
import ch.uzh.group8.checkersv3.movevalidator.MoveValidator;
import ch.uzh.group8.checkersv3.movevalidator.NoOtherMoveToJumpPossible;
import ch.uzh.group8.checkersv3.util.Console;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ClassCanBeRecord")
public class GameLogic {
  private final Console console;
  private final Board board;
  private final List<MoveValidator> moveValidators;
  private final MoveExecutor moveExecutor;
  private final NoOtherMoveToJumpPossible noOtherMoveToJumpPossible;
  private final WinCondition winCondition;

  public GameLogic(
      Console console,
      Board board,
      List<MoveValidator> moveValidators,
      MoveExecutor moveExecutor,
      NoOtherMoveToJumpPossible noOtherMoveToJumpPossible,
      WinCondition winCondition) {
    this.console = console;
    this.board = board;
    this.moveValidators = moveValidators;
    this.moveExecutor = moveExecutor;
    this.noOtherMoveToJumpPossible = noOtherMoveToJumpPossible;
    this.winCondition = winCondition;
  }

  public void run() {
    Player currentPlayer = Player.PLAYER_RED;
    while (true) {
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
      String userInput = console.getUserInput();
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
      Optional<Piece> pieceAtStart = board.getPieceAt(move.start());
      if (!moveValidators.stream().allMatch(moveValidator -> moveValidator.validate(move, board))) {
        console.print("Invalid move, try again");
        continue;
      }

      Move executedMove = moveExecutor.executeMove(move);

      boolean hasPlayerWon = winCondition.hasPlayerWon(player, board);
      if (hasPlayerWon) {
        console.print("Congratulations, player " + player + " has won");
        return true;
      }

      if (executedMove.jumpGambleResult() == JumpGambleResult.WON) {
        console.print("The gamble has been won, " + player + " can play again.");
        console.print(player + ", make your move");
        continue;
      }

      Optional<Piece> pieceAtEnd = board.getPieceAt(move.end());
      boolean wasKingCreated = wasKingCreated(pieceAtStart.orElse(null), pieceAtEnd.orElse(null));
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
