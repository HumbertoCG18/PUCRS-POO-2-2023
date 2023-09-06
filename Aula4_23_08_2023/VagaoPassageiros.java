public class VagaoPassageiros extends CarroFerroviario {
    private int nroAssentos;

    public VagaoPassageiros(int id, Trem trem, int nroAssentos) {
        super(id, trem, nroAssentos*120);
        this.nroAssentos = nroAssentos;
    }

    public int getNroAssentos() {
        return nroAssentos;
    }

    @Override
    public String toString(){
        return "Vagao passageiro: "+super.toString()+", nro de assentos: "+this.getNroAssentos();
    }

}
