package gameException;

import chessPieces.Coord;

public class IllegalMove extends Exception {
    private Coord pos;

    public IllegalMove(Coord pos) {
        this.pos = pos;
    }

    public IllegalMove(String message, Coord pos) {
        super(message);
        this.pos = pos;
    }

    public Coord getPos() {
        return pos;
    }
}
