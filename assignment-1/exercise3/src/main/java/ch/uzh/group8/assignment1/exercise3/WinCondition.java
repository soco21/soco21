package ch.uzh.group8.assignment1.exercise3;

import static ch.uzh.group8.assignment1.exercise3.BoardCoordinates.*;

import ch.uzh.group8.assignment1.exercise3.movevalidator.MoveValidator;
import java.util.List;

/**
 * Check if one player cannot move or has no pieces. This can be done in one go, as if one player
 * has no pieces, he cannot move. Use MoveValidators to check if any move is possible.
 */
@SuppressWarnings("ClassCanBeRecord")
public class WinCondition {
  private final List<MoveValidator> moveValidators;

  public WinCondition(List<MoveValidator> moveValidators) {
    this.moveValidators = moveValidators;
  }

  public boolean hasPlayerWon(Player player, Board board) {
    for (var row : Row.values()) {
      for (var col : Column.values()) {
        var currentCoordinates = new BoardCoordinates(row, col);
        var pieceAt = board.getPieceAt(currentCoordinates);
        if (pieceAt.isEmpty()) {
          continue;
        }
        var piece = pieceAt.get();
        if (piece.owner() == player) {
          continue;
        }
        var possibleMoves =
            Move.generatePossibleMoves(currentCoordinates, piece.owner(), List.of(1, 2));
        if (possibleMoves.stream()
            .anyMatch(
                move ->
                    moveValidators.stream()
                        .allMatch(moveValidator -> moveValidator.validate(move, board)))) {
          return false;
        }
      }
    }
    return true;
  }
}
