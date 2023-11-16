import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ClienteWindow extends JFrame {
    private String email;
    private String cpf;
    private String nome;

    public ClienteWindow(String email) {
        this.email = email;
        carregarInformacoesCliente(); // Carregar informações do cliente ao criar a janela

        setTitle("Janela do Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("CPF:"));
        panel.add(new JLabel(cpf));

        panel.add(new JLabel("Nome:"));
        panel.add(new JLabel(nome));

        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(email));

        add(panel, BorderLayout.CENTER);
    }

    private void carregarInformacoesCliente() {
    String diretorioAtual = System.getProperty("user.dir");
    File caminhoArquivoCliente = new File(diretorioAtual + "/Cliente.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoCliente))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3 && partes[2].equalsIgnoreCase(email)) {
                    cpf = partes[0];
                    nome = partes[1];
                    return;  // Encontrou o cliente, pode sair do loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se não encontrou o cliente, atribua valores padrão ou lide com a situação conforme necessário
        cpf = "CPF não encontrado";
        nome = "Nome não encontrado";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClienteWindow("cliente@example.com").setVisible(true);
        });
    }
}
