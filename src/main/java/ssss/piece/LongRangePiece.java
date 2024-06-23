package main.java.ssss.piece;

import main.java.ssss.board.Board;
import main.java.ssss.board.BoardUtils;
import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;

import java.util.List;

public abstract class LongRangePiece extends Piece{
    public LongRangePiece(Color color, Coordinates coordinates) {
        super(coordinates, color);
    }

    @Override
    public boolean isSquareAvailableForMove(Board board, Coordinates coordinates) {
        boolean result = super.isSquareAvailableForMove(board, coordinates);
        if (result) {
            return isSquareAvailableForAttack(coordinates,board);
        } else
            return false;
    }

    @Override
    public boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        // 1 . get squares between current pass and target pass
        List<Coordinates> coordinatesBetween;
        if (this.getCoordinates().file == coordinates.file) {
            coordinatesBetween = BoardUtils.getVerticalCoordinatesBetween(this.getCoordinates(), coordinates);
        } else if (this.getCoordinates().rank == coordinates.rank) {
            coordinatesBetween = BoardUtils.getHorinzontalCoordinatesBetween(this.getCoordinates(), coordinates);
        } else {
            coordinatesBetween = BoardUtils.getDiagonalCoordinatesBetween(this.getCoordinates(), coordinates);
        }
        for (Coordinates c : coordinatesBetween) {
            if (!board.isSquareEmpty(c)) return false;
        }
        return true;
    }
}
