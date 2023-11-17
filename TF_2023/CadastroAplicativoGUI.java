import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroAplicativoGUI extends JFrame {
    private JTextField codigoField;
    private JTextField nomeField;
    private JTextField soField;
    private JComboBox<String> assinaturaComboBox; // Adicionando combo box para selecionar a assinatura
    private JButton cadastrarButton;

    private Aplicativo aplicativo;
    private List<Aplicativo> listaAplicativos;

    public CadastroAplicativoGUI(Aplicativo aplicativo, List<Aplicativo> listaAplicativos) {
        this.aplicativo = aplicativo;
        this.listaAplicativos = listaAplicativos;

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
        valorMensalField.setEditable(false); // Campo para exibir o valor mensal (não editável)
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
                double valorMensal = aplicativo.calcularValorMensal(idAssinatura);
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

            aplicativo.setCodigo(codigo);
            aplicativo.setNome(nome);
            aplicativo.setSistemaOperacional(so);
            aplicativo.setValorMensal(valorMensal);

            listaAplicativos.add(aplicativo);

            JOptionPane.showMessageDialog(this, "Aplicativo cadastrado/editado com sucesso!");
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
                List<Aplicativo> listaAplicativos = new ArrayList<>();
                Aplicativo aplicativo = new Aplicativo(0, "", "");
                new CadastroAplicativoGUI(aplicativo, listaAplicativos).setVisible(true);
            }
        });
    }
}
