package main.java.ssss.piece;

import main.java.ssss.board.Board;
import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.CoordinatesShift;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    public Knight(Color color, Coordinates coordinates) {
        super(coordinates, color);
    }

    @Override
    protected Set<CoordinatesShift> allPossibleMoves() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(1, 2),
                new CoordinatesShift(2, 1),

                new CoordinatesShift(2, -1),
                new CoordinatesShift(1, -2),

                new CoordinatesShift(-2, -1),
                new CoordinatesShift(-1, -2),

                new CoordinatesShift(-2, 1),
                new CoordinatesShift(-1, 2)
        ));
    }

    @Override
    public String getSprite() {
        return " ♞ ";
    }

    @Override
    public boolean isSquareAvailableForAttack(Coordinates shiftedCoordinates, Board board) {
        return true;
    }
}
