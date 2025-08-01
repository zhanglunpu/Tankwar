package event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mypanel extends JPanel implements KeyListener {
    private int x=100;
    private int y=100;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 100, 100);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            y+=3;
        }else if(e.getKeyCode() == KeyEvent.VK_UP) {
            y-=3;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            x-=3;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x+=3;
        }else{
            x = x;
            y = y;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
