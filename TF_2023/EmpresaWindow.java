import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        JPanel clientePanel = createClientePanel();
        JPanel aplicativoPanel = createAplicativoPanel();
        JPanel assinaturaPanel = createAssinaturaPanel();

        tabbedPane.addTab("Informações", infoPanel);
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

    private JPanel createClientePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable clienteTable = createClienteTable(listaClientes);
        JScrollPane scrollPane = new JScrollPane(clienteTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        // Botões para adicionar, excluir e salvar clientes
        JButton addButton = new JButton("Adicionar Cliente");
        addButton.addActionListener(e -> adicionarCliente());
        JButton deleteButton = new JButton("Excluir Cliente");
        deleteButton.addActionListener(e -> excluirCliente(clienteTable));
        JButton saveButton = new JButton("Salvar Alterações");
        saveButton.addActionListener(e -> salvarAlteracoes());
    
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(saveButton);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
    
        return panel;
    }
    
    private void adicionarCliente() {
        Cliente novoCliente = new Cliente("Novo CPF", "Novo Nome", "Novo Email");
        listaClientes.add(novoCliente);
        refreshClienteTable();
    }
    
    private void excluirCliente(JTable clienteTable) {
        int selectedRow = clienteTable.getSelectedRow();
        if (selectedRow >= 0) {
            listaClientes.remove(selectedRow);
            refreshClienteTable();
        }
    }
    
private void salvarAlteracoes() {
    String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoCliente = new File(diretorioAtual + "/Cliente.txt");

    try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivoCliente))) {
        for (Cliente cliente : listaClientes) {
            writer.println(cliente.getCpf() + ";" + cliente.getNome() + ";" + cliente.getEmail());
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao salvar alterações nos clientes.");
    }
}
    
    private void refreshClienteTable() {
        // Atualiza a tabela de clientes após adicionar ou excluir um cliente
        JTable clienteTable = createClienteTable(listaClientes);
        JScrollPane scrollPane = new JScrollPane(clienteTable);
        Component[] components = ((Container) getContentPane().getLayout()).getComponents();
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                getContentPane().remove(component);
                getContentPane().add(scrollPane, BorderLayout.CENTER);
                revalidate();
                repaint();
                break;
            }
        }
    }
    
    private JTable createClienteTable(List<Cliente> listaClientes) {
        String[] columnNames = {"CPF", "Nome", "Email"};
        Object[][] data = new Object[listaClientes.size()][columnNames.length];
    
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            data[i][0] = cliente.getCpf();
            data[i][1] = cliente.getNome();
            data[i][2] = cliente.getEmail();
        }
    
        return new JTable(data, columnNames);
    }
    


    private JPanel createAplicativoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable aplicativoTable = createAplicativoTable(listaAplicativos);
        JScrollPane scrollPane = new JScrollPane(aplicativoTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JButton addButton = new JButton("Adicionar Aplicativo");
        addButton.addActionListener(e -> adicionarAplicativo());
        JButton deleteButton = new JButton("Excluir Aplicativo");
        deleteButton.addActionListener(e -> excluirAplicativo(aplicativoTable));
        JButton saveButton = new JButton("Salvar Alterações");
        saveButton.addActionListener(e -> salvarAlteracoesAplicativos());
    
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(saveButton);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
    
        return panel;

    }

    private void adicionarAplicativo() {
        // Gere um código para o novo aplicativo ou ajuste conforme necessário
        int novoCodigo = listaAplicativos.size() + 1;
        
        Aplicativo novoApp = new Aplicativo(novoCodigo, "Novo Nome", "Novo Sistema Operacional", 0.0);
        listaAplicativos.add(novoApp);
        refreshAplicativoTable();
    }
    
    private void salvarAlteracoesAplicativos() {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoArquivoAplicativos = diretorioAtual + "/Aplicativos.txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAplicativos))) {
            for (Aplicativo aplicativo : listaAplicativos) {
                writer.write(String.format("%d;%s;%s;%.2f%n",
                        aplicativo.getCodigo(),
                        aplicativo.getNome(),
                        aplicativo.getSistemaOperacional(),
                        aplicativo.getValorMensal()));
            }
            JOptionPane.showMessageDialog(null, "Alterações nos aplicativos salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar alterações nos aplicativos.");
        }
    }
    
    private void excluirAplicativo(JTable aplicativoTable) {
        int selectedRow = aplicativoTable.getSelectedRow();
        if (selectedRow >= 0) {
            listaAplicativos.remove(selectedRow);
            refreshAplicativoTable();
        }
    }
    
    private void refreshAplicativoTable() {
        JTable aplicativoTable = createAplicativoTable(listaAplicativos);
        JScrollPane scrollPane = new JScrollPane(aplicativoTable);
        Component[] components = ((Container) getContentPane().getLayout()).getComponents();
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                getContentPane().remove(component);
                getContentPane().add(scrollPane, BorderLayout.CENTER);
                revalidate();
                repaint();
                break;
            }
        }
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
    
        // Botões para adicionar, excluir e salvar alterações nas assinaturas
        JButton adicionarAssButton = new JButton("Adicionar Assinatura");
        adicionarAssButton.addActionListener(e -> adicionarAssinatura());
        JButton excluirAssButton = new JButton("Excluir Assinatura");
        excluirAssButton.addActionListener(e -> excluirAssinatura(assinaturaTable));
        JButton salvarAssButton = new JButton("Salvar Alterações");
        salvarAssButton.addActionListener(e -> salvarAlteracoesAssinaturas());
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarAssButton);
        buttonPanel.add(excluirAssButton);
        buttonPanel.add(salvarAssButton);
    
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        return panel;
    }
    private void adicionarAssinatura() {
        // Gere um código para a nova assinatura ou ajuste conforme necessário
        int novoCodigo = listaAssinaturas.size() + 1;
        
        Assinatura novaAssinatura = new Assinatura(novoCodigo, 0, "Novo CPF", "Nova Data Inicial", "Nova Data Final", 0.0);
        listaAssinaturas.add(novaAssinatura);
        refreshAssinaturaTable();
    }
    
    private void excluirAssinatura(JTable assinaturaTable) {
        int selectedRow = assinaturaTable.getSelectedRow();
        if (selectedRow >= 0) {
            listaAssinaturas.remove(selectedRow);
            refreshAssinaturaTable();
        }
    }
    private void salvarAlteracoesAssinaturas() {
        String diretorioAtual = System.getProperty("user.dir");
    String caminhoArquivoAssinaturas = diretorioAtual + "/Assinaturas.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAssinaturas))) {
        for (Assinatura assinatura : listaAssinaturas) {
            writer.write(String.format("%d;%d;%s;%s;%s;%.2f%n",
                    assinatura.getCodigo(),
                    assinatura.getCodigoAplicativo(),
                    assinatura.getCpfCliente(),
                    assinatura.getInicioVigencia(),
                    assinatura.getFimVigencia(),
                    assinatura.getValorMensal()));
        }
        JOptionPane.showMessageDialog(null, "Alterações nas assinaturas salvas com sucesso!");
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao salvar alterações nas assinaturas.");
    }
}

