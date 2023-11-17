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
import java.awt.event.ActionEvent;

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

        tabbedPane.addTab("Informacoes", infoPanel);
        tabbedPane.addTab("Visualizar Clientes", clientePanel);
        tabbedPane.addTab("Visualizar Aplicativos", aplicativoPanel);

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
        saveButton.addActionListener(e -> {
            salvarAlteracoes();
            JOptionPane.showMessageDialog(this, "Alterações nos clientes salvas com sucesso!");
            refreshClienteTable(); // Atualiza a tabela de clientes após salvar as alterações
        });
    
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(saveButton);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
    
        return panel;
    }
    
    
    public class AddClienteDialog extends JDialog {
        private JTextField cpfField;
        private JTextField nomeField;
        private JTextField emailField;
        private List<Cliente> listaClientes;
        private boolean clienteAdicionado = false;

        public boolean isClienteAdicionado() {
            return clienteAdicionado;
        }
    
        public AddClienteDialog(Frame owner, List<Cliente> listaClientes) {
            super(owner, "Adicionar Cliente", true);
            this.listaClientes = listaClientes;
    
            JPanel panel = new JPanel(new GridLayout(4, 2));
    
            panel.add(new JLabel("CPF:"));
            cpfField = new JTextField();
            panel.add(cpfField);
    
            panel.add(new JLabel("Nome:"));
            nomeField = new JTextField();
            panel.add(nomeField);
    
            panel.add(new JLabel("Email:"));
            emailField = new JTextField();
            panel.add(emailField);
    
            JButton salvarButton = new JButton("Salvar");
            salvarButton.addActionListener(this::salvarNovoCliente);
            panel.add(salvarButton);
    
            JButton cancelarButton = new JButton("Cancelar");
            cancelarButton.addActionListener(e -> dispose());
            panel.add(cancelarButton);
    
            add(panel);
            pack();
            setLocationRelativeTo(owner);
        }
    
        private void salvarNovoCliente(ActionEvent e) {
            String cpf = cpfField.getText();
            String nome = nomeField.getText();
            String email = emailField.getText();
    
            if (!cpf.isEmpty() && !nome.isEmpty() && !email.isEmpty()) {
                Cliente novoCliente = new Cliente(cpf, nome, email);
                listaClientes.add(novoCliente);
                clienteAdicionado = true; // Define como true quando um novo cliente é adicionado
                dispose(); // Fecha a janela de cadastro
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            }
        }
    }


    private void adicionarCliente() {
        AddClienteDialog dialog = new AddClienteDialog(this, listaClientes);
        dialog.setVisible(true); // Mostra a janela de cadastro
    
        // Atualiza a tabela apenas se um novo cliente foi adicionado
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
        String caminhoArquivoCliente = System.getProperty("user.dir") + "/Cliente.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivoCliente))) {
            for (Cliente cliente : listaClientes) {
                writer.println(cliente.getCpf() + ";" + cliente.getNome() + ";" + cliente.getEmail());
            }
            JOptionPane.showMessageDialog(this, "Alterações nos clientes salvas com sucesso!");
        } catch (IOException e) {
            exibirErroAoSalvar("clientes");
        }
    }
    
private void refreshClienteTable() {
    JTable clienteTable = createClienteTable(listaClientes);
    JScrollPane scrollPane = new JScrollPane(clienteTable);
    atualizarTabela(scrollPane);
}

private void exibirErroAoSalvar(String tipo) {
    JOptionPane.showMessageDialog(this, "Erro ao salvar alterações nos " + tipo + ".");
}

private void atualizarTabela(Component novaTabela) {
    getContentPane().removeAll();
    getContentPane().add(novaTabela, BorderLayout.CENTER);
    revalidate();
    repaint();
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
    addButton.addActionListener(e -> adicionarAplicativo("Nome do Aplicativo", "Sistema Operacional", 1)); // Substitua com os valores apropriados
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

    private void adicionarAplicativo(String nome, String sistemaOperacional, int idAssinatura) {
        int novoCodigo = listaAplicativos.size() + 1;
        Aplicativo novoApp = new Aplicativo(novoCodigo, nome, sistemaOperacional, 0); // Valor mensal será atualizado
        
        double valorMensal = novoApp.calcularValorMensal(idAssinatura);
        novoApp.setIdAssinatura(idAssinatura);
        novoApp.setValorMensal(valorMensal);
        
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
        String[] columnNames = {"Codigo", "Nome", "Sistema Operacional", "Valor Mensal"};
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
    
    private <T> List<T> carregarDados(String caminhoArquivo, DataExtractor<T> extractor) {
        List<T> dados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                T dado = extractor.extractData(linha);
                if (dado != null) {
                    dados.add(dado);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return dados;
    }

    private interface DataExtractor<T> {
        T extractData(String linha);
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
                    Assinatura assinatura = new Assinatura(
                            Integer.parseInt(partes[0].trim()),
                            codigoAplicativo,
                            cpfCliente,
                            partes[3].trim(),
                            partes[4].trim(),
                            Double.parseDouble(partes[5].trim())
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
            cnpj = "CNPJ nao encontrado";
            nome = "Nome nao encontrado";
        }

        // Carregar informações dos clientes
        String caminhoArquivoCliente = diretorioAtual + "/Cliente.txt";
        listaClientes = carregarClientes(caminhoArquivoCliente);

        // Carregar informações dos aplicativos
        String caminhoArquivoAplicativo = diretorioAtual + "/Aplicativos.txt";
        listaAplicativos = carregarAplicativos(caminhoArquivoAplicativo);

        // Carregar informações das assinaturas
        String caminhoArquivoAssinatura = diretorioAtual + "/Assinaturas.txt";
        listaAssinaturas = carregarAssinaturas(caminhoArquivoAssinatura, listaAplicativos, listaClientes);
    }
    private List<Cliente> carregarClientes(String caminhoArquivoCliente) {
        return carregarDados(caminhoArquivoCliente, linha -> {
            String[] partes = linha.split(";");
            return (partes.length >= 3) ? new Cliente(partes[0].trim(), partes[1].trim(), partes[2].trim()) : null;
        });
    }

    private List<Aplicativo> carregarAplicativos(String caminhoArquivoAplicativo) {
        return carregarDados(caminhoArquivoAplicativo, linha -> {
            String[] partes = linha.split(";");
            return (partes.length >= 4) ? new Aplicativo(Integer.parseInt(partes[0].trim()), partes[1].trim(), partes[2].trim(), Double.parseDouble(partes[3].trim())) : null;
        });
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
