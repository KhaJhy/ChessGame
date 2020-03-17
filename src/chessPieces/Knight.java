package chessPieces;

import game.ChessBoard;
import game.Piece;
import gameException.IllegalMove;
import gameException.IllegalPosition;

public class Knight extends Piece {
    /**
     * @param place Knight's position on the chessboard
     * @param col Knight's color on this game
     * @param board Actual chessboard where the knight is playing
     */
    public Knight(Coord place, Color col, ChessBoard board) {
        super(place, col, board);
    }

    @Override
    public String toString() {
        if (this.col.equals(Color.WHITE)){
            return "♘";
        } else return "♞";
    }

    /**
     * @param place The position chosen by the player
     * @throws IllegalMove
     * @throws IllegalPosition
     * @return
     */
    public boolean move(Coord place) throws IllegalMove, IllegalPosition {
        if (!board.isOccupied(place) || (board.getCase(place).getPiece().getCol() != this.col)) {
            int dx = Math.abs(this.place.getX() - place.getX());
            int dy = Math.abs(this.place.getY() - place.getY());
            if (!((dx == 2 && dy == 1) || (dx == 1 && dy == 2))) {
                throw new IllegalMove(place);
            }
            this.board.setOccupied(this.place, null);
            this.place = place;
            this.board.setOccupied(this.place, this);
        }else{
            throw new IllegalPosition(place);
        }
        return false;
    }

    @Override
    protected boolean isValidMove(Coord place) throws IllegalPosition {
        int dx = Math.abs(this.place.getX() - place.getX());
        int dy = Math.abs(this.place.getY() - place.getY());
        if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)){
            return !board.isOccupied(place) || this.board.isEatableBy(this.place, place);
        }
        return false;
    }
}
