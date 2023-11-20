package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RemoverClienteGUI extends JFrame {
    private JComboBox<String> clientesComboBox;
    private List<Cliente> clientes;

    public RemoverClienteGUI(List<Cliente> clientes) {
        this.clientes = clientes;

        setTitle("Remover Cliente");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        panel.add(new JLabel("Selecione o Cliente:"));

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (Cliente cliente : clientes) {
            comboBoxModel.addElement(cliente.getNome());
        }

        clientesComboBox = new JComboBox<>(comboBoxModel);
        panel.add(clientesComboBox);

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(this::removerCliente);
        panel.add(removerButton);

        add(panel);
        setLocationRelativeTo(null);
    }

    private void removerCliente(ActionEvent e) {
        String nomeSelecionado = (String) clientesComboBox.getSelectedItem();
        Cliente selecionado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getNome().equals(nomeSelecionado)) {
                selecionado = cliente;
                break;
            }
        }

        if (selecionado != null) {
            String diretorioAtual = System.getProperty("user.dir");
            String caminhoArquivoClientes = diretorioAtual + "/src/Cliente.txt";

            clientes.remove(selecionado);
            Cliente.removerClienteDoArquivo(caminhoArquivoClientes, clientes, caminhoArquivoClientes);
            JOptionPane.showMessageDialog(this, "Cliente removido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para remover.");
        }
    }

    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        SwingUtilities.invokeLater(() -> {
            new RemoverClienteGUI(clientes).setVisible(true);
        });
    }
}
