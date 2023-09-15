package T2_SCT_2023;
public class VagaoCargaRefrigerado extends VagaoCarga {
    private int temperaturaMinima;
    private int cargaRefrigerada;

    public VagaoCargaRefrigerado(int id, Trem trem, int capacidadeCarga, int temperaturaMinima) {
        super(id, trem, capacidadeCarga);
        this.temperaturaMinima = temperaturaMinima;
        this.cargaRefrigerada = 0;
    }

    public int getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void adicionarCargaRefrigerada(int quantidade) {
        if (quantidade >= 0) {
            cargaRefrigerada += quantidade;
        }
    }

    public int getCargaRefrigerada() {
        return cargaRefrigerada;
    }

    @Override
    public String toString() {
        return super.toString() + ", temperatura minima: " + getTemperaturaMinima() + ", carga refrigerada: "
                + getCargaRefrigerada();
    }
}