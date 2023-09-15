package T2_SCT_2023;
public class VagaoCarga extends Vagao {
    private int capacidadeCarga;

    public VagaoCarga(int id, Trem trem, int capacidadeCarga) {
        super(id, trem);
        this.capacidadeCarga = capacidadeCarga;
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    @Override
    public String toString() {
        return super.toString() + ", capacidadeCarga=" + capacidadeCarga;
    }
}
