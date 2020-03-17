package appli;

import chessPieces.*;
import game.ChessBoard;
import game.InitChess;
import gameException.IllegalPosition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;



public class GraphFX extends Application{
    Canvas unCanvas;
    private Image[][] pieces;
    ChessBoard game = new ChessBoard();

    @Override
    public void start(Stage primaryStage) throws Exception {
        InitChess.init(game);
        unCanvas = new Canvas(700, 700);
        primaryStage.setScene(new Scene(new HBox(unCanvas)));
        primaryStage.show();
        boolean color = true;
        loadAssets();

        for (int i = 0; i < ChessBoard.SIZE; i++){
            for (int j = 0; j < ChessBoard.SIZE; j++){
                if (color){
                    unCanvas.getGraphicsContext2D().setFill(Paint.valueOf("black"));
                } else {
                    unCanvas.getGraphicsContext2D().setFill(Paint.valueOf("white"));
                }
                unCanvas.getGraphicsContext2D().fillRect(87.5*j, 87.5*i, 87.5, 87.5);
                color = !color;
            }
            color = !color;
        }
        drawPieces(game);
    }

    public void loadAssets(){
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
        } catch (Exception e){
            System.err.println("Null Pointer Exception");
            e.printStackTrace();
        }
    }

    public void drawPieces(ChessBoard chessBoard) throws IllegalPosition {
        int x, y = 0;
        for (int i = 0; i < ChessBoard.SIZE; i++){
            for (int j = 0; j < ChessBoard.SIZE; j++){
                if (chessBoard.isOccupied(new Coord(i, j))){
                    if (chessBoard.getCase(new Coord(i, j)).getPiece().getCol() == Color.WHITE){
                        x = 0;
                    } else x = 1;
                    if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Pawn){
                        y = 0;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Rook){
                        y = 1;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Knight){
                        y = 2;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Bishop){
                        y = 3;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof Queen){
                        y = 4;
                    } else if (chessBoard.getCase(new Coord(i, j)).getPiece() instanceof King){
                        y = 5;
                    }
                    unCanvas.getGraphicsContext2D().drawImage(pieces[x][y], 87.5*j, 87.5*i, 87.5, 87.5);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
