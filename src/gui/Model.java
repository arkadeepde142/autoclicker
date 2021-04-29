package gui;

import org.jnativehook.keyboard.NativeKeyEvent;

public class Model {
    private long maxClicks;
    private int delayBetweenClicks;
    private int startKey = NativeKeyEvent.VC_W;
    private int stopKey = NativeKeyEvent.VC_S;
    private boolean running = false;

    public Model(long maxClicks, int delayBetweenClicks) {
        this.maxClicks = maxClicks;
        this.delayBetweenClicks = delayBetweenClicks;
    }

    public int getDelayBetweenClicks() {
        return delayBetweenClicks;
    }

    public int getStartKey() {
        return startKey;
    }

    public int getStopKey() {
        return stopKey;
    }

    public long getMaxClicks() {
        return maxClicks;
    }

    public void setMaxClicks(long maxClicks) {
        if (maxClicks > 0)
            this.maxClicks = maxClicks;
        else
            this.maxClicks = 0;
    }

    public void setDelayBetweenClicks(int delayBetweenClicks) {
        if (delayBetweenClicks > 0)
            this.delayBetweenClicks = delayBetweenClicks;
        else
            this.delayBetweenClicks = 0;
    }

    public boolean getRunning() {
        return running;
    }

    public void toggleRunning() {
        System.out.println("Running = " + !running);
        running = !running;
    }
}
