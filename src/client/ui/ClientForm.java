package client.ui;

import javax.swing.*;
import java.awt.*;

public class ClientForm extends JFrame {
    private JPanel rootPanel;
    private JButton hewwoButton;

    public ClientForm() {
        rootPanel.setMinimumSize(new Dimension(400, 400));
        setContentPane(rootPanel);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ClientForm form = new ClientForm();
        form.setMinimumSize(new Dimension(400, 400));
    }
}
