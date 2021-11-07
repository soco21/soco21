package ch.uzh.group8.checkersv2.movevalidator;

import static ch.uzh.group8.checkersv2.dom.BoardCoordinates.Column;
import static ch.uzh.group8.checkersv2.dom.BoardCoordinates.Row;

import ch.uzh.group8.checkersv2.dom.*;
import java.util.List;
import java.util.Optional;

public class NoOtherMoveToJumpPossible implements MoveValidator {

  private final List<MoveValidator> jumpValidators;

  public NoOtherMoveToJumpPossible(
      MoveIsForwardIfNotKing moveIsForwardIfNotKings,
      OpponentPieceBetweenJump opponentPieceBetweenJump,
      TargetFieldEmpty targetFieldEmpty) {
    jumpValidators = List.of(moveIsForwardIfNotKings, opponentPieceBetweenJump, targetFieldEmpty);
  }

  @Override
  public boolean validate(Move move, Board board) {
    Row[] rows = Row.values();
    Column[] columns = Column.values();
    if (move.isJumpMove()) {
      return true;
    }
    for (Row row : rows) {
      for (Column col : columns) {
        BoardCoordinates currentCoordinates = new BoardCoordinates(row, col);
        Optional<Piece> pieceAt = board.getPieceAt(currentCoordinates);
        if (pieceAt.isEmpty()) {
          continue;
        }
        if (pieceAt.get().owner() != move.player()) {
          continue;
        }
        if (jumpMovePossibleFrom(currentCoordinates, board)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean jumpMovePossibleFrom(BoardCoordinates boardCoordinates, Board board) {
    Optional<Piece> pieceAt = board.getPieceAt(boardCoordinates);
    if (pieceAt.isEmpty()) {
      return false;
    }
    Player pieceOwner = pieceAt.get().owner();

    List<Move> possibleJumpMoves =
        Move.generatePossibleMoves(boardCoordinates, pieceOwner, List.of(2));

    return possibleJumpMoves.stream()
        .anyMatch(
            move ->
                jumpValidators.stream()
                    .allMatch(moveValidator -> moveValidator.validate(move, board)));
  }
}
