package ch.uzh.group8.checkersv2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
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
