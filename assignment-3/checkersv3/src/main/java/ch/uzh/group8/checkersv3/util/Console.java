package ch.uzh.group8.checkersv3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
  private static final Console instance = new Console();

  public static Console getInstance() {
    return instance;
  }

  private Console() {
  }

  public void print(String string) {
    System.out.println(string);
  }

  public String getUserInput() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    try {
      return reader.readLine();
    } catch (IOException e) {
      return "";
    }
  }
}
