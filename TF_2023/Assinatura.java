// Importe as classes necessárias
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

// Classe Assinatura
public class Assinatura {
    private int codigo;
    private int codigoAplicativo;
    private String cpfCliente;
    private String inicioVigencia;
    private String fimVigencia;

    public Assinatura(int codigo, int codigoAplicativo, String cpfCliente, String inicioVigencia, String fimVigencia) {
        this.codigo = codigo;
        this.codigoAplicativo = codigoAplicativo;
        this.cpfCliente = cpfCliente;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoAplicativo() {
        return codigoAplicativo;
    }

    public void setCodigoAplicativo(int codigoAplicativo) {
        this.codigoAplicativo = codigoAplicativo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(String inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public String getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(String fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public double calcularCobrancaMensal() {
        // Lógica para obter os dados do aplicativo associado
        Aplicativo aplicativo = obterAplicativo();

        // Se o aplicativo for encontrado, calcula a cobrança mensal
        if (aplicativo != null) {
            return aplicativo.getValorMensal();
        } else {
            // Retorna -1 como sinal de que o aplicativo associado não foi encontrado
            return -1;
        }
    }
        private Aplicativo obterAplicativo() {
        String caminhoArquivo = "./TF_2023/Aplicativos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int codigoAplicativoArquivo = Integer.parseInt(partes[0].trim());
                if (codigoAplicativoArquivo == codigoAplicativo) {
                    // Encontrou o aplicativo correspondente
                    return new Aplicativo(codigoAplicativoArquivo, partes[1].trim(), partes[2].trim(), Double.parseDouble(partes[3].trim()));
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }

        // Retorna null se o aplicativo não for encontrado
        return null;
    }

    

    public void salvarAssinatura() {
        double cobrancaMensal = calcularCobrancaMensal();

        // Se o cálculo da cobrança mensal for bem-sucedido
        if (cobrancaMensal != -1) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./TF_2023/Assinaturas.txt", true))) {
                // Adiciona uma linha ao arquivo contendo os dados da assinatura e a cobrança mensal
                writer.write(String.format("%d;%d;%s;%s;%s;%.2f%n", codigo, codigoAplicativo, cpfCliente, inicioVigencia, fimVigencia, cobrancaMensal));
                JOptionPane.showMessageDialog(null, "Assinatura cadastrada com sucesso! Cobrança mensal: R$" + cobrancaMensal);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar a assinatura.");
            }
        } else {
            // Exibe uma mensagem de erro caso o aplicativo associado não seja encontrado
            JOptionPane.showMessageDialog(null, "Erro ao calcular a cobrança mensal. Aplicativo associado não encontrado.");
        }
    }
}
