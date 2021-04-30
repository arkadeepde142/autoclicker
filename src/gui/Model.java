package gui;

import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.concurrent.atomic.AtomicBoolean;

public class Model {
    private long maxClicks;
    private int delayBetweenClicks;
    private int startKey = NativeKeyEvent.VC_W;
    private int stopKey = NativeKeyEvent.VC_S;
    private AtomicBoolean running = new AtomicBoolean(false);

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
        this.delayBetweenClicks = Math.max(delayBetweenClicks, 0);
    }

    public boolean isRunning() {
        return running.get();
    }

    public void unsetRunning() {
        running.compareAndSet(true, false);
    }

    public void setRunning() {
        running.compareAndSet(false, true);
    }
}
