package ch.uzh.group8.checkersv2;

import java.util.Random;
import java.util.Scanner;

public class Gamble {
    public static boolean decision(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Feeling lucky? yes/no");
        String userDecision = userInput.nextLine();
        if (userDecision == "yes") {
            return true;
        }
        else if (userDecision == "no") {
            return false;
        }
        else {
            throw new IllegalArgumentException("invalid input: Type in yes or no!");
        }
    }
    public static boolean doGamble(){
        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("Congrats, you can move twice!");
            return true;
        }
        else {
            System.out.println("You lost! Your piece is gone!");
            return true;
        }
    }
}
