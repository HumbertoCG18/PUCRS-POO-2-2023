import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aplicativo {
    private int codigo;
    private String nome;
    private String sistemaOperacional;
    private double valorMensal;

    public Aplicativo(int codigo, String nome, String sistemaOperacional, double valorMensal) {
        this.codigo = codigo;
        this.nome = nome;
        this.sistemaOperacional = sistemaOperacional;
        this.valorMensal = valorMensal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public static void salvarAplicativosEmArquivo(List<Aplicativo> aplicativos, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Aplicativo aplicativo : aplicativos) {
                writer.write(String.format("%d;%s;%s;%.2f%n",
                        aplicativo.getCodigo(),
                        aplicativo.getNome(),
                        aplicativo.getSistemaOperacional(),
                        aplicativo.getValorMensal()));
            }
            System.out.println("Aplicativos salvos com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Exemplo de como criar e escrever aplicativos em um arquivo
        List<Aplicativo> aplicativos = new ArrayList<>();

        aplicativos.add(new Aplicativo(1, "App1", "Android", 9.99));
        aplicativos.add(new Aplicativo(2, "App2", "iOS", 12.99));
        aplicativos.add(new Aplicativo(3, "App3", "Windows", 8.99));

        salvarAplicativosEmArquivo(aplicativos, "./TF_2023/Aplicativos.txt");
    }
}
