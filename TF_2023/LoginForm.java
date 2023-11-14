import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginForm extends JFrame {
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton cadastrarClienteButton;
    private JButton cadastrarEmpresaButton;

    public LoginForm() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        panel.add(usuarioField);

        panel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> realizarLogin());
        panel.add(loginButton);

        cadastrarClienteButton = new JButton("Cadastrar Cliente");
        cadastrarClienteButton.addActionListener(e -> abrirCadastroCliente());
        panel.add(cadastrarClienteButton);

        cadastrarEmpresaButton = new JButton("Cadastrar Empresa");
        cadastrarEmpresaButton.addActionListener(e -> abrirCadastroEmpresa());
        panel.add(cadastrarEmpresaButton);

        add(panel);
    }


    private boolean autenticarUsuario(String usuario, char[] senha, String tipoUsuario) {
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivo = tipoUsuario.equals("empresa") ? new File(diretorioAtual + "/Empresa.txt") :new File(diretorioAtual + "/Cliente.txt");
    
        if (caminhoArquivo.exists() && !usuario.isEmpty() && verificarSenha(usuario, senha, caminhoArquivo)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Login inválido. Verifique usuário e senha.");
            return false;
        }
    }
    
    private boolean verificarSenha(String usuario, char[] senha, File caminhoArquivo) {
        String senhaDigitada = new String(senha);
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 2 && partes[0].equalsIgnoreCase(usuario)) {
                    String senhaArmazenada = partes[1]; // A senha está na segunda posição (0-indexed)
                    return senhaDigitada.equals(senhaArmazenada);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return false; // Tratamento de erro ou usuário não encontrado
    }

    private String getEmailTipoUsuario(String email) {
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoCliente = new File(diretorioAtual + "/Cliente.txt");
        File caminhoEmpresa = new File(diretorioAtual + "/Empresa.txt");
    
        if (existeEmailNoArquivo(email, caminhoCliente)) {
            return "cliente";
        } else if (existeEmailNoArquivo(email, caminhoEmpresa)) {
            return "empresa";
        }
    
        return null;
    }

    private boolean existeEmailNoArquivo(String email, File caminhoArquivo) {
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
    
        return false;
    }
    
    private void abrirCadastroCliente() {
        SwingUtilities.invokeLater(() -> {
            CadastroClienteGUI cadastroClienteGUI = new CadastroClienteGUI();
            cadastroClienteGUI.setVisible(true);
        });
    }

    private void abrirCadastroEmpresa() {
        SwingUtilities.invokeLater(() -> {
            CadastroEmpresaGUI cadastroEmpresaGUI = new CadastroEmpresaGUI();
            cadastroEmpresaGUI.setVisible(true);
        });
    }

    private void realizarLogin() {
        String email = usuarioField.getText();
        char[] senha = senhaField.getPassword();

        // Verificar se é um cliente ou uma empresa
        String tipoUsuario = getEmailTipoUsuario(email);

        if (tipoUsuario != null && autenticarUsuario(email, senha, tipoUsuario)) {
            abrirJanelaPrincipal(email, tipoUsuario);
        } else {
            usuarioField.setText("");
            senhaField.setText("");
        }
    }


    private void abrirJanelaPrincipal(String usuario, String tipoUsuario) {
        if (tipoUsuario.equalsIgnoreCase("cliente")) {
            SwingUtilities.invokeLater(() -> {
                ClienteWindow clienteWindow = new ClienteWindow(usuario);
                clienteWindow.setVisible(true);
                dispose(); // Fechar a tela de login
            });
        } else if (tipoUsuario.equalsIgnoreCase("empresa")) {
            // Se você tiver uma lista de clientes, aplicativos e assinaturas para passar para a EmpresaWindow
            List<Cliente> listaClientes = new ArrayList<>();
            List<Aplicativo> listaAplicativos = new ArrayList<>();
            List<Assinatura> listaAssinaturas = new ArrayList<>();

            SwingUtilities.invokeLater(() -> {
                EmpresaWindow empresaWindow = new EmpresaWindow(usuario, listaClientes, listaAplicativos, listaAssinaturas);
                empresaWindow.setVisible(true);
                dispose(); // Fechar a tela de login
            });
        } else {
            JOptionPane.showMessageDialog(this, "Tipo de usuário desconhecido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
