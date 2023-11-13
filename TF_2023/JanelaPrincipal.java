import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
    private String email;
    private boolean logado;

    public JanelaPrincipal(String email) {
        this.email = email;
        this.logado = true;

        setTitle("Janela Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel("Bem-vindo, " + getEmail() + "!");
        panel.add(label);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> realizarLogout());
        panel.add(logoutButton);

        add(panel);
    }

    private void realizarLogout() {
        this.logado = false;

        dispose();

        new LoginForm().setVisible(true);
    }

    private String getEmail() {
        return email;
    }

    public boolean isLogado() {
        return logado;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal("exemplo@email.com");
            janela.setVisible(true);

            if (!janela.isLogado()) {
                new LoginForm().setVisible(true);
            }
        });
    }
}
