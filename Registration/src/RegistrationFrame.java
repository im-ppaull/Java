import java.awt.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistrationFrame {
    public RegistrationFrame() {
        JFrame frame = new JFrame();
        frame.setSize(420, 420);
        frame.setTitle("Registration");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JTextField inputField = new JTextField(20);
        inputField.setMaximumSize(new Dimension(260, 30));
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(Box.createVerticalStrut(40));
        frame.add(inputField);
        frame.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JButton confirm = new JButton("Confirm");
        confirm.setPreferredSize(new Dimension(120, 40));
        buttonPanel.add(confirm);

        JButton cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(120, 40));
        cancel.addActionListener(_ -> System.exit(0));
        buttonPanel.add(cancel);

        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(buttonPanel);
        frame.add(Box.createVerticalGlue());

        JLabel messageLabel = new JLabel(" ");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(messageLabel);

        confirm.addActionListener(_ -> {
            String text = inputField.getText();
            if (text.isEmpty()) {
                messageLabel.setText("Enter text!");
                messageLabel.setForeground(Color.RED);
            } else {
                messageLabel.setText("Hello, " + text);
                messageLabel.setForeground(Color.BLACK);
            }
        });

        frame.setVisible(true);
    }
}