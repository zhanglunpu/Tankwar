package draw;

import javax.swing.*;
import java.awt.*;

public class Drawcircle extends JFrame {
    private JPanel panel;

    public Drawcircle() {
        panel = new MyPanel();
        this.add(panel);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Drawcircle();
    }
}

class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.draw3DRect(100, 10, 300, 200, false);
    }
}
