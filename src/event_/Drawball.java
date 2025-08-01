package event_;



import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Drawball extends JFrame {
    public static void main(String[] args) {
        Drawball d = new Drawball();
    }
    private Mypanel panel;
    public Drawball() {
        panel = new Mypanel();
        add(panel);
        setTitle("Drawball");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(panel);
        setSize(500, 500);
        setVisible(true);
    }


}
