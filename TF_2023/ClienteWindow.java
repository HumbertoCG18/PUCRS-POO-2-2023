import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteWindow extends JFrame {
    private String email;
    private String cpf;
    private String nome;
    private List<Cliente> listaClientes;
    private List<Aplicativo> listaAplicativos;
    private List<Assinatura> listaAssinaturas;

    public ClienteWindow(String email, List<Aplicativo> listaAplicativos, List<Assinatura> listaAssinaturas, List<Cliente> listaClientes) {
        this.email = email;
        this.listaClientes = listaClientes;
        this.listaAplicativos = listaAplicativos;
        this.listaAssinaturas = listaAssinaturas;
        carregarInformacoesCliente(); 
        carregarInformacoesCliente(); // Carregar informações do cliente logado

        setTitle("Janela da Empresa/Desenvolvedor");        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel infoPanel = createInfoPanel();
        JPanel aplicativoPanel = createAplicativoPanel();
        JPanel assinaturaPanel = createAssinaturaPanel();

        tabbedPane.addTab("Informações", infoPanel);
        tabbedPane.addTab("Visualizar Aplicativos", aplicativoPanel);
        tabbedPane.addTab("Visualizar Assinaturas", assinaturaPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("CPF:"));
        panel.add(new JLabel(cpf));

        panel.add(new JLabel("Nome:"));
        panel.add(new JLabel(nome));

        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(email));

        return panel;
    }

    private JPanel createAplicativoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        Cliente clienteAtual = null;
        for (Cliente cliente : listaClientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                clienteAtual = cliente;
                break;
            }
        }

        if (clienteAtual != null) {
            Map<Integer, Aplicativo> aplicativosCliente = clienteAtual.getAplicativos();
            JTable aplicativoTable = createAplicativoTable(new ArrayList<>(aplicativosCliente.values()));
            JScrollPane scrollPane = new JScrollPane(aplicativoTable);
            panel.add(scrollPane, BorderLayout.CENTER);
        } else {
            JLabel noAppsLabel = new JLabel("Nenhum aplicativo vinculado a este cliente.");
            panel.add(noAppsLabel, BorderLayout.CENTER);
        }

        JPanel buttonsPanel = new JPanel();
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JTable createAplicativoTable(List<Aplicativo> aplicativos) {
        String[] columnNames = {"Código", "Nome", "Sistema Operacional", "Valor Mensal"};
        Object[][] data = new Object[aplicativos.size()][columnNames.length];
    
        for (int i = 0; i < aplicativos.size(); i++) {
            Aplicativo aplicativo = aplicativos.get(i);
            data[i][0] = aplicativo.getCodigo();
            data[i][1] = aplicativo.getNome();
            data[i][2] = aplicativo.getSistemaOperacional();
            data[i][3] = aplicativo.getValorMensal();
        }
    
        return new JTable(data, columnNames);
    }


    private JPanel createAssinaturaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable assinaturaTable = createAssinaturaTable(listaAssinaturas);
        JScrollPane scrollPane = new JScrollPane(assinaturaTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private List<Assinatura> carregarAssinaturas(String caminhoArquivoAssinatura, List<Aplicativo> aplicativos, List<Cliente> clientes) {
        List<Assinatura> assinaturas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoAssinatura))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 6) {
                    int codigoAplicativo = Integer.parseInt(partes[1].trim());
                    String cpfCliente = partes[2].trim();

                    // Encontrando o aplicativo correspondente ao código
                    Aplicativo aplicativo = buscarAplicativoPorCodigo(aplicativos, codigoAplicativo);
                    // Encontrando o cliente correspondente ao CPF
                    Cliente cliente = buscarClientePorCPF(clientes, cpfCliente);

                    if (aplicativo != null && cliente != null) {
                        double valorMensal = aplicativo.calcularValorMensal(Integer.parseInt(partes[6].trim()));

                        Assinatura assinatura = new Assinatura(
                                Integer.parseInt(partes[0].trim()),
                                codigoAplicativo,
                                cpfCliente,
                                partes[3].trim(),
                                partes[4].trim(),
                                valorMensal // Define o valor mensal com base no ID da assinatura
                        );
                        assinatura.setNomeAplicativo(aplicativo.getNome());
                        assinatura.setNomeCliente(cliente.getNome());
                        assinaturas.add(assinatura);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return assinaturas;
    }
    
    private Aplicativo buscarAplicativoPorCodigo(List<Aplicativo> aplicativos, int codigo) {
        for (Aplicativo app : aplicativos) {
            if (app.getCodigo() == codigo) {
                return app;
            }
        }
        return null;
    }
    
    private Cliente buscarClientePorCPF(List<Cliente> clientes, String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
    
    private JTable createAssinaturaTable(List<Assinatura> assinaturas) {
        String[] columnNames = {"Código", "Código Aplicativo", "Nome Aplicativo", "CPF Cliente", "Nome Cliente", "Início Vigência", "Fim Vigência", "Valor Mensal"};
        Object[][] data = new Object[assinaturas.size()][columnNames.length];
    
        for (int i = 0; i < assinaturas.size(); i++) {
            Assinatura assinatura = assinaturas.get(i);
            data[i][0] = assinatura.getCodigo();
            data[i][1] = assinatura.getCodigoAplicativo();
            data[i][2] = assinatura.getNomeAplicativo();
            data[i][3] = assinatura.getCpfCliente();
            data[i][4] = assinatura.getNomeCliente();
            data[i][5] = assinatura.getInicioVigencia();
            data[i][6] = assinatura.getFimVigencia();
            data[i][7] = assinatura.getValorMensal();
        }
    
        return new JTable(data, columnNames);
    }


    private void carregarInformacoesCliente() {
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoCliente = new File(diretorioAtual + "/Cliente.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoCliente))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 5 && partes[0].equalsIgnoreCase(email)) {
                    cpf = partes[3].trim(); // Considerando que o CNPJ está na posição 3
                    nome = partes[2].trim(); // Considerando que o nome está na posição 1
                    break;  // Encontrou o cliente, pode sair do loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se não encontrou o cliente, atribuir valores padrão
        if (cpf == null || nome == null) {
            cpf = "CNPJ não encontrado";
            nome = "Nome não encontrado";
        }

        String caminhoArquivoAssinatura = diretorioAtual + "/Assinaturas.txt";
        listaAssinaturas = carregarAssinaturas(caminhoArquivoAssinatura, listaAplicativos, listaClientes);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Suponha que você tenha listas de clientes, aplicativos e assinaturas
            List<Cliente> listaClientes = new ArrayList<>();
            List<Aplicativo> listaAplicativos = new ArrayList<>();
            List<Assinatura> listaAssinaturas = new ArrayList<>();

            new ClienteWindow("empresa@example.com", listaAplicativos, listaAssinaturas, listaClientes).setVisible(true);
        });
    }
}
