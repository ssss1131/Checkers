package main.java.ssss.piece;

import main.java.ssss.board.Board;
import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

abstract public class Piece {
    private Color color;
    private Coordinates coordinates;

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Piece(Coordinates coordinates, Color color) {
        this.coordinates = coordinates;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    protected abstract Set<CoordinatesShift> allPossibleMoves();

    abstract public String getSprite();

    public Set<Coordinates> getAvailableMoveSquares(Board board) {
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift shift : allPossibleMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.Shift(shift);
                if (isSquareAvailableForMove(board, newCoordinates)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    public static Piece charToPromotedPiece(char choiceOfPromotedPiece, Coordinates coordinates, Color color){
        return switch (choiceOfPromotedPiece) {
            case 'Q' -> new Queen(color, coordinates);
            case 'B' -> new Bishop(color, coordinates);
            case 'K' -> new Knight(color, coordinates);
            case 'R' -> new Rook(color, coordinates);
            default -> null;
        };
    }


    abstract public boolean isSquareAvailableForAttack(Coordinates shiftedCoordinates, Board board);


    public boolean isSquareAvailableForMove(Board board, Coordinates shiftedCoordinates) {
        return board.isSquareEmpty(shiftedCoordinates) || color != board.getPiece(shiftedCoordinates).color ;
    }


}
