package control;

import gui.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class AutoClickerBot extends SwingWorker<Void, Void> {
    private Model model;
    private Robot robot;

    public AutoClickerBot(Model model) throws AWTException {
        robot = new Robot();
        this.model = model;
    }

    @Override
    protected Void doInBackground() {
        Random random = new Random();
        Point p = MouseInfo.getPointerInfo().getLocation();
        model.setRunning();
        System.out.println("Set called Running = " + model.isRunning());
        for (long count = 0; count < model.getMaxClicks(); ++count) {
            System.out.println(count);
            try {
                if (MouseInfo.getPointerInfo().getLocation() != p)
                    robot.mouseMove(p.x, p.y);
                robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
                robot.delay(model.getDelayBetweenClicks());
                robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);

                Thread.sleep(10 + random.nextInt(11));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            if (isCancelled()) {
                System.out.println("Cancelled successfully");
                break;
            }
        }
        return null;
    }

    @Override
    protected void done() {
        model.unsetRunning();
        System.out.println("Unset called Running = " + model.isRunning());
        System.out.println("Done");
    }
}
