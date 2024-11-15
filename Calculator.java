import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private StringBuilder input;
    private double result;
    private String operator;
    private boolean startNewNumber;

    /// Constructor that initializes the GUI components and layout for the calculator
    public Calculator() {
        this.setSize(300, 400);
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        input = new StringBuilder();
        result = 0;
        operator = "=";
        startNewNumber = true;

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String b : buttons) {
            addButton(buttonPanel, b);
        }

        this.add(buttonPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    /// Adds a button to the panel and sets its action listener
    /// @param panel The panel that the button is added to
    /// @param text The label of the button
    private void addButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        panel.add(button);

        button.addActionListener(this);
    }

    /// Handles button clicks and processes input for the calculation
    /// @param e The ActionEvent triggered by button clicks
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("0123456789.".indexOf(cmd) >= 0) {
            if (startNewNumber) {
                input.setLength(0);
                startNewNumber = false;
            }
            input.append(cmd);
            display.setText(input.toString());
        } else {
            if (!startNewNumber) {
                calculate(Double.parseDouble(input.toString()));
                input.setLength(0);
                startNewNumber = true;
            }
            operator = cmd;
            if (operator.equals("=")) {
                display.setText("" + result);
                result = 0;
                operator = "=";
            }
        }
    }

    /// Does the actual calculation
    /// @param x The value to used in the calculation
    private void calculate(double x) {
        switch (operator) {
            case "+":
                result += x;
                break;
            case "-":
                result -= x;
                break;
            case "*":
                result *= x;
                break;
            case "/":
                result /= x;
                break;
            case "=":
                result = x;
                break;
        }
    }
}
