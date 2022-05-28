package am.aua.chess.ui;
import javax.swing.*;
import java.awt.*;

public class BoardSquare extends JButton {
    private boolean isWhite;
    private int x;
    private int y;
    private Icon icon ;
    private final int size = 50;

    BoardSquare( boolean color, int x, int y){
        this.x =x;
        this.y = y;
        this.isWhite = color;
        if(color){
            this.setBackground(Color.WHITE);
        }
        else {
            this.setBackground(Color.MAGENTA);
        }
        this.setSize(size,size);
    }
    public int[] getPosition(){
        int[] pos = {this.x,this.y};
        return pos;
    }
    public void setPiece(String piece){
        switch (piece) {
            case "N":
                icon = new ImageIcon("gfx/KnightW.png");
                break;
            case "n":
                icon = new ImageIcon("gfx/KnightB.png");
                break;
                case "R":
            case "S":
                icon = new ImageIcon("gfx/RookW.png");
                break;
            case "r":
            case "s":
                icon = new ImageIcon("gfx/RookB.png");
                break;
            case "B":
                icon = new ImageIcon("gfx/BishopW.png");
                break;
            case "b":
                icon = new ImageIcon("gfx/BishopB.png");
                break;
            case "Q":
                icon = new ImageIcon("gfx/QueenW.png");
                break;
            case "q":
                icon = new ImageIcon("gfx/QueenB.png");
                break;
            case "P":
                icon = new ImageIcon("gfx/PawnW.png");
                break;
            case "p":
                icon = new ImageIcon("gfx/PawnB.png");
                break;
                case "K":
            case "L":
                icon = new ImageIcon("gfx/KingW.png");
                break;
            case "k":
            case "l":
                icon = new ImageIcon("gfx/KingB.png");
                break;
            default:
                icon = null;
        }
        this.setIcon(icon);
    }
    public void setPiece(){
        icon = null;
    }
    public void setHighlight(boolean isClicked){
        if(isClicked){
            this.setBackground(Color.YELLOW);
        }else{
            if(isWhite){
                this.setBackground(Color.WHITE);
            }else{
                this.setBackground(Color.MAGENTA);
            }
        }
    }
}