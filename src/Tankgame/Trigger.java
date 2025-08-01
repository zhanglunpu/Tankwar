package Tankgame;

public class Trigger implements Runnable{
    private boolean myTrigger = false;
    private boolean enTrigger = true;
    @Override
    public void run() {
        setEnTrigger(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setEnTrigger(false);
    }

    public boolean isMyTrigger() {
        return myTrigger;
    }

    public void setMyTrigger(boolean myTrigger) {
        this.myTrigger = myTrigger;
    }

    public boolean isEnTrigger() {
        return enTrigger;
    }

    public void setEnTrigger(boolean enTrigger) {
        this.enTrigger = enTrigger;
    }
}
