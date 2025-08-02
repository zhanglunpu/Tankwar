package hspTankgame;

import javax.swing.*;


public class Drawbg extends JFrame {
   private MyPanel panel;
   public Drawbg(){
       panel = new MyPanel();
       add(panel);
       setTitle("Tank War");
       addKeyListener(panel);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(1000,750);
       setVisible(true);
   }
}
