package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RemoverAplicativoGUI extends JFrame {
    private JComboBox<String> aplicativosComboBox;
    private List<Aplicativo> aplicativos;

    public RemoverAplicativoGUI(List<Aplicativo> aplicativos) {
        this.aplicativos = aplicativos;

        setTitle("Remover Aplicativo");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        panel.add(new JLabel("Selecione o Aplicativo:"));

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (Aplicativo app : aplicativos) {
            comboBoxModel.addElement(app.getNome());
        }

        aplicativosComboBox = new JComboBox<>(comboBoxModel);
        panel.add(aplicativosComboBox);

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(this::removerAplicativo);
        panel.add(removerButton);

        add(panel);
        setLocationRelativeTo(null);
    }

    private void removerAplicativo(ActionEvent e) {
        String nomeSelecionado = (String) aplicativosComboBox.getSelectedItem();
        Aplicativo selecionado = null;

        for (Aplicativo app : aplicativos) {
            if (app.getNome().equals(nomeSelecionado)) {
                selecionado = app;
                break;
            }
        }

        if (selecionado != null) {
            String diretorioAtual = System.getProperty("user.dir");
            String caminhoArquivoAplicativo = diretorioAtual + "/src/Aplicativos.txt";

            aplicativos.remove(selecionado);
            Aplicativo.removerAplicativoDoArquivo(aplicativos, ABORT, caminhoArquivoAplicativo);
            JOptionPane.showMessageDialog(this, "Aplicativo removido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um aplicativo para remover.");
        }
    }


    public static void main(String[] args) {
        List<Aplicativo> aplicativos = new ArrayList<>();
        // Carregar aplicativos do arquivo, se necessário

        SwingUtilities.invokeLater(() -> {
            new RemoverAplicativoGUI(aplicativos).setVisible(true);
        });
    }
}
