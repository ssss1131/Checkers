package main.java.ssss.coordinate;

import main.java.ssss.board.Board;
import main.java.ssss.piece.Piece;

import java.util.Objects;

public class Coordinates {
    public File file;
    public int rank;

    public Coordinates(File file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public Coordinates Shift(CoordinatesShift shift) {
        return new Coordinates(File.values()[this.file.ordinal() + shift.fileShift], this.rank + shift.rankShift);
    }

    public boolean canShift(CoordinatesShift shift) {
        int f = shift.fileShift + this.file.ordinal();
        int r = shift.rankShift + this.rank;

        if (f < 0 || f > 7) return false;
        if (r < 1 || r > 8) return false;
        return true;
    }

    public Coordinates getPieceBetween(Board board, Coordinates sourceCoordinates, Coordinates targetCoordinates) {
        Piece piece = board.getPiece(sourceCoordinates);

        int shiftFile = targetCoordinates.file.ordinal() - sourceCoordinates.file.ordinal();
        int shiftRank = targetCoordinates.rank - sourceCoordinates.rank;
        if (Math.abs(shiftFile) != 2 || Math.abs(shiftRank) != 2) {
            return null;
        }
        int shiftFileForAttackedPiece = shiftFile > 0 ? 1 : -1;
        int shiftRankForAttackedPiece = shiftRank > 0 ? 1 : -1;


        Coordinates coordinatesOfAttackedPiece = new Coordinates(File.values()[piece.getCoordinates().file.ordinal() + shiftFileForAttackedPiece], piece.getCoordinates().rank + shiftRankForAttackedPiece);

        return coordinatesOfAttackedPiece;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return rank == that.rank && file == that.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return file + "" + rank;

    }
}
