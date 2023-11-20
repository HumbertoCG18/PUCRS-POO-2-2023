package src;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ClienteWindow extends JFrame {
    private String email;
    private String cpf;
    private String nome;

    private List<Aplicativo> listaAplicativos;
    private List<Aplicativo> aplicativosInstalados;

    public ClienteWindow(String email, List<Aplicativo> listaAplicativos) {
        this.email = email;
        this.listaAplicativos = listaAplicativos;
        this.aplicativosInstalados = new ArrayList<>();
    
        carregarInformacoesCliente();

        setTitle("Janela do Cliente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Informacoes", createInfoPanel());
        tabbedPane.addTab("Aplicativos Disponiveis", createAplicativosDisponiveisPanel());
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
        JTable aplicativoTable = createAplicativoTable(listaAplicativos);
        JScrollPane scrollPane = new JScrollPane(aplicativoTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JPanel buttonsPanel = new JPanel();
        panel.add(buttonsPanel, BorderLayout.SOUTH);
    
        return panel;
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


    private JPanel createAplicativosInstaladosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable aplicativosInstaladosTable = createAplicativosInstaladosTable();
        JScrollPane scrollPane = new JScrollPane(aplicativosInstaladosTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton removeButton = new JButton("Remover Aplicativo");
        removeButton.addActionListener(e -> removerAplicativoSelecionado(aplicativosInstaladosTable));
        bottomPanel.add(removeButton);
    
        panel.add(bottomPanel, BorderLayout.SOUTH);
        return panel;
    }
    
private JTable createAplicativosInstaladosTable() {
    String[] columnNames = {"Nome", "Sistema Operacional", "Valor Mensal", "Assinatura"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    Map<Integer, String> mapAssinaturas = new HashMap<>();
    mapAssinaturas.put(1, "Básica");
    mapAssinaturas.put(2, "VIP");
    mapAssinaturas.put(3, "Premium");

    for (Aplicativo app : aplicativosInstalados) {
        Vector<Object> row = new Vector<>();
        row.add(app.getNome());
        row.add(app.getSistemaOperacional());
        row.add(app.getValorMensal());

        int idAssinatura = app.getIdAssinatura();
        String nomeAssinatura = mapAssinaturas.getOrDefault(idAssinatura, "Desconhecida");
        row.add(nomeAssinatura);

        model.addRow(row);
    }

    JTable table = new JTable(model);
    TableColumn assinaturaColumn = table.getColumnModel().getColumn(3);
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setToolTipText("Clique para escolher a assinatura");
    assinaturaColumn.setCellRenderer(renderer);

    JComboBox<String> assinaturaComboBox = new JComboBox<>(new String[]{"Básica", "VIP", "Premium"});
    assinaturaColumn.setCellEditor(new DefaultCellEditor(assinaturaComboBox));

    return table;
}
    
    private void removerAplicativoSelecionado(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
        }
    }


    private void carregarInformacoesCliente() {
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoClientes = new File(diretorioAtual + "/src/Cliente.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoClientes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 4 && partes[0].equalsIgnoreCase(email)) {
                    nome = partes[2].trim();
                    cpf = partes[3].trim();
                    String[] idsAplicativosString = partes[4].split(",");
                    
                    List<Integer> idsAplicativos = new ArrayList<>();
                    for (String id : idsAplicativosString) {
                        idsAplicativos.add(Integer.parseInt(id.trim()));
                    }
                    
                    carregarAplicativosInstalados(idsAplicativos); // Carregar os aplicativos instalados pelo cliente
                    break; // Encontrou o cliente, pode sair do loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Se não encontrou o cliente, atribuir valores padrão
        if (cpf == null || nome == null) {
            cpf = "CPF nao encontrado";
            nome = "Nome nao encontrado";
        }
    }
    
    private void carregarAplicativosInstalados(List<Integer> idsAplicativos) {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoArquivoAplicativo = diretorioAtual + "/src/Aplicativos.txt";
    
        listaAplicativos = carregarAplicativos(caminhoArquivoAplicativo);
    
        aplicativosInstalados = new ArrayList<>();
        for (Integer id : idsAplicativos) {
            for (Aplicativo app : listaAplicativos) {
                if (app.getCodigo() == id) {
                    aplicativosInstalados.add(app);
                    break;
                }
            }
        }
    }


    private <T>List<T> carregarDados(String caminhoArquivo, DataExtractor<T> extractor) {
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

    private List<Aplicativo> carregarAplicativos(String caminhoArquivoAplicativo) {
        return carregarDados(caminhoArquivoAplicativo, linha -> {
            String[] partes = linha.split(";");
            if (partes.length >= 4) {
                int codigo = Integer.parseInt(partes[0].trim());
                String nome = partes[1].trim();
                String sistemaOperacional = partes[2].trim();
    
                String[] valores = partes[3].split(",");
                if (valores.length >= 2) {
                    int idAssinatura = Integer.parseInt(valores[0].trim());
                    double valorMensal = Double.parseDouble(valores[1].trim());
    
                    return new Aplicativo(codigo, nome, sistemaOperacional, idAssinatura, valorMensal);
                }
            }
            return null;
        });
    }
    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        List<Aplicativo> listaAplicativos = new ArrayList<>();
    
            ClienteWindow clienteWindow = new ClienteWindow("cliente@example.com", listaAplicativos);
    
            clienteWindow.setVisible(true);
        });
    }
}
