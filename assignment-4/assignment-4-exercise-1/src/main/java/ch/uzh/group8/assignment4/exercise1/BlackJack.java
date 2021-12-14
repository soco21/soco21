package ch.uzh.group8.assignment4.exercise1;

import ch.uzh.group8.assignment4.exercise1.util.Console;
import java.util.Optional;

public class BlackJack {
  private final Console console;
  private final HumanPlayer humanPlayer;
  private final Dealer dealer;
  private final Pack pack;
  private final Play play;

  public BlackJack(Console console, HumanPlayer humanPlayer, Dealer dealer, Pack pack, Play play) {
    this.console = console;
    this.humanPlayer = humanPlayer;
    this.dealer = dealer;
    this.pack = pack;
    this.play = play;
  }

  public void run() {
    while (true) {
      humanPlayer.printBalance();
      while (true) {
        console.print("Do you want to play? Type 'yes' or 'no'");
        String userInput = console.getUserInput().strip();
        if ("yes".equalsIgnoreCase(userInput)) {
          break;
        } else if ("no".equalsIgnoreCase(userInput)) {
          return;
        }
      }

      humanPlayer.startRound();
      dealer.startRound();

      humanPlayer.placeBet();

      humanPlayer.addCard(pack.drawCard());
      humanPlayer.addCard(pack.drawCard());
      dealer.addCard(pack.drawCard());
      dealer.addCard(pack.drawCard());

      console.print("Dealers initial hand:");
      dealer.printInitialHand();
      console.print("\n\nYour initial hand:");
      humanPlayer.printInitialHand();

      Optional<Integer> humanPlayerScoreOptional = play.doPlayFor(humanPlayer);

      Optional<Integer> dealerScoreOptional = Optional.empty();
      if (humanPlayerScoreOptional.isPresent()) {
        console.print("Now it's the dealers turn:");
        dealerScoreOptional = play.doPlayFor(dealer);
      }

      if (humanPlayerScoreOptional.isEmpty()) {
        console.print("You were busted");
      } else if (dealerScoreOptional.isEmpty()) {
        console.print("The dealer was busted");
      }

      Integer humanPlayerScore = humanPlayerScoreOptional.orElse(-1);
      Integer dealerScore = dealerScoreOptional.orElse(0);
      if (humanPlayerScore > dealerScore) {
        console.print("You won");
        humanPlayer.applyWon();
      } else if (humanPlayerScore < dealerScore) {
        console.print("You lost");
        humanPlayer.applyLost();
      } else if (dealerScoreOptional.isPresent()) {
        console.print("It was a draw, you can keep your bet");
      }

      if (humanPlayer.isBroke()) {
        console.print("You lost all your money, see you next time");
        return;
      }
    }
  }
}
