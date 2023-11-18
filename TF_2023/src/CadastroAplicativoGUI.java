package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroAplicativoGUI extends JFrame {
    private JTextField codigoField;
    private JTextField nomeField;
    private JTextField soField;
    private JComboBox<String> assinaturaComboBox;
    private JButton cadastrarButton;

    public CadastroAplicativoGUI() {
        setTitle("Cadastro de Aplicativo");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Código:"));
        codigoField = new JTextField();
        panel.add(codigoField);

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Sistema Operacional:"));
        soField = new JTextField();
        panel.add(soField);

        panel.add(new JLabel("Assinatura:"));
        String[] assinaturas = {"Básica", "VIP", "Premium"};
        assinaturaComboBox = new JComboBox<>(assinaturas);
        panel.add(assinaturaComboBox);

        panel.add(new JLabel("Valor Mensal:"));
        JTextField valorMensalField = new JTextField();
        valorMensalField.setEditable(false);
        panel.add(valorMensalField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAplicativo(valorMensalField);
            }
        });
        panel.add(cadastrarButton);

        // Adiciona um listener para alterar o valor mensal quando a assinatura é selecionada
        assinaturaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idAssinatura = assinaturaComboBox.getSelectedIndex() + 1;
                double valorMensal = Aplicativo.calcularValorMensalPeloCodigo(idAssinatura);
                valorMensalField.setText(String.valueOf(valorMensal));
            }
        });

        add(panel);
    }

    private void cadastrarAplicativo(JTextField valorMensalField) {
        try {
            int codigo = Integer.parseInt(codigoField.getText());
            String nome = nomeField.getText();
            String so = soField.getText();
            double valorMensal = Double.parseDouble(valorMensalField.getText());

            Aplicativo app = Aplicativo.getAplicativoPeloCodigo(codigo);
            if (app != null) {
                app.setNome(nome);
                app.setSistemaOperacional(so);
                app.setValorMensal(valorMensal);

                JOptionPane.showMessageDialog(this, "Aplicativo editado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Código de aplicativo não encontrado!");
            }

            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar/editar aplicativo. Verifique os dados informados.");
        }
    }

    private void limparCampos() {
        codigoField.setText("");
        nomeField.setText("");
        soField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroAplicativoGUI().setVisible(true);
            }
        });
    }
}
