package ch.uzh.group8.assignment4.exercise1;

public interface Player {
  void addCard(Card card);

  void printAllCards();

  int getScore();

  PlayAction hitOrStay();
}
