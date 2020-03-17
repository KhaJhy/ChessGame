package appli;

import game.ChessBoard;

import java.io.*;

public class Serialize {

    public static void saveChessboard(ChessBoard game) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("save"));
            oos.writeObject(game);
        } finally {
            if (oos != null) oos.close();
        }
    }

    public static ChessBoard loadChessboard() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        ChessBoard game;
        try{
            ois = new ObjectInputStream(new FileInputStream("save"));
            game = (ChessBoard)ois.readObject();
        } finally {
            if (ois != null) ois.close();
        }
        return game;
    }
}
