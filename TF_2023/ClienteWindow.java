import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        panel.add(new JLabel("Preco total por mes: "), BorderLayout.SOUTH);
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
            cpf = "CPF nao encontrado";
            nome = "Nome nao encontrado";
        }

        // Carregar informações dos aplicativos
        String caminhoArquivoAplicativo = diretorioAtual + "/Aplicativos.txt";
        listaAplicativos = carregarAplicativos(caminhoArquivoAplicativo);

        
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
            return (partes.length >= 4) ? new Aplicativo(Integer.parseInt(partes[0].trim()), partes[1].trim(), partes[2].trim(), Double.parseDouble(partes[3].trim())) : null;
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
