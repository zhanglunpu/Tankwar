package hspTankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {
    Hero mytank = new Hero(100, 100, 1, 1);
    Vector<EnermyTank> enermytanks = new Vector();


    public MyPanel() {
        for (int i = 0; i < 3; i++) {

            enermytanks.add(new EnermyTank(100 * i, 0, 2, 3));
        }
        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 750);
        for (EnermyTank en : enermytanks) {
            drawtank(en.getX(), en.getY(), 2, 3, g);
        }
        drawtank(mytank.getX(), mytank.getY(), 1, mytank.getDirect(), g);
        if (mytank.bullet != null && mytank.bullet.isLive()) {
            switch (mytank.getType()) {
                case 1:
                    g.setColor(Color.GREEN);//我方坦克子弹
                    break;
                case 2:
                    g.setColor(Color.RED);//敌方坦克子弹
                    break;
            }
            g.fillOval(mytank.bullet.getX(), mytank.bullet.getY(), 10, 10);
        }


    }

    public void drawtank(int x, int y, int type, int direct, Graphics g) {
        switch (type) {
            case 1:
                g.setColor(Color.CYAN);//我方坦克
                break;
            case 2:
                g.setColor(Color.RED);//敌人坦克
                break;
        }
        switch (direct) {
            case 1://向上
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 40, 40, false);
                g.fill3DRect(x + 50, y, 10, 60, false);
                g.drawOval(x + 10, y + 10, 40, 40);
                g.drawLine(x + 30, y + 30, x + 30, y);
                break;
            case 2://向右
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 40, false);
                g.fill3DRect(x, y + 50, 60, 10, false);
                g.drawOval(x + 10, y + 10, 40, 40);
                g.drawLine(x + 30, y + 30, x + 60, y + 30);
                break;
            case 3://向下
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 40, 40, false);
                g.fill3DRect(x + 50, y, 10, 60, false);
                g.drawOval(x + 10, y + 10, 40, 40);
                g.drawLine(x + 30, y + 30, x + 30, y + 60);
                break;
            case 4://向左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 40, false);
                g.fill3DRect(x, y + 50, 60, 10, false);
                g.drawOval(x + 10, y + 10, 40, 40);
                g.drawLine(x + 30, y + 30, x, y + 30);
                break;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            mytank.setDirect(1);
            mytank.moveup();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            mytank.setDirect(2);
            mytank.moveright();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            mytank.setDirect(3);
            mytank.movedown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            mytank.setDirect(4);
            mytank.moveleft();
        } else {
            mytank.setDirect(mytank.getDirect());
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            mytank.shot();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }
}