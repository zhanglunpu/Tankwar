package hspTankgame;

import java.util.Vector;

public class Hero extends Tank {
    Vector<Bullet> HeroBullet = new Vector<>();
    Bullet bullet;
    private int health = 3;
    public Hero(int x, int y,int type, int direct) {
        super(x, y,type, direct);
    }
    public void shot(){
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
        HeroBullet.add(bullet);
        new Thread(bullet).start();
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
}
