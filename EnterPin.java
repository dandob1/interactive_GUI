import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnterPin extends JFrame {

    private JTextField code;
    private StringBuilder pin;

    /// Initializes the GUI components and layout for the PIN entry application.
    public EnterPin() {

        this.setSize(400, 400);
        this.setTitle("PIN Entry");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pin = new StringBuilder();

        code = new JTextField();
        code.setEditable(false);
        code.setHorizontalAlignment(JTextField.CENTER);
        code.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(code, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 3, 5, 5));

        for (int i = 1; i < 10; i++) {
            addButton(buttonPanel, String.valueOf(i));
        }
        addButton(buttonPanel, "<");
        addButton(buttonPanel, "0");

        this.add(buttonPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    /// Method to add a button with an action listener to the panel
    /// @param panel The panel that the button is added
    /// @param text The label of the button
    private void addButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (text.equals("<")) {
                    if (pin.length() > 0) {
                        pin.deleteCharAt(pin.length() - 1);
                    }
                } else {
                    if (pin.length() < 6) {
                        pin.append(text);
                    }
                }

                code.setText(pin.toString());

                if (pin.toString().equals("202113")) {
                    code.setText("YOU MAY ENTER!");
                    Calculator calc = new Calculator();
                    EnterPin.this.dispose();
                }
            }
        });
    }

    /// Main to run the app
    public static void main(String[] args) {
        EnterPin u = new EnterPin();
        u.setVisible(true);
    }

}