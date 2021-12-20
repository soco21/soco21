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
      if (!humanPlayer.wantsToContinue()) {
        return;
      }

      humanPlayer.startRound();
      dealer.startRound();

      humanPlayer.placeBet();

      dealCards();

      printInitialHands();

      console.print("\n");
      console.print("Now it's the players turn:");
      Optional<Integer> humanPlayerScoreOptional = play.doPlayFor(humanPlayer);

      Optional<Integer> dealerScoreOptional = Optional.empty();
      if (humanPlayerScoreOptional.isPresent()) {
        console.print("\n");
        console.print("Now it's the dealers turn:");
        dealerScoreOptional = play.doPlayFor(dealer);
      }

      boolean dealerBusted = dealerScoreOptional.isEmpty();
      printIfBusted(humanPlayerScoreOptional.isEmpty(), dealerBusted);

      applyScore(humanPlayerScoreOptional.orElse(-1), dealerScoreOptional.orElse(0), dealerBusted);

      if (humanPlayer.isBroke()) {
        console.print("You lost all your money, see you next time");
        return;
      }
    }
  }

  private void printInitialHands() {
    console.print("Dealers initial hand:");
    dealer.printInitialHand();
    console.print("\n");
    console.print("Your initial hand:");
    humanPlayer.printInitialHand();
  }

  private void dealCards() {
    humanPlayer.addCard(pack.drawCard());
    humanPlayer.addCard(pack.drawCard());
    dealer.addCard(pack.drawCard());
    dealer.addCard(pack.drawCard());
  }

  private void printIfBusted(boolean humanPlayerBusted, boolean dealerBusted) {
    if (humanPlayerBusted) {
      console.print("You were busted");
    } else if (dealerBusted) {
      console.print("The dealer was busted");
    }
  }

  private void applyScore(Integer humanPlayerScore, Integer dealerScore, boolean dealerBusted) {
    if (humanPlayerScore > dealerScore) {
      console.print("You won");
      humanPlayer.applyWon();
    } else if (humanPlayerScore < dealerScore) {
      console.print("You lost");
      humanPlayer.applyLost();
    } else if (!dealerBusted) {
      console.print("It was a draw, you can keep your bet");
    }
  }
}
