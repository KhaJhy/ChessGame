package game;

import chessPieces.Movable;

import java.io.Serializable;

public class Case implements Serializable {
    Movable piece;

    public Case() {
        super();
    }

    public Movable getPiece() {
        return piece;
    }

    public void setPiece(Movable piece) {
        this.piece = piece;
    }

    public boolean isOccupied(){
        return piece != null;
    }
}
