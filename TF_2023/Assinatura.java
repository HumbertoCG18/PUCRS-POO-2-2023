import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

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

    private String nomeAplicativo;
    private String nomeCliente;

    public String getNomeAplicativo() {
        return nomeAplicativo;
    }

    public void setNomeAplicativo(String nomeAplicativo) {
        this.nomeAplicativo = nomeAplicativo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }


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
                    return Double.parseDouble(partes[3].trim());
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
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
                    return new Aplicativo(codigoAplicativoArquivo, partes[1].trim(), partes[2].trim(), Double.parseDouble(partes[3].trim()));
                }
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void salvarAssinatura(List<Assinatura> assinaturas, String caminhoArquivoAssinatura) {
        double cobrancaMensal = calcularCobrancaMensal();

        if (cobrancaMensal != -1) {
            String[] dadosCliente = buscarDadosClientePorEmail(cpfCliente);
            String nomeCliente = dadosCliente != null ? dadosCliente[0] : null;
            String cpfCliente = dadosCliente != null ? dadosCliente[1] : null;

            if (nomeCliente != null && cpfCliente != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAssinatura, true))) {
                    writer.write(String.format("%d;%d;%s;%s;%s;%.2f;%s;%s%n", codigo, codigoAplicativo, cpfCliente, nomeCliente, inicioVigencia, fimVigencia, cobrancaMensal));
                    JOptionPane.showMessageDialog(null, "Assinatura cadastrada com sucesso! Cobrança mensal: R$" + cobrancaMensal);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar a assinatura.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao encontrar o cliente associado ao CPF.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao calcular a cobrança mensal. Aplicativo associado não encontrado.");
        }
    }

    private String[] buscarDadosClientePorEmail(String email) {
        String diretorioAtual = System.getProperty("user.dir");
        File caminhoArquivoCliente = new File(diretorioAtual + "/Cliente.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoCliente))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                String emailArquivo = partes[0].trim();
                if (emailArquivo.equals(email)) {
                    return new String[]{partes[2].trim(), partes[3].trim()};
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}