import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaWindow extends JFrame {
    private String email;
    private String cnpj;
    private String nome;
    private List<Cliente> listaClientes;
    private List<Aplicativo> listaAplicativos;
    private List<Assinatura> listaAssinaturas;

    // Construtor que aceita parâmetros
    public EmpresaWindow(String email, List<Cliente> listaClientes, List<Aplicativo> listaAplicativos, List<Assinatura> listaAssinaturas) {
        this.email = email;
        this.listaClientes = listaClientes;
        this.listaAplicativos = listaAplicativos;
        this.listaAssinaturas = listaAssinaturas;
        carregarInformacoesEmpresa(); // Carregar informações da empresa ao criar a janela

        setTitle("Janela da Empresa/Desenvolvedor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel infoPanel = createInfoPanel();
        JPanel userAppPanel = createUserAppPanel();
        JPanel clientePanel = createClientePanel();
        JPanel aplicativoPanel = createAplicativoPanel();
        JPanel assinaturaPanel = createAssinaturaPanel();

        tabbedPane.addTab("Informações", infoPanel);
        tabbedPane.addTab("Gerenciar Usuários e Aplicativos", userAppPanel);
        tabbedPane.addTab("Visualizar Clientes", clientePanel);
        tabbedPane.addTab("Visualizar Aplicativos", aplicativoPanel);
        tabbedPane.addTab("Visualizar Assinaturas", assinaturaPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("CNPJ:"));
        panel.add(new JLabel(cnpj));

        panel.add(new JLabel("Nome:"));
        panel.add(new JLabel(nome));

        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(email));

        return panel;
    }

    private JPanel createUserAppPanel() {
        JPanel panel = new JPanel();

        // Implemente a lógica para a aba de gerenciamento de usuários e aplicativos
        // Adicione componentes conforme necessário

        return panel;
    }

    private JPanel createClientePanel() {
        JPanel panel = new JPanel();
        JTextArea clienteListTextArea = new JTextArea();

        for (Cliente cliente : listaClientes) {
            clienteListTextArea.append(cliente.toString() + "\n");
        }

        JScrollPane clienteListScrollPane = new JScrollPane(clienteListTextArea);
        panel.add(clienteListScrollPane);

        return panel;
    }

    private JPanel createAplicativoPanel() {
        JPanel panel = new JPanel();
        JTextArea aplicativoListTextArea = new JTextArea();

        for (Aplicativo aplicativo : listaAplicativos) {
            aplicativoListTextArea.append(aplicativo.toString() + "\n");
        }

        JScrollPane aplicativoListScrollPane = new JScrollPane(aplicativoListTextArea);
        panel.add(aplicativoListScrollPane);

        return panel;
    }

    private JPanel createAssinaturaPanel() {
        JPanel panel = new JPanel();
        JTextArea assinaturaListTextArea = new JTextArea();

        for (Assinatura assinatura : listaAssinaturas) {
            assinaturaListTextArea.append(assinatura.toString() + "\n");
        }

        JScrollPane assinaturaListScrollPane = new JScrollPane(assinaturaListTextArea);
        panel.add(assinaturaListScrollPane);

        return panel;
    }

    private void carregarInformacoesEmpresa() {
        // Implemente a lógica para carregar as informações da empresa a partir do arquivo "Empresa.txt"
        String caminhoArquivoEmpresa = "./TF_2023/Empresa.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoEmpresa))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3 && partes[2].equalsIgnoreCase(email)) {
                    cnpj = partes[0];
                    nome = partes[1];
                    break;  // Encontrou a empresa, pode sair do loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se não encontrou a empresa, atribua valores padrão ou lide com a situação conforme necessário
        if (cnpj == null) {
            cnpj = "CNPJ não encontrado";
            nome = "Nome não encontrado";
        }

        // Carregar informações dos clientes
        String caminhoArquivoCliente = "./TF_2023/Cliente.txt";
        listaClientes = carregarClientes(caminhoArquivoCliente);

        // Carregar informações dos aplicativos
        String caminhoArquivoAplicativo = "./TF_2023/Aplicativos.txt";
        listaAplicativos = carregarAplicativos(caminhoArquivoAplicativo);

        // Carregar informações das assinaturas
        String caminhoArquivoAssinatura = "./TF_2023/Assinaturas.txt";
        listaAssinaturas = carregarAssinaturas(caminhoArquivoAssinatura);
    }

    private List<Cliente> carregarClientes(String caminhoArquivo) {
        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    Cliente cliente = new Cliente(partes[0].trim(), partes[1].trim(), partes[2].trim());
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    private List<Aplicativo> carregarAplicativos(String caminhoArquivo) {
        List<Aplicativo> aplicativos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 4) {
                    Aplicativo aplicativo = new Aplicativo(
                            Integer.parseInt(partes[0].trim()),
                            partes[1].trim(),
                            partes[2].trim(),
                            Double.parseDouble(partes[3].trim())
                    );
                    aplicativos.add(aplicativo);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return aplicativos;
    }

    private List<Assinatura> carregarAssinaturas(String caminhoArquivo) {
        List<Assinatura> assinaturas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 6) {
                    Assinatura assinatura = new Assinatura(
                            Integer.parseInt(partes[0].trim()),
                            Integer.parseInt(partes[1].trim()),
                            partes[2].trim(),
                            partes[3].trim(),
                            partes[4].trim(),
                            Double.parseDouble(partes[5].trim())
                    );
                    assinaturas.add(assinatura);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return assinaturas;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Suponha que você tenha listas de clientes, aplicativos e assinaturas
            List<Cliente> listaClientes = new ArrayList<>();
            List<Aplicativo> listaAplicativos = new ArrayList<>();
            List<Assinatura> listaAssinaturas = new ArrayList<>();

            new EmpresaWindow("empresa@example.com", listaClientes, listaAplicativos, listaAssinaturas).setVisible(true);
        });
    }
}
