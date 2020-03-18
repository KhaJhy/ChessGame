package chessPieces;

import game.Case;
import game.ChessBoard;
import game.Piece;
import gameException.IllegalMove;
import gameException.IllegalPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
        if (dx>=-1 && dx <= 1 && dy >= -1 && dy <= 1){
            return !board.isOccupied(place) || this.board.isEatableBy(this.place, place);
        }
        return false;
    }

    @Override
    public List<Coord> getValidCases(Coord pos){
        Coord[] trialCoordinates = {
                new Coord(pos.getX()+1, pos.getY()),
                new Coord(pos.getX()+1, pos.getY()+1),
                new Coord(pos.getX()+1, pos.getY()-1),
                new Coord(pos.getX(), pos.getY()-1),
                new Coord(pos.getX(), pos.getY()+1),
                new Coord(pos.getX()-1, pos.getY()),
                new Coord(pos.getX()-1, pos.getY()+1),
                new Coord(pos.getX()-1, pos.getY()-1)
        };
        List<Coord> coordinates = new ArrayList<Coord>();

        for (Coord trialCoordinate : trialCoordinates) {
            try {
                if (!board.isOccupied(trialCoordinate) || board.isEatableBy(pos, trialCoordinate))
                    coordinates.add(trialCoordinate);
            } catch (IllegalPosition ignored) {
            }
        }

        return coordinates;
    }
}
