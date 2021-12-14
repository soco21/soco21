package ch.uzh.group8.assignment4.exercise1;

import ch.uzh.group8.assignment4.exercise1.util.Console;
import java.util.Optional;

public class Play {
  private final Pack pack;
  private final Console console;

  public Play(Pack pack, Console console) {
    this.pack = pack;
    this.console = console;
  }

  /** Returns empty if the player was busted, else Optional.of(score). */
  public Optional<Integer> doPlayFor(Player player) {
    player.printAllCards();
    int currentScore = player.getScore();
    console.print("Score of hand is: " + currentScore);
    PlayAction playAction = player.hitOrStay();
    while (playAction == PlayAction.HIT) {
      player.addCard(pack.drawCard());
      player.printAllCards();
      currentScore = player.getScore();
      console.print("Score of hand is: " + currentScore);
      if (currentScore > 21) {
        return Optional.empty();
      }
      playAction = player.hitOrStay();
    }
    return Optional.of(player.getScore());
  }
}
