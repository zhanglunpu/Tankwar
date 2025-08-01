package Tankgame;

import java.util.Vector;

public class EnermyTank extends Tank{
    private Vector<Bullet> bullets;
    public EnermyTank(int x, int y,int type, int direct) {
        super(x, y,type, direct);
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }
}
