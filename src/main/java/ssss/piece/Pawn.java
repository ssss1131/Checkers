package main.java.ssss.piece;

import main.java.ssss.board.Board;
import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    public Pawn(Coordinates coordinates, Color color) {
        super(coordinates, color);
    }

    @Override
    public String getSprite() {
        return " " + "♟" + " ";
    }

    @Override
    public boolean isSquareAvailableForAttack(Coordinates shiftedCoordinates, Board board) {
        Coordinates coordinatesOfAttackedPiece = shiftedCoordinates.getPieceBetween(board, this.getCoordinates(), shiftedCoordinates);
        Piece attackedPiece = board.getPiece(coordinatesOfAttackedPiece);

        return attackedPiece != null && attackedPiece.getColor() != this.getColor() && board.isSquareEmpty(shiftedCoordinates);

    }


    @Override
    public Set<CoordinatesShift> allPossibleMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        if (this.getColor() == Color.WHITE) {
            result.add(new CoordinatesShift(1, 1));
            result.add(new CoordinatesShift(-1, 1));
            result.add(new CoordinatesShift(2, 2));
            result.add(new CoordinatesShift(-2, 2));
        } else {
            result.add(new CoordinatesShift(1, -1));
            result.add(new CoordinatesShift(-1, -1));
            result.add(new CoordinatesShift(2, -2));
            result.add(new CoordinatesShift(-2, -2));
        }

        return result;
    }

    @Override
    public boolean isSquareAvailableForMove(Board board, Coordinates shiftedCoordinates) {
        return board.isSquareEmpty(shiftedCoordinates);
    }

    @Override
    public Set<Coordinates> getAvailableMoveSquares(Board board) {
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift shift : allPossibleMoves()) {
            if (this.getCoordinates().canShift(shift)) {
                Coordinates newCoordinates = this.getCoordinates().Shift(shift);
                if (Math.abs(shift.rankShift) == 2) {
                    if (isSquareAvailableForAttack(newCoordinates, board)) {
                        result.add(newCoordinates);
                    }
                } else {
                    if (isSquareAvailableForMove(board, newCoordinates)) {
                        result.add(newCoordinates);
                    }
                }
            }
        }
        return result;
    }


}
