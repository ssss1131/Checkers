package main.java.ssss.piece;

import main.java.ssss.coordinate.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface IBishop {
    default Set<CoordinatesShift> getBishopMoves(){
        Set<CoordinatesShift> result = new HashSet<>();

        //from top right to bottom left
        //получается справа сверху с угла до нижнего угла надо -7 и -7 сделать шагов по гориз и вертик
        for (int i = -7; i <= 7; i++) {
            if (i == 0) continue; //слон не может переместиться на свое же место

            result.add(new CoordinatesShift(i, i));
        }
        //from top right to bottom left
        for (int i = -7; i <= 7; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(i, -i));
        }
        return result;
    }
}
