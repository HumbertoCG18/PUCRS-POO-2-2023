import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginForm extends JFrame implements ActionListener {
    JButton submitButton, cancelButton;
    JPanel panel;
    JLabel userLabel, passLabel;
    JTextField userTextField;
    JPasswordField passPasswordField;

    LoginForm() {
        userLabel = new JLabel("Email:");
        passLabel = new JLabel("Senha:");

        userTextField = new JTextField();
        passPasswordField = new JPasswordField();

        submitButton = new JButton("Entrar");
        cancelButton = new JButton("Cancelar");

        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        panel = new JPanel(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passLabel);
        panel.add(passPasswordField);
        panel.add(submitButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String username = userTextField.getText();
            String password = new String(passPasswordField.getPassword());

            if (username.equals("normaluser") && password.equals("userpass")) {
                openNewPage("Normal User", username);
            } else if (username.equals("developer") && password.equals("devpass")) {
                openNewPage("Developer", username);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }

    private void openNewPage(String userType, String username) {
        NewPage newPage = new NewPage(userType, username);
        newPage.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}

class NewPage extends JFrame {
    JLabel welcomeLabel;

    NewPage(String userType, String username) {
        welcomeLabel = new JLabel("Welcome " + userType + ": " + username);
        add(welcomeLabel);
        setTitle("Welcome Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
