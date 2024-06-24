package main.java.ssss;

import main.java.ssss.board.Board;
import main.java.ssss.board.BoardRenderer;
import main.java.ssss.coordinate.Color;
import main.java.ssss.coordinate.Coordinates;
import main.java.ssss.coordinate.InputCoordinates;
import main.java.ssss.piece.Pawn;
import main.java.ssss.piece.Piece;

import java.util.ArrayList;

public class Game {
    private final BoardRenderer renderer = new BoardRenderer();
    private final Board board;

    public Game(Board board) {
        this.board = board;

    }

    public void GameLoop() {
        Color colorToMove = Color.WHITE;
        boolean isCreatedPromotedPiece = false;
        boolean isStalemate =false;

        board.setUpDefaultPosition();

        while (true) {

            renderer.render(board);

            if (colorToMove == Color.WHITE) {
                System.out.println("White to move");
            } else System.out.println("Black to move");


            ArrayList<Coordinates> sourceAndTargetCoordinates = InputCoordinates.inputMove(board, colorToMove, renderer, isCreatedPromotedPiece);
            Coordinates sourceCoordinates = sourceAndTargetCoordinates.get(0);
            Coordinates targetCoordinates = sourceAndTargetCoordinates.get(1);
            isCreatedPromotedPiece = false;

            if ((targetCoordinates.rank == 8 || targetCoordinates.rank == 1) && board.getPiece(sourceCoordinates) instanceof Pawn) {
                Piece promotedPiece = InputCoordinates.checkForValidInputPromotedPiece(targetCoordinates);
                board.movePromotedPiece(sourceCoordinates, targetCoordinates, promotedPiece);
                isCreatedPromotedPiece = true;
            } else {
                board.makeMove(sourceCoordinates, targetCoordinates);
            }

            if (!board.isHavePiecesWithColor(colorToMove.opposite())) {
                break;
            } if (!board.isHaveMoveForColorPiece(colorToMove.opposite())) {
                isStalemate = true;
                break;
            }

            colorToMove = colorToMove.opposite();
        }

        renderer.render(board);
        if (isStalemate) {
            System.out.println("Game ended with state = Stalemate");
        } else {
            System.out.println("Game ended with state = " + colorToMove.toString().toLowerCase() + " WINNER WINNER CHICKEN DINNER!");
        }
    }

}
