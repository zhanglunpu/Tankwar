package hspTankgame;

public class Hero extends Tank {
    Bullet bullet;
    public Hero(int x, int y,int type, int direct) {
        super(x, y,type, direct);
    }
    public void shot(){
        switch (getDirect()){
            case 1://向上
                bullet = new Bullet(getX() + 25, getY() - 5, getDirect());
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
        new Thread(bullet).start();
    }
}
