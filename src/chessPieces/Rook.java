package chessPieces;

import game.ChessBoard;
import game.Piece;
import gameException.IllegalMove;
import gameException.IllegalPosition;

public class Rook extends Piece {
    /**
     * Rook's constructor
     * @param place Rook's position on the chessboard
     * @param col Rook's color
     * @param board Actual chessboard where the rook is playing
     */
    public Rook(Coord place, Color col, ChessBoard board) {
        super(place, col, board);
    }

    @Override
    public String toString() {
        if (this.col.equals(Color.WHITE)){
            return "♖";
        } else return "♜";
    }

    @Override
    protected boolean isValidMove(Coord place) throws IllegalPosition {
        int dx = Math.abs(this.place.getX() - place.getX());
        int dy = Math.abs(this.place.getY() - place.getY());
        if (!((dx == 0 && dy == 0) || (dx != 0 && dy != 0)) && isPathClear(place)){
            return !this.board.isOccupied(place) || this.board.isEatableBy(this.place, place);
        }
        return false;
    }
}
