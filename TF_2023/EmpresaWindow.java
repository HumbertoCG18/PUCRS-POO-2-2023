import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmpresaWindow extends JFrame {
    private String email;
    private String cnpj;
    private String nome;

    public EmpresaWindow(String email) {
        this.email = email;
        carregarInformacoesEmpresa(); // Carregar informações da empresa ao criar a janela

        setTitle("Janela da Empresa/Desenvolvedor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("CNPJ:"));
        panel.add(new JLabel(cnpj));

        panel.add(new JLabel("Nome:"));
        panel.add(new JLabel(nome));

        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(email));

        add(panel, BorderLayout.CENTER);
    }

    private void carregarInformacoesEmpresa() {
        // Lógica para carregar as informações da empresa a partir do arquivo "Empresa.txt"
        String caminhoArquivo = "./TF_2023/Empresa.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3 && partes[2].equalsIgnoreCase(email)) {
                    cnpj = partes[0];
                    nome = partes[1];
                    return;  // Encontrou a empresa, pode sair do loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se não encontrou a empresa, atribua valores padrão ou lide com a situação conforme necessário
        cnpj = "CNPJ não encontrado";
        nome = "Nome não encontrado";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmpresaWindow("empresa@example.com").setVisible(true);
        });
    }
}
