// Importe as classes necessárias
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    public double valorMensal;

    public Assinatura(int codigo, int codigoAplicativo, String cpfCliente, String inicioVigencia, String fimVigencia, double valorMensal) {
        this.codigo = codigo;
        this.codigoAplicativo = codigoAplicativo;
        this.cpfCliente = cpfCliente;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.valorMensal = valorMensal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public double getValorMensal() {
        return calcularCobrancaMensal();
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

    //Aplicativo aplicativo = obterAplicativo();

    public double calcularCobrancaMensal() {
        double valorMensalArquivo = obterValorMensalDoArquivo(codigoAplicativo);
        Aplicativo aplicativo = obterAplicativo();
        if (aplicativo != null) {
            double valorMensal = obterValorMensalDoArquivo(aplicativo.getCodigo());
            if (valorMensal != -1) {
                this.valorMensal = valorMensalArquivo; // Atualiza o valorMensal na instância de Assinatura
                return valorMensalArquivo;
            }
        }
        // Retorna -1 como sinal de que o aplicativo associado não foi encontrado ou o valor mensal não está disponível
        return -1;
    }
    
    private double obterValorMensalDoArquivo(int codigoAplicativo) {
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoAssinatura = new File(diretorioAtual + "/Assinaturas.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoAssinatura))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int codigoAplicativoArquivo = Integer.parseInt(partes[0].trim());
                if (codigoAplicativoArquivo == codigoAplicativo) {
                    // Encontrou o aplicativo correspondente
                    return Double.parseDouble(partes[3].trim()); // Retorna o valor mensal
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    
        // Retorna -1 se o aplicativo não for encontrado
        return -1;
    }
    
    private Aplicativo obterAplicativo() {
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoAplicativos = new File(diretorioAtual + "/Aplicativos.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoAplicativos))) {
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
