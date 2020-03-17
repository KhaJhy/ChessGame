package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class GraphFX extends JFrame implements MouseListener {

    public GraphFX() {
        this.setPreferredSize(new Dimension(240, 265));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint (Graphics g){
        boolean color = true;
        for(int row =0; row<8;row++){
            for(int col = 0;col<8;col++){
                if(color){
                    g.setColor(Color.BLACK);
                }
                else g.setColor(Color.WHITE);
                g.fillRect(30*row, 30*col+25, 30, 30);
                color = !color;
            }
            color = !color;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
