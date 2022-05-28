package am.aua.chess.ui;

import am.aua.chess.core.Chess;
import am.aua.chess.core.IllegalArrangementException;
import am.aua.chess.core.Move;
import am.aua.chess.core.Position;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessUI extends JFrame {
    private Chess game;
    private BoardSquare[][] squares = new BoardSquare[8][8];
    private Position origin;
    private JPanel panel;

    public ChessUI(){
        
        try {
            game = new Chess();
        } catch (IllegalArrangementException e) {
            e.printStackTrace();
        }
        boolean isWhite;
        for (int i = 0; i < Chess.BOARD_RANKS; i++) {
            for (int j = 0; j < Chess.BOARD_FILES; j++) {
                if ((i + j) % 2 == 0) {
                    isWhite = true;
                } else {
                    isWhite = false;
                }
                this.squares[i][j] = new BoardSquare(isWhite, j, i);
                Position p = Position.generateFromRankAndFile(i, j);
                if (game.getPieceAt(p) != null) {
                    this.squares[i][j].setPiece(game.getPieceAt(Position.generateFromRankAndFile(i, j)).toString());
                }
                this.squares[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BoardSquare source = (BoardSquare) e.getSource();
                        boardClicked(source.getPosition());
                    }
                });
            }
        }
        this.setTitle("chess");
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridLayout(8, 8));
        this.setLocationRelativeTo(null);
        this.add(panel, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(400, 400));
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                panel.add(squares[i][j]);
            }
        }
    }

    private void boardClicked(int[] coordinates) {
        if (this.origin == null) {
            this.origin = Position.generateFromRankAndFile(coordinates[1], coordinates[0]);
            if (game.getTurn() == game.getPieceAt(origin).getPieceColor() && !game.isEmpty(origin)){
                squares[coordinates[1]][coordinates[0]].setHighlight(true);
                Position[] arr = game.reachableFrom(origin);
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i]);
                    squares[arr[i].getRank()][arr[i].getFile()].setHighlight(true);
                }
            }
        } else {
            Position destination = Position.generateFromRankAndFile(coordinates[1], coordinates[0]);
            Position[] arr = game.reachableFrom(origin);
            for (int i = 0; i < arr.length && arr != null; i++) {
                if (destination.equals(arr[i])) {
                    Move move = new Move(origin, destination);
                    game.performMove(move);
                    break;
                }
            }
            for (int i = 0; i < arr.length &&  arr != null; i++) {
                squares[arr[i].getRank()][arr[i].getFile()].setHighlight(false);
            }

            updatePieces();
            squares[origin.getRank()][origin.getFile()].setHighlight(false);
            origin = null;
        }
    }

    public void updatePieces(){
        for (int m = 0; m < Chess.BOARD_RANKS; m++){
            for (int n = 0; n < Chess.BOARD_FILES; n++){
                Position pos = Position.generateFromRankAndFile(m, n);
                if (!game.isEmpty(pos)){
                    squares[m][n].setPiece(game.getPieceAt(pos).toString());
                } else{
                    squares[m][n].setPiece("-");
                }
            }
        }
    }
}