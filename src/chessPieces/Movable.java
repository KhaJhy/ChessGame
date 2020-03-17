package chessPieces;

import gameException.IllegalMove;
import gameException.IllegalPosition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Movable{
    public boolean move(Coord place) throws IllegalMove, IllegalPosition;
    public Color getCol();
    public List<Coord> getValidCases(Coord pos) throws IllegalPosition, IllegalMove;
    public void printValidMove(List<Coord> possibility);
}
