package ch.uzh.group8.checkersv3.util;

import ch.uzh.group8.checkersv3.dom.*;

import java.util.Optional;
import java.util.Random;

public class Gambler {
    public static float gambleCalculator(Board board, Move move) {
        float pointsPlayerWhite = 0;
        float pointsPlayerRed = 0;
        float pointsTotal;
        double winChance;
        BoardCoordinates.Row[] rows = BoardCoordinates.Row.values();
        BoardCoordinates.Column[] columns = BoardCoordinates.Column.values();
        for (BoardCoordinates.Row row : rows) {
            for (BoardCoordinates.Column col : columns) {
                BoardCoordinates currentCoordinates = new BoardCoordinates(row, col);
                Optional<Piece> pieceAt = board.getPieceAt(currentCoordinates);
                if (pieceAt.isPresent()) {
                    if (pieceAt.get().owner() == Player.PLAYER_WHITE) {
                        if (pieceAt.get().isKing()) {
                            pointsPlayerWhite += 2;
                        } else {
                            pointsPlayerWhite += 1;
                        }
                    } else {
                        if (pieceAt.get().isKing()) {
                            pointsPlayerRed += 2;
                        } else {
                            pointsPlayerRed += 1;
                        }
                    }
                }
            }
        }
        pointsTotal = pointsPlayerRed + pointsPlayerWhite;
        if (move.player() == Player.PLAYER_WHITE) {
            winChance = 1 - pointsPlayerWhite / pointsTotal;
        } else {
            winChance = 1 - pointsPlayerRed / pointsTotal;
        }
        return (float) winChance;
    }
    public static boolean gambleExecutor(float winChance){
        Random random = new Random();
        float nextFloat = random.nextFloat();
        return nextFloat <= winChance;
    }
}

