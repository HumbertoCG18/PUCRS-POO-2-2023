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
}
