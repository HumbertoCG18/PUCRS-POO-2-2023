import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aplicativo {
    private int codigo;
    private String nomeAplicativo;
    private String sistemaOperacional;
    private double valorAssinaturaBasica;
    private double valorAssinaturaVip;
    private double valorAssinaturaPremium;
    private String inicioVigencia;
    private String fimVigencia;
    private int idAssinatura;

    public Aplicativo(int codigo, String nomeAplicativo, String sistemaOperacional) {
        this.codigo = codigo;
        this.nomeAplicativo = nomeAplicativo;
        this.sistemaOperacional = sistemaOperacional;
    }

    public void definirPrecosAssinatura(double valorAssinaturaBasica, double valorAssinaturaVip, double valorAssinaturaPremium) {
        this.valorAssinaturaBasica = valorAssinaturaBasica;
        this.valorAssinaturaVip = valorAssinaturaVip;
        this.valorAssinaturaPremium = valorAssinaturaPremium;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nomeAplicativo;
    }

    public void setNome(String nomeAplicativo) {
        this.nomeAplicativo = nomeAplicativo;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public double getValorAssinaturaBasica() {
        return valorAssinaturaBasica;
    }

    public double getValorAssinaturaVip() {
        return valorAssinaturaVip;
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

    public double getValorAssinaturaPremium() {
        return valorAssinaturaPremium;
    }

    public int getIdAssinatura() {
        return idAssinatura;
    }

    public void setIdAssinatura(int idAssinatura) {
        this.idAssinatura = idAssinatura;
    }

    public void setValorMensal(double valorMensal) {
    }

    public double calcularValorMensal(int idAssinatura) {
        double valorMensal = 0.0;

        switch (idAssinatura) {
            case 1:
                valorMensal = valorAssinaturaBasica;
                break;
            case 2:
                valorMensal = valorAssinaturaVip;
                break;
            case 3:
                valorMensal = valorAssinaturaPremium;
                break;
            default:
                // Tratamento para assinatura inválida, se necessário
                break;
        }

        return valorMensal;
    }

    public static void salvarAplicativosEmArquivo(List<Aplicativo> aplicativos, String caminhoArquivoAplicativo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAplicativo))) {
            for (Aplicativo aplicativo : aplicativos) {
                double valorMensal = aplicativo.calcularValorMensal(aplicativo.getIdAssinatura());
                writer.write(String.format("%d;%s;%s;%.2f;%.2f;%.2f;%d%n",
                        aplicativo.getCodigo(),
                        aplicativo.getNome(),
                        aplicativo.getSistemaOperacional(),
                        aplicativo.getValorAssinaturaBasica(),
                        aplicativo.getValorAssinaturaVip(),
                        aplicativo.getValorAssinaturaPremium(),
                        aplicativo.getIdAssinatura()));

                aplicativo.setValorMensal(valorMensal);
            }
            System.out.println("Aplicativos salvos com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Aplicativo> aplicativos = new ArrayList<>();

        Aplicativo app1 = new Aplicativo(1, "App1", "Android");
        app1.definirPrecosAssinatura(9.90, 18.90, 22.99);
        app1.setIdAssinatura(1); // Assinatura básica

        Aplicativo app2 = new Aplicativo(2, "App2", "iOS");
        app2.definirPrecosAssinatura(9.90, 18.90, 22.99);
        app2.setIdAssinatura(2); // Assinatura VIP

        Aplicativo app3 = new Aplicativo(3, "App3", "Windows");
        app3.definirPrecosAssinatura(9.90, 18.90, 22.99);
        app3.setIdAssinatura(3); // Assinatura Premium

        aplicativos.add(app1);
        aplicativos.add(app2);
        aplicativos.add(app3);

        salvarAplicativosEmArquivo(aplicativos, "Aplicativos.txt");
    }
}
