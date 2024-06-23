package main.java.ssss.piece;

import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.CoordinatesShift;

import java.util.Set;

public class Rook extends LongRangePiece implements IRook {
//интерфейс сделан для того чтобы не дублировать код для ходов Ферзя
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }


    @Override
    protected Set<CoordinatesShift> allPossibleMoves() {
        return getRookMoves();
    }

    @Override
    public String getSprite() {
        return " ♜ ";
    }
}
