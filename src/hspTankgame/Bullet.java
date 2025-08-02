package hspTankgame;


public class Bullet implements Runnable {
    private int x;
    private int y;
    private int direct;
    private int speed = 5;
    private boolean isLive = true;

    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 1:
                    y -= speed;
                    break;
                case 2:
                    x += speed;
                    break;
                case 3:
                    y += speed;
                    break;
                case 4:
                    x -= speed;
                    break;
            }
            System.out.println("x: " + x + " y: " + y);
            if (!(x > 0 && x < 1000 && y > 0 && y < 750)) {
                isLive = false;
                break;
            }
        }
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
