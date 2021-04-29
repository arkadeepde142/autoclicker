package control;

import gui.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AutoClickerBot extends SwingWorker<Void, Void> {
    private Model model;
    private Robot robot;

    public AutoClickerBot(Model model) throws AWTException {
        robot = new Robot();
        this.model = model;
    }

    @Override
    protected Void doInBackground() throws Exception {
        model.toggleRunning();
        for(long count = 0; count < model.getMaxClicks(); ++count) {
            System.out.println(count);
            robot.delay(model.getDelayBetweenClicks());
            robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
            robot.delay(Math.min(model.getDelayBetweenClicks(), 10));
            robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
            if(isCancelled()) {
//                model.toggleRunning();
                System.out.println("Cancelled successfully");
                break;
            }
        }
        model.toggleRunning();
        return null;
    }

    @Override
    protected void done() {
        System.out.println("Normally exited");
    }
}
