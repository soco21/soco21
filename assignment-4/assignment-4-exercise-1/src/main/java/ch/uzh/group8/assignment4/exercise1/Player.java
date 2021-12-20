package ch.uzh.group8.assignment4.exercise1;

import ch.uzh.group8.assignment4.exercise1.domain.Card;

public interface Player {
  void addCard(Card card);

  void printAllCards();

  int getScore();

  PlayAction hitOrStay();
}
