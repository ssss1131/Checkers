package main.java.ssss.board;

import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.File;
import main.java.ssss.piece.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardRenderer {

    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";

    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";

    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";

    public static final String ANSI_RESET = "\u001B[0m";


    public void render(Board board, Piece pieceToMove) {

        Set<Coordinates> availableMoveSquares = emptySet();
        if (pieceToMove != null) {
            availableMoveSquares = pieceToMove.getAvailableMoveSquares(board);
        }

        for (int rank = 8; rank >= 1; rank--) {
            String line = "";
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighlight = availableMoveSquares.contains(coordinates);


                if (board.isSquareEmpty(coordinates)) {
                    line += getSpriteForEmptySquare(coordinates, isHighlight);
                } else {
                    line += getPieceSprite(board.getPiece(coordinates), isHighlight);
                }

            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    public void render(Board board) {
        render(board, null);
    }

    private String getPieceSprite(Piece piece, boolean isHighLight) {
        return colorizeSprite(piece.getSprite(), piece.getColor(), isSquareDark(piece.getCoordinates()), isHighLight);
    }

    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighLight) {
        return colorizeSprite("    ", Color.WHITE, isSquareDark(coordinates), isHighLight);
    }

    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighLight) {
        String result = sprite;
        if (pieceColor == Color.BLACK) {
            result = ANSI_BLACK_PIECE_COLOR + result;
        } else {
            result = ANSI_WHITE_PIECE_COLOR + result;
        }
        if (isHighLight) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if (isSquareDark) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }

        return result;

    }

    private boolean isSquareDark(Coordinates coordinates) {
        return (coordinates.file.ordinal() + coordinates.rank) % 2 != 0;
    }
}

