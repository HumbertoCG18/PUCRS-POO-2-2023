import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroClienteGUI extends JFrame {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;  // Adiciona campo de senha
    private JButton cadastrarButton;

    public CadastroClienteGUI() {
        setTitle("Cadastro de Cliente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

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
        char[] senha = senhaField.getPassword();  // Obtém a senha como array de caracteres

        // Adiciona lógica para validar e cadastrar o cliente no arquivo
        if (validarCampos(cpf, nome, email, senha)) {
            cadastrarNoArquivo(cpf, nome, email, new String(senha));
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            limparCampos();
        }
    }

    private boolean validarCampos(String cpf, String nome, String email, char[] senha) {
        // Adiciona lógica de validação (pode ser mais robusta dependendo dos requisitos)
        return !cpf.isEmpty() && !nome.isEmpty() && !email.isEmpty() && senha.length > 0;
    }

    private void cadastrarNoArquivo(String cpf, String nome, String email, String senha) {
    String diretorioAtual = System.getProperty("user.dir");
    File caminhoArquivoCliente = new File(diretorioAtual + "/Cliente.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoCliente, true))) {
            // Formato: CPF;Nome;Email;Senha
            writer.write(cpf + ";" + nome + ";" + email + ";" + senha);
            writer.newLine();  // Adiciona uma nova linha para o próximo cadastro
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente.");
        }
    }

    private void limparCampos() {
        cpfField.setText("");
        nomeField.setText("");
        emailField.setText("");
        senhaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroClienteGUI().setVisible(true);
        });
    }
}
