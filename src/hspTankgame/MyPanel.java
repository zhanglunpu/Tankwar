package hspTankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    Hero mytank = new Hero(100, 100, 1, 1);
    Vector<EnermyTank> enermytanks = new Vector();
    Vector<Bomb> bombs = new Vector();
    //创建图片
    Image small = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/小.png"));
    Image middle = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/中.png"));
    Image large = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/大.png"));

    public MyPanel() {
        for (int i = 0; i < 5; i++) {
            EnermyTank em = new EnermyTank(100 * i, 0, 2, 3);
            enermytanks.add(em);
            Bullet embullet = new Bullet(em.getX() + 25, em.getY() + 50, em.getDirect());
            em.bullets.add(embullet);
            new Thread(embullet).start();
        }
        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 750);


        //绘制我方坦克
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

        //绘制爆炸效果
        if (bombs != null) {
            for (int i = bombs.size() - 1; i >= 0; i--) {
                Bomb bomb = bombs.get(i);
                if (bomb.getHealth() > 6) {
                    g.drawImage(small, bomb.getX(), bomb.getY(), 60, 60, this);
                } else if (bomb.getHealth() > 3) {
                    g.drawImage(middle, bomb.getX(), bomb.getY(), 60, 60, this);
                } else if (bomb.getHealth() > 0) {
                    g.drawImage(large, bomb.getX(), bomb.getY(), 60, 60, this);
                } else {
                    bombs.remove(i);
                }
            }
        }
        //绘制敌人坦克和子弹
        for (EnermyTank en : enermytanks) {
            drawtank(en.getX(), en.getY(), 2, en.getDirect(), g);
            if(en.isCanchage()){
                new Thread(en).start();
            }
            for (int i = 0; i < en.bullets.size(); i++) {
                Bullet bullet = en.bullets.get(i);
                if (bullet.isLive()) {
                    g.fillOval(bullet.getX(), bullet.getY(), 10, 10);
                } else {
                    en.bullets.remove(bullet);
                }
            }
        }
    }


    //绘制坦克的方法
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

    //判断敌方坦克是否被子弹击中
    public void checkEnlive() {
        if (mytank.bullet != null && mytank.bullet.isLive()) {
            for (int i = 0; i < enermytanks.size(); i++) {
                EnermyTank en = enermytanks.get(i);
                if (mytank.bullet.getX() > en.getX() - 10 && mytank.bullet.getY() > en.getY() - 10
                        && mytank.bullet.getX() < en.getX() + 60 && mytank.bullet.getY() < en.getY() + 60) {
                    Bomb bomb = new Bomb(en.getX(), en.getY());
                    bombs.add(bomb);
                    repaint();
                    enermytanks.remove(i);
                    mytank.bullet.setLive(false);
                    break;
                }
            }
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
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(int i = bombs.size()-1; i >= 0; i--) {
                Bomb bomb = bombs.get(i);
                bomb.Healthdown();
                if(!bomb.isLive()){
                    break;
                }
            }
            checkEnlive();
            repaint();
        }
    }
}