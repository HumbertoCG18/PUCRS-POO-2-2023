import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroEmpresaGUI extends JFrame {
    private JTextField cnpjField;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;  // Adiciona campo de senha
    private JButton cadastrarButton;

    public CadastroEmpresaGUI() {
        setTitle("Cadastro de Empresa");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("CNPJ:"));
        cnpjField = new JTextField();
        panel.add(cnpjField);

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
                cadastrarEmpresa();
            }
        });
        panel.add(cadastrarButton);

        add(panel);
    }

    private void cadastrarEmpresa() {
        String cnpj = cnpjField.getText();
        String nome = nomeField.getText();
        String email = emailField.getText();
        char[] senha = senhaField.getPassword();  // Obtém a senha como array de caracteres

        // Adiciona lógica para validar e cadastrar a empresa no arquivo
        if (validarCampos(cnpj, nome, email, senha)) {
            cadastrarNoArquivo(cnpj, nome, email, new String(senha));
            JOptionPane.showMessageDialog(this, "Empresa cadastrada com sucesso!");
            limparCampos();
        }
    }

    private boolean validarCampos(String cnpj, String nome, String email, char[] senha) {
        // Adiciona lógica de validação (pode ser mais robusta dependendo dos requisitos)
        return !cnpj.isEmpty() && !nome.isEmpty() && !email.isEmpty() && senha.length > 0;
    }

    private void cadastrarNoArquivo(String cnpj, String nome, String email, String senha) {
        String caminhoArquivo = "./TF_2023/Empresa.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            // Formato: CNPJ;Nome;Email;Senha
            writer.write(cnpj + ";" + nome + ";" + email + ";" + senha);
            writer.newLine();  // Adiciona uma nova linha para o próximo cadastro
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar empresa.");
        }
    }

    private void limparCampos() {
        cnpjField.setText("");
        nomeField.setText("");
        emailField.setText("");
        senhaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroEmpresaGUI().setVisible(true);
        });
    }
}
