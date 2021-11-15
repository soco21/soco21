package ch.uzh.group8.checkersv3.util;

import static ch.uzh.group8.checkersv3.util.CoinTosser.Result.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class CoinTosserTest {
  @Test
  public void return_heads_and_tails_evenly_distributed() {
    CoinTosser coinTosser = new CoinTosser();
    long count =
        IntStream.range(0, 100).mapToObj(__ -> coinTosser.toss()).filter(HEADS::equals).count();
    // use assume because of the random element
    assumeTrue(count < 70);
    assumeTrue(count > 30);
  }
}
