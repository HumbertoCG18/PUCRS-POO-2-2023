public class Locomotiva extends CarroFerroviario {
    private int numeroMaximoVagoes;

    public Locomotiva(int id, Trem trem, int capacidadeCarga, int numeroMaximoVagoes) {
        super(id, trem, capacidadeCarga);
        this.numeroMaximoVagoes = numeroMaximoVagoes;
    }

    public int getNumeroMaximoVagoes() {
        return numeroMaximoVagoes;
    }

    @Override
    public String toString(){
        return "Locomotiva: "+super.toString()+", max vagoes: "+this.getNumeroMaximoVagoes();
    }
}
