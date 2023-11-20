package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CadastroAplicativoGUI extends JFrame {
    private JTextField nomeField;
    private JTextField sistemaOperacionalField;
    private JTextField valorMensalField;
    private JComboBox<String> assinaturaComboBox;
    private List<Aplicativo> aplicativos;

    public CadastroAplicativoGUI(List<Aplicativo> aplicativos) {
        this.aplicativos = aplicativos;

        setTitle("Adicionar Aplicativo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Sistema Operacional:"));
        sistemaOperacionalField = new JTextField();
        panel.add(sistemaOperacionalField);

        panel.add(new JLabel("Valor Mensal:"));
        valorMensalField = new JTextField();
        panel.add(valorMensalField);

        panel.add(new JLabel("Assinatura:"));
        assinaturaComboBox = new JComboBox<>(new String[]{"Básica", "VIP", "Premium"});
        panel.add(assinaturaComboBox);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(this::salvarNovoAplicativo);
        panel.add(salvarButton);

        add(panel);
        setLocationRelativeTo(null);
    }

    private void salvarNovoAplicativo(ActionEvent e) {
        String nome = nomeField.getText();
        String sistemaOperacional = sistemaOperacionalField.getText();
        double valorMensal = Double.parseDouble(valorMensalField.getText());
        int idAssinatura = assinaturaComboBox.getSelectedIndex() + 1; // IDs são de 1 a 3

        if (!nome.isEmpty() && !sistemaOperacional.isEmpty()) {
            Aplicativo novoApp = new Aplicativo(aplicativos.size() + 1, nome, sistemaOperacional, idAssinatura, valorMensal);
            aplicativos.add(novoApp);
            Aplicativo.salvarAplicativosEmArquivo(aplicativos, "Aplicativos.txt");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
        }
    }

    public static void main(String[] args) {
        List<Aplicativo> aplicativos = new ArrayList<>();
        SwingUtilities.invokeLater(() -> {
            new CadastroAplicativoGUI(aplicativos).setVisible(true);
        });
    }
}