package Tankgame;




public class Bullet implements Runnable {
    Tank tank;
    MyPanel panel;
    private int x;
    private int y;
    private int direct;
    private int speed = 5;
    private boolean dead = false;

    public Bullet(Tank tank,MyPanel panel) {
        this.tank = tank;
        this.x = tank.getX();
        this.y = tank.getY();
        this.direct = tank.getDirect();
        this.panel = panel;
    }

    @Override
    public void run() {

        if (direct == 1) {

            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (x < 0 || x > 1000 || y < 0 || y > 750) {
                    setDead(true);
                    break;
                }
                moveup();
                System.out.println("x=" + x + " y=" + y);
            }
        } else if (direct == 2) {

            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (x < 0 || x > 1000 || y < 0 || y > 750) {
                    setDead(true);
                    break;
                }
                moveright();
                System.out.println("x=" + x + " y=" + y);
            }
        } else if (direct == 3) {

            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (x < 0 || x > 1000 || y < 0 || y > 750) {
                    setDead(true);
                    break;
                }
                movedown();
                System.out.println("x=" + x + " y=" + y);
            }
        } else {

            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (x < 0 || x > 1000 || y < 0 || y > 750) {
                    setDead(true);
                    break;
                }
                moveleft();
                System.out.println("x=" + x + " y=" + y);
            }
        }
    }


    public void moveup() {
        y -= speed;
        panel.drawbullets(x,y,1,direct,panel.getGraphics());
        panel.repaint();
    }

    public void movedown() {
        y += speed;
        panel.drawbullets(x,y,1,direct,panel.getGraphics());
        panel.repaint();
    }

    public void moveleft() {
        x -= speed;
        panel.drawbullets(x,y,1,direct,panel.getGraphics());
        panel.repaint();
    }

    public void moveright() {
        x += speed;
        panel.drawbullets(x,y,1,direct,panel.getGraphics());
        panel.repaint();
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

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
