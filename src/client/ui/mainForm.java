package client.ui;

import javax.swing.*;

public class mainForm extends JFrame {
    private JPanel rootPanel;
    private JButton hewwoButton;

    public mainForm() {
        setContentPane(rootPanel);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new mainForm();
    }
}
