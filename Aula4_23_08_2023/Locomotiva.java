public class Locomotiva extends CarroFerroviario {
    private int capacidadeCarga;
    private int numeroMaximoVagoes;

    public Locomotiva(int id, Trem trem, int capacidadeCarga, int numeroMaximoVagoes) {
        super(id, trem);
        this.capacidadeCarga = capacidadeCarga;
        this.numeroMaximoVagoes = numeroMaximoVagoes;
    }

    public int getCapacidadeCarga(){
        return this.capacidadeCarga;
    }

    public int getNumeroMaximoVagoes() {
        return this.numeroMaximoVagoes;
    }

    @Override
    public String toString(){
        return "Locomotiva: "+super.toString()+", capacidade de carga: "+this.capacidadeCarga+", max vagoes: "+this.getNumeroMaximoVagoes();
    }
}
