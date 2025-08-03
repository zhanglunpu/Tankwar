package hspTankgame;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

public class EnermyTank extends Tank implements Runnable {
    private boolean canchage = true;
    Vector<Bullet> bullets = new Vector<Bullet>();

    public EnermyTank(int x, int y, int type, int direct) {
        super(x, y, type, direct);
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }


    @Override
    public void run() {



        while (true) {
            //敌人发射多枚子弹
            if(bullets.size() == 0) {
                Bullet bullet = null;
                switch (getDirect()){
                    case 1://向上
                        bullet=new Bullet(getX() + 25, getY() - 5, getDirect());
                        break;
                    case 2://向右
                        bullet = new Bullet(getX() + 55, getY() + 25, getDirect());
                        break;
                    case 3://向下
                        bullet = new Bullet(getX() + 25, getY() + 55, getDirect());
                        break;
                    case 4://向左
                        bullet = new Bullet(getX() - 5, getY() + 25, getDirect());
                        break;
                }
                bullets.add(bullet);
                new Thread(bullet).start();
            }


            //控制敌人坦克移动方向和范围
            switch (getDirect()) {
                case 1:
                    setCanchage(false);
                    for (int i = 0; i < 30; i++) {
                        if (!(getX() > 0 && getY() > 0 && getX() - 60 < 1000 && getY() + 60 < 750)) {
                            setDirect(3);
                            setY(getY()+ 1);
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        moveup();
                    }
                    setCanchage(true);
                    break;
                case 2:
                    setCanchage(false);
                    for (int i = 0; i < 30; i++) {
                        if (!(getX() > 0 && getY() > 0 && getX() - 60 < 1000 && getY() + 60 < 750)) {
                            setDirect(4);
                            setX(getX()-1);
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        moveright();
                    }
                    setCanchage(true);
                    break;
                case 3:
                    setCanchage(false);
                    for (int i = 0; i < 30; i++) {
                        if (!(getX() >0 && getY() > 0 && getX() - 60 < 1000 && getY() + 60 < 750)) {
                            setDirect(1);
                            setY(getY()-1);
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        movedown();
                    }
                    setCanchage(true);
                    break;
                case 4:
                    setCanchage(false);
                    for (int i = 0; i < 30; i++) {
                        if (!(getX() > 0 && getY() > 0 && getX() - 60 < 1000 && getY() + 60 < 750)) {
                            setDirect(2);
                            setX(getX()+1);
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        moveleft();
                    }
                    setCanchage(true);
                    break;
            }
            setDirect((int) (Math.random() * 4) + 1);
        }


    }

    public boolean isCanchage() {
        return canchage;
    }

    public void setCanchage(boolean canchage) {
        this.canchage = canchage;
    }
}
