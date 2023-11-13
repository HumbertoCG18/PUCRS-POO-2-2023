import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginForm extends JFrame {
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton cadastrarClienteButton;  // Botão para ir para o cadastro de clientes

    public LoginForm() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Usuário:"));
        usuarioField = new JTextField();
        panel.add(usuarioField);

        panel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
        panel.add(loginButton);

        cadastrarClienteButton = new JButton("Cadastrar Cliente");
        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirCadastroCliente();
            }
        });
        panel.add(cadastrarClienteButton);

        add(panel);
    }

    private boolean autenticarUsuario(String usuario, char[] senha, String tipoUsuario) {
        String caminhoArquivo = tipoUsuario.equals("empresa") ? "Empresa.txt" : "Cliente.txt";

        if (!usuario.isEmpty() && verificarSenha(usuario, senha, caminhoArquivo)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Login inválido. Verifique usuário e senha.");
            return false;
        }
    }

    private boolean verificarSenha(String email, char[] senha, String caminhoArquivo) {
        String senhaDigitada = new String(senha);
        String senhaArmazenada = obterSenhaArmazenada(email, caminhoArquivo);
        return senhaDigitada.equals(senhaArmazenada);
    }

    private String obterSenhaArmazenada(String email, String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 2 && partes[0].equalsIgnoreCase(email)) {
                    return partes[1];  // A senha está na segunda posição (0-indexed)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";  // Email não encontrado ou erro na leitura do arquivo
    }

private void abrirJanelaPrincipal(String usuario, String tipoUsuario) {
    if (tipoUsuario.equalsIgnoreCase("cliente")) {
        SwingUtilities.invokeLater(() -> {
            // Implemente a janela do cliente
            ClienteWindow clienteWindow = new ClienteWindow(usuario);
            clienteWindow.setVisible(true);
        });
    } else if (tipoUsuario.equalsIgnoreCase("empresa")) {
        SwingUtilities.invokeLater(() -> {
            // Implemente a janela da empresa
            EmpresaWindow empresaWindow = new EmpresaWindow(usuario);
            empresaWindow.setVisible(true);
        });
    } else {
        JOptionPane.showMessageDialog(this, "Tipo de usuário desconhecido.");
    }
}

    private void abrirCadastroCliente() {
        SwingUtilities.invokeLater(() -> {
            // Abre a janela de cadastro de cliente
            new CadastroClienteGUI().setVisible(true);
        });
    }

    private void realizarLogin() {
        String email = usuarioField.getText();
        char[] senha = senhaField.getPassword();

        // Verificar se é um cliente ou uma empresa
        String tipoUsuario = getEmailTipoUsuario(email);

        if (tipoUsuario != null && autenticarUsuario(email, senha, tipoUsuario)) {
            abrirJanelaPrincipal(email, tipoUsuario);
        }

        usuarioField.setText("");
        senhaField.setText("");
    }

    private String getEmailTipoUsuario(String email) {
        // Implemente a lógica para determinar se é um cliente ou uma empresa
        // Você pode fazer isso verificando se o email está presente no arquivo "Cliente.txt" ou "Empresa.txt"

        String caminhoCliente = "Cliente.txt";
        String caminhoEmpresa = "Empresa.txt";

        if (existeEmailNoArquivo(email, caminhoCliente)) {
            return "cliente";
        } else if (existeEmailNoArquivo(email, caminhoEmpresa)) {
            return "empresa";
        }

        return null;  // Email não encontrado em nenhum arquivo
    }

    private boolean existeEmailNoArquivo(String email, String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 1 && partes[0].equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;  // Email não encontrado ou erro na leitura do arquivo
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
