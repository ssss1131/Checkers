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

            if((targetCoordinates.rank == 8 || targetCoordinates.rank == 1) && board.getPiece(sourceCoordinates) instanceof Pawn){
                Piece promotedPiece = InputCoordinates.checkForValidInputPromotedPiece(targetCoordinates);
                board.movePromotedPiece(sourceCoordinates,targetCoordinates ,promotedPiece);
                isCreatedPromotedPiece = true;
            }

            else{
                board.makeMove(sourceCoordinates, targetCoordinates);
            }
//НАААААААААААААдо добавить логику для пада, тип когда для цвета который ходит нету хода то это пад, и мб поменять логику для королевы и остальных на ходы как в шашках(надо убрать коня так как его логика вообще не такая)

            if(!board.isHavePieceWithColor(colorToMove.opposite())){
                break;
            }
            colorToMove = colorToMove.opposite();
        }
        renderer.render(board);
        System.out.println("Game ended with state = " + colorToMove.toString().toLowerCase() + " WINNER WINNER CHICKEN DINNER!");
    }

}
