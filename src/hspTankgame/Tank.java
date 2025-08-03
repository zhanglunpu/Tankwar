package hspTankgame;

public class Tank {
    private int x = 100;
    private int y = 100;
    private int type;
    private int direct = 1;
    private int speed = 3;
    private boolean dead = false;

    public Tank(int x, int y, int type, int direct) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void moveup() {
        if (!(y - speed >= 0)) {
            return;
        }
        y -= speed;
    }

    public void movedown() {
        if (!(y + 60  <= 750)) {
            return;
        }

        y += speed;
    }

    public void moveleft() {
        if (!(x - speed >= 0)) {
            return;
        }
        x -= speed;
    }

    public void moveright() {
        if (!(x + 60 +speed <= 1000)) {
            return;
        }
        x += speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
