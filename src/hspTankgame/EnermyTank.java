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
            switch (getDirect()) {
                case 1:
                    setCanchage(false);
                    for (int i = 0; i < 30; i++) {
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
