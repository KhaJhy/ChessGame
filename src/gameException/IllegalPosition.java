package gameException;

import chessPieces.Coord;

/**
 * Exception when we call a square out of the chessboard
 */
public class IllegalPosition extends Exception {
    private Coord pos;

    /**
     * @param pos The illegal square captured
     */
    public IllegalPosition(Coord pos) {
        this.pos = pos;
    }

    /**
     * @param message Message posted when the error has been captured
     * @param pos The illegal square captured
     */
    public IllegalPosition(String message, Coord pos) {
        super(message);
        this.pos = pos;
    }

    /**
     * @return The illegal square captured
     */
    public Coord getPos() {
        return pos;
    }
}
