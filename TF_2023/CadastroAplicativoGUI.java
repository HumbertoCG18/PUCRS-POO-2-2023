import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroAplicativoGUI extends JFrame {
    private JTextField codigoField;
    private JTextField nomeField;
    private JTextField soField;
    private JTextField valorMensalField;
    private JButton cadastrarButton;

    public CadastroAplicativoGUI() {
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
                // Adicione aqui a lógica para cadastrar o aplicativo
            }
        });
        panel.add(cadastrarButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroAplicativoGUI().setVisible(true);
            }
        });
    }
}
