package game;

import chessPieces.Color;
import chessPieces.Coord;
import chessPieces.Movable;
import gameException.IllegalPosition;

import java.io.Serializable;
import java.util.ArrayList;

public class ChessBoard implements Serializable {
    private Case cases[][];
    public static final int SIZE = 8;
    public static int turn = 0;
    public Color currentPlayer = Color.WHITE;

    /**
     * Chessboard's constructor
     */
    public ChessBoard(){
        this.cases = new Case[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                this.cases[i][j] = new Case();
            }
        }
    }


    /**
     * @param pos The square where we want to check if it is occupied
     * @return  A boolean (true if is occupied)
     * @throws IllegalPosition If the method is used for a square out of the chessboard
     */
    public boolean isOccupied(Coord pos) throws IllegalPosition {
        if (pos.getX() < 0 || pos.getX() >= 8 || pos.getY() < 0 || pos.getY() >= 8){
            throw new IllegalPosition(pos);
        }
        return getCase(pos).isOccupied();
    }

    /**
     * @param pos Square where the piece has been moved or has been removed
     * @param p Interface which analyse if a square is occupied
     * @throws IllegalPosition
     */
    public void setOccupied(Coord pos, Movable p) throws IllegalPosition {
        try {
            getCase(pos).setPiece(p);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new IllegalPosition(pos);
        }
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Printer of the chessboard on the terminal
     * Place pieces in there square
     * Number rows and columns
     */
    public void smartPrint(){
        for (int i = 0; i<SIZE; i++){
            System.out.print((SIZE-i) + " ");
            for (int j = 0; j<SIZE; j++){
                try {
                    if (isOccupied(new Coord(i, j))) {
                        System.out.print(this.getCase(new Coord(i, j)).getPiece());
                    }
                    else {
                        System.out.print(". ");
                    }
                } catch (IllegalPosition illegalPosition) {
                    System.err.println("Sorry the dev isn't that good... " + illegalPosition);
                }
            }
            System.out.println();
        }
        System.out.print("  A B C D E F G H");
        System.out.println();
    }

    public Case getCase(Coord pos){
        return this.cases[pos.getX()][pos.getY()];
    }

    public boolean isEatableBy(Coord departure, Coord arrival) throws IllegalPosition {
        return this.isOccupied(arrival) && this.getCase(departure).getPiece().getCol() != this.getCase(arrival).getPiece().getCol();
    }

    public boolean isMovable(Coord pos){
        if (currentPlayer.equals(Color.WHITE)){
            if (this.getCase(pos).getPiece().getCol().equals(Color.WHITE)){
                return false;
            }
        } else {
            if (this.getCase(pos).getPiece().getCol().equals(Color.BLACK)){
                return false;
            }
        }
        return true;
    }

    public void changePlayerTurn(){
        switch (currentPlayer){
            case WHITE:
                currentPlayer = Color.BLACK;
                break;
            case BLACK:
                currentPlayer = Color.WHITE;
                break;
        }
    }
}
