package main.java.ssss.board;

import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.File;
import main.java.ssss.piece.Pawn;
import main.java.ssss.piece.Piece;
import main.java.ssss.piece.Queen;

import java.util.HashMap;

public class Board {
    public HashMap<Coordinates, Piece> pieces = new HashMap<>();


    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    public void setPiece(Coordinates coordinates, Piece piece) {
        piece.setCoordinates(coordinates);
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    public void makeMove(Coordinates from, Coordinates to) {
        Piece piece = getPiece(from);
        if (piece instanceof Pawn) {
            Coordinates coordinatesOfPieceBetween = from.getPieceBetween(this, from, to);
            Piece pieceBetween = this.getPiece(coordinatesOfPieceBetween);
            removePiece(from);
            setPiece(to, piece);
            if (pieceBetween != null) {
                this.removePiece(pieceBetween.getCoordinates());
            }
        }

        removePiece(from);

        setPiece(to, piece);
    }

    public void movePromotedPiece(Coordinates from, Coordinates to, Piece piece) {
            Coordinates coordinatesOfPieceBetween = from.getPieceBetween(this, from, to);
            Piece pieceBetween = this.getPiece(coordinatesOfPieceBetween);
            removePiece(from);
            setPiece(to, piece);
            if (pieceBetween != null) {
                this.removePiece(pieceBetween.getCoordinates());
        }
    }

    public boolean isHavePiecesWithColor(Color color) {
        for (Piece piece : pieces.values()) {
            if (piece.getColor() == color) {
                return true;
            }
        }
        return false;
    }

    public boolean isHaveMoveForColorPiece(Color color){
        for (Piece value : pieces.values()) {
            if(value.getColor() == color){
                if(!value.getAvailableMoveSquares(this).isEmpty())
                    return true;
            }
        }
        return false;
    }


    public void setUpDefaultPosition() {

        // Белые шашки
        for (int rank = 1; rank <= 3; rank++) {
            for (File file : File.values()) {
                if ((file.ordinal() + rank) % 2 != 0) {
                    setPiece(new Coordinates(file, rank), new Pawn(new Coordinates(file, rank), Color.WHITE));
                }
            }
        }

        // Черные шашки
        for (int rank = 6; rank <= 8; rank++) {
            for (File file : File.values()) {
                if ((file.ordinal() + rank) % 2 != 0) {
                    setPiece(new Coordinates(file, rank), new Pawn(new Coordinates(file, rank), Color.BLACK));
                }
            }
        }
    }

//    public void setUpSpecialPosition() {
//        setPiece(new Coordinates(File.B,8),new Queen(Color.WHITE,new Coordinates(File.B,8)));
//        setPiece(new Coordinates(File.D,8),new Bishop(Color.WHITE,new Coordinates(File.D,8)));
//        setPiece(new Coordinates(File.F,8),new Knight(Color.WHITE,new Coordinates(File.F,8)));
//        setPiece(new Coordinates(File.H,8),new Rook(Color.WHITE,new Coordinates(File.H,8)));
//        setPiece(new Coordinates(File.A,1),new Queen(Color.BLACK,new Coordinates(File.A,1)));
//        setPiece(new Coordinates(File.C,1),new Bishop(Color.BLACK,new Coordinates(File.C,1)));
//        setPiece(new Coordinates(File.E,1),new Knight(Color.BLACK,new Coordinates(File.E,1)));
//        setPiece(new Coordinates(File.G,1),new Rook(Color.BLACK,new Coordinates(File.G,1)));
//        setPiece(new Coordinates(File.A, 3), new Pawn(new Coordinates(File.A, 3), Color.BLACK));
//        setPiece(new Coordinates(File.C, 1), new Pawn(new Coordinates(File.C, 1), Color.WHITE));
//        setPiece(new Coordinates(File.B, 2), new Pawn(new Coordinates(File.B, 2), Color.WHITE));
//        setPiece(new Coordinates(File.D, 4), new Pawn(new Coordinates(File.D, 4), Color.WHITE));
//
//
//
//    }
//
//    public void setUpFinalPosition() {
//        setPiece(new Coordinates(File.B, 8), new Queen(Color.WHITE, new Coordinates(File.B, 8)));
//        setPiece(new Coordinates(File.B, 1), new Queen(Color.BLACK, new Coordinates(File.B, 1)));
//    }


    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }
}
