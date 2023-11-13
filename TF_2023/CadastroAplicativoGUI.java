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
    private JTextField valorMensalField;
    private JButton cadastrarButton;

    private Aplicativo aplicativo; // Referência para o objeto Aplicativo
    private List<Aplicativo> listaAplicativos; // Lista para armazenar os aplicativos

    public CadastroAplicativoGUI(Aplicativo aplicativo, List<Aplicativo> listaAplicativos) {
        this.aplicativo = aplicativo;
        this.listaAplicativos = listaAplicativos;

        setTitle("Cadastro de Aplicativo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Código:"));
        codigoField = new JTextField();
        panel.add(codigoField);

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Sistema Operacional:"));
        soField = new JTextField();
        panel.add(soField);

        panel.add(new JLabel("Valor Mensal:"));
        valorMensalField = new JTextField();
        panel.add(valorMensalField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAplicativo();
            }
        });
        panel.add(cadastrarButton);

        add(panel);
    }

    private void cadastrarAplicativo() {
        try {
            int codigo = Integer.parseInt(codigoField.getText());
            String nome = nomeField.getText();
            String so = soField.getText();
            double valorMensal = Double.parseDouble(valorMensalField.getText());

            // Atualiza o objeto Aplicativo com os novos dados
            aplicativo.setCodigo(codigo);
            aplicativo.setNome(nome);
            aplicativo.setSistemaOperacional(so);
            aplicativo.setValorMensal(valorMensal);

            // Adiciona o Aplicativo à lista (simulando armazenamento)
            listaAplicativos.add(aplicativo);

            // Exemplo de exibição de mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Aplicativo cadastrado/editado com sucesso!");

            // Limpar os campos após cadastrar/editar
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar/editar aplicativo. Verifique os dados informados.");
        }
    }

    private void limparCampos() {
        codigoField.setText("");
        nomeField.setText("");
        soField.setText("");
        valorMensalField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                List<Aplicativo> listaAplicativos = new ArrayList<>();
                Aplicativo aplicativo = new Aplicativo(0, "", "", 0.0);
                new CadastroAplicativoGUI(aplicativo, listaAplicativos).setVisible(true);
            }
        });
    }
}
