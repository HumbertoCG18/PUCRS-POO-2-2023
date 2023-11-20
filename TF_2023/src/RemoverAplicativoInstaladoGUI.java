package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class RemoverAplicativoInstaladoGUI extends JFrame {
    private JComboBox<String> aplicativosComboBox;
    private List<Cliente> clientes;

    public RemoverAplicativoInstaladoGUI(List<Cliente> clientes) {
        this.clientes = clientes;

        setTitle("Remover Aplicativo Instalado");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        panel.add(new JLabel("Selecione o Aplicativo:"));

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        // Assumindo que há apenas um cliente (o primeiro) para este exemplo
        Cliente cliente = clientes.get(0);

        for (Map.Entry<Integer, Aplicativo> entry : cliente.getAplicativos().entrySet()) {
            comboBoxModel.addElement(entry.getValue().getNome());
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
        Cliente cliente = clientes.get(0); // Assumindo apenas um cliente para este exemplo

        for (Map.Entry<Integer, Aplicativo> entry : cliente.getAplicativos().entrySet()) {
            if (entry.getValue().getNome().equals(nomeSelecionado)) {
                cliente.desinstalarAplicativo(entry.getValue());
                Cliente.escreverClientesNoArquivo("Cliente.txt", clientes);
                JOptionPane.showMessageDialog(this, "Aplicativo removido com sucesso.");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Selecione um aplicativo para remover.");
    }

    public static void main(String[] args) {
        List<Cliente> clientes = Cliente.lerClientesDoArquivo("Cliente.txt", null);
        // Carregar aplicativos do arquivo, se necessário

        SwingUtilities.invokeLater(() -> {
            new RemoverAplicativoInstaladoGUI(clientes).setVisible(true);
        });
    }
}
