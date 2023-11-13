import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CadastroClienteGUI extends JFrame {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField emailField;
    private JButton cadastrarButton;

    public CadastroClienteGUI() {
        setTitle("Cadastro de Cliente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
        panel.add(cadastrarButton);

        add(panel);
    }

      private void cadastrarCliente() {
        String cpf = cpfField.getText();
        String nome = nomeField.getText();
        String email = emailField.getText();

        // Validar se os campos estão preenchidos
        if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        // Adicionar lógica para cadastrar o cliente no arquivo "Cliente.txt"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cliente.txt", true))) {
            writer.write(String.format("%s;%s;%s%n", cpf, nome, email));
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            // Limpar campos após cadastrar
            cpfField.setText("");
            nomeField.setText("");
            emailField.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar o cliente.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroClienteGUI().setVisible(true);
            }
        });
    }
}
