package src;
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
    public JTabbedPane assinaturaComboBox;

    // Construtor que aceita parâmetros
    public EmpresaWindow(String email, List<Cliente> listaClientes, List<Aplicativo> listaAplicativos) {
        this.email = email;
        this.listaClientes = listaClientes;
        this.listaAplicativos = listaAplicativos;
        carregarInformacoesEmpresa();

        setTitle("Janela do desenvolvedor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);;

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Informacoes da Empresa", createInfoEmpresaPanel());
        tabbedPane.addTab("Suas Informacoes", createInfoPanel());
        tabbedPane.addTab("Visualizar Clientes", createClientePanel());
        tabbedPane.addTab("Visualizar Aplicativos", createAplicativoPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("CNPJ:"));
        panel.add(new JLabel(cnpj));
        panel.add(new JLabel("Nome Funcionario:"));
        panel.add(new JLabel(nome));
        panel.add(new JLabel("Email Funcionario:"));
        panel.add(new JLabel(email));
        return panel;
    }
    
    private JPanel createInfoEmpresaPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("CNPJ da Empresa: 86.573.972/0001-90"));
        panel.add(new JLabel("Nome da empresa: PUCRS"));
        panel.add(new JLabel("Email da Empresa: pucrs@gmail.com"));

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
        JButton saveButton = new JButton("Salvar Alteracoes");
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
    // Métodos para adicionar, excluir e salvar dados
    private void adicionarCliente() {
        AddClienteDialog dialog = new AddClienteDialog(this, listaClientes);
        dialog.setVisible(true); // Mostra a janela de cadastro
    
        // Atualiza a tabela apenas se um novo cliente foi adicionado
        refreshClienteTable();
    }


    private void salvarAlteracoes() {
        String caminhoArquivoCliente = System.getProperty("user.dir") + "/src/Cliente.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivoCliente))) {
            for (Cliente cliente : listaClientes) {
                writer.println(cliente.getCpf() + ";" + cliente.getNome() + ";" + cliente.getEmail());
            }
            JOptionPane.showMessageDialog(this, "Alteracoes nos clientes salvas com sucesso!");
        } catch (IOException e) {
            exibirErroAoSalvar("clientes");
        }
    }


    private void excluirCliente(JTable clienteTable) {
        int selectedRow = clienteTable.getSelectedRow();
        if (selectedRow >= 0) {
            listaClientes.remove(selectedRow);
            refreshClienteTable();
        }
    }

    
     private JPanel createAplicativoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable aplicativoTable = createAplicativoTable(listaAplicativos);
        JScrollPane scrollPane = new JScrollPane(aplicativoTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JButton addButton = new JButton("Adicionar Aplicativo");
        addButton.addActionListener(e -> adicionarAplicativo("Novo Aplicativo", "Sistema Operacional", 0.0)); // Valores padrão
        JButton deleteButton = new JButton("Excluir Aplicativo");
        deleteButton.addActionListener(e -> excluirAplicativo(aplicativoTable));
        JButton saveButton = new JButton("Salvar Alteracoes");
        saveButton.addActionListener(e -> {
            salvarAlteracoesAplicativos();
            JOptionPane.showMessageDialog(this, "Alterações nos aplicativos salvas com sucesso!");
            refreshAplicativoTable(); // Atualiza a tabela de aplicativos após salvar as alterações
        });
    
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(saveButton);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
    
        return panel;
    }

    private void salvarAlteracoesAplicativos() {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoArquivoAplicativos = diretorioAtual + "/src/Aplicativos.txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAplicativos))) {
            for (Aplicativo aplicativo : listaAplicativos) {
                writer.write(String.format("%d;%s;%s;%.2f%n",
                        aplicativo.getCodigo(),
                        aplicativo.getNome(),
                        aplicativo.getSistemaOperacional(),
                        aplicativo.getValorMensal()));
            }
            JOptionPane.showMessageDialog(null, "Alteracoes nos aplicativos salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar alteracoes nos aplicativos.");
        }
    }

    
    private void refreshClienteTable() {
        JTable clienteTable = createClienteTable(listaClientes);
        JScrollPane scrollPane = new JScrollPane(clienteTable);
        atualizarTabela(scrollPane);
    }

    private void exibirErroAoSalvar(String tipo) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar alteracoes nos " + tipo + ".");
    }

    
    private JTable createClienteTable(List<Cliente> listaClientes) {
        String[] columnNames = {"CPF", "Nome", "Email"};
        Object[][] data = new Object[listaClientes.size()][columnNames.length];
    
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            data[i][2] = cliente.getCpf();
            data[i][1] = cliente.getNome();
            data[i][0] = cliente.getEmail();
        }
    
        return new JTable(data, columnNames);
    }

    public class AddAplicativoDialog extends JDialog {
        private JTextField nomeField;
        private JTextField sistemaOperacionalField;
        private JTextField valorMensalField;
        private List<Aplicativo> listaAplicativos;
        private boolean aplicativoAdicionado = false;
    
        public boolean isAplicativoAdicionado() {
            return aplicativoAdicionado;
        }
    
        public AddAplicativoDialog(Frame owner, List<Aplicativo> listaAplicativos) {
            super(owner, "Adicionar Aplicativo", true);
            this.listaAplicativos = listaAplicativos;
    
            JPanel panel = new JPanel(new GridLayout(4, 2));
    
            panel.add(new JLabel("Nome:"));
            nomeField = new JTextField();
            panel.add(nomeField);
    
            panel.add(new JLabel("Sistema Operacional:"));
            sistemaOperacionalField = new JTextField();
            panel.add(sistemaOperacionalField);
    
            panel.add(new JLabel("Valor Mensal:"));
            valorMensalField = new JTextField();
            panel.add(valorMensalField);
    
            JButton salvarButton = new JButton("Salvar");
            salvarButton.addActionListener(this::salvarNovoAplicativo);
            panel.add(salvarButton);
    
            JButton cancelarButton = new JButton("Cancelar");
            cancelarButton.addActionListener(e -> dispose());
            panel.add(cancelarButton);
    
            add(panel);
            pack();
            setLocationRelativeTo(owner);
        }
    
        private void salvarNovoAplicativo(ActionEvent e) {  
                String nome = nomeField.getText();
                String sistemaOperacional = sistemaOperacionalField.getText();
                double valorMensal = Double.parseDouble(valorMensalField.getText());
                int idAssinatura = assinaturaComboBox.getSelectedIndex() + 1;
        
                if (!nome.isEmpty() && !sistemaOperacional.isEmpty()) {
                    Aplicativo novoApp = new Aplicativo(listaAplicativos.size() + 1, nome, sistemaOperacional, idAssinatura, valorMensal);
                    listaAplicativos.add(novoApp);
                    aplicativoAdicionado = true; // Define como true quando um novo aplicativo é adicionado
                    dispose(); // Fecha a janela de cadastro
                } else {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
                }
            }
        }

    
    private void adicionarAplicativo(String nome, String sistemaOperacional, double valorMensal) {
        AddAplicativoDialog dialog = new AddAplicativoDialog(this, listaAplicativos);
        dialog.setVisible(true); // Mostra a janela de cadastro
    
        // Atualiza a tabela apenas se um novo aplicativo foi adicionado
        refreshAplicativoTable();
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
        atualizarTabela(scrollPane);
    }
    
    private void atualizarTabela(Component novaTabela) {
        getContentPane().removeAll();
        getContentPane().add(novaTabela, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    private JTable createAplicativoTable(List<Aplicativo> aplicativos) {
        String[] columnNames = {"Codigo", "Nome", "Sistema Operacional", "Faturamento"};
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
    

    private void carregarInformacoesEmpresa() {
        // Obter o diretório atual
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoEmpresa = new File(diretorioAtual + "/src/Empresa.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoEmpresa))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 4 && partes[0].equalsIgnoreCase(email)) {
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
        String caminhoArquivoCliente = diretorioAtual + "/src/Cliente.txt";
        listaClientes = carregarClientes(caminhoArquivoCliente);

        // Carregar informações dos aplicativos
        String caminhoArquivoAplicativo = diretorioAtual + "/src/Aplicativos.txt";
        listaAplicativos = carregarAplicativos(caminhoArquivoAplicativo);
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


    private List<Cliente> carregarClientes(String caminhoArquivoCliente) {
        return carregarDados(caminhoArquivoCliente, linha -> {
            String[] partes = linha.split(";");
            return (partes.length >= 3) ? new Cliente(partes[0].trim(), partes[2].trim(), partes[3].trim()) : null;
        });
    }

    private List<Aplicativo> carregarAplicativos(String caminhoArquivoAplicativo) {
        return carregarDados(caminhoArquivoAplicativo, linha -> {
            String[] partes = linha.split(";");
            if (partes.length >= 4) {
                int codigo = Integer.parseInt(partes[0].trim());
                String nome = partes[1].trim();
                String sistemaOperacional = partes[2].trim();
    
                // Convertendo a string do preço para um double
                double valorMensal = Double.parseDouble(partes[3].replace(',', '.'));
    
                // Obtendo o último caractere para identificar a assinatura
                char idAssinaturaChar = partes[3].charAt(partes[3].length() - 1);
                int idAssinatura;
    
                // Mapeando o último caractere para o ID da assinatura
                switch (idAssinaturaChar) {
                    case '0':
                        idAssinatura = 1; // Assinatura Básica
                        break;
                    case '1':
                        idAssinatura = 2; // Assinatura VIP
                        break;
                    case '2':
                        idAssinatura = 3; // Assinatura Premium
                        break;
                    default:
                        idAssinatura = 1; // Se não corresponder a nenhum dos caracteres esperados, atribui o valor padrão
                        break;
                }
    
                return new Aplicativo(codigo, nome, sistemaOperacional, idAssinatura, valorMensal);
            }
            return null;
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Suponha que você tenha listas de clientes, aplicativos e assinaturas
            List<Cliente> listaClientes = new ArrayList<>();
            List<Aplicativo> listaAplicativos = new ArrayList<>();

            new EmpresaWindow("empresa@example.com", listaClientes, listaAplicativos).setVisible(true);
        });
    }
}
