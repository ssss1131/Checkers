package main.java.ssss.piece;

import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.CoordinatesShift;

import java.util.Set;

public class Bishop extends LongRangePiece implements IBishop {

    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> allPossibleMoves() {
        return getBishopMoves();
    }

    @Override
    public String getSprite() {
        return " ♝ ";
    }
}
