package ch.uzh.group8.checkersv3.util;

import java.util.Random;

public class CoinTosser {
  private final Random random = new Random();

  public Result toss() {
    int nextInt = random.nextInt(2);
    return nextInt == 1 ? Result.HEADS : Result.TAILS;
  }

  public enum Result {
    HEADS,
    TAILS
  }
}