private void refreshAssinaturaTable() {
    JTable assinaturaTable = createAssinaturaTable(listaAssinaturas);
    JScrollPane scrollPane = new JScrollPane(assinaturaTable);
    Component[] components = ((Container) getContentPane().getLayout()).getComponents();
    for (Component component : components) {
        if (component instanceof JScrollPane) {
            getContentPane().remove(component);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            revalidate();
            repaint();
            break;
        }
    }
}
    
    private JTable createAssinaturaTable(List<Assinatura> assinaturas) {
        String[] columnNames = {"Código", "Código Aplicativo", "CPF Cliente", "Início Vigência", "Fim Vigência", "Valor Mensal"};
        Object[][] data = new Object[assinaturas.size()][columnNames.length];
    
        for (int i = 0; i < assinaturas.size(); i++) {
            Assinatura assinatura = assinaturas.get(i);
            data[i][0] = assinatura.getCodigo();
            data[i][1] = assinatura.getCodigoAplicativo();
            data[i][2] = assinatura.getCpfCliente();
            data[i][3] = assinatura.getInicioVigencia();
            data[i][4] = assinatura.getFimVigencia();
            data[i][5] = assinatura.getValorMensal();
        }
    
        return new JTable(data, columnNames);
    }

    private void carregarInformacoesEmpresa() {
        // Obter o diretório atual
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoEmpresa = new File(diretorioAtual + "/Empresa.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoEmpresa))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 5 && partes[0].equalsIgnoreCase(email)) {
                    cnpj = partes[3].trim();
                    nome = partes[2].trim();
                    break;  // Encontrou a empresa, pode sair do loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Se não encontrou a empresa, atribuir valores padrão
        if (cnpj == null || nome == null) {
            cnpj = "CNPJ não encontrado";
            nome = "Nome não encontrado";
        }

        // Carregar informações dos clientes
        String caminhoArquivoCliente = diretorioAtual + "/Cliente.txt";
        listaClientes = carregarClientes(caminhoArquivoCliente);

        // Carregar informações dos aplicativos
        String caminhoArquivoAplicativo = diretorioAtual + "/Aplicativos.txt";
        listaAplicativos = carregarAplicativos(caminhoArquivoAplicativo);

        // Carregar informações das assinaturas
        String caminhoArquivoAssinatura = diretorioAtual + "/Assinaturas.txt";
        listaAssinaturas = carregarAssinaturas(caminhoArquivoAssinatura);
    }

    private List<Cliente> carregarClientes(String caminhoArquivoCliente) {
        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoCliente))) {
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

    private List<Aplicativo> carregarAplicativos(String caminhoArquivoAplicativo) {
        List<Aplicativo> aplicativos = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoAplicativo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 4) {
                    int codigo = Integer.parseInt(partes[0].trim());
                    String nome = partes[1].trim();
                    String sistemaOperacional = partes[2].trim();
                    double valorMensal = Double.parseDouble(partes[3].trim());
    
                    Aplicativo app = new Aplicativo(codigo, nome, sistemaOperacional, valorMensal);
                    aplicativos.add(app);
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
