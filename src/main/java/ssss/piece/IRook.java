package main.java.ssss.piece;

import main.java.ssss.coordinate.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface IRook {

    default Set<CoordinatesShift> getRookMoves(){
        Set<CoordinatesShift> result = new HashSet<>();
        //from left to right
        for (int i = 1; i <= 7; i++) {
            result.add(new CoordinatesShift(i, 0));
            result.add(new CoordinatesShift(-i, 0));
            result.add(new CoordinatesShift(0, i));
            result.add(new CoordinatesShift(0, -i));
        }
        //from bottom to top


        return result;
    }
}
