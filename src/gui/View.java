package gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

class View {
    private JFrame frame;
    private JLabel startKeyLabel;
    private JLabel stopKeyLabel;
    private JLabel maxCountLabel;
    private JLabel delayBetweenClickLabel;
    private JFormattedTextField maxCountTextField;
    private JFormattedTextField delayBetweenClickTextField;
    private NumberFormat maxCountFormat;
    private NumberFormat delayFormat;
    private JButton saveButton;

    public View() {
        frame = new JFrame("Auto Clicker");
//        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);

        maxCountFormat = NumberFormat.getIntegerInstance();
        delayFormat = NumberFormat.getIntegerInstance();

        startKeyLabel = new JLabel("Start Key: W");
        stopKeyLabel = new JLabel("Start Key: S");
        maxCountLabel = new JLabel("Max Count: ");
        delayBetweenClickLabel = new JLabel("Delay: ");



        maxCountTextField = new JFormattedTextField(maxCountFormat);
//        maxCountTextField.setColumns(10);
        delayBetweenClickTextField = new JFormattedTextField(delayFormat);
//        delayBetweenClickTextField.setColumns(10);


        saveButton = new JButton("Save");


        maxCountLabel.setLabelFor(maxCountTextField);
        delayBetweenClickLabel.setLabelFor(delayBetweenClickTextField);

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup().addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(maxCountLabel)
                                .addComponent(delayBetweenClickLabel)
                )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(maxCountTextField)
                                .addComponent(delayBetweenClickTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(saveButton)
                                )

        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(maxCountLabel)
                        .addComponent(maxCountTextField).addComponent(saveButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(delayBetweenClickLabel)
                        .addComponent(delayBetweenClickTextField)

        ));

        layout.linkSize(SwingConstants.HORIZONTAL, saveButton);
        frame.getContentPane().setLayout(layout);
    }

    public JFrame getFrame() {
        return frame;
    }

    public NumberFormat getMaxCountFormat() {
        return maxCountFormat;
    }

    public NumberFormat getDelayFormat() {
        return delayFormat;
    }

    public JLabel getStartKeyLabel() {
        return startKeyLabel;
    }

    public JLabel getStopKeyLabel() {
        return stopKeyLabel;
    }

    public JFormattedTextField getMaxCountTextField() {
        return maxCountTextField;
    }

    public JFormattedTextField getDelayBetweenClickTextField() {
        return delayBetweenClickTextField;
    }

    public void setStartKey(int startKeyVK) {
        String key = KeyEvent.getKeyText(startKeyVK);
        startKeyLabel.setText("Start Key: " + key);
    }

    public void setStopKey(int stopKeyVK) {
        String key = KeyEvent.getKeyText(stopKeyVK);
        stopKeyLabel.setText("Start Key: " + key);
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
