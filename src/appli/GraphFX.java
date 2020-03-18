package appli;

import chessPieces.*;
import game.ChessBoard;
import game.InitChess;
import gameException.IllegalMove;
import gameException.IllegalPosition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;



public class GraphFX extends Application implements EventHandler<MouseEvent> {
    Canvas unCanvas;
    private Image[][] pieces;
    ChessBoard game = new ChessBoard();
    final double sizeCase = 87.5;
    boolean from = true;
    protected int fromx, fromy, tox, toy;
    protected double oldx = 0, oldy = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        InitChess.init(game);
        unCanvas = new Canvas(700, 700);
        primaryStage.setScene(new Scene(new HBox(unCanvas)));
        drawChessboard();
        loadAssets();
        drawPieces(game);
        primaryStage.show();
        unCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        System.out.println("IT IS WHITE'S TURN !");
    }

    public void loadAssets() {
        try {
            Image imagePieces = new Image("ChessPieces.png", false);
            int h = (int) imagePieces.getHeight();
            int w = (int) imagePieces.getWidth();
            int pieceHeight = h / 2;
            int pieceWidth = w / 6;

            this.pieces = new Image[2][6];
            PixelReader reader = imagePieces.getPixelReader();
            for (int i = 0; i < 6; i++) {
                this.pieces[0][i] = new WritableImage(reader, i * pieceWidth, 0, pieceWidth, pieceHeight);
                this.pieces[0][i] = new WritableImage(reader, (5 - i) * pieceWidth, pieceHeight, pieceWidth, pieceHeight);
                this.pieces[1][i] = new WritableImage(reader, i * pieceWidth, 1, pieceWidth, pieceHeight);
            }
        } catch (Exception e) {
            System.err.println("Null Pointer Exception");
            e.printStackTrace();
        }
    }

    public void drawChessboard(){
        boolean color = true;
        for (int i = 0; i < ChessBoard.SIZE; i++) {
            for (int j = 0; j < ChessBoard.SIZE; j++) {
                if (color) {
                    unCanvas.getGraphicsContext2D().setFill(Paint.valueOf("black"));
                } else {
                    unCanvas.getGraphicsContext2D().setFill(Paint.valueOf("white"));
                }
                unCanvas.getGraphicsContext2D().fillRect(sizeCase * j, sizeCase * i, sizeCase, sizeCase);
                color = !color;
            }
            color = !color;
        }
    }

    public void drawPieces(ChessBoard chessBoard) throws IllegalPosition {
        int x, y = 0;
        for (int i = 0; i < ChessBoard.SIZE; i++) {
            for (int j = 0; j < ChessBoard.SIZE; j++) {
                if (chessBoard.isOccupied(new Coord(i, j))) {
                    if (chessBoard.getCase(new Coord(i, j)).getPiece().getCol() == Color.WHITE) {
                        x = 1;
                    } else x = 0;
                    if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Pawn) {
                        y = 0;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Rook) {
                        y = 1;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Knight) {
                        y = 2;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Bishop) {
                        y = 3;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Queen) {
                        y = 4;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof King) {
                        y = 5;
                    }
                    unCanvas.getGraphicsContext2D().drawImage(pieces[x][y], sizeCase * j, sizeCase * i, sizeCase, sizeCase);
                }
            }
        }
    }

    @Override
    public void handle(MouseEvent event) {
        if (from) {
            fromx = (int) (event.getX() / sizeCase);
            fromy = (int) (event.getY() / sizeCase);
            from = false;
        } else {
            tox = (int) (event.getX() / sizeCase);
            toy = (int) (event.getY() / sizeCase);
            try {
                if (game.getCase(new Coord(fromy, fromx)).getPiece().getCol().equals(game.currentPlayer)) {
                    game.getCase(new Coord(fromy, fromx)).getPiece().move(new Coord(toy, tox));
                    game.changePlayerTurn();
                    if (game.getCurrentPlayer().equals(Color.BLACK)){
                        System.out.println("IT IS BLACK'S TURN !");
                    } else {
                        System.out.println("IT IS WHITE'S TURN !");
                    }
                }
                drawChessboard();
                drawPieces(game);
            } catch (IllegalMove ex) {
                System.err.println("Ce mouvement est impossible... ");
            } catch (IllegalPosition pos){
                System.err.println("Cette case n'est pas disponible... ");
            } catch (NullPointerException nullpointer){
                System.err.println("Cette case ne contient pas de pièce... ");
            }
            from = true;
        }
        oldx = event.getX();
        oldy = event.getY();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
