package chessPieces;

import game.ChessBoard;
import game.Piece;
import gameException.IllegalMove;
import gameException.IllegalPosition;

public class Bishop extends Piece {
    public Bishop(Coord place, Color col, ChessBoard board) {
        super(place, col, board);
    }

    @Override
    public String toString() {
        if (this.col.equals(Color.WHITE)){
            return "♗";
        } else return "♝";
    }

    @Override
    protected boolean isValidMove(Coord place) throws IllegalPosition{
        int dx = Math.abs(this.place.getX() - place.getX());
        int dy = Math.abs(this.place.getY() - place.getY());
        if (dx == dy && isPathClear(place)) {
            return !board.isOccupied(place) || (this.board.isEatableBy(this.place, place));
        }
        return false;
    }
}
