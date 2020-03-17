package chessPieces;

import game.Case;
import game.ChessBoard;
import game.Piece;
import gameException.IllegalPosition;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    /**
     * @param place Pawn's position on the chessboard
     * @param col Pawn's color
     * @param board Actual chessboard when the pawn is playing
     */
    public Pawn(Coord place, Color col, ChessBoard board) {
        super(place, col, board);
    }

    @Override
    public String toString() {
        if (this.col.equals(Color.WHITE)){
            return "♙";
        } else return "♟";
    }

    @Override
    protected boolean isValidMove(Coord place) throws IllegalPosition{
        if (!board.isOccupied(place) || this.board.isEatableBy(this.place, place)){
            int dx = this.place.getX() - place.getX();
            int dy = this.place.getY() - place.getY();
            if (this.getCol().equals(Color.BLACK)){
                if (dx == -1){
                    if ((dy == -1 || dy == 1) && this.board.isEatableBy(this.place, place)){
                        return true;
                    }
                    else return dy == 0;
                }
            }else{
                if (dx == 1){
                    if ((dy == -1 || dy == 1) && this.board.isEatableBy(this.place, place)){
                        return true;
                    }
                    else return dy == 0;
                }
            }
        }
        return false;
    }

    public List<Coord> getValidCases() throws IllegalPosition {
        ArrayList<Coord> allowedMovement = new ArrayList<>();
        Coord pos;
        if (this.getCol().equals(Color.BLACK)){
            pos = new Coord(getPlace().getX()+1, getPlace().getY());
            if (isValidMove(pos)){
                allowedMovement.add(pos);
            }
        } else {
            pos = new Coord(getPlace().getX()+1, getPlace().getY()+1);
            if (isValidMove(pos)){
                allowedMovement.add(pos);
            }
            pos = new Coord(getPlace().getX()+1, getPlace().getY()-1);
            if(isValidMove(pos)){
                allowedMovement.add(pos);
            }
        }
        return allowedMovement;
    }
}
