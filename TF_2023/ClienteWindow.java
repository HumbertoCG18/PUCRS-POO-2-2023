import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClienteWindow extends JFrame {
    private String email;
    private String cpf;
    private String nome;
    private List<Cliente> listaClientes;
    private List<Aplicativo> listaAplicativos;
    private List<Aplicativo> aplicativosInstalados;

    public ClienteWindow(String email, List<Aplicativo> listaAplicativos, List<Cliente> listaClientes) {
        this.email = email;
        this.listaClientes = listaClientes;
        this.listaAplicativos = listaAplicativos;
        this.aplicativosInstalados = new ArrayList<>();
    
        carregarInformacoesCliente();

        setTitle("Janela do Cliente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Informacoes", createInfoPanel());
        tabbedPane.addTab("Aplicativos Disponíveis", createAplicativosDisponiveisPanel());
        tabbedPane.addTab("Aplicativos Instalados", createAplicativosInstaladosPanel());

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

    private JPanel createAplicativosDisponiveisPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable aplicativosTable = createAplicativosTable();
        JScrollPane scrollPane = new JScrollPane(aplicativosTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JTable createAplicativosTable() {
        String[] columnNames = {"ID", "Nome", "Sistema Operacional", "Valor Mensal", "Instalar"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Boolean.class : super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        for (Aplicativo app : listaAplicativos) {
            Vector<Object> row = new Vector<>();
            row.add(app.getCodigo());
            row.add(app.getNome());
            row.add(app.getSistemaOperacional());
            row.add(app.getValorMensal());
            row.add(false);
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> salvarAplicativos(table.getModel()));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(salvarButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return table;
    }

    private void salvarAplicativos(TableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean instalar = (boolean) model.getValueAt(i, 4);
            if (instalar) {
                int codigo = (int) model.getValueAt(i, 0);
                Aplicativo app = findAplicativoByCodigo(codigo);
                if (app != null) {
                    aplicativosInstalados.add(app);
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Aplicativos instalados salvos com sucesso!");
    }


    private Aplicativo findAplicativoByCodigo(int codigo) {
        for (Aplicativo app : listaAplicativos) {
            if (app.getCodigo() == codigo) {
                return app;
            }
        }
        return null;
    }

    private JPanel createAplicativosInstaladosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable aplicativosInstaladosTable = createAplicativosInstaladosTable();
        JScrollPane scrollPane = new JScrollPane(aplicativosInstaladosTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(new JLabel("Preço total por mês: "), BorderLayout.SOUTH);
        return panel;
    }

    private JTable createAplicativosInstaladosTable() {
        String[] columnNames = {"Nome", "Sistema Operacional", "Valor Mensal"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Aplicativo app : aplicativosInstalados) {
            Vector<Object> row = new Vector<>();
            row.add(app.getNome());
            row.add(app.getSistemaOperacional());
            row.add(app.getValorMensal());
            model.addRow(row);
        }

        return new JTable(model);
    }

    private List<Aplicativo> carregarAplicativos() {
        List<Aplicativo> aplicativos = new ArrayList<>();
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoAplicativos = new File(diretorioAtual + "/Aplicativos.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoAplicativos))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 5) {
                    int codigo = Integer.parseInt(partes[0].trim());
                    String nome = partes[1].trim();
                    String sistemaOperacional = partes[2].trim();
                    double valorMensal = Double.parseDouble(partes[3].trim());
                    int idAssinatura = Integer.parseInt(partes[4].trim());
    
                    Aplicativo app = new Aplicativo(codigo, nome, sistemaOperacional, valorMensal);
                    app.setIdAssinatura(idAssinatura);
                    aplicativos.add(app);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return aplicativos;
    }

    private void carregarInformacoesCliente() {
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoClientes = new File(diretorioAtual + "/Cliente.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoClientes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 4 && partes[0].equalsIgnoreCase(email)) {
                    nome = partes[2].trim();
                    cpf = partes[3].trim();
                    break; // Encontrou o cliente, pode sair do loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Se não encontrou o cliente, atribuir valores padrão
        if (cpf == null || nome == null) {
            cpf = "CPF não encontrado";
            nome = "Nome não encontrado";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Cliente> listaClientes = carregarListaClientes(); // Carregar a lista de clientes
            List<Aplicativo> listaAplicativos = new ArrayList<>();
    
            ClienteWindow clienteWindow = new ClienteWindow("cliente@example.com", listaAplicativos, listaClientes);
            listaAplicativos = clienteWindow.carregarAplicativos(); // Carregar a lista de aplicativos
    
            clienteWindow.setVisible(true);
        });
    }

    // Método para carregar a lista de clientes a partir do arquivo
    private static List<Cliente> carregarListaClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoClientes = new File(diretorioAtual + "/Cliente.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoClientes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    String email = partes[0].trim();
                    String nome = partes[1].trim();
                    String cpf = partes[2].trim();

                    Cliente cliente = new Cliente(email, nome, cpf);
                    listaClientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }
}
