package main.java.ssss.piece;

import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.CoordinatesShift;

import java.util.Set;

public class Queen extends LongRangePiece implements IRook,IBishop{

    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> allPossibleMoves() {
        Set<CoordinatesShift> moves = getBishopMoves();
        moves.addAll(getRookMoves());
        return moves;
    }

    @Override
    public String getSprite() {
        return " ♛ ";
    }
}
