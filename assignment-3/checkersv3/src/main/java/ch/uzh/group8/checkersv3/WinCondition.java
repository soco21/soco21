package ch.uzh.group8.checkersv3;

import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Column;
import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Row;

import ch.uzh.group8.checkersv3.dom.*;
import ch.uzh.group8.checkersv3.movevalidator.MoveValidator;
import java.util.List;
import java.util.Optional;

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
    for (Row row : Row.values()) {
      for (Column col : Column.values()) {
        BoardCoordinates currentCoordinates = new BoardCoordinates(row, col);
        Optional<Piece> pieceAt = board.getPieceAt(currentCoordinates);
        if (pieceAt.isEmpty()) {
          continue;
        }
        Piece piece = pieceAt.get();
        if (piece.owner() == player) {
          continue;
        }
        List<Move> possibleMoves =
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
