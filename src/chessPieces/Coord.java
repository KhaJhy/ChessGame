package chessPieces;
import game.ChessBoard;

import java.io.Serializable;

/**
 * Coordinate used to locate pieces on the board
 */
public class Coord implements Serializable {
    private int x, y;

    /**
     * @param x Row
     * @param y Column
     */
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param coord World standard for the localisation
     */
    public Coord(String coord){
        this.y = Integer.parseInt(coord.substring(0, 1)) - 1;
        this.x = ChessBoard.SIZE - Integer.parseInt(coord.substring(1, 2));
    }

    /**
     * @return Row's getter
     */
    public int getX() {
        return x;
    }

    /**
     * @return Column's getter
     */
    public int getY() {
        return y;
    }

    public String toString(){
        return "[" + (y+1) + "." + (ChessBoard.SIZE - x) + "]";
    }
}
