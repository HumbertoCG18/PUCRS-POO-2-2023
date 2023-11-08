// Classe Aplicativo
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
}
