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
            EnermyTank em = new EnermyTank(100 * i, 1, 2, 3);
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

        if (mytank.getHealth() > 0) {
            //绘制我方坦克
            drawtank(mytank.getX(), mytank.getY(), 1, mytank.getDirect(), g);

            //绘制我方坦克子弹
            for (int i = mytank.HeroBullet.size() - 1; i >= 0; i--) {
                Bullet bullet = mytank.HeroBullet.get(i);
                if (bullet != null && bullet.isLive()) {
                    g.setColor(Color.GREEN);//我方坦克子弹
                    g.fillOval(bullet.getX(), bullet.getY(), 10, 10);
                } else {
                    mytank.HeroBullet.remove(i);
                }
            }
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
            if (en.isCanchage()) {
                new Thread(en).start();
            }
            for (int i = en.bullets.size() - 1; i >= 0; i--) {
                Bullet bullet = en.bullets.get(i);
                if (bullet.isLive()) {
                    g.fillOval(bullet.getX(), bullet.getY(), 10, 10);
                } else {
                    en.bullets.remove(i);
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

    //判断我方坦克是否存活
    public void checkmylive() {
        for (EnermyTank en : enermytanks) {
            for (Bullet bullet : en.bullets) {
                if (bullet.getX() > mytank.getX() - 10 && bullet.getY() > mytank.getY() - 10
                        && bullet.getX() < mytank.getX() + 60 && bullet.getY() < mytank.getY() + 60) {
                    Bomb bomb = new Bomb(mytank.getX(), mytank.getY());
                    bombs.add(bomb);
                    bullet.setLive(false);
                    mytank.setHealth(mytank.getHealth() - 1);
                    break;
                }
            }
        }
    }

    //判断敌方坦克是否被子弹击中
    public void checkEnlive() {
        for (int i = mytank.HeroBullet.size() - 1; i >= 0; i--) {
            Bullet bullet = mytank.HeroBullet.get(i);
            if (bullet != null && bullet.isLive()) {
                for (int j = enermytanks.size() - 1; j >= 0; j--) {
                    EnermyTank en = enermytanks.get(j);
                    if (bullet.getX() > en.getX() - 10 && bullet.getY() > en.getY() - 10
                            && bullet.getX() < en.getX() + 60 && bullet.getY() < en.getY() + 60) {
                        Bomb bomb = new Bomb(en.getX(), en.getY());
                        bombs.add(bomb);
                        enermytanks.remove(j);
                        bullet.setLive(false);
                        mytank.HeroBullet.remove(i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (mytank.getHealth() > 0) {
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            mytank.setHealth(3);
        }
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
            for (int i = bombs.size() - 1; i >= 0; i--) {
                Bomb bomb = bombs.get(i);
                bomb.Healthdown();
                if (!bomb.isLive()) {
                    break;
                }
            }
            checkmylive();
            checkEnlive();
            repaint();
        }
    }
}