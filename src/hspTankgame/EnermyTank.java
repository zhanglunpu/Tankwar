package hspTankgame;

import java.util.Vector;

public class EnermyTank extends Tank {
    Vector<Bullet> bullets = new Vector<Bullet>();
    public EnermyTank(int x, int y,int type, int direct) {
        super(x, y,type, direct);
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }


}
