package Tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener {
    Graphics g;
    Tank tank = new Tank(100, 100, 1);
    Vector<Bullet> enermybullets = new Vector();
    Vector<EnermyTank> enermytanks = new Vector();
    boolean loop = false;
    boolean flag = true;
public MyPanel() {
    for (int i = 0; i < 3; i++) {
        enermytanks.add(new EnermyTank(100 * i, 0, 3));
    }

}
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 750);
            for (EnermyTank en : enermytanks) {
                drawtank(en.getX(), en.getY(), 2, 3, g);

        }

        drawtank(tank.getX(), tank.getY(), 1, tank.getDirect(), g);

        if (loop == true) {
            Bullet bullet = new Bullet(tank,this);
            Thread thread = new Thread(bullet);
            drawbullets(bullet.getX(), bullet.getY(), 1, tank.getDirect(), g);
            thread.start();
            loop = false;
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

    public void drawbullets(int x, int y, int type, int direct, Graphics g) {
        switch (type) {
            case 1:
                g.setColor(Color.GREEN);//我方坦克子弹
                break;
            case 2:
                g.setColor(Color.RED);//敌方坦克子弹
                break;
        }
        switch (direct) {
            case 1://向上
                g.fillOval(x + 25, y - 5, 10, 10);
                break;
            case 2://向右
                g.fillOval(x + 55, y + 25, 10, 10);
                break;
            case 3://向下
                g.fillOval(x + 25, y + 55, 10, 10);
                break;
            case 4://向左
                g.fillOval(x - 5, y + 25, 10, 10);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            tank.setDirect(1);
            tank.moveup();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            tank.setDirect(2);
            tank.moveright();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            tank.setDirect(3);
            tank.movedown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            tank.setDirect(4);
            tank.moveleft();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            loop = true;
        } else {
            tank.setDirect(tank.getDirect());
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}