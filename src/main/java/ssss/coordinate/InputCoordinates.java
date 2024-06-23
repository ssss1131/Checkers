package main.java.ssss.coordinate;

import main.java.ssss.board.Board;
import main.java.ssss.board.BoardRenderer;
import main.java.ssss.piece.Piece;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {

    private static final Scanner scanner = new Scanner(System.in);

    public static ArrayList<Coordinates> inputMove(Board board, Color color, BoardRenderer renderer, boolean isCreatedPromotedPiece) {
        ArrayList<Coordinates> result = new ArrayList<>();

        Coordinates sourceCoordinates = inputPieceCoordinatesForColor(color, board, isCreatedPromotedPiece);
        Piece piece = board.getPiece(sourceCoordinates);
        result.add(sourceCoordinates);
        renderer.render(board, piece);

        Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
        Coordinates targetCoordinates = inputAvailableSquare(availableMoveSquares);
        result.add(targetCoordinates);

        return result;
    }


    public static Coordinates input(boolean isCreatedPromotedPiece) {
        while (true) {
            System.out.println("Please enter coordinates (ex. a1)");

            String line = scanner.nextLine();
            if(isCreatedPromotedPiece){
                line = scanner.nextLine();
            }

            if (line.length() != 2) {
                System.out.println("Invalid format");
                continue;
            }

            char fileChar = line.charAt(0);
            char rankChar = line.charAt(1);

            if (!Character.isLetter(fileChar)) {
                System.out.println("Invalid format");
                continue;
            }
            if (!Character.isDigit(rankChar)) {
                System.out.println("Invalid format");
                continue;
            }
            int rank = Character.getNumericValue(rankChar);
            if (rank < 1 || rank > 8) {
                System.out.println("Invalid format");
                continue;
            }
            File file = File.fromChar(fileChar);

            if (file == null) {
                System.out.println("Invalid format");
                continue;
            }
            return new Coordinates(file, rank);
        }
    }

    public static Coordinates inputPieceCoordinatesForColor(Color color, Board board, boolean isCreatedPromotedPiece) {
        while (true) {
            System.out.println("Enter coordinates for a piece to move: ");
            Coordinates coordinates = input(isCreatedPromotedPiece);

            if (board.isSquareEmpty(coordinates)) {
                System.out.println("Empty square");
                continue;
            }

            Piece piece = board.getPiece(coordinates);
            if (color != piece.getColor()) {
                System.out.println("Wrong color");
                continue;
            }
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            if (availableMoveSquares.size() == 0) {
                System.out.println("Blocked piece");
                continue;
            }
            return coordinates;

        }
    }


    public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates) {
        while (true) {
            System.out.println("Enter your move for selected piece");
            Coordinates input = input(false);
            if (!coordinates.contains(input)) {
                System.out.println("Non-Available square");
                continue;
            }

            return input;
        }
    }

    public static Piece checkForValidInputPromotedPiece(Coordinates input) {
        char choice =' ';
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Choose promotion piece: (Q)ueen, (K)night, (B)ishop, (R)ook");
            choice = scanner.next().toUpperCase().charAt(0);

            if (choice == 'Q' || choice == 'K' || choice == 'B' || choice == 'R') {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please enter Q, K, B, or R.");
            }
        }
        Piece piece = Piece.charToPromotedPiece(choice, input, input.rank == 8 ? Color.WHITE : Color.BLACK);
        return piece;
    }
}

