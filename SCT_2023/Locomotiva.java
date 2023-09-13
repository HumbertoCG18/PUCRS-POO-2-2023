package SCT_2023;
public class Locomotiva extends CarroFerroviario {
    private int capacidadeCarga;
    private int numeroMaximoVagoes;

    public Locomotiva(int id, Trem trem, int capacidadeCarga, int numeroMaximoVagoes) {
        super(id, trem);
        this.capacidadeCarga = capacidadeCarga;
        this.numeroMaximoVagoes = numeroMaximoVagoes;
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public int getNumeroMaximoVagoes() {
        return numeroMaximoVagoes;
    }

    @Override
    public String toString() {
        return "Locomotiva: " + super.toString() + ", capacidade de carga: " + capacidadeCarga + ", max vagoes: "
                + getNumeroMaximoVagoes();
    }
}
