package game;

import chessPieces.*;
import gameException.IllegalPosition;
import javax.swing.*;

public class InitChess {
    ChessBoard game;

    public static void init(ChessBoard game) throws IllegalPosition {
        int blackRow = 7, whiteRow = 2, y = 1;

        new Rook(new Coord("18"), Color.BLACK, game);
        new Knight(new Coord("28"), Color.BLACK, game);
        new Bishop(new Coord("38"), Color.BLACK, game);
        new Queen(new Coord("48"), Color.BLACK, game);
        new King(new Coord("58"), Color.BLACK, game);
        new Bishop(new Coord("68"), Color.BLACK, game);
        new Knight(new Coord("78"), Color.BLACK, game);
        new Rook(new Coord("88"), Color.BLACK, game);
        for (int i = 0; i < ChessBoard.SIZE; i++){
            new Pawn(new Coord(Integer.toString(y) + Integer.toString(blackRow)), Color.BLACK, game);
            new Pawn(new Coord(Integer.toString(y) + Integer.toString(whiteRow)), Color.WHITE, game);
            y++;
        }

        new Rook(new Coord("11"), Color.WHITE, game);
        new Knight(new Coord("21"), Color.WHITE, game);
        new Bishop(new Coord("31"), Color.WHITE, game);
        new Queen(new Coord("41"), Color.WHITE, game);
        new King(new Coord("51"), Color.WHITE, game);
        new Bishop(new Coord("61"), Color.WHITE, game);
        new Knight(new Coord("71"), Color.WHITE, game);
        new Rook(new Coord("81"), Color.WHITE, game);
    }
}
