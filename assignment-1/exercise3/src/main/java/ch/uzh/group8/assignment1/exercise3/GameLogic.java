package ch.uzh.group8.assignment1.exercise3;

import ch.uzh.group8.assignment1.exercise3.movevalidator.MoveValidator;
import ch.uzh.group8.assignment1.exercise3.movevalidator.NoOtherMoveToJumpPossible;
import java.util.List;

@SuppressWarnings("ClassCanBeRecord")
public class GameLogic {
  private final Console console;
  private final Board board;
  private final BoardPrinter boardPrinter;
  private final List<MoveValidator> moveValidators;
  private final NoOtherMoveToJumpPossible noOtherMoveToJumpPossible;
  private final WinCondition winCondition;

  public GameLogic(
      Console console,
      Board board,
      BoardPrinter boardPrinter,
      List<MoveValidator> moveValidators,
      NoOtherMoveToJumpPossible noOtherMoveToJumpPossible,
      WinCondition winCondition) {
    this.console = console;
    this.board = board;
    this.boardPrinter = boardPrinter;
    this.moveValidators = moveValidators;
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
    while (true) {
      var userInput = console.getUserInput();
      Move move;
      try {
        move = Move.parse(player, userInput);
      } catch (IllegalArgumentException e) {
        console.print("Invalid input, try again");
        continue;
      }
      var pieceAtStart = board.getPieceAt(move.start());
      if (!moveValidators.stream().allMatch(moveValidator -> moveValidator.validate(move, board))) {
        console.print("Invalid move, try again");
        continue;
      }

      board.executeMove(move);

      var hasPlayerWon = winCondition.hasPlayerWon(player, board);
      if (hasPlayerWon) {
        console.print("Congratulations, player " + player + " has won");
        return true;
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
    }
  }

  private boolean wasKingCreated(Piece pieceAtStart, Piece pieceAtEnd) {
    if (pieceAtStart == null || pieceAtEnd == null) {
      return false;
    }
    return !pieceAtStart.isKing() && pieceAtEnd.isKing();
  }
}
