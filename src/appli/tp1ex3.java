package appli;

import chessPieces.*;
import game.ChessBoard;
import game.InitChess;
import gameException.IllegalMove;
import gameException.IllegalPosition;
import org.ietf.jgss.GSSManager;

import java.io.*;

public class tp1ex3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ChessBoard game = new ChessBoard();
        try {
            InitChess.init(game);
        } catch (IllegalPosition illegalPosition) {
            System.err.println("There is an error with this position..." + illegalPosition.getPos());
        }
        game.smartPrint();
        String from;
        String to;
        String reload;
        System.out.println("Do you want to reload the last save? O/n");
        reload = br.readLine();
        if (reload.equals("O") || reload.equals("o")){
            game = Serialize.loadChessboard();
            game.smartPrint();
        }
        do {
            try {
                if (game.getCurrentPlayer().equals(Color.BLACK)){
                    System.out.println("IT IS BLACK'S TURN !");
                } else {
                    System.out.println("IT IS WHITE'S TURN !");
                }
                System.out.println("From?");
                System.out.println("(q to quit)");
                from = br.readLine();
                if (from.equals("q")) break;
                while (!game.isMovable(new Coord(from))){
                    System.out.println("Choose a valid piece to move...");
                    from = br.readLine();
                }
                Coord currentPiece = new Coord(from);
                game.getCase(currentPiece).getPiece().printValidMove(game.getCase(currentPiece).getPiece().getValidCases(currentPiece));
                System.out.println("To?");
                to = br.readLine();
                game.getCase(new Coord(from)).getPiece().move(new Coord(to));
                game.smartPrint();
                game.changePlayerTurn();
            } catch (IllegalMove illegalMove){
                System.err.println("Invalid action... " + illegalMove.getPos());
            } catch (IllegalPosition illegalPosition){
                System.err.println("This position is not accessible...  " + illegalPosition.getPos());
            } catch (IOException | StringIndexOutOfBoundsException e) {
                System.err.println("Invalid parameters entered... ");
            } catch (NullPointerException n){
                System.err.println("This case doesn't contain a piece... ");
            }
        } while (true);
        Serialize.saveChessboard(game);
    }
}
