package ch.uzh.group8.checkersv3.util;

import ch.uzh.group8.checkersv3.dom.Board;
import ch.uzh.group8.checkersv3.dom.Move;
import ch.uzh.group8.checkersv3.dom.Player;

import java.util.HashMap;
import java.util.Random;

import static ch.uzh.group8.checkersv3.dom.Player.PLAYER_RED;
import static ch.uzh.group8.checkersv3.dom.Player.PLAYER_WHITE;

public class Gambler {

  private static Board board;
  private static PointsCalculator pointsCalculator = new PointsCalculator(board);
  private static Random random = new Random();
  private Move move;

  public float Gambler(Random random) {
    return random.nextFloat();
  }

  public float calculateOdds(Move move, Board board) {
    HashMap<Player, Float> pointsOnBoard;
    pointsOnBoard = pointsCalculator.calculatePoints(board);
    float pointsPlayerWhite = pointsOnBoard.get(PLAYER_WHITE);
    float pointsPlayerRed = pointsOnBoard.get(PLAYER_RED);
    float totalPoints = pointsPlayerRed + pointsPlayerWhite;
    float winChance = 0;
    if (move.player() == PLAYER_RED) {
      winChance = 1 - pointsPlayerRed / totalPoints;
    } else {
      winChance = 1 - pointsPlayerWhite / totalPoints;
    }
    return winChance;
  }

  public boolean gambleExecutor() {
    float winChance = calculateOdds(move, board);
    if (winChance >= Gambler(random)) {
      return true;
    }
    return false;
  }
}
