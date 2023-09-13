public class VagaoPassageiros extends Vagao {
    private int nroAssentos;
    private int numeroPassageiros;

    public VagaoPassageiros(int id, Trem trem, int nroAssentos) {
        super(id, trem);
        this.nroAssentos = nroAssentos;
        this.numeroPassageiros = 0;
    }

    public int getNroAssentos() {
        return nroAssentos;
    }

    public void adicionarPassageiro(int quantidade) {
        if (quantidade >= 0 && numeroPassageiros + quantidade <= nroAssentos) {
            numeroPassageiros += quantidade;
        }
    }

    public int getNumeroPassageiros() {
        return numeroPassageiros;
    }

    @Override
    public String toString() {
        return super.toString() + ", nro de assentos: " + getNroAssentos() + ", nro de passageiros: "
                + getNumeroPassageiros();
    }
}