package chessPieces;

import game.ChessBoard;
import game.Piece;
import gameException.IllegalMove;
import gameException.IllegalPosition;

public class Queen extends Piece {
    /**
     * @param place Queen's position on the chessboard
     * @param col Queen's color
     * @param board Actual chessboard where the queen is playing
     */
    public Queen(Coord place, Color col, ChessBoard board) {
        super(place, col, board);
    }

    @Override
    public String toString() {
        if (this.col.equals(Color.WHITE)){
            return "♕";
        } else return "♛";
    }

    @Override
    protected boolean isValidMove(Coord place) throws IllegalPosition {
        int dx = Math.abs(this.place.getX() - place.getX());
        int dy = Math.abs(this.place.getY() - place.getY());
        if ((dx == dy || dx == 0 || dy == 0) && isPathClear(place)){
            return !board.isOccupied(place) || (this.board.isEatableBy(this.place, place));
        }
        return false;
    }
}
