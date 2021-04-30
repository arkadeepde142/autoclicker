package gui;

import control.AutoClickerBot;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.dispatcher.SwingDispatchService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;

class Controller {
    private final Model model;
    private final View view;
    private AutoClickerBot bot = null;

    public Controller(Model model, View view) {
        GlobalScreen.setEventDispatcher(new SwingDispatchService());
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.SEVERE);
        logger.setUseParentHandlers(false);

        this.model = model;
        this.view = view;
        initView();
        view.getSaveButton().addActionListener(this::saveButton);
//        view.getMaxCountTextField().addPropertyChangeListener("value", evt -> {
//            var maxCountField = view.getMaxCountTextField();
//            if(evt.getSource().equals(maxCountField))
//            {
//                if(((Number)maxCountField.getValue()).longValue() > Long.MAX_VALUE) {
//
//                }
//            }
//        });

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

            }

            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                int startKey = model.getStartKey();
                int stopKey = model.getStopKey();
                if (nativeKeyEvent.getKeyCode() == startKey && !model.isRunning()) {
                    System.out.println("W");
                    try {
                        bot = new AutoClickerBot(model);
                        System.out.printf("Started with max_clicks = %d delay = %d ms\n", model.getMaxClicks(), model.getDelayBetweenClicks());
                        bot.execute();

                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                } else if (nativeKeyEvent.getKeyCode() == stopKey && model.isRunning()) {
                    System.out.println("S");
                    if (bot != null) {
                        bot.cancel(true);
                        bot = null;
                    }
                }

                else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_K) {
                    System.exit(0);
                }
            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

            }
        });


        view.getFrame().addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    GlobalScreen.registerNativeHook();
                } catch (NativeHookException ex) {
                    System.out.println("could not register hook");
                }

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException ex) {
                    System.out.println("could not unregister hook");
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


    }

    private void initView() {
        view.getDelayBetweenClickTextField().setValue((long)(model.getDelayBetweenClicks()));
        view.getMaxCountTextField().setValue(model.getMaxClicks());
        view.setStartKey(model.getStartKey());
        view.setStopKey(model.getStopKey());
        view.getFrame().setVisible(true);
    }

    private void saveButton(ActionEvent e) {
        int delay = ((Long)(view.getDelayBetweenClickTextField().getValue())).intValue();
        model.setDelayBetweenClicks(delay);
        model.setMaxClicks((Long) view.getMaxCountTextField().getValue());
    }
}
