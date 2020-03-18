package game;
import chessPieces.Color;
import chessPieces.Coord;
import chessPieces.Movable;
import gameException.IllegalMove;
import gameException.IllegalPosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *  Abstract class piece
 */
public abstract class Piece implements Movable, Serializable {
    protected Coord place;
    protected Color col;
    protected final ChessBoard board;

    /**
     * @param place Piece's position on the chessboard
     * @param col Color of the piece (important for the pawn)
     * @param board Actual chessboard where the game is progressing
     */
    public Piece(Coord place, Color col, ChessBoard board) {
        this.place = place;
        this.col = col;
        this.board = board;
        try {
            this.board.setOccupied(place, this);
        } catch (IllegalPosition illegalPosition) {
            System.err.println("Errors have been committed... " + illegalPosition);;
        }
    }

    /**
     * @return Place's getter
     */
    public Coord getPlace() {
        return place;
    }

    /**
     * @return Color's getter
     */
    public Color getCol() {
        return col;
    }

    /**
     * @return Chessboard's getter
     */
    public ChessBoard getBoard() {
        return board;
    }

    /**
     * @param place The position chosen by the player
     * @throws IllegalMove When the movement is impossible due to the rules
     * @throws IllegalPosition When the movement is impossible due to the chessboard
     * @return
     */
    public boolean move(Coord place) throws IllegalPosition, IllegalMove {
        if (isValidMove(place)){
            this.board.setOccupied(this.place, null);
            this.place = place;
            this.board.setOccupied(this.place, this);
            return true;
        }
        throw new IllegalMove(place);
    }

    protected boolean isPathClear(Coord place){
        int dx = Integer.signum(place.getX() - this.place.getX());
        int dy = Integer.signum(place.getY() - this.place.getY());
        int x = this.place.getX();
        int y = this.place.getY();
        while (x != place.getX() || y != place.getY()){
            x += dx;
            y += dy;
            try {
                if ((x != place.getX() || y != place.getY()) && this.board.isOccupied(new Coord(x, y))) {
                    return false;
                }
            }
            catch(IllegalPosition ex){
                return false;
            }
        }
        return true;
    }

    protected abstract boolean isValidMove(Coord place) throws IllegalPosition;


    public List<Coord> getValidCases(Coord pos) throws IllegalPosition, IllegalMove {
        ArrayList<Coord> res = new ArrayList<>();
        Coord test;
        for (int i = 0; i < ChessBoard.SIZE; i++){
            for (int j = 0; j < ChessBoard.SIZE; j++){
                test = new Coord(i, j);
                if (isValidMove(new Coord(i, j))){
                    res.add(test);
                }
            }
        }
        if (res.size() == 0){
            throw new IllegalMove("This piece can not go anyway...", pos);
        }
        return res;
    }

    public void printValidMove(List<Coord> possibility){
        System.out.println("Available movement :");
        for (int i = 0; i < possibility.size(); i++){
            System.out.println("    - " + possibility.get(i));
        }
    }
}
