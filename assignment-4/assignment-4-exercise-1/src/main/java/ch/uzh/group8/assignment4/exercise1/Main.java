package ch.uzh.group8.assignment4.exercise1;

import ch.uzh.group8.assignment4.exercise1.state.Dealer;
import ch.uzh.group8.assignment4.exercise1.state.HumanPlayer;
import ch.uzh.group8.assignment4.exercise1.state.Pack;
import ch.uzh.group8.assignment4.exercise1.util.Console;

public class Main {
  public static void main(String[] args) {
    Pack pack = new Pack();
    Console console = new Console();
    HumanPlayer humanPlayer = new HumanPlayer(console);
    Dealer dealer = new Dealer(console);
    Play play = new Play(pack, console);
    BlackJack blackJack = new BlackJack(console, humanPlayer, dealer, pack, play);
    blackJack.run();
  }
}
