package chessPieces;

import game.ChessBoard;
import game.Piece;
import gameException.IllegalMove;
import gameException.IllegalPosition;

public class King extends Piece {

    /**
     * @param place Position on the chessboard
     * @param col Color of the piece
     * @param board Board where the king is actually playing
     */
    public King(Coord place, Color col, ChessBoard board) {
        super(place, col, board);
    }

    @Override
    public String toString() {
        if (this.col.equals(Color.WHITE)){
            return "♔";
        } else return "♚";
    }

    @Override
    protected boolean isValidMove(Coord place) throws IllegalPosition{
        int dx = Math.abs(this.place.getX() - place.getX());
        int dy = Math.abs(this.place.getY() - place.getY());
        if (dx > 1 || dy > 1 || (dx == 0 && dy == 0)){
            return !board.isOccupied(place) || this.board.isEatableBy(this.place, place);
        }
        return false;
    }
}
