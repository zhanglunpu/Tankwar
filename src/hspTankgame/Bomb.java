package hspTankgame;

public class Bomb {
    private int x, y;
    private boolean isLive = true;
    private int health = 9;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void Healthdown() {
        if (health > 0) {
            health--;
        } else {
            isLive = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
