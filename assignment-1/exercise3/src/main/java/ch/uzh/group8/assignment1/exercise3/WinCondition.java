package ch.uzh.group8.assignment1.exercise3;

import ch.uzh.group8.assignment1.exercise3.movevalidator.MoveValidator;
import java.util.List;

/**
 * Check if one player cannot move or has no pieces. This can be done in one go, as if one player
 * has no pieces, he cannot move. Use MoveValidators to check if any move is possible.
 */
public class WinCondition {
  public WinCondition(List<MoveValidator> moveValidators) {}

  public boolean hasPlayerWon(Player player, Board board) {
    return false;
  }
}
